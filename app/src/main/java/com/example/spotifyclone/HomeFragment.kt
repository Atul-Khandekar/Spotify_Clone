package com.example.spotifyclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
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
                HomeScreenModel(listOf(), UUID.randomUUID(), "Government"),
                HomeScreenModel(listOf(), UUID.randomUUID(), "Art"),
                HomeScreenModel(listOf(), UUID.randomUUID(), "Movies"),

                )
        )
        binding.parentRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            setHasFixedSize(true)
            this.adapter = adapter
        }

//        val toggle = ActionBarDrawerToggle(
//            activity,
//            binding.drawerLayout,
//            binding.actionBarHomeActivity,
//            R.string.navigation_drawer_open,
//            R.string.navigation_drawer_close
//        )
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//        toggle.isDrawerIndicatorEnabled = false
//        val drawable = ResourcesCompat.getDrawable(resources , R.drawable.ic_menu , activity?.theme)
//        toggle.setHomeAsUpIndicator(drawable)
//
//        toggle.toolbarNavigationClickListener = View.OnClickListener {
//            if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
//                binding.drawerLayout.closeDrawer(GravityCompat.START)
//            } else {
//                binding.drawerLayout.openDrawer(GravityCompat.START)
//            }
//        }

        binding.apply {
            navigationView.apply {
                bringToFront()
            }
            actionBarHomeActivity.apply {

            }

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