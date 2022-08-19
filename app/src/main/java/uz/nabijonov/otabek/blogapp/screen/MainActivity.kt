package uz.nabijonov.otabek.blogapp.screen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.nabijonov.otabek.blogapp.MainViewModel
import uz.nabijonov.otabek.blogapp.adapter.PostAdapter
import uz.nabijonov.otabek.blogapp.adapter.UserAdapter
import uz.nabijonov.otabek.blogapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipe.setOnRefreshListener {
            loadData()
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.progress.observe(this) {
            binding.swipe.isRefreshing = it
        }

        viewModel.usersData.observe(this) {
            binding.recyclerUsers.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerUsers.adapter = UserAdapter(it)
        }

        viewModel.postsData.observe(this) {
            binding.recyclerPosts.layoutManager = LinearLayoutManager(this)
            binding.recyclerPosts.adapter = PostAdapter(it)
        }

        loadData()
    }

    private fun loadData() {
        viewModel.getUsers()
        viewModel.getPosts()
    }
}