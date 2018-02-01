package source

import io.reactivex.Flowable
import model.CommentRoot
import model.Detail
import model.One
import model.Result
import source.remote.TasksRemoteDataSource

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TasksRepository(private val tasksRemoteDataSource: TasksRemoteDataSource) : TasksDataSource {

    companion object {
        private var INSTANCE: TasksRepository? = null

        fun getInstance(tasksRemoteDataSource: TasksRemoteDataSource): TasksRepository
                = INSTANCE ?: TasksRepository(tasksRemoteDataSource).apply { INSTANCE = this }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getHome(date: String, city: String): Flowable<Result<One>> = tasksRemoteDataSource.getHome(date, city)

    override fun getDetail(category: String, id: String, source_id: String): Flowable<Result<Detail>>
            = tasksRemoteDataSource.getDetail(category, id, source_id)

    override fun getDetailComment(category: String, id: String, lastCommentId: String): Flowable<Result<CommentRoot>>
            = tasksRemoteDataSource.getDetailComment(category, id, lastCommentId)
}