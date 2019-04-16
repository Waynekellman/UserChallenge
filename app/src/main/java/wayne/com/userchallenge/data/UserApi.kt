package wayne.com.userchallenge.data

import io.reactivex.Observable
import retrofit2.http.*

interface UserApi {

    @GET("users")
    fun getUsers(@Query("page") page: Int = 1) : Observable<Users>

    @FormUrlEncoded
    @POST("users")
    fun postUser(@Field("name") name: String,
                 @Field("job") job: String) : Observable<CreatedUser>
}