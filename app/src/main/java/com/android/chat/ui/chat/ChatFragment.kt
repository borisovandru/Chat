package com.android.chat.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.chat.R
import com.android.chat.databinding.FragmentChatBinding
import com.android.chat.sl.core.Feature
import com.android.chat.ui.main.BaseFragment
import com.android.chat.ui.core.ClickListener

class ChatFragment : BaseFragment<ChatViewModel>() {
    override fun viewModelClass() = ChatViewModel::class.java
    override val titleResId = R.string.title_chat
    override fun name() = Feature.CHAT.name
    override fun showBottomNavigation() = false

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendMessageButton.setOnClickListener {
            viewModel.send(binding.messageEditText.text.toString())
            binding.messageEditText.text?.clear()
        }
        val adapter = ChatAdapter(Retry(), ReadMessage())
        binding.recyclerView.adapter = adapter
        viewModel.observe(this) {
            adapter.map(it)
            binding.recyclerView.scrollToPosition(it.size - 1)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startGettingUpdates()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopGettingUpdates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class Retry : ClickListener<MessageUi> {
        override fun click(item: MessageUi) {
            item.map(viewModel)
        }
    }

    private inner class ReadMessage : ClickListener<MessageUi> {
        override fun click(item: MessageUi) {
            item.read(viewModel)
        }
    }
}