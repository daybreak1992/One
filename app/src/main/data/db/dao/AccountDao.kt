package db.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import model.Account

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg account: Account): List<Long>

    @Update
    fun update(vararg account: Account): Int

    @Query("SELECT * FROM account")
    fun queryAll(): Flowable<List<Account>>
}