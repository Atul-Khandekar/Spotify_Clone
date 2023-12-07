package com.example.spotifyclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.customadapters.SearchSongAdapter
import com.example.spotifyclone.databinding.FragmentSearchSongBinding
import com.example.spotifyclone.extensions.onLeftDrawableClicked
import com.example.spotifyclone.viewmodel.SearchItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchSongFragment : Fragment() {

    private var _binding: FragmentSearchSongBinding? = null
    private val binding get() = _binding!!
    private val searchSongAdapter = SearchSongAdapter()
    private val viewModel: SearchItemViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setUpObservers()
        setUpListeners()
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.searchData.collectLatest {
                    searchSongAdapter.submitList(it)
                }

                viewModel.errorMessage.collectLatest {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setUpUI() {
        binding.searchSongRecyclerView.apply {
            adapter = searchSongAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setUpListeners() {
        binding.apply {
            searchView.onLeftDrawableClicked {
                findNavController().navigateUp()
            }

            searchView.doOnTextChanged { text, _, _, _ ->
                viewModel.search(text.toString())
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}