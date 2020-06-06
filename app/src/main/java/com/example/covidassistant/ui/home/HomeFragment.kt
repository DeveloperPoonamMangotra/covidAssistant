package com.example.covidassistant.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.covidassistant.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchList()
        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer { homeState ->
            when(homeState){
                HomeViewModel.HomeState.Progress -> {
                    progressBar.visibility = View.VISIBLE }
                HomeViewModel.HomeState.Idle -> {
                    progressBar.visibility = View.GONE }
                is HomeViewModel.HomeState.Failure -> {
                    progressBar.visibility = View.GONE
                    showMessage(homeState.message)
                }
                is HomeViewModel.HomeState.Success -> {
                    progressBar.visibility = View.GONE
                    //showMessage(homeState.message)
                    recyclerView.adapter = activity?.let { HomeAdapter(it,homeState.homeModelList) }
                }
            }
        })
    }

    private fun showMessage(message:String){
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

}
