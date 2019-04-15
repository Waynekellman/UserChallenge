package wayne.com.userchallenge.data

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    fun getUsers(@Query("page") page: Int = 1) : Observable<Users>
}