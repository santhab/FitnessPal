package hu.bme.aut.android.fitnesspal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.fitnesspal.databinding.FragmentCreatefoodBinding
import hu.bme.aut.android.fitnesspal.model.Food
import kotlin.random.Random

class FoodCreateFragment : DialogFragment() {

    private lateinit var listener: FoodCreatedListener
    private lateinit var binding: FragmentCreatefoodBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = if (parentFragment != null) {
                parentFragment as FoodCreatedListener
            } else {
                activity as FoodCreatedListener
            }
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreatefoodBinding.inflate(inflater, container, false)
        dialog?.setTitle(R.string.itemCreateFood)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateFood.setOnClickListener {

        if(     binding.etFoodName.text.toString() != "" &&
                binding.etFoodCalorie.text.toString() != "" &&
                binding.etFoodProtein.text.toString() != "" &&
                binding.etFoodCarb.text.toString() != "" &&
                binding.etFoodFat.text.toString() != "")
            {
                listener.onFoodCreated(Food(
                        id = Random.nextInt(),
                        name =  binding.etFoodName.text.toString(),
                        calorie = binding.etFoodCalorie.text.toString().toInt(),
                        protein = binding.etFoodProtein.text.toString().toInt(),
                        carb = binding.etFoodCarb.text.toString().toInt(),
                        fat = binding.etFoodFat.text.toString().toInt()))
                dismiss()
            }
        else
            {
                Toast.makeText(context, "You have to fill every input!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancelCreateFood.setOnClickListener {
            dismiss()
        }

    }
    interface FoodCreatedListener{
        fun onFoodCreated(food: Food)
    }
}