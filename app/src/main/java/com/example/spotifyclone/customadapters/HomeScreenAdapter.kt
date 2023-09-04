package com.example.spotifyclone.customadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyclone.databinding.ItemHomeScreenBinding
import com.example.spotifyclone.databinding.ItemHorizontalRecyclerViewBinding
import com.example.spotifyclone.models.AlbumModel
import com.example.spotifyclone.models.HomeScreenModel
import java.util.UUID

class HomeScreenAdapter :
    androidx.recyclerview.widget.ListAdapter<HomeScreenModel, HomeScreenAdapter.ViewHolder>(
        HomeDiffUtil()
    ) {
    class ViewHolder(val binding: ItemHomeScreenBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeScreenModel) {
            binding.sectionPlaceholder.text = data.sectionName
            val adapter = HorizontalAdapter()
            adapter.submitList(
                listOf(
                    AlbumModel(
                        UUID.randomUUID(),
                        "https://www.billboard.com/wp-content/uploads/2022/06/beyonce-Lemonade-album-art-billboard-1240.jpg?w=1024",
                        "Ajay Atulkahfkhdkfhkdhfkaklsflkdakhasdhfkjasdfkjadksfkjhdfkjhjdhgjakhskjhfakjshdf "
                    ), AlbumModel(
                        UUID.randomUUID(),
                        "https://wpimg.pixelied.com/blog/wp-content/uploads/2021/06/15134429/Spotify-Good-Contrast-Examples-2-480x476.jpg",
                        "Ajay Atul khfkafkakjfslkjsadlkfjajkfkjlahflkasdklhfkjlahdfkjhaskjlfhkjahfjklshfjkhsdkjhfkjldhs"
                    ), AlbumModel(
                        UUID.randomUUID(),
                        "https://wpimg.pixelied.com/blog/wp-content/uploads/2021/06/15134429/Spotify-Good-Contrast-Examples-2-480x476.jpg",
                        "Ajay Atul khfkafkakjfslkjsadlkfjajkfkjlahflkasdklhfkjlahdfkjhaskjlfhkjahfjklshfjkhsdkjhfkjldhs"
                    ) ,
                    AlbumModel(
                        UUID.randomUUID(),
                        "https://wpimg.pixelied.com/blog/wp-content/uploads/2021/06/15134429/Spotify-Good-Contrast-Examples-2-480x476.jpg",
                        "Ajay Atul khfkafkakjfslkjsadlkfjajkfkjlahflkasdklhfkjlahdfkjhaskjlfhkjahfjklshfjkhsdkjhfkjldhs"
                    ) ,
                    AlbumModel(
                        UUID.randomUUID(),
                        "https://wpimg.pixelied.com/blog/wp-content/uploads/2021/06/15134429/Spotify-Good-Contrast-Examples-2-480x476.jpg",
                        "Ajay Atul khfkafkakjfslkjsadlkfjajkfkjlahflkasdklhfkjlahdfkjhaskjlfhkjahfjklshfjkhsdkjhfkjldhs"
                    ) ,
                    AlbumModel(
                        UUID.randomUUID(),
                        "https://wpimg.pixelied.com/blog/wp-content/uploads/2021/06/15134429/Spotify-Good-Contrast-Examples-2-480x476.jpg",
                        "Ajay Atul khfkafkakjfslkjsadlkfjajkfkjlahflkasdklhfkjlahdfkjhaskjlfhkjahfjklshfjkhsdkjhfkjldhs"
                    )
                )
            )
            binding.horizontalRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                this.adapter = adapter
            }

        }
    }

    class HomeDiffUtil : DiffUtil.ItemCallback<HomeScreenModel>() {
        override fun areItemsTheSame(oldItem: HomeScreenModel, newItem: HomeScreenModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HomeScreenModel, newItem: HomeScreenModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHomeScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }

}