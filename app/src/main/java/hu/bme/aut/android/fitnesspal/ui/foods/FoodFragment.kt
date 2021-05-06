package hu.bme.aut.android.fitnesspal.ui.foods

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.fitnesspal.R
import hu.bme.aut.android.fitnesspal.databinding.ContentScrollingBinding
import hu.bme.aut.android.fitnesspal.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding
    private lateinit var foodViewModel: FoodViewModel

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

        return binding.root
    }
}