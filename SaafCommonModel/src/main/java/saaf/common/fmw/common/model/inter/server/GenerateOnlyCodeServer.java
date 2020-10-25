package saaf.common.fmw.common.model.inter.server;


import com.yhg.redis.framework.JedisClusterCore;

import org.springframework.stereotype.Component;

/**
 * redis生成唯一ID
 */
@Component("generateOnlyCodeServer")
public class GenerateOnlyCodeServer extends JedisClusterCore {
    public GenerateOnlyCodeServer() {
        super();
    }

    public String getSequenceId(String type) {
        Long seq = jedisCluster.incr(type);
        return seq.toString();
    }

    public static void main(String[] args) {
        GenerateOnlyCodeServer server = new GenerateOnlyCodeServer();
        server.getSequenceId("test");
    }
}
