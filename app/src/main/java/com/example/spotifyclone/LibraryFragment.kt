package com.example.spotifyclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.constants.ImageConstants
import com.example.spotifyclone.constants.StringConstants
import com.example.spotifyclone.customadapters.PlaylistAdapter
import com.example.spotifyclone.databinding.FragmentLibraryBinding
import com.example.spotifyclone.models.PlayListModel
import java.util.UUID

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlaylistAdapter()
        adapter.submitList(
            listOf(
                PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.INDIE_KID_ROOM_VIBES_COVER,
                    StringConstants.INDIE_KID_ROOM_VIBES_COVER,
                    "45"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.GALAXY_ROOM_VIBES,
                    StringConstants.GALAXY_ROOM_VIBES,
                    "10"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.COTTAGECORE_ROOM_VIBES,
                    StringConstants.COTTAGECORE_ROOM_VIBES,
                    "5"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.DARK_ACADEMIA_ROOM_VIBER,
                    StringConstants.DARK_ACADEMIA_ROOM_VIBER,
                    "13"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.INDIE_KID_ROOM_VIBES_COVER,
                    StringConstants.INDIE_KID_ROOM_VIBES_COVER,
                    "12"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.E_GIRL_ROOM_VIBERS,
                    StringConstants.E_GIRL_ROOM_VIBERS,
                    "8"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.ART_HOE_ROOM_VIBES,
                    StringConstants.ART_HOE_ROOM_VIBES,
                    "17"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.WATCHCORE_ROOM_VIBES,
                    StringConstants.WATCHCORE_ROOM_VIBES,
                    "23"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.SOFT_GIRL_ROOM_VIBES,
                    StringConstants.SOFT_GIRL_ROOM_VIBES,
                    "19"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.GRUNGE_ROOM_VIBES,
                    StringConstants.GRUNGE_ROOM_VIBES,
                    "11"
                ), PlayListModel(
                    UUID.randomUUID(),
                    ImageConstants.VINTAGE_ROOM_VIBES,
                    StringConstants.VINTAGE_ROOM_VIBES,
                    "10"
                )
            )
        )

        binding.playlistRecyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(view.context)
            setHasFixedSize(true)
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