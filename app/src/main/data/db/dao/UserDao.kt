package db.dao

import android.arch.persistence.room.*
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User): List<Long>

    @Update
    fun update(vararg user: User): Int

    @Query("SELECT * FROM user")
    fun queryAll(): Flowable<List<User>>
}