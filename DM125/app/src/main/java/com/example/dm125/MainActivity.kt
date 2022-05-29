package com.example.dm125

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.dm125.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Log.d("MainActivity", "onCreate")

        binding.btnSave.setOnClickListener {
            Log.d("MainActivity", "Save button pressed")

            if (binding.edtName.text.isNotEmpty()) {
                binding.textName.text = binding.edtName.text
                binding.textDescription.text = binding.edtDescription.text
                binding.textCode.text = binding.edtCode.text
                binding.textPrice.text = binding.edtPrice.text
            } else {
                Toast.makeText(this, "Please, enter the name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MainActivity", "onSaveInstanceState")

        val product = Product(
            name = binding.edtName.text.toString(),
            description = binding.edtDescription.text.toString(),
            code = binding.edtCode.text.toString(),
            price = if(binding.edtPrice.text.isNotEmpty()) binding.edtPrice.text.toString().toDouble() else 0.0
        )
        outState.putSerializable("product", product)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("MainActivity", "onRestoreInstanceState")

        val product = savedInstanceState.getSerializable("product") as Product
        binding.textName.text = product.name
        binding.textDescription.text = product.description
        binding.textCode.text = product.code
        binding.textPrice.text = product.price.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }
}