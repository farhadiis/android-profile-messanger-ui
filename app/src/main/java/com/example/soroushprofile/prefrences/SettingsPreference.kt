package com.example.soroushprofile.prefrences

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

import com.example.soroushprofile.ProfileActivity
import com.example.soroushprofile.R
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import com.example.soroushprofile.prefrences.widgets.SoroushPreference
import com.example.soroushprofile.prefrences.widgets.SoroushPreferenceCategory
import com.example.soroushprofile.prefrences.widgets.UserMediaPreference
import com.example.soroushprofile.userprofile.ProfileViewModel


class SettingsPreference : PreferenceFragmentCompat() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle, rootKey: String) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeRestrictTextColor(findPreference(RESTRICT_PREFERENCE))
    }

    private fun bindViewModel() {
        val model = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)
        val type = requireActivity().intent.getStringExtra(ProfileActivity.CONVERSATION_TYPE)
        model.getConversationThread(type).observe(viewLifecycleOwner, Observer<ConversationThread> { this.bindThread(it) })
    }

    private fun bindThread(thread: ConversationThread) {
        initializeUserMedia(thread)
        initializeDescription(thread)

        when (thread.type) {
            ConversationType.Individual -> initializeRestrictAction(R.string.profile_user_restrict_individual, thread.type)
            ConversationType.Group -> initializeRestrictAction(R.string.profile_user_restrict_group, thread.type)
            ConversationType.Channel -> initializeRestrictAction(R.string.profile_user_restrict_channel, thread.type)
        }
    }


    private fun initializeRestrictTextColor(preference: Preference?) {
        val soroushPreference = preference as SoroushPreference?
        soroushPreference!!.setTitleColor(Color.RED)
    }

    private fun initializeRestrictAction(@StringRes title: Int, type: ConversationType) {
        val blockPreference = findPreference<Preference>(RESTRICT_PREFERENCE)!!
        blockPreference.setTitle(title)
        blockPreference.onPreferenceClickListener = BlockClickListener(type)
    }

    private fun initializeUserMedia(thread: ConversationThread) {
        val category = findPreference<SoroushPreferenceCategory>(USER_MEDIA_PREFERENCE_CATEGORY)
        val preference = findPreference<UserMediaPreference>(USER_MEDIA_PREFERENCE)!!

        preference.refresh(thread)

        category!!.setOnSummaryClickListener(View.OnClickListener { v: View? -> Toast.makeText(v!!.context, "More...", Toast.LENGTH_SHORT).show() })
        category.summary = if (thread.media!!.isEmpty())
            null
        else
            getString(R.string.profile_user_media_category_more_title)
        category.title = if (thread.media!!.isEmpty())
            null
        else
            getString(R.string.profile_user_media_category_title)
    }

    private fun initializeDescription(thread: ConversationThread) {
        val preference = findPreference<Preference>(DESCRIPTION_PREFERENCE)!!

        if (thread.type.isIndividual) {
            preference.setSummary(R.string.profile_user_description_bio_title)
            preference.setTitle(thread.description ?: getString(R.string.profile_user_description_bio_empty))
        } else {
            preference.setSummary(R.string.profile_user_description_title)
            preference.setTitle(thread.description ?: getString(R.string.profile_user_description_empty))
        }

        preference.setSummary(if (thread.type.isIndividual)
            R.string.profile_user_description_bio_title
        else
            R.string.profile_user_description_title)
    }

    private inner class BlockClickListener public constructor(private val type: ConversationType) : Preference.OnPreferenceClickListener {

        override fun onPreferenceClick(preference: Preference): Boolean {
            when (type) {
                ConversationType.Individual -> Toast.makeText(context, "Block Action Trigger...", Toast.LENGTH_SHORT).show()
                ConversationType.Group -> Toast.makeText(context, "Leave Group Trigger...", Toast.LENGTH_SHORT).show()
                ConversationType.Channel -> Toast.makeText(context, "Leave Channel Trigger...", Toast.LENGTH_SHORT).show()
            }
            return true
        }

    }

    companion object {

        private val USER_MEDIA_PREFERENCE = "user_media_preference"
        private val USER_MEDIA_PREFERENCE_CATEGORY = "user_media_preference_category"
        private val RESTRICT_PREFERENCE = "restrict_preference"
        private val DESCRIPTION_PREFERENCE = "description_preference"
    }
}
