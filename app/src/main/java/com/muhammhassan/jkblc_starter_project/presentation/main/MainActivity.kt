package com.muhammhassan.jkblc_starter_project.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.muhammhassan.jkblc_starter_project.R
import com.muhammhassan.jkblc_starter_project.databinding.ActivityMainBinding
import com.muhammhassan.jkblc_starter_project.utils.RequestState

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        NewsAdapter(mutableListOf()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(intent)
        }
    }

    // TODO 9 : Inisiasikan object view model
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getNews(query)
                binding.searchBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Do nothing
                return false
            }

        })

        //TODO 10 : Observe live data
        viewModel.newsState.observe(this) {
            when (it) {
                is RequestState.Error -> {
                    showSnackbar(it.message)
                    binding.progressBar.visibility = View.GONE
                }

                RequestState.Loading -> {
                    binding.rvList.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is RequestState.Success -> {
                    binding.rvList.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    adapter.updateData(it.data)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvList.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = this@MainActivity.adapter
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}