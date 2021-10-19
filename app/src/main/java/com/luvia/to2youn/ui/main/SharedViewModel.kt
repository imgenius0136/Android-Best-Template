package com.luvia.to2youn.ui.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luvia.to2youn.base.BaseViewModel
import com.luvia.to2youn.network.model.search.UserItem
import com.luvia.to2youn.ui.main.search.SearchRepositoryImpl
import com.luvia.to2youn.utils.BlueUtil
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : BaseViewModel(application) {

    private val searchRepository = SearchRepositoryImpl()

    private val searchResultData = MutableLiveData<ArrayList<UserItem>>()
    private val searchResultDataRefresh = MutableLiveData<Boolean>()

    private val bookmarkData = MutableLiveData<ArrayList<UserItem>>()
    private val bookmarkRefresh = MutableLiveData<Boolean>()

    private val searchEmptyPlaceHolder = MutableLiveData<Boolean>()
    private val bookmarkEmptyPlaceHolder = MutableLiveData<Boolean>()

    fun getSearchResultData(): MutableLiveData<ArrayList<UserItem>> {
        return searchResultData
    }

    fun getSearchRefresh(): MutableLiveData<Boolean>{
        return searchResultDataRefresh
    }

    fun getBookmarkItems(): MutableLiveData<ArrayList<UserItem>>{
        return bookmarkData
    }

    fun getBookmarkRefresh(): MutableLiveData<Boolean>{
        return bookmarkRefresh
    }

    fun getSearchEmptyPlaceHolder(): MutableLiveData<Boolean>{
        return searchEmptyPlaceHolder
    }

    fun getBookmarkEmptyPlaceHolder(): MutableLiveData<Boolean>{
        return bookmarkEmptyPlaceHolder
    }

    fun init(){
        searchResultData.value = ArrayList()
        bookmarkData.value = makeBookmarkItems(preferenceManager.getBookmarkData())
        bookmarkRefresh.value = false
        searchEmptyPlaceHolder.value = false
        bookmarkEmptyPlaceHolder.value = false
    }

    private fun makeBookmarkItems(bookmarkItems: HashSet<UserItem>): ArrayList<UserItem> {
        val returnList = ArrayList<UserItem>()
        for(item in bookmarkItems){
            returnList.add(item)
            BlueUtil.d(item.toString())
        }
        return sortResultDataAlphabetical(returnList)
    }

    fun requestSearchUsers(keyword: String){
        progress.value = true
        viewModelScope.launch {
            try{
                val result = searchRepository.requestSearchUsers(getApplication<Application>().applicationContext, keyword)
                if(result.items != null){
                    val userList = addBookmarkFlag(sortResultDataAlphabetical(result.items))
                    searchResultData.value = userList
                }else{
                    searchResultData.value = ArrayList()
                }
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                progress.value = false
            }
        }
    }

    fun bookmark(item: UserItem){
        if(item.isBookmarked){
            preferenceManager.setBookmarkData(item)
        }else{
            preferenceManager.removeBookmarkData(item)
            for(i in searchResultData.value!!){
                if(i.id == item.id){
                    i.isBookmarked = false
                    searchResultDataRefresh.value = true
                    break
                }
            }
        }

        bookmarkData.value = makeBookmarkItems(preferenceManager.getBookmarkData())
        bookmarkRefresh.value = true
    }

    // 대소문자 관계없이 정렬한다.
    private fun sortResultDataAlphabetical(list: ArrayList<UserItem>): ArrayList<UserItem> {
        list.sortWith(Comparator { p0, p1 -> p0?.login?.lowercase()!!.compareTo(p1?.login?.lowercase()!!) })
        return makeSortWord(list)
    }

    //북마크 플레그를 붙여준다.
    private fun addBookmarkFlag(list: ArrayList<UserItem>): ArrayList<UserItem> {
        for(searchItem in list){
            for(bookmarkedItem in bookmarkData.value!!){
                if(searchItem.id == bookmarkedItem.id){
                    searchItem.isBookmarked = true
                }
            }
        }
        return list
    }

    // 헤더 word 를 뽑아낸다.
    private fun makeSortWord(list: ArrayList<UserItem>): ArrayList<UserItem> {

        var sortWord = ""

        for((index, item) in list.withIndex()){
            if(index == 0){
                sortWord = item.login?.get(0).toString().lowercase()
                item.sortWord = sortWord
            }else{
                if(item.login?.get(0).toString().lowercase() != sortWord){
                    sortWord = item.login?.get(0).toString().lowercase()
                    item.sortWord = sortWord
                }else{
                    item.sortWord = ""
                }
            }
        }

        return list
    }

    private fun showToast(message: String){
        Toast.makeText(getApplication<Application>().applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}