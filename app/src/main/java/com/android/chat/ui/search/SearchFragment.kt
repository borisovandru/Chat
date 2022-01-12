package com.android.chat.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.android.chat.R
import com.android.chat.databinding.FragmentSearchBinding
import com.android.chat.sl.core.Feature
import com.android.chat.ui.main.BaseFragment

class SearchFragment : BaseFragment<SearchViewModel>() {
    override fun viewModelClass() = SearchViewModel::class.java
    override val titleResId = R.string.title_search
    override fun name() = Feature.SEARCH.name

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //todo simplify this
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query.orEmpty().trim())
                return !query.isNullOrEmpty()
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText.orEmpty().trim())
                return !newText.isNullOrEmpty()
            }
        })
        val adapter = SearchUserAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.observe(this) { it.map(adapter) }
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}