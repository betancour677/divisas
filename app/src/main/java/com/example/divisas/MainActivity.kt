package com.example.divisas
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.divisas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val currencies = arrayOf("Euro", "Dólar", "Peso Mexicano", "Peso Chileno", "Peso Argentino")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFromCurrency.adapter = adapter
        binding.spinnerToCurrency.adapter = adapter

        binding.buttonConvert.setOnClickListener { convertCurrency() }
        binding.buttonReset.setOnClickListener { resetFields() }
    }

    private fun convertCurrency() {
        val fromCurrency = binding.spinnerFromCurrency.selectedItem as String
        val toCurrency = binding.spinnerToCurrency.selectedItem as String
        val amount = binding.editTextAmount.text.toString().toDoubleOrNull() ?: 0.0

        val result = performConversion(fromCurrency, toCurrency, amount)
        binding.textViewResult.text = result.toString()
    }

    private fun resetFields() {
        binding.editTextAmount.text.clear()
        binding.spinnerFromCurrency.setSelection(0)
        binding.spinnerToCurrency.setSelection(0)
        binding.textViewResult.text = ""
    }

    private fun performConversion(fromCurrency: String, toCurrency: String, amount: Double): Double {

            val conversionRate = getConversionRate(fromCurrency, toCurrency)
            return amount * conversionRate
        }

        private fun getConversionRate(fromCurrency: String, toCurrency: String): Double {

            val euroToDollarRate = 1.2
            val euroToMexicanPesoRate = 23.7
            val euroToChileanPesoRate = 792.5
            val euroToArgentinePesoRate = 122.5
            val dollarToEuroRate = 1 / euroToDollarRate
            val dollarToMexicanPesoRate = euroToMexicanPesoRate / euroToDollarRate
            val dollarToChileanPesoRate = euroToChileanPesoRate / euroToDollarRate
            val dollarToArgentinePesoRate = euroToArgentinePesoRate / euroToDollarRate
            val mexicanPesoToEuroRate = 1 / euroToMexicanPesoRate
            val mexicanPesoToDollarRate = 1 / dollarToMexicanPesoRate
            val mexicanPesoToChileanPesoRate = euroToChileanPesoRate / euroToMexicanPesoRate
            val mexicanPesoToArgentinePesoRate = euroToArgentinePesoRate / euroToMexicanPesoRate
            val chileanPesoToEuroRate = 1 / euroToChileanPesoRate
            val chileanPesoToDollarRate = 1 / dollarToChileanPesoRate
            val chileanPesoToMexicanPesoRate = 1 / mexicanPesoToChileanPesoRate
            val chileanPesoToArgentinePesoRate = euroToArgentinePesoRate / euroToChileanPesoRate
            val argentinePesoToEuroRate = 1 / euroToArgentinePesoRate
            val argentinePesoToDollarRate = 1 / dollarToArgentinePesoRate
            val argentinePesoToMexicanPesoRate = 1 / mexicanPesoToArgentinePesoRate
            val argentinePesoToChileanPesoRate = 1 / chileanPesoToArgentinePesoRate

            return when {
                fromCurrency == "Euro" && toCurrency == "Dólar" -> euroToDollarRate
                fromCurrency == "Euro" && toCurrency == "Peso Mexicano" -> euroToMexicanPesoRate
                fromCurrency == "Euro" && toCurrency == "Peso Chileno" -> euroToChileanPesoRate
                fromCurrency == "Euro" && toCurrency == "Peso Argentino" -> euroToArgentinePesoRate
                fromCurrency == "Dólar" && toCurrency == "Euro" -> dollarToEuroRate
                fromCurrency == "Dólar" && toCurrency == "Peso Mexicano" -> dollarToMexicanPesoRate
                fromCurrency == "Dólar" && toCurrency == "Peso Chileno" -> dollarToChileanPesoRate
                fromCurrency == "Dólar" && toCurrency == "Peso Argentino" -> dollarToArgentinePesoRate
                fromCurrency == "Peso Mexicano" && toCurrency == "Euro" -> mexicanPesoToEuroRate
                fromCurrency == "Peso Mexicano" && toCurrency == "Dólar" -> mexicanPesoToDollarRate
                fromCurrency == "Peso Mexicano" && toCurrency == "Peso Chileno" -> mexicanPesoToChileanPesoRate
                fromCurrency == "Peso Mexicano" && toCurrency == "Peso Argentino" -> mexicanPesoToArgentinePesoRate
                fromCurrency == "Peso Chileno" && toCurrency == "Euro" -> chileanPesoToEuroRate
                fromCurrency == "Peso Chileno" && toCurrency == "Dólar" -> chileanPesoToDollarRate
                fromCurrency == "Peso Chileno" && toCurrency == "Peso Mexicano" -> chileanPesoToMexicanPesoRate
                fromCurrency == "Peso Chileno" && toCurrency == "Peso Argentino" -> chileanPesoToArgentinePesoRate
                fromCurrency == "Peso Argentino" && toCurrency == "Euro" -> argentinePesoToEuroRate
                fromCurrency == "Peso Argentino" && toCurrency == "Dólar" -> argentinePesoToDollarRate
                fromCurrency == "Peso Argentino" && toCurrency == "Peso Mexicano" -> argentinePesoToMexicanPesoRate
                fromCurrency == "Peso Argentino" && toCurrency == "Peso Chileno" -> argentinePesoToChileanPesoRate
                else -> 1.0
            }
        }


    }

