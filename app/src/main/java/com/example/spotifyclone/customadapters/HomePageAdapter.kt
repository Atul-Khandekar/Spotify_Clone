package com.example.spotifyclone.customadapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyclone.R
import com.example.spotifyclone.base.BaseAdapter
import com.example.spotifyclone.databinding.ItemHomeScreenBinding
import com.example.spotifyclone.listeners.ItemClickListener
import com.example.spotifyclone.models.local.HomePageData
import com.example.spotifyclone.models.local.HomePageItemData

class HomePageAdapter(private val  clickHandler: (HomePageItemData) -> Unit) : BaseAdapter<HomePageData>(HomeDiffUtil()) {

    class HomeDiffUtil : DiffUtil.ItemCallback<HomePageData>() {
        override fun areItemsTheSame(oldItem: HomePageData, newItem: HomePageData): Boolean {
            return oldItem.section == newItem.section
        }

        override fun areContentsTheSame(oldItem: HomePageData, newItem: HomePageData): Boolean {
           return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_home_screen
    }

    override fun setDataForItems(binding: ViewDataBinding, item: HomePageData) {
        super.setDataForItems(binding, item)
        val adapter = HomePageItemAdapter()
        (binding as ItemHomeScreenBinding).apply {
            horizontalRecyclerView.apply {
                layoutManager = LinearLayoutManager(binding.root.context , LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }

        adapter.submitList(item.items)
        adapter.itemClickListener = object : ItemClickListener<HomePageItemData> {
            override fun onItemClick(item: HomePageItemData, position: Int) {
                clickHandler(item)
            }
        }
    }

}