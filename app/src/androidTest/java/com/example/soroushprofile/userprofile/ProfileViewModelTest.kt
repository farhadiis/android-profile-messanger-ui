package com.example.soroushprofile.userprofile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.soroushprofile.models.*
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

import org.junit.Rule

class ProfileViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val conversationObserver: Observer<ConversationThread> = mock()
    private val viewModel = ProfileViewModel()


    @Before
    fun setUp() {
        viewModel.mConversationThread.observeForever(conversationObserver)
    }

    @Test
    fun getIndividualConversationThread() {
        val type = ConversationType.Individual
        viewModel.initialize(type)

        val targetInstance = viewModel.mConversationThread.value
        
        verify(conversationObserver).onChanged(targetInstance)
    }

}