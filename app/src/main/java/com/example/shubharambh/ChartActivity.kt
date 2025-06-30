package com.example.shubharambh

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shubharambh.databinding.ActivityChartBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.backBtn.setOnClickListener { finish() }
        try {
            binding.progressBar.visibility= View.VISIBLE
            RetrofitClient.api.getProducts().enqueue(object : Callback<ProductResponse> {
                override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let { showCharts(it.products) }
                    }
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    binding.progressBar.visibility=View.GONE
                    Log.e("api-error", t.message.toString())
                    Toast.makeText(this@ChartActivity,"Something going wrong!", Toast.LENGTH_SHORT).show()
                }
            })
        }catch(e: Exception){
            Log.e("chart-error",e.message.toString())
            Toast.makeText(this@ChartActivity,"Something going wrong!", Toast.LENGTH_SHORT).show()
        }

    }
    private fun showCharts(products: List<Product>) {
        // Pie Chart by Category Count
        val categoryCount = products.groupingBy { it.category }.eachCount()
        val pieEntries = categoryCount.map { PieEntry(it.value.toFloat(), it.key) }
        val pieDataSet = PieDataSet(pieEntries, "Product Categories")
        pieDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        binding.pieChart.data = PieData(pieDataSet)
        binding.pieChart.description.isEnabled = false
        binding.pieChart.invalidate()

        // Line Chart by Product Price
        val lineEntries = products.take(10).mapIndexed { index, product ->
            Entry(index.toFloat(), product.price.toFloat())
        }
        val lineDataSet = LineDataSet(lineEntries, "Product Prices")
        lineDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        lineDataSet.setCircleColors(*ColorTemplate.MATERIAL_COLORS)
        binding.lineChart.data = LineData(lineDataSet)
        binding.lineChart.description.isEnabled = false
        binding.lineChart.invalidate()

        binding.progressBar.visibility=View.GONE
        binding.linearLayout.visibility=View.VISIBLE
    }
}