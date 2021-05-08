package hu.bme.aut.android.fitnesspal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.adapter.FoodItemRecyclerViewAdapter
import hu.bme.aut.android.fitnesspal.databinding.FragmentCreateentryBinding
import hu.bme.aut.android.fitnesspal.databinding.FragmentCreatefoodBinding
import hu.bme.aut.android.fitnesspal.model.Food
import kotlin.random.Random

class EntryCreateFragment : DialogFragment(), FoodItemRecyclerViewAdapter.FoodItemClickListener {

    private lateinit var listener: EntryCreatedListener
    private lateinit var binding: FragmentCreateentryBinding
    private lateinit var foodItemRecyclerViewAdapter: FoodItemRecyclerViewAdapter


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

    }
    interface EntryCreatedListener{
        fun onEntryCreated(food: Food)
    }

    override fun onItemClick(food: Food) {
        Log.d("TAG", "hello from the dialogfragment short")
    }

    override fun onItemLongClick(position: Int, view: View): Boolean {
        Log.d("TAG", "hello from the dialogfragment long")
        return false
    }
}