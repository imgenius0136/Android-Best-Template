package com.luvia.to2youn.ui.main.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.luvia.to2youn.base.BaseMvvmFragment
import com.luvia.to2youn.databinding.FragmentBookmarkBinding
import com.luvia.to2youn.network.model.search.UserItem
import com.luvia.to2youn.ui.main.SharedViewModel
import com.luvia.to2youn.ui.main.search.SearchRecyclerAdapter
import com.luvia.to2youn.utils.BlueUtil

//북마크 관리 프레그먼트
class BookmarkFragment : BaseMvvmFragment<FragmentBookmarkBinding>() {

    private val viewModel: SharedViewModel by activityViewModels()
    private val adapter: BookmarkRecyclerAdapter by lazy {
        BookmarkRecyclerAdapter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = BookmarkFragment().apply { arguments = Bundle().apply {} }
    }

    override fun getViewBinding(): FragmentBookmarkBinding {
        return FragmentBookmarkBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): ViewModel {
        return viewModel
    }

    override fun init() {
        viewModel.getBookmarkEmptyPlaceHolder().value = true
        initRecyclerView()
        initObserve()
    }

    private fun initRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = linearLayoutManager

        adapter.bookmarkInterface = object : BookmarkRecyclerAdapter.BookmarkInterface {
            override fun bookmark(item: UserItem) {
                viewModel.bookmark(item)
            }
        }

        binding.recyclerView.adapter = adapter
    }

    private fun initObserve() {
        viewModel.getBookmarkEmptyPlaceHolder().observe(this, Observer {
            if(it){
                binding.textViewEmptyHolder.visibility = View.VISIBLE
            }else{
                binding.textViewEmptyHolder.visibility = View.GONE
            }
        })

        viewModel.getBookmarkRefresh().observe(this, Observer {
            if(it){
                adapter.notifyDataSetChanged()
                viewModel.getBookmarkRefresh().value = false
            }
        })

        viewModel.getBookmarkItems().observe(this, Observer {
            if(it.size > 0){
                viewModel.getBookmarkEmptyPlaceHolder().value = false
            }
            adapter.setData(it)
        })
    }
}