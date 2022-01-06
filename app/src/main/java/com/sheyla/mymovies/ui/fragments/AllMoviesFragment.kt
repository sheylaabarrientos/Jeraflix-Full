package com.sheyla.mymovies.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sheyla.mymovies.R
import com.sheyla.mymovies.ui.FailSystemActivity
import com.sheyla.mymovies.ui.adpater.MoviesAdapter
import com.sheyla.mymovies.ui.model.MoviesViewModel
import com.sheyla.mymovies.ui.model.ViewState

class AllMoviesFragment : Fragment(){

    private lateinit var listAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_allmovies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMovies = view.findViewById<RecyclerView>(R.id.rcvContainer)

        listAdapter = MoviesAdapter(context = view.context)
        rvMovies.adapter = listAdapter

        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
        moviesViewModel.getPopularMovies()

        observeListMovies()
        observeViewState()
    }

    override fun onResume() {
        super.onResume()
        listAdapter.notifyDataSetChanged()
    }

    private fun observeListMovies() {
        moviesViewModel.movieListLiveData.observe(viewLifecycleOwner, { response ->
            response?.let {
                listAdapter.dataSet.clear()
                listAdapter.dataSet.addAll(it)
                listAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun observeViewState() {
        moviesViewModel.viewStateLiveData.observe(viewLifecycleOwner, { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(requireContext(), FailSystemActivity::class.java)
                startActivity(intent)
            }
        })
    }
}




