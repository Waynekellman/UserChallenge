package wayne.com.userchallenge.data

class Users(
    val page : Int,
    val per_page : Int,
    val total : Int,
    val total_pages : Int,
    val data : List<User>
)

class User(
    val id : Int,
    val first_name : String,
    val last_name : String,
    val avatar : String
)
