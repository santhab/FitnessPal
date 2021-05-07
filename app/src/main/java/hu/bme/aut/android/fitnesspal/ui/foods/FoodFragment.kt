package hu.bme.aut.android.fitnesspal.ui.foods

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.MainActivity
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
        Log.d("TAG", "FoodFragment onCreate()")
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

        foodItemRecyclerViewAdapter = FoodItemRecyclerViewAdapter()
        foodItemRecyclerViewAdapter.itemClickListener = this
        foodItemRecyclerViewAdapter.addAll( (activity as MainActivity).demoFoodDataFromMainActivity )

    }

    override fun onItemClick(food: Food) {
        Log.d("TAG", "short click from FoodFragment!")
    }

    override fun onItemLongClick(position: Int, view: View): Boolean {
         Log.d("TAG", "long click from FoodFragment!")
            return false
    }


}