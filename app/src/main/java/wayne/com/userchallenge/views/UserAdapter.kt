package wayne.com.userchallenge.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_item.view.*
import wayne.com.userchallenge.R
import wayne.com.userchallenge.data.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>(), IUserAdapter {

    val userList: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
       return UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val firstName = userList[position].first_name
        val lastName = userList[position].last_name
        val name = "$firstName $lastName"
        holder.view1.text = name
        val imgUrl = userList[position].avatar
        holder.updateWithUrl(imgUrl)
    }

    override fun setList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view1 = itemView.textView
        val profilePic = itemView.profile
        fun updateWithUrl(url: String) {
            Picasso.get().load(url).into(profilePic)
        }
    }

}
