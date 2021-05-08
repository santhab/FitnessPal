package hu.bme.aut.android.fitnesspal.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.databinding.RowFoodBinding
import hu.bme.aut.android.fitnesspal.model.Entry
import hu.bme.aut.android.fitnesspal.model.Food
import hu.bme.aut.android.fitnesspal.ui.daily.DailyFragment

class EntryItemRecyclerViewAdapter(context: DailyFragment) : ListAdapter<Entry, EntryItemRecyclerViewAdapter.ViewHolder>(itemCallback){

    private var entryList = emptyList<Entry>()
    private var foodList = emptyList<Food>()
    private var entryToFood: HashMap<Int, Food>? = null
    var itemClickListener: EntryItemClickListener? = null



    companion object{
        object itemCallback : DiffUtil.ItemCallback<Entry>(){

            override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
                return oldItem == newItem
            }
        }
    }



    interface EntryItemClickListener {
        fun onItemClick(food: Food)
        fun onItemLongClick(position: Int, view: View): Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        RowFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: EntryItemRecyclerViewAdapter.ViewHolder, position: Int) {
        val entry = entryList[position]

        holder.food=entryToFood?.get(entry.id)

        holder.entry = entry
        holder.binding.tvFoodCalorie.text = (holder.food?.calorie!! * holder.entry!!.quantity).toInt().toString()
        holder.binding.tvFoodProtein.text = (holder.food?.protein!! * holder.entry!!.quantity).toInt().toString()
        holder.binding.tvFoodCarb.text = (holder.food?.carb!! * holder.entry!!.quantity).toInt().toString()
        holder.binding.tvFoodFat.text = (holder.food?.fat!! * holder.entry!!.quantity).toInt().toString()
        holder.binding.tvFoodName.text = (holder.food?.name)
        holder.binding.tvFoodQuantity.text = (holder.entry?.quantity?.times(100))?.toInt().toString()


        /*val resource = when (todo.priority) {
            Todo.Priority.LOW -> R.drawable.ic_low
            Todo.Priority.MEDIUM -> R.drawable.ic_medium
            Todo.Priority.HIGH -> R.drawable.ic_high
        }
        holder.binding.ivPriority.setImageResource(resource)*/
    }

    fun addAllEntry(_entryList: MutableList<Entry>, _entryToFood: HashMap<Int, Food>) {
        Log.d("TAG", "addAllEntry: sizeof _entrylist: "+_entryList.size.toString())
        entryList += _entryList
        submitList(entryList)
        this.entryToFood = _entryToFood
    }

    fun removeAllEntry(_entryList: MutableList<Entry>, _entryToFood: HashMap<Int, Food>) {
        Log.d("TAG", "removeAllEntry: sizeof _entrylist: "+_entryList.size.toString())
        entryList = _entryList
        submitList(entryList)
        this.entryToFood = _entryToFood
    }

    fun removeEntry(_entryList: MutableList<Entry>, _entryToFood: HashMap<Int, Food>, position: Int ){
        Log.d("TAG", "removeEntry: sizeof _entrylist: "+_entryList.size.toString())
        entryList = entryList.filterIndexed { index, _ -> index != position }
        submitList(entryList)
        this.entryToFood = _entryToFood
    }

   /* fun applyEntry(_entryList: MutableList<Entry>, _entryToFood: HashMap<Int, Food>){
        Log.d("TAG", "ApplyEntry: sizeof _entrylist: "+_entryList.size.toString())
        entryList += _entryList
        submitList(entryList)
        this.entryToFood = _entryToFood
    }*/

    inner class ViewHolder(val binding: RowFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        var entry: Entry? = null
        var food: Food? = null

        init {
            itemView.setOnClickListener {
                food?.let { food -> itemClickListener?.onItemClick(food) }
            }

            itemView.setOnLongClickListener { view ->
                itemClickListener?.onItemLongClick(adapterPosition, view)
                true
            }
        }
    }

}