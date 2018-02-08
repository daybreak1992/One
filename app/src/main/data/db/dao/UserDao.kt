package db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Dao
interface UserDao {

    @Update
    fun update(user: User): Int

    @Query("SELECT * FROM user")
    fun queryAll(): Flowable<List<User>>
}