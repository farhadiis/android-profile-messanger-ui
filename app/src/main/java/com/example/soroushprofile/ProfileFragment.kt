package com.example.soroushprofile

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.soroushprofile.models.ChannelConversation
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import com.example.soroushprofile.models.GroupConversation
import com.example.soroushprofile.userprofile.FabPageProfile
import com.example.soroushprofile.userprofile.ProfilePage
import com.example.soroushprofile.userprofile.ProfileViewModel
import com.example.soroushprofile.userprofile.avatar.ProfileAvatar
import com.example.soroushprofile.util.fabutil.FabClickListener
import com.example.soroushprofile.util.fabutil.FabOption
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        initializeToolbar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        initializeWindow(false)

    }

    override fun onDestroy() {
        super.onDestroy()
        initializeWindow(true)
    }


    private fun initializeWindow(k: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity?.window?.decorView?.systemUiVisibility =
                    if (k) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
                return true
            }
            R.id.more_settings -> Toast.makeText(context,
                    "More Option Trigger...", Toast.LENGTH_SHORT).show()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initializeToolbar() {
        val activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    private fun bindViewModel() {
        val model = ViewModelProvider(this).get(ProfileViewModel::class.java)
        arguments?.let {
            val type = ProfileFragmentArgs.fromBundle(it).conversationType
            model.getConversationThread(type).observe(viewLifecycleOwner,
                    Observer<ConversationThread> { t -> bindConversationThread(t) })
        }
    }

    private fun bindConversationThread(thread: ConversationThread) {

        ProfileAvatar.show(requireActivity(), thread, avatar_image_view, header_image_view, toolbar_layout)

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

        val fabInjector = FabPageProfile(thread, layoutInflater, root)
        fabInjector.onInject(object : FabClickListener {
            override fun onFabClick(option: FabOption) {
                Toast.makeText(context,
                        "Fab Option Trigger... ${option.name}", Toast.LENGTH_SHORT).show()
            }
        })

        val profilePage = ProfilePage(thread, layoutInflater, body)
        profilePage.onCreate()

    }

}
