package http

import io.reactivex.Flowable
import model.*
import retrofit2.http.*

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

    @GET("all/list/{id}")
    fun getCategory(@Path("id") id: String): Flowable<Category>

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("user_name") user_name: String, @Field("sex") sex: String,
              @Field("reg_type") reg_type: String, @Field("uid") uid: String): Flowable<Result<Account>>

    @GET("user/info/{id}")
    fun getUser(@Path("id") id: String, @Query("user_id") user_id: String, @Query("jwt") token: String): Flowable<Result<User>>

    @GET("search/{type}/{word}/{page}")
    fun getSearchWord(@Path("type") type: String, @Path("word") word: String, @Path("page") page: Int): Flowable<Result<Search>>
}