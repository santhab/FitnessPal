package hu.bme.aut.android.fitnesspal.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import hu.bme.aut.android.fitnesspal.database.food.FoodDao
import hu.bme.aut.android.fitnesspal.database.food.RoomFood
import hu.bme.aut.android.fitnesspal.model.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val foodDao: FoodDao) {

    fun getAllTodos(): LiveData<List<Food>> {
        return foodDao.getAllFood()
            .map {roomFood ->
                roomFood.map {roomFood ->
                    roomFood.toDomainModel() }
            }
    }

    suspend fun insert(food: Food) = withContext(Dispatchers.IO) {
        foodDao.insertFood(food.toRoomModel())
    }

    suspend fun delete(food: Food) = withContext(Dispatchers.IO) {
        val roomFood = foodDao.getFoodById(food.id) ?: return@withContext
        foodDao.deleteFood(roomFood)
    }
    private fun RoomFood.toDomainModel(): Food {
        return Food(
            id = id,
            name = name,
            calorie = calorie,
            protein = protein,
            carb = carb,
            fat = fat
        )
    }

    private fun Food.toRoomModel(): RoomFood {
        return RoomFood(
            name = name,
            calorie = calorie,
            protein = protein,
            carb = carb,
            fat = fat
        )
    }
}