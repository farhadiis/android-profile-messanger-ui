package com.example.soroushprofile.userprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.soroushprofile.R

class UserMediaProfileAdapter(private val mSelectionProtocol: UserMediaProfileSelectionProtocol?) : RecyclerView.Adapter<UserMediaProfileAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_media_profile_item, parent, false)
        val holder = MyViewHolder(view)
        view.setOnClickListener(if (mSelectionProtocol != null) View.OnClickListener { mSelectionProtocol.onMediaClick(it) } else null)
        return holder

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    class MyViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

}
