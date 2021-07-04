package com.dicoding.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.moviecatalogue.R
import com.dicoding.moviecatalogue.databinding.TvShowFragmentBinding
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment(R.layout.tv_show_fragment) {

    private lateinit var tvShowFragmentBinding: TvShowFragmentBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        tvShowFragmentBinding = TvShowFragmentBinding.inflate(layoutInflater, container, false)
        return tvShowFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShowAdapter = TVShowAdapter()

            tvShowFragmentBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTVShow().observe(this, { tvShow ->
                tvShowFragmentBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setTVShow(tvShow)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(tvShowFragmentBinding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}