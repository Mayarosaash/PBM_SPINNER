package com.example.pbm_spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var edBerat: EditText
    lateinit var edTinggi: EditText
    lateinit var btn: Button
    lateinit var status : TextView

    val pilihan = arrayOf("Pilihan", "Dewasa", "Anak-Anak")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner2)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pilihan)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext, "kategori :" + pilihan[position], Toast.LENGTH_LONG
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edBerat = findViewById(R.id.numberWeight)
        edTinggi = findViewById(R.id.numberHeight)
        btn = findViewById(R.id.btnCalculate)
        status = findViewById(R.id.status)



        btn.setOnClickListener {
            val isiBerat = edBerat.text.toString()
            val isiTinggi = edTinggi.text.toString()
            val result: TextView = findViewById(R.id.result)

            when {
                isiBerat.isNullOrBlank() ->{
                    Toast.makeText(this, "Harus Diisi", Toast.LENGTH_LONG).show()
                }
                isiTinggi.isNullOrBlank() ->{
                    Toast.makeText(this, "Harus Diisi", Toast.LENGTH_LONG).show()

                }
                else -> {
                    val hitungBMI = isiBerat.toFloat()/((isiTinggi.toFloat()/100)*(isiTinggi.toFloat()/100))
                    result.text = hitungBMI.toString()
                    val pilihanStatus = spinner.selectedItem.toString()
                    statusBMI(hitungBMI, pilihanStatus)
                }
            }
        }

    }

    private fun statusBMI(bmi: Float, kategori: String){
        val result: TextView = findViewById(R.id.result)

        when(kategori) {
            "Dewasa" -> {
                result.text = bmi.toString()
                when{
                    bmi < 18.5 -> {
                        status.text = "Status = Underweight"
                    }
                    bmi in 18.5..25.0 -> {
                        status.text = "Status = Normal"
                    }
                    bmi in 25.0..30.0 -> {
                        status.text = "Status = Overweight"
                    }
                    else -> {
                        status.text = "Status = Obese"
                    }
                }
            }
            "Anak - Anak" -> {
                result.text = bmi.toString()
                when{
                    bmi < 5 -> {
                        status.text = "Status = Underweight"
                    }
                    bmi in 5.0..85.0 -> {
                        status.text = "Status = Normal"
                    }
                    bmi in 85.0..95.0 -> {
                        status.text = "Status = Overweight"
                    }
                    bmi > 95.0 -> {
                        status.text = "Status = Obese"
                    }
                }
            }

        }
    }
//    private fun resultBMI(bmi: Float) {
//        val result: TextView = findViewById(R.id.result)
//        result.text = bmi.toString()
//        when {
//            bmi < 18.5 -> {
//                Toast.makeText(this, "UnderWeight", Toast.LENGTH_LONG).show()
//            }
//
//            bmi in 18.5..24.9 -> {
//                Toast.makeText(this, "Healty", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
}