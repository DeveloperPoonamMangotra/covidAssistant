package com.example.covidassistant.ui.home

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidassistant.R

/**
 * Created by Poonam on 06-06-2020.
 */
class HomeViewModel : ViewModel(){
    init {
        Log.i("HomeViewModel", "HomeViewModel created!")
    }

    private val _stateLiveData = MutableLiveData<HomeState>(HomeState.Idle)

    var state : HomeState = HomeState.Idle

    val stateLiveData : LiveData<HomeState> = _stateLiveData

    fun fetchList(){
        _stateLiveData.value = HomeState.Progress
        processList { homeList ->
            if(homeList.size > 0){
                _stateLiveData.value = HomeState.Success(message = "List Loaded", homeModelList = homeList)
            }else{
                _stateLiveData.value = HomeState.Failure(message = "Something went wrong")
            }
        }
    }

    private fun processList(callback : (ArrayList<HomeModel>) -> Unit){
        val homeList : ArrayList<HomeModel> = ArrayList<HomeModel>()
        Handler().postDelayed({
            homeList.add(HomeModel("Guidelines", R.drawable.guideline_gradient_color,"http://docs.google.com/gview?embedded=true&url="+"https://www.mohfw.gov.in/pdf/Guidelinesondisinfectionofcommonpublicplacesincludingoffices.pdf"))
            homeList.add(HomeModel("Tracker", R.drawable.tracker_gradient_color,"https://www.mygov.in/"))
            homeList.add(HomeModel("Support", R.drawable.support_gradient_color,""))
            callback(homeList)
        },2000)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("HomeViewModel", "HomeViewModel destroyed!")
    }

    sealed class HomeState{

        object Progress : HomeState()

        object Idle : HomeState()

        data class Failure(val message : String) : HomeState()

        data class Success(val message : String, val homeModelList : ArrayList<HomeModel>) : HomeState()

    }
}