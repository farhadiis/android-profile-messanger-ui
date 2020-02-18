package com.example.soroushprofile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity

import com.example.soroushprofile.models.ConversationType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeWindow()
        initializeRes()
    }

    private fun initializeRes() {
        individual_preview.setOnClickListener(this)
        group_preview.setOnClickListener(this)
        channel_preview.setOnClickListener(this)
    }

    private fun initializeWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun onClick(v: View) {
        val conversationType: ConversationType = when (v.id) {
            R.id.individual_preview -> ConversationType.Individual
            R.id.group_preview -> ConversationType.Group
            R.id.channel_preview -> ConversationType.Channel
            else -> throw IllegalArgumentException()
        }
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.CONVERSATION_TYPE, conversationType.name)
        startActivity(intent)
    }
}
