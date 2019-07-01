package com.teambloodformypeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambloodformypeople.R
import com.teambloodformypeople.data.models.User
import com.teambloodformypeople.databinding.UserListItemBinding
import com.teambloodformypeople.listeners.UserListener
import com.teambloodformypeople.viewmodels.UserViewModel


class UserListAdapter(val context: Context): RecyclerView.Adapter<UserListAdapter.UserViewHolder>(), UserListener {

    private var users: List<User> = emptyList()
    private var viewModel: UserViewModel? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: UserListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_list_item, parent, false
        )
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.userListItemBinding.data = user
        holder.userListItemBinding.executePendingBindings()
        holder.userListItemBinding.userListener = this

    }

    class UserViewHolder(itemView: UserListItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val userListItemBinding: UserListItemBinding = itemView
    }

    internal fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }
    internal fun setViewModel(viewModel: UserViewModel){
        this.viewModel = viewModel
    }
    override fun onDeleteButtonClicked(view: View, user: User) {
        viewModel?.deleteUser(user.userId!!)
        Toast.makeText(view.context,"Deleted User",Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }
}
