package model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Entity(tableName = "user")
data class User constructor(@PrimaryKey var user_id: String = "", var user_name: String = "", var desc: String = "",
                            var wb_name: String = "", var settled_type: String = "", var summary: String = "",
                            var fans_total: String = "0", var web_url: String = "", var background: String = "",
                            var score: String = "0") {
}