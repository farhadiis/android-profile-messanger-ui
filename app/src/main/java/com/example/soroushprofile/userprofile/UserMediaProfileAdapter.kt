package com.example.soroushprofile.userprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.soroushprofile.R
import kotlinx.android.synthetic.main.user_media_profile_item.view.*
import kotlin.properties.Delegates


class UserMediaProfileAdapter(private val selectionProtocol: UserMediaProfileSelectionProtocol?,
                              private val glide: RequestManager) : RecyclerView.Adapter<UserMediaProfileAdapter.MyViewHolder>() {


    var list: List<String> by Delegates.observable(ArrayList()) { _, _, _ -> notifyDataSetChanged() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_media_profile_item, parent, false)
        val holder = MyViewHolder(view)
        view.setOnClickListener(if (selectionProtocol != null)
            View.OnClickListener { selectionProtocol.onMediaClick(it) } else null)
        return holder

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        glide.load(list[position])
                .placeholder(R.drawable.placeholder)
                .into(holder.itemView.image)
    }

    override fun getItemCount(): Int = list.size

    class MyViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

}
