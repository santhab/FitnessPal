package hu.bme.aut.android.fitnesspal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.fitnesspal.R
import hu.bme.aut.android.fitnesspal.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        /*homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)*/

        val binding = FragmentHomeBinding.inflate(layoutInflater)


        /*val currentWeightView: TextView = root.findViewById(R.id.tvCurrentWeight)
        val goalWeightView: TextView = root.findViewById(R.id.tvGoalWeight)
        val goalCalorieView: TextView = root.findViewById(R.id.tvDailyCalorieGoal)
        val goalProteinView: TextView = root.findViewById(R.id.tvDailyProteinGoal)
        val goalCarbView: TextView = root.findViewById(R.id.tvDailyCarbGoal)
        val goalFatView: TextView = root.findViewById(R.id.tvDailyFatGoal)

        homeViewModel._currentWeight.observe(viewLifecycleOwner, Observer {
            currentWeightView.text = it
        })

        homeViewModel._goalWeight.observe(viewLifecycleOwner, Observer {
            goalWeightView.text = it
        })
        homeViewModel._goalCalorie.observe(viewLifecycleOwner, Observer {
            goalCalorieView.text = it
        })
        homeViewModel._goalProtein.observe(viewLifecycleOwner, Observer {
            goalProteinView.text = it
        })
        homeViewModel._goalCarb.observe(viewLifecycleOwner, Observer {
            goalCarbView.text = it
        })
        homeViewModel._goalFat.observe(viewLifecycleOwner, Observer {
            goalFatView.text = it
        })*/

        return binding.root
    }

}