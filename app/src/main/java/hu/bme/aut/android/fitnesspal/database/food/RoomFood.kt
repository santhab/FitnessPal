package hu.bme.aut.android.fitnesspal.database.food


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class RoomFood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val calorie: Int,
    val protein: Int,
    val carb: Int,
    val fat: Int
)

