package com.example.spotifyclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spotifyclone.customadapters.SearchCategoryAdapter
import com.example.spotifyclone.databinding.FragmentSearchBinding
import com.example.spotifyclone.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private val searchCategoryAdapter = SearchCategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setUpClickListeners()
        binding.searchRecyclerView.apply {
            adapter = searchCategoryAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)

        }
    }

    private fun setUpClickListeners() {
        binding.apply {
            searchField.setOnClickListener {
                val destination =
                    SearchFragmentDirections.actionSearchFragmentToSearchSongFragment()
                findNavController().navigate(destination)
            }
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.categoryList.collectLatest {
                    searchCategoryAdapter.submitList(it)
                }

                viewModel.errorMessage.collectLatest {
                    it?.also {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}