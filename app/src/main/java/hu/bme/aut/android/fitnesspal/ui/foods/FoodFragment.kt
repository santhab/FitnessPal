package hu.bme.aut.android.fitnesspal.ui.foods

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.R
import hu.bme.aut.android.fitnesspal.adapter.FoodItemRecyclerViewAdapter
import hu.bme.aut.android.fitnesspal.databinding.FragmentFoodBinding
import hu.bme.aut.android.fitnesspal.model.Food

class FoodFragment : Fragment(), FoodItemRecyclerViewAdapter.FoodItemClickListener {

    private lateinit var binding: FragmentFoodBinding
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var foodItemRecyclerViewAdapter: FoodItemRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        /*foodViewModel =
                ViewModelProvider(this).get(FoodViewModel::class.java)*/

        val binding = FragmentFoodBinding.inflate(layoutInflater)

        /*val textView: TextView = root.findViewById(R.id.text_notifications)
        foodViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/


        binding.root.findViewById<RecyclerView>(R.id.food_list).adapter = foodItemRecyclerViewAdapter
        return binding.root
    }


    private fun setupRecyclerView() {
        val demoData = mutableListOf(
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
        foodItemRecyclerViewAdapter = FoodItemRecyclerViewAdapter()
        foodItemRecyclerViewAdapter.itemClickListener = this
        foodItemRecyclerViewAdapter.addAll(demoData)

    }

    override fun onItemClick(food: Food) {
        Log.d("TAG", "short click!")
    }

    override fun onItemLongClick(position: Int, view: View): Boolean {
         Log.d("TAG", "long click!")
            return false
    }


}