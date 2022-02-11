package com.snacktruck.orders.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.snacktruck.orders.R
import com.snacktruck.orders.model.Snack

var order: MutableList<Snack> = mutableListOf()

/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays a list of [Snack] data object.
 * uses [order] to build a list of checked items
 */
class SnackItemAdapter(
    private val context: Context,
    private var dataset: List<Snack>
) : RecyclerView.Adapter<SnackItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val snackName: TextView = view.findViewById(R.id.snack_name)
        val snackCheckbox: CheckBox = view.findViewById(R.id.snack_check_box)
    }


    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.snack_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * 1. Sets the text of the list item to [Snack.snackName]
     *
     * the onBindViewHolder does a number of things for our order
     * 2. Checks if the [order] has any [Snack] in it, if not sets the checkbox to unchecked
     * if it is checked, we loop through the order to see which item in the order matches the item in our dataSet so we can appropriately check it
     * This allows us to preserve orders through the lifecycle
     *
     * 3. Sets the text color of the [Snack] based on the [Snack.snackType]
     *
     * 4. Adds and removes [Snack] to [order] when they are checked or unchecked
     */

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //1. Set the list item to the snackName
        val item = dataset[position]
        holder.snackName.text = item.snackName

        //2. Checks the checkboxes based on the contents of [order]
        if (order.size == 0) {
            holder.snackCheckbox.isChecked = false
        } else {
            for (orderItem in order) {
                if (orderItem.snackName == holder.snackName.text) {
                    holder.snackCheckbox.isChecked = true
                }
            }
        }

        //3. Sets the text color of the snackName
        if (item.snackType == "Veggie") {
            holder.snackName.setTextColor(context.resources.getColor(R.color.veggie_green))
        }
        if (item.snackType == "Non-veggie") {
            holder.snackName.setTextColor(context.resources.getColor(R.color.meat_red))
        }

        //4. Adds or removes items to the order list when a list item is check or unchecked
        holder.snackCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                order.add(item)
            } else {
                order.remove(item)
            }
        }


    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size


    override fun onViewRecycled(itemViewHolder: ItemViewHolder) {
        if (itemViewHolder.snackCheckbox != null) {
            itemViewHolder.snackCheckbox.setOnClickListener(null)
        }
        super.onViewRecycled(itemViewHolder)
    }

}
