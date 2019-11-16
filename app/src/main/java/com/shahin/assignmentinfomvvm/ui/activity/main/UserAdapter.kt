package com.shahin.assignmentinfomvvm.ui.activity.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.shahin.assignmentinfomvvm.R
import com.shahin.assignmentinfomvvm.data.network.model.UserData


import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Shahin on 17/11/2019.
 */
class UserAdapter(private val mListener: OnMovieAdapter) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var mItems: List<UserData>? = null

    interface OnMovieAdapter {
        fun onMovieClicked(userData: UserData)
    }

    init {
        mItems = ArrayList()
    }

    fun setItems(items: List<UserData>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = getItem(position)

        holder.setOnClickListener(userData)
        holder.setTitle(userData.title)
        holder.setImage(userData.image)
        userData.description?.let { holder.setDescription(it) }
    }

    override fun getItemCount(): Int {
        return mItems!!.size
    }

    private fun getItem(position: Int): UserData {
        return mItems!![position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        @BindView(R.id.image)
        internal var image: AppCompatImageView? = null
        @BindView(R.id.title)
        internal var title: TextView? = null
        @BindView(R.id.desc)
        internal var desc: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
        }

        fun setTitle(title: String?) {
            this.title!!.text = title
        }

        fun setImage(imageUrl: String?) {
            Glide.with(itemView.context).load(imageUrl).into(image!!)
        }

        fun setDescription(description: String) {
            desc!!.text = description
        }

        fun setOnClickListener(userData: UserData) {
            itemView.tag = userData
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mListener.onMovieClicked(view.tag as UserData)
        }
    }
}
