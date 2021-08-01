package app.ThreadManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public enum RunnableScheduler {
    INSTANCE;

    public ExecutorService executor = Executors.newCachedThreadPool();
    public ScheduledExecutorService execService = Executors.newSingleThreadScheduledExecutor();
}
