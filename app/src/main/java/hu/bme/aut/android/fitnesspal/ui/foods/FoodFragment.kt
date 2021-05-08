package hu.bme.aut.android.fitnesspal.ui.foods

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.FoodCreateFragment
import hu.bme.aut.android.fitnesspal.MainActivity
import hu.bme.aut.android.fitnesspal.R
import hu.bme.aut.android.fitnesspal.adapter.EntryItemRecyclerViewAdapter
import hu.bme.aut.android.fitnesspal.adapter.FoodItemRecyclerViewAdapter
import hu.bme.aut.android.fitnesspal.databinding.FragmentFoodBinding
import hu.bme.aut.android.fitnesspal.model.Food

class FoodFragment : Fragment(), FoodItemRecyclerViewAdapter.FoodItemClickListener, FoodCreateFragment.FoodCreatedListener {

    private lateinit var binding: FragmentFoodBinding
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var foodItemRecyclerViewAdapter: FoodItemRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "FoodFragment onCreate()")
        binding = FragmentFoodBinding.inflate(layoutInflater)
        (activity as MainActivity).setSupportActionBar(binding.tbFood)
        binding.tbFood.title = "FoodFragment toolbar "
        setHasOptionsMenu(true)
        setupRecyclerView()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        /*foodViewModel =
                ViewModelProvider(this).get(FoodViewModel::class.java)*/

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
        val popup = PopupMenu(activity, view)
        popup.inflate(R.menu.menu_food)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete -> foodItemRecyclerViewAdapter.deleteRow(position)
            }
            false
        }
        popup.show()
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.menu_foodbank, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemCreateFood ->{
                val todoCreateFragment = FoodCreateFragment()
                todoCreateFragment.show(childFragmentManager, "TAG")
            }
            R.id.itemDeleteAllFood ->{
                foodItemRecyclerViewAdapter.deleteAll()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFoodCreated(food: Food) {
        foodItemRecyclerViewAdapter.addFood(food)
    }


}