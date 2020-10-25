
package saaf.common.fmw.utils;

import com.alibaba.fastjson.JSON;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import saaf.common.fmw.base.model.dao.SaafUsersDAO_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.solr.repository.SaafUserRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Admin on 2017/9/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml")
public class solrTest {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private SaafUsersDAO_HI saafUsersDAO_hi;

    @Autowired
    private SaafUserRepository repository;

    @Test
    public void saveBeans() throws IOException, SolrServerException {

        //新增索引
        List<SaafUsersEntity_HI> list=saafUsersDAO_hi.findByProperty(new HashMap<String, Object>());
        repository.save(list);

    }


    @Test
    public void test(){
        Iterable<SaafUsersEntity_HI> queryResult= repository.findAll();
        System.out.println(JSON.toJSON(queryResult));
        Iterator<SaafUsersEntity_HI> iterator=queryResult.iterator();
        while (iterator.hasNext()){
            System.out.println(JSON.toJSON(iterator.next()));
        }

        System.out.println(JSON.toJSON(repository.findAll(new PageRequest(1, 10, Sort.Direction.ASC,"userFullName"))));
        //SolrResultPage resultPage= (SolrResultPage) repository.findAll(new PageRequest(1, (int) repository.count()));

        //System.out.println(JSON.toJSON(resultPage));
        //System.out.println(JSON.toJSON(resultPage.getContent()));



        SaafUsersEntity_HI obj= repository.findOne("8");
        System.out.print("索引查询:");
        System.out.println(JSON.toJSON(obj));
        System.out.print("分页查询:");
        System.out.println(JSON.toJSON(repository.findAll(new PageRequest(2,10))));
        //删除
        repository.delete(obj);
        //新增/修改
        //repository.save(obj);

        //System.out.println(repository.count());
    }



}

