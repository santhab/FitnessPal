package hu.bme.aut.android.fitnesspal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

     val _currentWeight = MutableLiveData<String>().apply {
        value = "80"
     }
    val _goalWeight = MutableLiveData<String>().apply {
        value = "70"
    }
    val _goalCalorie = MutableLiveData<String>().apply {
        value = "1800"
    }
    val _goalProtein = MutableLiveData<String>().apply {
        value = "120"
    }
    val _goalCarb = MutableLiveData<String>().apply {
        value = "200"
    }
    val _goalFat = MutableLiveData<String>().apply {
        value = "69"
    }

    val currentWeight: LiveData<String> = _currentWeight
    val goalWeight: LiveData<String> = _goalWeight
    val goalCalorie: LiveData<String> = _goalCalorie
    val goalProtein: LiveData<String> = _goalProtein
    val goalCarb: LiveData<String> = _goalCarb
    val goalFat: LiveData<String> = _goalFat

}