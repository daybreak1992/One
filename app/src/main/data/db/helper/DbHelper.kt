package db.helper

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.tanghong.commonlibrary.utils.AppUtils
import db.dao.AccountDao
import db.dao.UserDao

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object DbHelper {
    private var db: AppDatabase? = null
    val db_name = "one_db"

    fun initDb(context: Context) {
        if (db == null) {
            synchronized(DbHelper::class) {
                if (db == null) {
                    db = Room.databaseBuilder(
                            AppUtils.context,
                            AppDatabase::class.java,
                            db_name
                    )
//                            .addMigrations(MigrateDb(1, 2))
                            .build()
                }
            }
        }
    }

    fun getUserDao(): UserDao = db!!.userDao()

    fun getAccountDao():AccountDao = db!!.accountDao()

    class MigrateDb(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {

        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }
}