package com.example.soroushprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.soroushprofile.models.ConversationType
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRes()
    }

    override fun onClick(v: View) {
        val type: ConversationType = when (v.id) {
            R.id.individual_preview -> ConversationType.Individual
            R.id.group_preview -> ConversationType.Group
            R.id.channel_preview -> ConversationType.Channel
            else -> throw IllegalArgumentException()
        }
        val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment(type.name)
        findNavController().navigate(action)
    }

    private fun initializeRes() {
        individual_preview.setOnClickListener(this)
        group_preview.setOnClickListener(this)
        channel_preview.setOnClickListener(this)
    }
}
