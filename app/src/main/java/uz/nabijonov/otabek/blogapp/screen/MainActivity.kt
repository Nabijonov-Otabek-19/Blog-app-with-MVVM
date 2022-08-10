package uz.nabijonov.otabek.blogapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import uz.nabijonov.otabek.blogapp.MainViewModel
import uz.nabijonov.otabek.blogapp.R
import uz.nabijonov.otabek.blogapp.adapter.PostAdapter
import uz.nabijonov.otabek.blogapp.adapter.UserAdapter

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe.setOnRefreshListener {
            loadData()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.progress.observe(this, Observer {
            swipe.isRefreshing = it
        })

        viewModel.usersData.observe(this, Observer {
            recyclerUsers.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerUsers.adapter = UserAdapter(it)
        })

        viewModel.postsData.observe(this, Observer {
            recyclerPosts.layoutManager = LinearLayoutManager(this)
            recyclerPosts.adapter = PostAdapter(it)
        })

        loadData()
    }

    fun loadData() {
        viewModel.getUsers()
        viewModel.getPosts()
    }
}