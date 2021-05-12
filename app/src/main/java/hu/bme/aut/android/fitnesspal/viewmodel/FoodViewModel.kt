package hu.bme.aut.android.fitnesspal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.android.fitnesspal.FitnessApplication
import hu.bme.aut.android.fitnesspal.model.Food
import hu.bme.aut.android.fitnesspal.repository.Repository
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {

    private val repository: Repository

    val allFood: LiveData<List<Food>>

    init {
        val todoDao = FitnessApplication.foodDatabase.foodDao()
        repository = Repository(todoDao)
        allFood = repository.getAllTodos()
    }

    fun insert(food: Food) = viewModelScope.launch {
        repository.insert(food)
    }

    fun delete(food: Food) = viewModelScope.launch {
        repository.delete(food)
    }

}