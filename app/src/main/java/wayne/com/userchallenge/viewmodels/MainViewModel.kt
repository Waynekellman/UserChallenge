package wayne.com.userchallenge.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import wayne.com.userchallenge.data.*
import wayne.com.userchallenge.views.IMainActivity

class MainViewModel(private val repository: UserRepository) : ViewModel(){

    private val userList: MutableList<User> = mutableListOf()
    lateinit var mainActivity: IMainActivity

    fun getUsers(page : Int = 1){

        if (page == 1) {
            userList.clear()
        }
        repository.getUsers(handleResponse = this::handleResponse,handleError = this::handleError)

    }

    private fun handleResponse(apiResponse: Users){
        userList.addAll(apiResponse.data)
        if (apiResponse.page != apiResponse.total_pages){
            repository.getUsers(apiResponse.page + 1,this::handleResponse,this::handleError)
        } else {
            mainActivity.setList(userList)
        }
    }

    private fun handleError(apiResponse: Throwable){
        Log.d("MainViewModel", apiResponse.localizedMessage)
        if (apiResponse.localizedMessage == "timeout") repository.getUsers(handleResponse = this::handleResponse, handleError = this::handleError)
    }

    @SuppressLint("CheckResult")
    fun postUser(name: String, job: String){
        repository.postUser(name = name,job = job,handleResponse = this::handlePostResponse,handleError = this::handlePostError)
    }

    private fun handlePostResponse(apiPostResponse: CreatedUser) {
        mainActivity.makeToast("User " + apiPostResponse.name + " created")
    }
    private fun handlePostError(apiPostError: Throwable){
        mainActivity.makeToast("Couldn't create user. Error: " + apiPostError.localizedMessage)
    }

    fun dispose(){
        repository.dispose()
    }
}