package com.example.soroushprofile

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.example.soroushprofile.avatar.AvatarPaletteDelegate
import com.example.soroushprofile.avatar.ProfileAvatar
import com.example.soroushprofile.models.ChannelConversation
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import com.example.soroushprofile.models.GroupConversation
import com.example.soroushprofile.userprofile.ProfileViewModel
import com.example.soroushprofile.util.ColorUtil
import com.example.soroushprofile.util.fabutil.FabClickListener
import com.example.soroushprofile.util.fabutil.FabInjectProcessor
import com.example.soroushprofile.util.fabutil.FabOption
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    companion object {
        const val CONVERSATION_TYPE = "conversation_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initializeToolbar()
        bindViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.more_settings -> Toast.makeText(this,
                    "More Option Trigger...", Toast.LENGTH_SHORT).show()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    private fun bindViewModel() {
        val model = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val type = intent.getStringExtra(CONVERSATION_TYPE)
        model.getConversationThread(type).observe(this,
                Observer<ConversationThread> { t -> bindConversationThread(t) })
    }

    private fun initializeProfileAvatar(thread: ConversationThread) {
        ProfileAvatar.of(this, thread, avatar_image_view, header_image_view, object : AvatarPaletteDelegate {
            override fun onPalette(palette: Palette?) {
                initializeColorPalette(palette)
            }
        })
    }

    private fun initializeColorPalette(palette: Palette?) {
        val colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary)
        val colorPrimaryDark = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        if (palette != null) {
            val vibrantColor = palette.getMutedColor(colorPrimary)
            toolbar_layout.setContentScrimColor(vibrantColor)
            val darkVibrantColor = ColorUtil.manipulateColor(vibrantColor, 0.8f)
            toolbar_layout.setStatusBarScrimColor(darkVibrantColor)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = Color.TRANSPARENT
            }
        } else {
            toolbar_layout.setContentScrimColor(colorPrimary)
            toolbar_layout.setStatusBarScrimColor(colorPrimaryDark)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = colorPrimaryDark
            }
        }
    }

    private fun bindConversationThread(thread: ConversationThread) {
        initializeProfileAvatar(thread)

        username.text = thread.title
        status.text = when (thread.type) {
            ConversationType.Individual -> getString(R.string.profile_status_online_user)
            ConversationType.Group -> {
                val conversation = thread as GroupConversation
                resources.getQuantityString(R.plurals.profile_status_group, conversation.member
                        ?: 0, conversation.member)
            }
            ConversationType.Channel -> {
                val conversation = thread as ChannelConversation
                resources.getQuantityString(R.plurals.profile_status_channel, conversation.subscriber
                        ?: 0, conversation.subscriber)
            }
        }

        val fabInjector = FabInjectProcessor(thread, layoutInflater, root)
        fabInjector.onInject()
        fabInjector.mFabListener = object : FabClickListener {
            override fun onFabClick(option: FabOption) {
                Toast.makeText(this@ProfileActivity,
                        "Fav Option Trigger... ${option.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
