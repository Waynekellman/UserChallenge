package wayne.com.userchallenge.data

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class UserRepository(retrofit: Retrofit) {
    private val userApi: UserApi = retrofit.create(UserApi::class.java)
    private val compositeDisposable = CompositeDisposable()

    fun getUsers(page: Int = 1, handleResponse: (Users) -> Unit, handleError: (Throwable) -> Unit) {

        compositeDisposable.add(userApi.getUsers(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(handleResponse, handleError)
        )

    }

    @SuppressLint("CheckResult")
    fun postUser(name: String, job: String, handleResponse: (CreatedUser) -> Unit, handleError: (Throwable) -> Unit){
        compositeDisposable.add(userApi.postUser(name,job)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(handleResponse, handleError)
        )
    }

    fun dispose(){
        compositeDisposable.dispose()
    }

}