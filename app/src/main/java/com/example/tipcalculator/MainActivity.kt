package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setting the listener for the calculate button
        binding.calculateButton.setOnClickListener { calcTip() }
    }

    /**
     * The function that will calculate the tip
     */
    private fun calcTip(){
        //checking the state of the radio button
        // getting the percentage
        val tipPercentage = when(binding.tipOptionsRadio.checkedRadioButtonId){
            R.id.amazing_radio -> .20
            R.id.good_radio -> .18
            else -> .15
        }
        // getting the amount of tip to give
        var tipAmount = binding.tipAmount.text.toString().toDoubleOrNull()
        if (tipAmount == null) {
            binding.tipAmount.text = ""
            return // returning is there is nothing
        }
        // checking to see if we need to round up
        if(binding.roundUpSwitch.isChecked){
            tipAmount = kotlin.math.ceil(tipAmount)
        }
        // doing the formatting of the number
        val tip = NumberFormat.getCurrencyInstance().format(tipAmount)
        // setting the text of the tipAmount
        binding.tipAmount.text = getString(R.string.formated_tip, tip)
    }


}