package hu.bme.aut.android.fitnesspal.ui.daily

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.EntryCreateFragment
import hu.bme.aut.android.fitnesspal.FoodCreateFragment
import hu.bme.aut.android.fitnesspal.MainActivity
import hu.bme.aut.android.fitnesspal.R
import hu.bme.aut.android.fitnesspal.adapter.EntryItemRecyclerViewAdapter
import hu.bme.aut.android.fitnesspal.adapter.FoodItemRecyclerViewAdapter
import hu.bme.aut.android.fitnesspal.databinding.FragmentDailyBinding
import hu.bme.aut.android.fitnesspal.model.Entry
import hu.bme.aut.android.fitnesspal.model.Food

class DailyFragment : Fragment(), EntryItemRecyclerViewAdapter.EntryItemClickListener, EntryCreateFragment.EntryCreatedListener {

    private lateinit var binding: FragmentDailyBinding
    private lateinit var dailyViewModel: DailyViewModel
    private lateinit var entryItemRecyclerViewAdapter: EntryItemRecyclerViewAdapter
    private lateinit var entryToFood: HashMap<Int, Food>
    private lateinit var entryList: MutableList<Entry>
    private  var sumKcal: Int = 0
    private  var sumProt = 0
    private  var sumCarb = 0
    private  var sumFat = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "DailyFragment onCreate()")
        binding = FragmentDailyBinding.inflate(layoutInflater)
        (activity as MainActivity).setSupportActionBar(binding.tbDaily)
        binding.tbDaily.title = "DailyFragment toolbar"
        setHasOptionsMenu(true)
        setupRecyclerView()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        /*dailyViewModel =
                ViewModelProvider(this).get(DailyViewModel::class.java)*/
        Log.d("TAG", "oncreateview")
        /*val textView: TextView = root.findViewById(R.id.text_dashboard)
        dailyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        binding.root.findViewById<RecyclerView>(R.id.food_list).adapter = entryItemRecyclerViewAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG", "onviewcreated")

        super.onViewCreated(view, savedInstanceState)
        calculateSummary()
    }

    private fun setupRecyclerView() {
        entryItemRecyclerViewAdapter = EntryItemRecyclerViewAdapter(this)
        entryItemRecyclerViewAdapter.itemClickListener = this
        val foodList = (activity as MainActivity).demoFoodDataFromMainActivity
        entryList = (activity as MainActivity).demoEntryDataFromMainActivity
        entryToFood = HashMap<Int , Food>()
        for (entryItem in entryList){
            for(foodItem in foodList){
                if(entryItem.FoodId==foodItem.id){
                    entryToFood[entryItem.id]=foodItem
                    break
                }
            }
        }
        entryItemRecyclerViewAdapter.addAllEntry( entryList, entryToFood )



    }

    override fun onItemClick(food: Food) {
        Log.d("TAG", "short click from DailyFragment!")
    }

    override fun onItemLongClick(position: Int, view: View): Boolean {
        Log.d("TAG", "long click from DailyFragment!")
        val popup = PopupMenu(activity, view)
        popup.inflate(R.menu.menu_food)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete -> {
                    entryList.removeAt(position)
                    entryItemRecyclerViewAdapter.removeEntry(entryList,  entryToFood, position)
                    calculateSummary()
                }
            }
            false
        }
        popup.show()
        return false
    }

    fun calculateSummary(){
        Log.d("TAG", "calculate summary")
        Log.d("TAG", entryList.size.toString())
        sumKcal = 0
        sumProt = 0
        sumCarb = 0
        sumFat = 0
        for (item in entryList){
            sumKcal += (item.quantity*entryToFood.get(item.id)?.calorie!!).toInt()
            sumProt += (item.quantity*entryToFood.get(item.id)?.protein!!).toInt()
            sumCarb += (item.quantity*entryToFood.get(item.id)?.carb!!).toInt()
            sumFat+= (item.quantity*entryToFood.get(item.id)?.fat!!).toInt()
        }
        updateSummaryView()
    }

    fun updateSummaryView(){
        Log.d("TAG", "update summary")
        binding.tvSummaryKcal.text = sumKcal.toString()
        binding.tvSummaryProtein.text = sumProt.toString()
        binding.tvSummaryCarb.text = sumCarb.toString()
        binding.tvSummaryFat.text = sumFat.toString()
    }


    fun deleteAll(){
        entryList.removeAll(entryList)
        entryItemRecyclerViewAdapter.removeAllEntry(entryList, entryToFood)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.menu_dailyfragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemCreateEntry ->{
                val entryCreateFragment = EntryCreateFragment()
                entryCreateFragment.show(childFragmentManager, "TAG")
            }
            R.id.itemDeleteAllEntry ->{
                deleteAll()
                calculateSummary()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onEntryCreated(food: Food) {
        TODO("Not yet implemented")
    }


}