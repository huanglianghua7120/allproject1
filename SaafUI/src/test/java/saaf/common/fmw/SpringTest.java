package saaf.common.fmw;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class SpringTest {
	
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");

		JedisCluster jedisCluster = (JedisCluster) context
				.getBean("jedisCluster");
		
		Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
		for (String key : clusterNodes.keySet()) {
			System.out.println("Getting keys from:"+key);
			JedisPool jp = clusterNodes.get(key); 
            Jedis redis = jp.getResource();  
            try {
            	Set<String> keys = redis.keys("*_QuickQuery");  
                Iterator<String> it = keys.iterator();
                while(it.hasNext()){
                	String k = it.next();
                	String value = redis.get(k);
                	System.out.println(k+"\t----->\t"+value);
                }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				redis.close();
			}
		}
		
		//jedisCluster.del("431_QuickQuery");
	}

}
