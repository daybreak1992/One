package http

import io.reactivex.Flowable
import model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface Api {

    @GET("channel/one/{date}/{city_name}")
    fun getHome(@Path("date") date: String, @Path("city_name") city_name: String): Flowable<Result<One>>

    @GET("{category}/htmlcontent/{id}")
    fun getDetail(@Path("category") category: String, @Path("id") id: String,
                  @Query("source_id") source_id: String): Flowable<Result<Detail>>

    @GET("comment/praiseandtime/{category}/{id}/{lastCommentId}")
    fun getDetailComments(@Path("category") category: String, @Path("id") id: String,
                          @Path("lastCommentId") lastCommentId: String): Flowable<Result<CommentRoot>>

    @GET("banner/list/{type}")
    fun getBanners(@Path("type") type: String, @Query("last_id") last_id: String): Flowable<Result<List<Banner>>>

    @GET("author/hot")
    fun getHotAuthors(): Flowable<Result<List<User>>>
}