package ex03.pyrmont.connector.pool;

import java.util.concurrent.*;

/**
 * @Description : TODO
 * @author: zeng.maosen
 * @date: 2023/1/13
 * @version: 1.0
 */
public class SocketThreadPool {

    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private TimeUnit unit;
    private BlockingQueue<Runnable> workQueue;
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler handler;
    private ThreadPoolExecutor threadPoolExecutor;

    private volatile static   SocketThreadPool socketThreadPool;

    private SocketThreadPool(ThreadFactory threadFactory) {
        corePoolSize = 10;
        maximumPoolSize = 20;
        keepAliveTime = 30;
        unit = TimeUnit.SECONDS;
        workQueue = new ArrayBlockingQueue<>(30);
        this.threadFactory = threadFactory;
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory);

    }

    private SocketThreadPool(){
        corePoolSize = 10;
        maximumPoolSize = 20;
        keepAliveTime = 30;
        unit = TimeUnit.SECONDS;
        workQueue = new ArrayBlockingQueue<>(30);
        threadFactory = Executors.defaultThreadFactory();
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory);
    }

    public Future<String> submit(Callable<String> call){
        return threadPoolExecutor.submit(call);
    }

    public void execute(Runnable r){
        threadPoolExecutor.execute(r);
    }

    public static  SocketThreadPool getInstance(){
        if(socketThreadPool==null){
            synchronized (SocketThreadPool.class){
                socketThreadPool = new SocketThreadPool();
            }
        }
        return socketThreadPool;
    }

}
