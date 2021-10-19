package com.luvia.to2youn.ui.main.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.luvia.to2youn.base.BaseMvvmFragment
import com.luvia.to2youn.databinding.FragmentSearchBinding
import com.luvia.to2youn.network.model.search.UserItem
import com.luvia.to2youn.ui.main.SharedViewModel
import com.luvia.to2youn.utils.BlueUtil
import com.luvia.to2youn.utils.ViewUtil
import java.text.FieldPosition

class SearchFragment : BaseMvvmFragment<FragmentSearchBinding>() {

    private val viewModel: SharedViewModel by activityViewModels()
    private val adapter: SearchRecyclerAdapter by lazy {
        SearchRecyclerAdapter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment().apply { arguments = Bundle().apply {} }
    }

    override fun getViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): ViewModel {
        return viewModel
    }

    override fun init() {
        viewModel.getSearchEmptyPlaceHolder().value = true
        initListener()
        initRecyclerView()
        initObserver()
    }

    private fun initListener(){
        binding.imageViewSearchRequest.setOnClickListener {
            val keyword = binding.editTextKeyword.text
            viewModel.requestSearchUsers(keyword.toString())
            ViewUtil.hideKeyboard(requireActivity())
            binding.editTextKeyword.clearFocus()
            viewModel.getSearchEmptyPlaceHolder().value = false
        }
    }

    private fun initRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = linearLayoutManager

        adapter.searchInterface = object : SearchRecyclerAdapter.SearchInterface {
            override fun bookmark(item: UserItem, position: Int) {
                viewModel.bookmark(item)
            }
        }

        binding.recyclerView.adapter = adapter
    }

    private fun initObserver(){

        viewModel.getSearchEmptyPlaceHolder().observe(this, Observer {
            if(it){
                binding.textViewEmptyHolder.visibility = View.VISIBLE
            }else{
                binding.textViewEmptyHolder.visibility = View.GONE
            }
        })

        viewModel.getSearchResultData().observe(this, Observer {
            adapter.setData(it)
        })

        viewModel.getSearchRefresh().observe(this, Observer {
            adapter.notifyDataSetChanged()
        })

    }

}