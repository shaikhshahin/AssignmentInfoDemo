package com.shahin.assignmentinfomvvm.ui.activity.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.shahin.assignmentinfomvvm.R
import com.shahin.assignmentinfomvvm.data.DataManager
import com.shahin.assignmentinfomvvm.data.network.model.UserData
import com.shahin.assignmentinfomvvm.ui.activity.details.DetailsActivity

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Shahin on 17/11/2019.
 */

class MainActivity : AppCompatActivity(), UserAdapter.OnMovieAdapter {

    internal lateinit var movieAdapter: UserAdapter

    @BindView(R.id.recycler_view)
    internal var recyclerView: RecyclerView? = null

    @BindView(R.id.progress_bar)
    internal var progressBar: ProgressBar? = null

    @BindView(R.id.empty_view)
    internal var emptyView: TextView? = null

    internal lateinit var viewModel: MainViewModel



    private fun createViewModel(): MainViewModel {
        val factory = MainViewModelFactory(DataManager.instance.userService)
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        



        movieAdapter = UserAdapter(this)

        recyclerView?.adapter = movieAdapter

        



        viewModel = createViewModel()

        viewModel.loadingStatus.observe(this, LoadingObserver())
        viewModel.userDatas.observe(this, MovieObserver())

        viewModel.loadUserDatasNetwork()

    }


    override fun onMovieClicked(userData: UserData) {

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("user", userData)
        startActivity(intent)

    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {

    }

    //Observer
    private inner class LoadingObserver : Observer<Boolean> {

        override fun onChanged(isLoading: Boolean?) {
            if (isLoading == null) return

            if (isLoading) {
                progressBar?.visibility = View.VISIBLE
            } else {
                progressBar?.visibility = View.GONE
            }
        }
    }

    private inner class MovieObserver : Observer<List<UserData>> {

        override fun onChanged(userData: List<UserData>?) {
            if (userData == null) return
            movieAdapter.setItems(userData)

            if (userData.isEmpty()) {
                emptyView?.visibility = View.VISIBLE
            } else {
                emptyView?.visibility = View.GONE
            }
        }
    }
}
