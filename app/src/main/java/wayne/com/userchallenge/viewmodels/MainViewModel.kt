package wayne.com.userchallenge.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import wayne.com.userchallenge.data.User
import wayne.com.userchallenge.data.UserApi
import wayne.com.userchallenge.data.Users
import wayne.com.userchallenge.views.UserAdapter

class MainViewModel(val retrofit: Retrofit) : ViewModel(){

    val userList: MutableList<User> = mutableListOf()
    val userApi: UserApi = retrofit.create(UserApi::class.java)
    val compositeDisposable = CompositeDisposable()
    lateinit var adapter : UserAdapter

    fun getUsers(page : Int = 1){
        if (page == 1) {
            userList.clear()
        }
        compositeDisposable.add(userApi.getUsers(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(apiResponse: Users){
        userList.addAll(apiResponse.data)
        if (apiResponse.page != apiResponse.total_pages){
            getUsers(apiResponse.page + 1)
        } else {
            adapter.setList(userList)
        }
    }

    private fun handleError(apiResponse: Throwable){
        Log.d("MainViewModel", apiResponse.localizedMessage)
    }

    fun postUser(name: String, job: String){
        //TODO: create post
    }
}