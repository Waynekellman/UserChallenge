package wayne.com.userchallenge.views

import wayne.com.userchallenge.data.User

interface IMainActivity {

    fun setList(users: List<User>)
    fun makeToast(text: String)
    fun removeSpinner()
}