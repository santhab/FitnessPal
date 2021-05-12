package hu.bme.aut.android.fitnesspal

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.adapter.FoodItemRecyclerViewAdapter
import hu.bme.aut.android.fitnesspal.databinding.FragmentCreateentryBinding
import hu.bme.aut.android.fitnesspal.model.Food
import java.util.zip.DeflaterOutputStream


class EntryCreateFragment : DialogFragment(), FoodItemRecyclerViewAdapter.FoodItemClickListener {

    private lateinit var listener: EntryCreatedListener
    private lateinit var binding: FragmentCreateentryBinding
    private lateinit var foodItemRecyclerViewAdapter: FoodItemRecyclerViewAdapter
    private lateinit var selectedFood : Food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodItemRecyclerViewAdapter = FoodItemRecyclerViewAdapter()
        foodItemRecyclerViewAdapter.itemClickListener = this
        foodItemRecyclerViewAdapter.addAll( (activity as MainActivity).demoFoodDataFromMainActivity )
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = if (parentFragment != null) {
                parentFragment as EntryCreatedListener
            } else {
                activity as EntryCreatedListener
            }
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateentryBinding.inflate(inflater, container, false)
        dialog?.setTitle(R.string.itemCreateEntry)
        binding.root.findViewById<RecyclerView>(R.id.food_list).adapter = foodItemRecyclerViewAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etFoodQuantity.setText(1.toString())
        binding.etFoodQuantity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var multiplier: Double

                if(binding.etFoodQuantity.text.toString()== ""){
                   multiplier = 1.0
                }
                else{
                    multiplier = binding.etFoodQuantity.text.toString().toDouble()
                }

                binding.etFoodCalorie.setText((selectedFood.calorie*multiplier).toInt().toString())
                binding.etFoodProtein.setText((selectedFood.protein*multiplier).toInt().toString())
                binding.etFoodCarb.setText((selectedFood.carb*multiplier).toInt().toString())
                binding.etFoodFat.setText((selectedFood.fat*multiplier).toInt().toString())
                // you can call or do what you want with your EditText here

                // yourEditText...
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        binding.btnCreateFood.setOnClickListener {
            /*listener.onEntryCreated(Entry(
                    Random.nextInt(),

                )
            )*/
            dismiss()
        }

        binding.btnCancelCreateFood.setOnClickListener {
            dismiss()
        }
    }

    interface EntryCreatedListener{
        fun onEntryCreated(food: Food)
    }

    override fun onItemClick(food: Food) {
        Log.d("TAG", "hello from the dialogfragment short")
        binding.etFoodCalorie.setText(food.calorie.toString())
        binding.etFoodProtein.setText(food.protein.toString())
        binding.etFoodCarb.setText(food.carb.toString())
        binding.etFoodFat.setText(food.fat.toString())
        binding.etFoodName.setText(food.name.toString())
        binding.etFoodQuantity.isEnabled=true
        /*binding.etFoodCalorie.isEnabled=false
        binding.etFoodProtein.isEnabled=false
        binding.etFoodCarb.isEnabled=false
        binding.etFoodFat.isEnabled=false
        binding.etFoodName.isEnabled=false*/
        selectedFood=food
    }



    override fun onItemLongClick(position: Int, view: View, food: Food): Boolean {
        Log.d("TAG", "hello from the dialogfragment long")
        return false
    }
}