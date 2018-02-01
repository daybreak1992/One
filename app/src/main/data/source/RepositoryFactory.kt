package source

import source.remote.TasksRemoteDataSource

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class RepositoryFactory {

    companion object {

        fun provideRepository(): TasksRepository
                = TasksRepository.getInstance(TasksRemoteDataSource.getInstace()!!)

    }
}