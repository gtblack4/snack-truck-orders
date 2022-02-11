package com.snacktruck.orders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.snacktruck.orders.adapter.SnackItemAdapter
import com.snacktruck.orders.adapter.order
import com.snacktruck.orders.data.SnackList
import com.snacktruck.orders.databinding.ActivityMainBinding
import com.snacktruck.orders.model.Snack

/**
 *
 */
private var dataSet = SnackList.allSnacks

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.snackRecyclerView
        recyclerView.adapter = SnackItemAdapter(this, dataSet)
        recyclerView.setHasFixedSize(true)

        // Set a Toolbar to replace the ActionBar. This allows the addition of a button in the toolbar
        val toolbar = binding.toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.submitOrderButton.setOnClickListener { showConfirmationDialog(order) }
        binding.addNewSnackButton.setOnClickListener { addNewSnackDialog() }
        binding.veggieCheckbox.setOnClickListener { updateSnackItems() }
        binding.nonVeggieCheckbox.setOnClickListener { updateSnackItems() }


    }

    /**
     * [addNewSnackDialog] Is called when the add snack button is pressed.
     * It opens [R.layout.add_snack_dialog] a custom layout for the dialog
     */
    private fun addNewSnackDialog() {
        var li = LayoutInflater.from(this)
        val promptsView: View = li.inflate(R.layout.add_snack_dialog, null)
        val snackNameInput = promptsView.findViewById<EditText>(R.id.snack_name_entry)
        val snackTypeInput = promptsView.findViewById<SwitchCompat>(R.id.veggie_switch)

        MaterialAlertDialogBuilder(this)
            .setTitle("Submit Your Order?")
            .setMessage("Add new Snack")
            .setCancelable(true)
            .setView(promptsView)
            .setNegativeButton("Cancel") { _, _ -> }
            .setPositiveButton("Save") { _, _ ->
                saveSnackItem(
                    snackNameInput.text.toString(),
                    snackTypeInput.isChecked
                )
            }
            .show()
    }

    /**
     *
     * showConfirmationDialog is called when the when the Submit button is pressed.
     * uses the standard [MaterialAlertDialogBuilder]
     * Takes a List of [Snack]
     */
    private fun showConfirmationDialog(order: MutableList<Snack>) {
        //orderMessage is what will be displayed to the user. We loop over the list of Snacks and append the snack name and a new line to the message
        var orderMessage = ""
        for (item in order) {
            orderMessage += "${item.snackName} ${System.lineSeparator()}"
            Log.e("orderMessage", "${item}")
        }
        MaterialAlertDialogBuilder(this)
            .setTitle("Confirm your order:")
            .setMessage(orderMessage)
            .setCancelable(false)
            .setNegativeButton("Change Order") { _, _ -> }
            .setPositiveButton("Submit Order") { _, _ -> submitOrder() }
            .show()
    }

    /**
     *saveSnackItem is called by [addNewSnackDialog] and takes a string and a boolean to build a Snack object and add it to our dataset to be displayed
     *Once the new item is put into a Snack object, we call [updateSnackItems] to update the adapters list
     */
    private fun saveSnackItem(veggieName: String, isVeggie: Boolean) {
        val veggieString: String
        if (isVeggie == true) {
            veggieString = "Veggie"
        } else {
            veggieString = "Non-veggie"
        }
        var newSnack = Snack(veggieName, veggieString)
        Log.e("new Item", "$veggieName $isVeggie  $newSnack")
        dataSet = dataSet + newSnack
        updateSnackItems()
    }

    /**
     * updateSnackItems is used by both [saveSnackItem] and onclick listeners for the Veggie and Non-veggie checkboxes
     *
     * This function notifies the recycler view that the list has changed.
     * This list can be filtered by the veggie_checkbox and non_veggie_checkbox or expanded by the [addNewSnackDialog]
     */
    private fun updateSnackItems() {
        val recyclerView = findViewById<RecyclerView>(R.id.snack_recycler_view)
        var snackDisplayList = dataSet

        if (!findViewById<CheckBox>(R.id.veggie_checkbox).isChecked) {
            snackDisplayList = snackDisplayList.filter { it.snackType == "Non-veggie" }
        }
        if (!findViewById<CheckBox>(R.id.non_veggie_checkbox).isChecked) {
            snackDisplayList = snackDisplayList.filter { it.snackType == "Veggie" }
        }

        recyclerView.adapter = SnackItemAdapter(this, snackDisplayList)
        findViewById<RecyclerView>(R.id.snack_recycler_view).adapter?.notifyDataSetChanged()
    }

    /**
     * SubmitOrder is called by the onClickLister in the [showConfirmationDialog]
     *The function clears out the current order
     *
     * TODO implement backend service and send the order
     */

    private fun submitOrder() {

        Log.d("Main Activity", "Submit the order to a backend service here")
        order.clear()
        findViewById<RecyclerView>(R.id.snack_recycler_view).adapter?.notifyDataSetChanged()

    }
}


