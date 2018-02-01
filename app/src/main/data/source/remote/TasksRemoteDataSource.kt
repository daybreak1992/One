package source.remote

import http.RetrofitManager
import io.reactivex.Flowable
import model.*
import source.TasksDataSource

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TasksRemoteDataSource private constructor() : TasksDataSource {

    companion object {
        private var INSTANCE: TasksRemoteDataSource? = null

        fun getInstace(): TasksRemoteDataSource? {
            if (INSTANCE == null) {
                synchronized(TasksRemoteDataSource::javaClass) {
                    INSTANCE = TasksRemoteDataSource()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getHome(date: String, city: String): Flowable<Result<One>> = RetrofitManager.api.getHome(date, city)

    override fun getDetail(category: String, id: String, source_id: String): Flowable<Result<Detail>>
            = RetrofitManager.api.getDetail(category, id, source_id)

    override fun getDetailComment(category: String, id: String, lastCommentId: String): Flowable<Result<CommentRoot>>
            = RetrofitManager.api.getDetailComments(category, id, lastCommentId)

    override fun getBanners(type: String, last_id: String): Flowable<Result<List<Banner>>>
            = RetrofitManager.api.getBanners(type, last_id)

    override fun getHotAuthors(): Flowable<Result<List<User>>>
            = RetrofitManager.api.getHotAuthors()
}