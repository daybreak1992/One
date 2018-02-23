package source.local

import db.helper.DbHelper
import io.reactivex.Flowable
import model.Account
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TasksLocalDataSource private constructor() {

    companion object {
        private var INSTANCE: TasksLocalDataSource? = null

        fun getInstance(): TasksLocalDataSource? {
            if (INSTANCE == null) {
                synchronized(TasksLocalDataSource::javaClass) {
                    INSTANCE = TasksLocalDataSource()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    fun saveAccount(vararg account: Account): List<Long> =
            DbHelper.getAccountDao().insert(*account)

    fun saveUser(vararg user: User): List<Long> =
            DbHelper.getUserDao().insert(*user)

    fun getUser(): Flowable<List<User>> =
            DbHelper.getUserDao().queryAll()
}