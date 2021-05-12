package hu.bme.aut.android.fitnesspal

import android.app.Application
import androidx.room.Room
import hu.bme.aut.android.fitnesspal.database.food.FoodDatabase

class FitnessApplication : Application() {

    companion object {
        lateinit var foodDatabase: FoodDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        foodDatabase = Room.databaseBuilder(
            applicationContext,
            FoodDatabase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }

}