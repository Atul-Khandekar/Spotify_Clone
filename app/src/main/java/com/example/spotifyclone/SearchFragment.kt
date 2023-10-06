package com.example.spotifyclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.customadapters.SearchCategoryAdapter
import com.example.spotifyclone.databinding.FragmentSearchBinding
import com.example.spotifyclone.models.SearchCategoryModel
import java.util.UUID

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchAdapter = SearchCategoryAdapter()
        searchAdapter.submitList(  listOf(
            SearchCategoryModel(UUID.randomUUID() , "POP" , "noimage") ,
            SearchCategoryModel(UUID.randomUUID() , "POP" , "noimage"),
            SearchCategoryModel(UUID.randomUUID() , "POP" , "noimage"),
            SearchCategoryModel(UUID.randomUUID() , "POP" , "noimage"),
            SearchCategoryModel(UUID.randomUUID() , "POP" , "noimage"),
            SearchCategoryModel(UUID.randomUUID() , "POP" , "noimage"),
            SearchCategoryModel(UUID.randomUUID() , "POP" , "noimage"),
            SearchCategoryModel(UUID.randomUUID() , "POP" , "noimage"),
            ))
        binding.searchRecyclerView.adapter = searchAdapter
        binding.searchRecyclerView.setHasFixedSize(true)
        binding.searchRecyclerView.layoutManager = GridLayoutManager(binding.root.context , 2)
        binding.searchField.setOnClickListener {

          val activity = activity as HomeScreenActivity
            activity.supportFragmentManager.commit {
                add(R.id.nav_host_fragment , SearchSongFragment())
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu , menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =  null
    }

}