package saaf.common.fmw.solr.core;

import org.springframework.data.solr.repository.SolrCrudRepository;

import java.io.Serializable;

/**
 * Created by Admin on 2017/9/7.
 */
public interface SolrServerRepository<T, ID extends Serializable> extends SolrCrudRepository<T, ID> {
}
