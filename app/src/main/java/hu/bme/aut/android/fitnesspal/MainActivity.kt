package hu.bme.aut.android.fitnesspal

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.fitnesspal.model.Entry
import hu.bme.aut.android.fitnesspal.model.Food

class MainActivity : AppCompatActivity() {

    val demoFoodDataFromMainActivity = mutableListOf(
        Food(1, "csirkecomb", 500,  20,30,10 ),
        Food(2, "csokitorta", 800, 15, 78, 42),
        Food(3, "marhapörkölt", 600, 24, 23, 30),
        Food(4, "pizza", 1000,  20,30,10 ),
        Food(5, "banán", 200, 15, 78, 42),
        Food(6, "palacsinta", 230,  20,30,10 ),
        Food(7, "túró", 140, 15, 78, 42),
        Food(8, "kenyér", 360,  20,30,10 ),
        Food(9, "kolbász", 504, 15, 78, 42),
        Food(10, "kakaó", 500,  20,30,10 ),
        Food(11, "narancs", 800, 15, 78, 42),
        Food(12, "alma", 600, 24, 23, 30),
        Food(13, "lazac", 1000,  20,30,10 ),
        Food(14, "karaj", 200, 15, 78, 42),
        Food(15, "zabpehely", 230,  20,30,10 ),
        Food(16, "tojás", 140, 15, 78, 42),
        Food(17, "tej", 360,  20,30,10 ),
        Food(18, "szaloncukor", 504, 15, 78, 42)
    )

    val demoEntryDataFromMainActivity = mutableListOf(
        Entry(1, 1, 1.5),
        Entry(2,5, 2.0),
        Entry(3,13, 1.7),
        Entry(4,18, 1.2),
        Entry(5, 17, 2.5),
        Entry(6, 9, 2.0),
        Entry(7, 7, 1.5),
        Entry(8,11, 1.7),
        Entry(9,15, 1.2),
        Entry(10, 3, 2.5),
        Entry(11, 6, 2.0),
        Entry(12, 14, 1.5)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        /*setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



}