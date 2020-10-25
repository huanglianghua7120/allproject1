package saaf.common.fmw.utils;

import com.yhg.redis.TestJRedisCluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.JedisCluster;


public class CalculateBudget {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateBudget.class);
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");
    private static final JedisCluster bean = (JedisCluster)context.getBean("jedisCluster");
    private static final TestJRedisCluster jredisCluster = new TestJRedisCluster();
    static String host = "localhost";
    static String addValue = "local value = redis.call('get', KEYS[1])\n" +
        "value = value + ARGV[1]\n" +
        "redis.call('set', KEYS[1], value)\n" +
        "return value";

    static String minusValue = "local value = redis.call('get', KEYS[1])\n" +
        "value = value - ARGV[1]\n" +
        "if ( value < 0 )\n" +
        "then\n" +
        "return -1\n" +
        "else\n" +
        "redis.call('set', KEYS[1], value)\n" +
        "return value\n" +
        "end";
    
    /**
     * 扣减预算
     * @param ysWdCode 预算资金池
     * @param discountFee
     */
     public boolean minusValue(String ysWdCode,Integer discountFee){
         jredisCluster.setJedisCluster(bean);
         boolean b = false;
         String result = jredisCluster.exectScript(minusValue, ysWdCode, discountFee.toString());
         if(!result.equals("-1")){
             b = true;
         }
         return b;
     }
    
    /**
     * 回滚资金池
     * @param ysWdCode 预算资金池
     * @param discountFee
     */
    public String addValue(String ysWdCode,Integer discountFee){
        jredisCluster.setJedisCluster(bean);
        System.out.println("====>计算前,"+ysWdCode+": "+jredisCluster.get(ysWdCode));
        LOGGER.info("====>计算前,"+ysWdCode+": "+jredisCluster.get(ysWdCode));
        String result = jredisCluster.exectScript(addValue, ysWdCode, discountFee.toString());
        System.out.println("====>计算后,"+ysWdCode+": "+jredisCluster.get(ysWdCode));
        LOGGER.info("====>计算后,"+ysWdCode+": "+jredisCluster.get(ysWdCode));
        return result;
    }
    
    public CalculateBudget() {
        super();
    }
    
    public String getRemainderBudget(String ysWdCode){
        return jredisCluster.get(ysWdCode);
    }
    
    public static void main(String[] args) throws Exception{
        CalculateBudget myThread1 = new CalculateBudget();  
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:saaf/common/fmw/config/spring.hibernate.cfg.xml");
        JedisCluster bean = (JedisCluster) context.getBean("jedisCluster");
        final TestJRedisCluster jredisCluster = new TestJRedisCluster();
        jredisCluster.setJedisCluster(bean);
//        jredisCluster.exectScript(addValue, "YS00000070_HD00000043", "50");
        System.out.println("====>计算前："+jredisCluster.get("YS00000070_HD00000044"));
//        myThread1.addValue("YS00000070_HD00000043",500);
//        System.out.println("====>result:"+result);
//        System.out.println("====>计算后："+jredisCluster.get("YS00000070_HD00000043"));
    
    }
}
