package com.luvia.to2youn.ui.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.luvia.to2youn.R
import com.luvia.to2youn.databinding.ItemSearchSortHeaderBinding
import com.luvia.to2youn.databinding.ItemSearchUserBinding
import com.luvia.to2youn.network.model.search.UserItem
import com.luvia.to2youn.ui.main.SharedViewModel
import com.luvia.to2youn.utils.ImageUtil

class SearchRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface SearchInterface{
        fun bookmark(item: UserItem, position: Int)
    }

    var searchInterface: SearchInterface? = null

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

            if(item.isBookmarked){
                binding.buttonBookmark.text = "BOOKMARK OFF"
                binding.constraintProfileContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200))
            }else{
                binding.buttonBookmark.text = "BOOKMARK"
                binding.constraintProfileContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }

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
                item.isBookmarked = !item.isBookmarked
                notifyItemChanged(position)
                searchInterface?.bookmark(item, position)
            }
        }
    }

}