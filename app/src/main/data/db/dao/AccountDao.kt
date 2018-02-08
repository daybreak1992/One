package db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
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

    @Update
    fun update(account: Account): Int

    @Query("SELECT * FROM account")
    fun queryAll(): Flowable<List<Account>>
}