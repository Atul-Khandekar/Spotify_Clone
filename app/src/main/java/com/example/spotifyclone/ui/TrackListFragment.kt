package com.example.spotifyclone.ui

import android.annotation.SuppressLint
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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.R
import com.example.spotifyclone.bindings.imageFromUrl
import com.example.spotifyclone.customadapters.TrackListAdapter
import com.example.spotifyclone.databinding.FragmentTrackListBinding
import com.example.spotifyclone.models.local.MediaItemType
import com.example.spotifyclone.viewmodel.TrackListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrackListFragment : Fragment() {

    private var _binding: FragmentTrackListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TrackListViewModel by viewModels()
    private val args: TrackListFragmentArgs by navArgs()
    private val trackListAdapter = TrackListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrackListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpObservers()
        binding.trackRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            this.adapter = trackListAdapter
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setUpObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.tracks.collectLatest {
                    it?.also {
                        trackListAdapter.submitList(it.dataList)
                        binding.apply {
                            playlistCoverImage.imageFromUrl(it.images)
                            trackListToolbar.title = it.name
                            trackListToolbar.setTitleTextColor(R.color.white)
                        }
                    }
                }

                viewModel.errorMessage.collectLatest { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun setUpView() {
        when (args.type) {
            is MediaItemType.Playlist -> {
                viewModel.getTrackList(args.playlistId)
            }

            is MediaItemType.Album -> {

            }
        }
    }
}