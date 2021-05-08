package hu.bme.aut.android.fitnesspal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.fitnesspal.databinding.RowFoodBinding
import hu.bme.aut.android.fitnesspal.model.Food

class FoodItemRecyclerViewAdapter : ListAdapter<Food, FoodItemRecyclerViewAdapter.ViewHolder>(itemCallback){


    companion object{
        object itemCallback : DiffUtil.ItemCallback<Food>(){

            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var foodList = emptyList<Food>()
    var itemClickListener: FoodItemClickListener? = null

    interface FoodItemClickListener {
        fun onItemClick(food: Food)
        fun onItemLongClick(position: Int, view: View): Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        RowFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FoodItemRecyclerViewAdapter.ViewHolder, position: Int) {
        val food = foodList[position]

        holder.food = food

        holder.binding.tvFoodCalorie.text = food.calorie.toString()
        holder.binding.tvFoodProtein.text = food.protein.toString()
        holder.binding.tvFoodCarb.text = food.carb.toString()
        holder.binding.tvFoodFat.text = food.fat.toString()
        holder.binding.tvFoodName.text = food.name.toString()
        holder.binding.tvFoodQuantity.text = 100.toString()


        /*val resource = when (todo.priority) {
            Todo.Priority.LOW -> R.drawable.ic_low
            Todo.Priority.MEDIUM -> R.drawable.ic_medium
            Todo.Priority.HIGH -> R.drawable.ic_high
        }
        holder.binding.ivPriority.setImageResource(resource)*/
    }

    fun addAll(_foodList: MutableList<Food>) {
        foodList += _foodList
        submitList(foodList)
    }

    fun addFood(_food: Food) {
        foodList += _food
        submitList(foodList)
    }

    fun deleteRow(position: Int) {
        foodList = foodList.filterIndexed { index, _ -> index != position }
        submitList(foodList)
    }

    fun deleteAll(){
        foodList = foodList.filterIndexed { index, _ -> index == -1}
        submitList(foodList)
    }

    inner class ViewHolder(val binding: RowFoodBinding) : RecyclerView.ViewHolder(binding.root) {
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