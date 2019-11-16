package com.shahin.assignmentinfomvvm.ui.activity.details


import android.os.Bundle

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.bumptech.glide.Glide
import com.shahin.assignmentinfomvvm.R
import com.shahin.assignmentinfomvvm.data.network.model.UserData

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Shahin on 10/11/2019.
 */
class DetailsActivity : AppCompatActivity() {

    @BindView(R.id.image)
    internal var image: AppCompatImageView? = null
    @BindView(R.id.title)
    internal var title: TextView? = null
    @BindView(R.id.desc)
    internal var desc: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        ButterKnife.bind(this)

        val viewModel = createViewModel()

        viewModel.movie.observe(this, MovieObserver())

        viewModel.loadMovieData(intent)
    }

    private fun createViewModel(): DetailsViewModel {
        return ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }

    private inner class MovieObserver : Observer<UserData> {
        override fun onChanged(userData: UserData?) {
            if (userData == null) return

            title!!.text = userData.title
            desc!!.text = userData.description
            Glide.with(applicationContext).load(userData.image).into(image!!)
        }
    }

}

