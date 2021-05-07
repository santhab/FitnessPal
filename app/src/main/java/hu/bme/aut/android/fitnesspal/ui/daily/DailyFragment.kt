package hu.bme.aut.android.fitnesspal.ui.daily

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.MainActivity
import hu.bme.aut.android.fitnesspal.R
import hu.bme.aut.android.fitnesspal.adapter.EntryItemRecyclerViewAdapter
import hu.bme.aut.android.fitnesspal.databinding.FragmentDailyBinding
import hu.bme.aut.android.fitnesspal.model.Entry
import hu.bme.aut.android.fitnesspal.model.Food

class DailyFragment : Fragment(), EntryItemRecyclerViewAdapter.EntryItemClickListener {

    private lateinit var binding: FragmentDailyBinding
    private lateinit var dailyViewModel: DailyViewModel
    private lateinit var entryItemRecyclerViewAdapter: EntryItemRecyclerViewAdapter
    private  var sumKcal = 0
    private  var sumProt = 0
    private  var sumCarb = 0
    private  var sumFat = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "DailyFragment onCreate()")
        setupRecyclerView()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        /*dailyViewModel =
                ViewModelProvider(this).get(DailyViewModel::class.java)*/


        val binding = FragmentDailyBinding.inflate(layoutInflater)

        /*val textView: TextView = root.findViewById(R.id.text_dashboard)
        dailyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        binding.root.findViewById<RecyclerView>(R.id.food_list).adapter = entryItemRecyclerViewAdapter
        return binding.root
    }

    private fun setupRecyclerView() {
        entryItemRecyclerViewAdapter = EntryItemRecyclerViewAdapter(this)
        entryItemRecyclerViewAdapter.itemClickListener = this
        entryItemRecyclerViewAdapter.addAllFood((activity as MainActivity).demoFoodDataFromMainActivity )

        val foodList = (activity as MainActivity).demoFoodDataFromMainActivity
        val entryList = (activity as MainActivity).demoEntryDataFromMainActivity

        val entryToFood = HashMap<Int , Food>()

        for (item in list){
            entryToFood[]
        }

        entryItemRecyclerViewAdapter.addAllEntry( (activity as MainActivity).demoEntryDataFromMainActivity )


    }

    override fun onItemClick(food: Food) {
        Log.d("TAG", "short click from DailyFragment!")
    }

    override fun onItemLongClick(position: Int, view: View): Boolean {
        Log.d("TAG", "long click from DailyFragment!")
        return false
    }

    fun updateSummary(){
        binding.tvSummaryKcal.text = sumKcal.toString()
        binding.tvSummaryProtein.text = sumProt.toString()
        binding.tvSummaryCarb.text = sumCarb.toString()
        binding.tvSummaryFat.text = sumFat.toString()
    }
}