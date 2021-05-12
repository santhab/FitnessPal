package hu.bme.aut.android.fitnesspal.database.food

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    exportSchema = false,
    entities = [RoomFood::class]
)

abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

}