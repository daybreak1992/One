package source

import io.reactivex.Flowable
import model.*
import source.local.TasksLocalDataSource
import source.remote.TasksRemoteDataSource

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TasksRepository(private val tasksRemoteDataSource: TasksRemoteDataSource,
                      private val tasksLocalDataSource: TasksLocalDataSource) : TasksDataSource {

    companion object {
        private var INSTANCE: TasksRepository? = null

        fun getInstance(tasksRemoteDataSource: TasksRemoteDataSource,
                        tasksLocalDataSource: TasksLocalDataSource): TasksRepository = INSTANCE
                ?: TasksRepository(tasksRemoteDataSource, tasksLocalDataSource).apply { INSTANCE = this }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getHome(date: String, city: String): Flowable<Result<One>> = tasksRemoteDataSource.getHome(date, city)

    override fun getDetail(category: String, id: String, source_id: String): Flowable<Result<Detail>> = tasksRemoteDataSource.getDetail(category, id, source_id)

    override fun getDetailComment(category: String, id: String, lastCommentId: String): Flowable<Result<CommentRoot>> = tasksRemoteDataSource.getDetailComment(category, id, lastCommentId)

    override fun getBanners(type: String, last_id: String): Flowable<Result<List<Banner>>> = tasksRemoteDataSource.getBanners(type, last_id)

    override fun getHotAuthors(): Flowable<Result<List<User>>> = tasksRemoteDataSource.getHotAuthors()

    override fun getCategory(id: String): Flowable<Category> = tasksRemoteDataSource.getCategory(id)

    override fun login(user_name: String, sex: String, reg_type: String, uid: String): Flowable<Result<Account>> = tasksRemoteDataSource.login(user_name, sex, reg_type, uid)

    override fun getUser(id: String, user_id: String, token: String): Flowable<Result<User>> = tasksRemoteDataSource.getUser(id, user_id, token)

    fun saveAccount(vararg account: Account) = tasksLocalDataSource.saveAccount(*account)

    fun saveUser(vararg user: User): List<Long> = tasksLocalDataSource.saveUser(*user)

    fun getUser(): Flowable<List<User>> = tasksLocalDataSource.getUser()
}