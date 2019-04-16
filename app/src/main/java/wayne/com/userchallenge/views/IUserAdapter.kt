package wayne.com.userchallenge.views

import wayne.com.userchallenge.data.User

interface IUserAdapter {
    fun setList(users: List<User>)
}