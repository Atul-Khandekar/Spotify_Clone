package com.example.spotifyclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.customadapters.HomeScreenAdapter
import com.example.spotifyclone.databinding.FragmentHomeBinding
import com.example.spotifyclone.models.HomeScreenModel
import java.util.UUID

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeScreenAdapter()
        adapter.submitList(
            listOf(
                HomeScreenModel(listOf(), UUID.randomUUID(), "Music"),
                HomeScreenModel(listOf(), UUID.randomUUID(), "Sports"),
                HomeScreenModel(listOf(), UUID.randomUUID(), "Government") ,
                HomeScreenModel(listOf(), UUID.randomUUID(), "Art") ,
                HomeScreenModel(listOf(), UUID.randomUUID(), "Movies"),

            )
        )
        binding.parentRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }

}