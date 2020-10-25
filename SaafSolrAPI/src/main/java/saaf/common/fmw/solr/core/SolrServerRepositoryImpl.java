package saaf.common.fmw.solr.core;

import org.apache.solr.common.SolrInputDocument;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTransactionSynchronizationAdapterBuilder;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.data.solr.repository.query.SolrEntityInformation;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Admin on 2017/9/7.
 */
@NoRepositoryBean
public class SolrServerRepositoryImpl<T, ID extends Serializable> extends SimpleSolrRepository<T, ID> implements SolrServerRepository<T,ID> {
    private static final String DEFAULT_ID_FIELD = "id";
    private SolrOperations solrOperations;
    private String idFieldName;
    private Class<T> entityClass;
    private SolrEntityInformation<T, ?> entityInformation;

    public SolrServerRepositoryImpl() {
        this.idFieldName = "id";
    }

    public SolrServerRepositoryImpl(SolrOperations solrOperations) {
        this.idFieldName = "id";
        Assert.notNull(solrOperations, "SolrOperations must not be null!");
        this.setSolrOperations(solrOperations);
    }

    public SolrServerRepositoryImpl(SolrEntityInformation<T, ?> metadata, SolrOperations solrOperations) {
        this(solrOperations);
        Assert.notNull(metadata, "Metadata must not be null!");
        this.entityInformation = metadata;
        this.setIdFieldName(this.entityInformation.getIdAttribute());
        this.setEntityClass(this.entityInformation.getJavaType());
    }

    public SolrServerRepositoryImpl(SolrOperations solrOperations, Class<T> entityClass) {
        this(solrOperations);
        this.setEntityClass(entityClass);
    }

    @Override
    public long count() {
        return this.count(new SimpleQuery((new Criteria("*")).expression("*")));
    }

    @Override
    protected long count(Query query) {
        Assert.notNull(entityInformation.getSolrCoreName(), "entity's solrCoreName undefine.");
        Query countQuery = SimpleQuery.fromQuery(query);
        return this.getSolrOperations().count(entityInformation.getSolrCoreName(),countQuery);
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "Cannot save 'null' entity.");
        Assert.notNull(entityInformation.getSolrCoreName(), "entity's solrCoreName undefine.");
        this.registerTransactionSynchronisationIfSynchronisationActive();
        getSolrOperations().saveBean(entityInformation.getSolrCoreName(),entity);
        this.commitIfTransactionSynchronisationIsInactive();
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        Assert.notNull(entities, "Cannot insert 'null' as a List.");
        Assert.notNull(entityInformation.getSolrCoreName(), "entity's solrCoreName undefine.");
        if(!(entities instanceof Collection)) {
            throw new InvalidDataAccessApiUsageException("Entities have to be inside a collection");
        } else {
            this.registerTransactionSynchronisationIfSynchronisationActive();
            getSolrOperations().saveBeans(entityInformation.getSolrCoreName(),(Collection)entities);
            this.commitIfTransactionSynchronisationIsInactive();
            return entities;
        }
    }


    @Override
    public void delete(ID id) {
        Assert.notNull(id, "Cannot delete entity with id 'null'.");
        Assert.notNull(entityInformation.getSolrCoreName(), "entity's solrCoreName undefine.");
        this.registerTransactionSynchronisationIfSynchronisationActive();
        getSolrOperations().deleteById(entityInformation.getSolrCoreName(),id.toString());
        this.commitIfTransactionSynchronisationIsInactive();
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "Cannot delete 'null' entity.");
        this.delete((Iterable) Arrays.asList(new Object[]{entity}));
    }

    @Override
    public void delete(Iterable<? extends T> entities) {

        Assert.notNull(entities, "Cannot delete 'null' list.");
        ArrayList<String> idsToDelete = new ArrayList();
        Iterator var3 = entities.iterator();

        while(var3.hasNext()) {
            T entity = (T) var3.next();
            idsToDelete.add(this.extractIdFromBean(entity).toString());
        }

        this.registerTransactionSynchronisationIfSynchronisationActive();
        getSolrOperations().deleteById(entityInformation.getSolrCoreName(),idsToDelete);
        this.commitIfTransactionSynchronisationIsInactive();
    }

    @Override
    public void deleteAll() {
        Assert.notNull(entityInformation.getSolrCoreName(), "entity's solrCoreName undefine.");
        this.registerTransactionSynchronisationIfSynchronisationActive();
        getSolrOperations().delete(entityInformation.getSolrCoreName(),new SimpleFilterQuery((new Criteria("*")).expression("*")));
        this.commitIfTransactionSynchronisationIsInactive();
    }






    /*public List<T> findAll() {
        int itemCount = (int)this.count();
        return (Iterable)(itemCount == 0?new PageImpl(Collections.emptyList()):this.findAll((new SolrPageRequest(0, itemCount))));
    }*/

    public SolrResultPage<T> findAll(Pageable pageable) {
        return (SolrResultPage<T>) this.getSolrOperations().queryForPage((new SimpleQuery((new Criteria("*")).expression("*"))).setPageRequest(pageable), this.getEntityClass());
    }

    public Iterable<T> findAll(Sort sort) {
        int itemCount = (int)this.count();
        return (Iterable)(itemCount == 0?new PageImpl(Collections.emptyList()):this.getSolrOperations().queryForPage((new SimpleQuery((new Criteria("*")).expression("*"))).setPageRequest(new SolrPageRequest(0, itemCount)).addSort(sort), this.getEntityClass()));
    }

    public Iterable<T> findAll(Iterable<ID> ids) {
        Query query = new SimpleQuery((new Criteria(this.idFieldName)).in(ids));
        query.setPageRequest(new SolrPageRequest(0, (int)this.count(query)));
        return this.getSolrOperations().queryForPage(query, this.getEntityClass());
    }




    private Object extractIdFromBean(T entity) {
        if(this.entityInformation != null) {
            return this.entityInformation.getId(entity);
        } else {
            SolrInputDocument solrInputDocument = getSolrOperations().convertBeanToSolrInputDocument(entity);
            return this.extractIdFromSolrInputDocument(solrInputDocument);
        }
    }

    private String extractIdFromSolrInputDocument(SolrInputDocument solrInputDocument) {
        Assert.notNull(solrInputDocument.getField(getIdFieldName()), "Unable to find field '" + getIdFieldName() + "' in SolrDocument.");
        Assert.notNull(solrInputDocument.getField(getIdFieldName()).getValue(), "ID must not be 'null'.");
        return solrInputDocument.getField(getIdFieldName()).getValue().toString();
    }

    private void registerTransactionSynchronisationIfSynchronisationActive() {
        if(TransactionSynchronizationManager.isSynchronizationActive()) {
            this.registerTransactionSynchronisationAdapter();
        }

    }

    private void registerTransactionSynchronisationAdapter() {
        TransactionSynchronizationManager.registerSynchronization(SolrTransactionSynchronizationAdapterBuilder.forOperations(getSolrOperations()).withDefaultBehaviour());
    }

    private void commitIfTransactionSynchronisationIsInactive() {
        if(!TransactionSynchronizationManager.isSynchronizationActive()) {
            getSolrOperations().commit(entityInformation.getSolrCoreName());
        }

    }




}
