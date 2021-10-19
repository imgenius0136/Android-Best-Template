package com.luvia.to2youn.ui.main.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.luvia.to2youn.databinding.ItemSearchUserBinding
import com.luvia.to2youn.network.model.search.UserItem
import com.luvia.to2youn.utils.ImageUtil

class BookmarkRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface BookmarkInterface{
        fun bookmark(item: UserItem)
    }

    var bookmarkInterface: BookmarkInterface? = null

    private var data: ArrayList<UserItem>? = null

    private val USER_PROFILE_TYPE = 0

    fun setData(data: ArrayList<UserItem>){
        this.data = data
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        var holder: RecyclerView.ViewHolder? = null
        var dataBinding: ViewDataBinding?    = null

        when(viewType){
            USER_PROFILE_TYPE -> {
                dataBinding = ItemSearchUserBinding.inflate(inflater, parent, false)
                holder = UserProfileViewHolder(parent.context, dataBinding)
            }
        }

        return holder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is UserProfileViewHolder -> {
                data?.get(position)?.let { holder.onBind(it, position) }
            }
        }
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

    override fun getItemViewType(position: Int): Int {
        return USER_PROFILE_TYPE
    }

    inner class UserProfileViewHolder(private var context: Context, val binding: ItemSearchUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(item: UserItem, position: Int){
            binding.buttonBookmark.text = "BOOKMARK OFF"
            binding.textViewSortBy.visibility = if(item.sortWord != ""){
                View.VISIBLE
            }else{
                View.GONE
            }
            binding.textViewSortBy.text = item.sortWord
            binding.textViewName.text = item.login
            ImageUtil.setImageLoadCircle(context, binding.imageViewProfile, item.avatarURL)
            initListener(item, position)
        }

        private fun initListener(item: UserItem, position: Int){
            binding.buttonBookmark.setOnClickListener {
                item.isBookmarked = false
                bookmarkInterface?.bookmark(item)
            }
        }
    }

}