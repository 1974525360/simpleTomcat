package ex03.pyrmont.connector.pool;

import ex03.pyrmont.connector.factory.SocketThreadPoolFactory;

import javax.security.auth.callback.CallbackHandler;
import java.util.concurrent.*;

/**
 * @Description : TODO
 * @author: zeng.maosen
 * @date: 2023/1/13
 * @version: 1.0
 */
public class SocketThreadPool {

    BlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>();
    SocketThreadPoolFactory factory = new SocketThreadPoolFactory();

    ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 30, TimeUnit.SECONDS, blockingDeque, factory, new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("线程池");
        }
    });
}
