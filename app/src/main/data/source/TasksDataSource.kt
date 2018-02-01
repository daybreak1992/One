package source

import io.reactivex.Flowable
import model.*

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface TasksDataSource {

    fun getHome(date: String, city: String): Flowable<Result<One>>

    fun getDetail(category: String, id: String, source_id: String): Flowable<Result<Detail>>

    fun getDetailComment(category: String, id: String, lastCommentId: String): Flowable<Result<CommentRoot>>

    fun getBanners(type: String, last_id: String): Flowable<Result<List<Banner>>>

    fun getHotAuthors(): Flowable<Result<List<User>>>
}