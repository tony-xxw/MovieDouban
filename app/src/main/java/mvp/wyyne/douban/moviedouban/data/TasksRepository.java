package mvp.wyyne.douban.moviedouban.data;


import androidx.annotation.NonNull;

import mvp.wyyne.douban.moviedouban.data.TasksDataSource.TaskRemoteData;
import mvp.wyyne.douban.moviedouban.data.TasksDataSource.TaskLocalData;

/**
 * @author Wynne
 * @date 2018/3/26
 */

public class TasksRepository {

    private static TasksRepository INSTANCE = null;
    private TaskRemoteData mTaskRemoteData;
    private TaskLocalData mTaskLocalData;

    private TasksRepository(@NonNull TaskRemoteData taskRemoteData, @NonNull TaskLocalData taskLocalData) {
        mTaskRemoteData = taskRemoteData;
        mTaskLocalData = taskLocalData;

    }

    public TasksRepository getInstance(TaskRemoteData taskRemoteData, TaskLocalData taskLocalData) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(taskRemoteData, taskLocalData);
        }
        return INSTANCE;
    }

}
