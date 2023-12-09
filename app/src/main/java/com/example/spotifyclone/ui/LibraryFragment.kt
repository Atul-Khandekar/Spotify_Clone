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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.customadapters.LibraryAdapter
import com.example.spotifyclone.databinding.FragmentLibraryBinding
import com.example.spotifyclone.listeners.ItemClickListener
import com.example.spotifyclone.models.local.LibraryItem
import com.example.spotifyclone.viewmodel.UserLibraryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserLibraryViewModel by viewModels()
    private val libraryAdapter = LibraryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setUpObservers()
        setUpClickListeners()
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.libraryItem.collectLatest {
                    libraryAdapter.submitList(it)
                }

                viewModel.errorMessage.collectLatest {
                    it?.also {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setUpUI() {
        binding.playlistRecyclerView.apply {
            this.adapter = libraryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

    }

    private fun handleArtistState() {

        viewLifecycleOwner.lifecycleScope.launch {
            binding.apply {
                if (chipArtists.isSelected) {
                    btnClose.visibility = View.VISIBLE
                    chipPlaylist.visibility = View.GONE
                    viewModel.artistChecked()
                } else {
                    chipPlaylist.visibility = View.VISIBLE
                    btnClose.visibility = View.GONE
                    viewModel.uncheckedList()
                }
            }
        }

    }

    private fun handlePlaylistState() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.apply {
                if (chipPlaylist.isSelected) {
                    btnClose.visibility = View.VISIBLE
                    chipArtists.visibility = View.GONE
                    viewModel.playlistChecked()

                } else {
                    chipArtists.visibility = View.VISIBLE
                    btnClose.visibility = View.GONE
                    viewModel.uncheckedList()
                }
            }
        }

    }

    private fun setUpClickListeners() {
        binding.apply {

            chipPlaylist.setOnClickListener {
                chipPlaylist.isSelected = !chipPlaylist.isSelected
                handlePlaylistState()
            }

            chipArtists.setOnClickListener {
                chipArtists.isSelected = !chipArtists.isSelected
                handleArtistState()
            }

            btnClose.setOnClickListener {
                btnClose.isSelected = !btnClose.isSelected
                chipPlaylist.isSelected = false
                chipArtists.isSelected = false
                if (btnClose.isSelected) {
                    btnClose.visibility = View.GONE
                }
                handleArtistState()
                handlePlaylistState()
            }

            libraryAdapter.apply {
                itemClickListener = object : ItemClickListener<LibraryItem> {
                    override fun onItemClick(item: LibraryItem, position: Int) {
                        item.id?.also {
                            val destination =
                                LibraryFragmentDirections.actionLibraryFragmentToTrackListFragment(
                                    item.id, item.type
                                )
                            findNavController().navigate(destination)
                        }
                    }

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}