package saaf.common.fmw;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import redis.clients.jedis.JedisCluster;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;

public class testRedis {
	public static final ApplicationContext context = new FileSystemXmlApplicationContext(
			"classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");

	public static void main(String[] args) throws Exception {
		JedisCluster jedisCluster = (JedisCluster) context.getBean("jedisCluster");
		jedisCluster.set("ss", "111"); 
		Map<String,String> map = new HashMap<String,String>();
		map.put("2012", "22");
		map.put("2013", "23");
//		jedisCluster.hmset("testmap", map);
		
		System.out.println("!!!!!!!!!!"+jedisCluster.get("ss"));
		System.out.println("!!!!!!!!!!"+jedisCluster.hmget("testmap","2012")); 
		

		jedisCluster.expire("ss", 1);
		jedisCluster.expire("testmap", 5);
		try {
			Thread.sleep(1000*6);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println("!!!!!222!!!!!"+jedisCluster.get("ss"));
		System.out.println("!!!!!!222!!!!"+jedisCluster.hmget("testmap","2012"));
		
		
	}
}
