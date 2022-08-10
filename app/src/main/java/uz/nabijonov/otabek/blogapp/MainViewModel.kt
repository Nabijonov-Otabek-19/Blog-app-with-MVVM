package uz.nabijonov.otabek.blogapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.nabijonov.otabek.blogapp.api.repository.BlogRepository
import uz.nabijonov.otabek.blogapp.model.PostModel
import uz.nabijonov.otabek.blogapp.model.UserModel

class MainViewModel : ViewModel() {

    private val repository = BlogRepository()

    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val usersData = MutableLiveData<List<UserModel>>()
    val postsData = MutableLiveData<List<PostModel>>()

    fun getUsers() {
        repository.getUsers(error, progress, usersData)
    }

    fun getPosts() {
        repository.getPosts(error, postsData)
    }
}