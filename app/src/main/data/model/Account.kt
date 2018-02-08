package model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Entity(tableName = "account")
data class Account(@PrimaryKey var id: String, var token: String) {

}