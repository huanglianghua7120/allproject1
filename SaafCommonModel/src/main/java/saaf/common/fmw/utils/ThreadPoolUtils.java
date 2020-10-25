package saaf.common.fmw.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author: ZHJ
 * @date:Created in 11:53 2020/5/22
 * @modify By:
 * @description :连接池
 */
public class ThreadPoolUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolUtils.class);
    private static ExecutorService executorService;

    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        executorService = new ThreadPoolExecutor(10,
                200, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    private ThreadPoolUtils() {

    }

    /**
     * 获取一个线程池，该线程池为单例
     *
     * @return 返回线程池对象
     */
    public static ExecutorService getThreadPool() {
        return executorService;
    }
}
