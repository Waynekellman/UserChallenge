package wayne.com.userchallenge.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*
import wayne.com.userchallenge.R
import wayne.com.userchallenge.data.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    val userList: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
       return UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.view1.text = userList[position].first_name
        holder.view2.text = userList[position].last_name
    }

    fun setList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view1 = itemView.textView
        val view2 = itemView.textView2
    }

}
