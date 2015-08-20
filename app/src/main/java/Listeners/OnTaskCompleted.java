package Listeners;

import java.util.concurrent.ExecutionException;

/**
 * Created by Ahmed on 6/23/2015.
 */
public interface OnTaskCompleted {
    void onTaskCompleted() throws ExecutionException, InterruptedException;
}
