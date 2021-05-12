package hu.bme.aut.android.fitnesspal.database.food


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDao {

    @Insert
    fun insertFood(todo: RoomFood)

    @Query("SELECT * FROM food")
    fun getAllFood(): LiveData<List<RoomFood>>

    @Update
    fun updateFood(todo: RoomFood): Int

    @Delete
    fun deleteFood(todo: RoomFood)

    @Query("SELECT * FROM food WHERE id == :id")
    fun getFoodById(id: Int?): RoomFood?

    @Query("DELETE FROM food")
    fun clearTable()
}