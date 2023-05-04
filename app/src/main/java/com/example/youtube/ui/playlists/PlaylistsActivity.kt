package com.example.youtube.ui.playlists

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.BaseActivity.BaseActivity
import com.example.youtube.databinding.ActivityPlaylistsBinding
import com.example.youtube.model.Playlist
import com.example.youtube.ui.deta.DetailActivity
import com.example.youtube.ui.playlists.adapter.PlaylistsAdapter

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    private lateinit var adapter: PlaylistsAdapter
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun initViews() {
        super.initViews()
        adapter = PlaylistsAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlists().observe(this) {
            binding.recyclerView.adapter = adapter
            adapter.addList(it.items!! as List<Playlist.Item>)
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun onClick(item: Playlist.Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(ID, item.id)
        startActivity(intent)
    }

    companion object {
        const val ID = "ID"
    }

}
