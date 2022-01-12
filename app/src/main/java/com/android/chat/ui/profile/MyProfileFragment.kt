package com.android.chat.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.chat.R
import com.android.chat.databinding.FragmentMyProfileBinding
import com.android.chat.sl.core.Feature
import com.android.chat.ui.core.BaseActivity
import com.android.chat.ui.main.BaseFragment

class MyProfileFragment : BaseFragment<MyProfileViewModel>() {
    override fun viewModelClass() = MyProfileViewModel::class.java

    override val titleResId = R.string.title_profile

    override fun name() = Feature.MY_PROFILE.name

    private var _binding: FragmentMyProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(this) {
            it.map(binding.nameTextview, binding.loginTextView, binding.avatarImageView)
        }

        binding.signOutButton.setOnClickListener {
            viewModel.signOut()
            (requireActivity() as BaseActivity).switchToLogin()
        }

        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}