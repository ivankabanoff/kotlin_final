package com.example.rf_project

import com.example.rf_project.data.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MainActivity : AppCompatActivity() {
    object RetrofitClient {
        private const val BASE_URL = "https://raw.githubusercontent.com/ivankabanoff/countries/"

        val apiService: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)

        button.setOnClickListener {
            val countryFile = "https://raw.githubusercontent.com/ivankabanoff/countries/main/" + editText.text.toString().lowercase() + ".json"
            println(countryFile)
            fetchData(countryFile)
        }
    }

    private fun fetchData(url: String) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getData(url)
                }
                textView.text = "City: ${response.city}\nCountry: ${response.country}\n" +
                        "Population: ${response.population}"
            } catch (e: Exception) {
                textView.text = "Error: ${e.message}"
            }
        }
    }
}