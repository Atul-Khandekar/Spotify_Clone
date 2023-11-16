package com.example.spotifyclone

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.customadapters.HomePageAdapter
import com.example.spotifyclone.databinding.FragmentHomeBinding
import com.example.spotifyclone.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeScreenViewModel by viewModels()
    private val rvAdapter = HomePageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        binding.parentRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            setHasFixedSize(true)
            this.adapter = rvAdapter
        }
    }

    private fun setUpObserver() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.homePageList.collectLatest {
                        rvAdapter.submitList(it)
                    }

                    viewModel.errorMessage.collectLatest {
                        it?.also {
                            Toast.makeText(binding.root.context, it, Toast.LENGTH_LONG).show()
                        }
                    }
                }
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