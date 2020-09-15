package com.example.demoapplication.ui.activity.home.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.R
import com.example.demoapplication.networking.responsePojo.home.PostList
import com.example.demoapplication.utility.GlideApp

class MainAdapter(
    val context: Context?,
    val list: List<PostList>?
) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.main_list,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        if (!TextUtils.isEmpty(list?.get(position)?.authorInfo?.fullName))
            holder.itemText?.text = list?.get(position)?.authorInfo?.fullName

        if (!TextUtils.isEmpty(list?.get(position)?.postDescription))
            holder.itemTextt?.text = list?.get(position)?.postDescription

//        context?.let {
//            holder.side_image?.let { it1 ->
//                GlideApp.with(it)
//                    .load(list?.get(position)?.imageLists?.get(position)?.imageUrl)
//                    .placeholder(R.mipmap.ic_launcher)
//                    .error(R.mipmap.ic_launcher)
//                    .into(it1)
//            }
//        }


    }


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return list!!.size
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val side_image: AppCompatImageView? = view.findViewById(R.id.side_image)
    val itemText: TextView? = view.findViewById(R.id.text_1)
    val itemTextt: TextView? = view.findViewById(R.id.text_2)

}