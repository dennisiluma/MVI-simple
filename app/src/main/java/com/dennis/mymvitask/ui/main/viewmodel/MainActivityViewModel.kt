package com.dennis.mymvitask.ui.main.viewmodel


import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennis.mymvitask.data.model.Post
import com.dennis.mymvitask.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel(private val repository: Repository): ViewModel() {


    private val _myMultiQueryPost: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    val myMultiQueryPost: LiveData<Response<List<Post>>> get() = _myMultiQueryPost

    fun getMultiQueryPost(userId: Int?, options:Map<String, String>){


        viewModelScope.launch {
            try {
                val multiQueryPost = repository.getMultiplrQueryPosts(userId,options)
                _myMultiQueryPost.value = multiQueryPost

            }catch (e:Exception){
                Log.i("Error", "Network may be bad: ${e.message}")
            }

        }
    }
    //handles filter
    fun filterText(text: String): List<Post>? {
        if (text != null && text != "") { val filteredListText = myMultiQueryPost.value?.body()?.filter { it -> it.title.contains(text) }
            if (text.isDigitsOnly()) { val filteredListNumber = myMultiQueryPost.value?.body()?.filter { inputNumber -> inputNumber.id == text.toInt() }
                return filteredListNumber
            } else { Log.i("err", "Search field") }
            return filteredListText
        } else { return myMultiQueryPost.value?.body() }
    }


             /* Unusable codes starts here */
//    fun getPost(){
//        viewModelScope.launch {
//            val response = repository.getPost()
//            myResponse.value = response
//
//        }
//    }
//    fun getPost2(postId:Int){
//        viewModelScope.launch {
//            val response2 = repository.getPost2(postId)
//            myResponse2.value = response2
//        }
//    }
//    fun getAllPostOfUserId(userId:Int){
//        viewModelScope.launch {
//            val responseUserId = repository.getAlPostByUserId(userId)
//            myResponseByUserId.setValue(responseUserId)
//        }
//    }
                /* Unusable codes ends here */



}