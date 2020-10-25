package saaf.common.fmw.base.services;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import saaf.common.fmw.services.CommonAbstractServices;

@Component("baseSaafRedisManageServices")
@Path("/saafRedisManageServlet")
public class BaseSaafRedisManageServices extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseSaafRedisManageServices.class);
	
	@Autowired
	private JedisCluster jedisCluster;

	public BaseSaafRedisManageServices() {
		super();
	}

	@POST
	@Path("list")
	public String list(@FormParam("params") String params) {
		JSONArray retArray = new JSONArray();
		
		Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
		for (String key : clusterNodes.keySet()) {
			JedisPool jp = clusterNodes.get(key); 
            Jedis redis = jp.getResource();  
            try {
            	Set<String> keys = redis.keys("*");  
                Iterator<String> it = keys.iterator();
                while(it.hasNext()){
                	String k = it.next();
                	String value = redis.get(k);
                	JSONObject obj = new JSONObject();
                	obj.put("redis_key", k);
                	obj.put("redis_value", value);
                	obj.put("redis_server", key);
                	
                	retArray.add(obj);
                }
			} catch (Exception e) {
				//e.printStackTrace();
			}finally{
				redis.close();
			}
		}
		
		return null;
	}
}
