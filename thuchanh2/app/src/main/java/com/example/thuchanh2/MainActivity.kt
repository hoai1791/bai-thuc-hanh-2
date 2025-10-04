package com.example.thuchanh2

import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var btnCreate: Button
    private lateinit var tvError: TextView
    private lateinit var layoutList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumber = findViewById(R.id.edtNumber)
        btnCreate = findViewById(R.id.btnCreate)
        tvError = findViewById(R.id.tvError)
        layoutList = findViewById(R.id.layoutList)

        btnCreate.setOnClickListener {
            val input = edtNumber.text.toString().trim()

            // Xóa danh sách cũ trước khi tạo mới
            layoutList.removeAllViews()

            if (input.isEmpty()) {
                showError()
                return@setOnClickListener
            }

            try {
                val n = input.toInt()
                if (n <= 0) {
                    showError()
                    return@setOnClickListener
                }

                // Nếu hợp lệ -> ẩn lỗi và tạo danh sách
                tvError.visibility = TextView.GONE

                for (i in 1..n) {
                    val tv = TextView(this)
                    tv.text = i.toString()
                    tv.setBackgroundColor(0xFFFF4444.toInt()) // nền đỏ
                    tv.setTextColor(0xFFFFFFFF.toInt())       // chữ trắng
                    tv.textSize = 18f
                    tv.setPadding(20, 20, 20, 20)
                    tv.gravity = Gravity.CENTER

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 10, 0, 0)
                    tv.layoutParams = params

                    layoutList.addView(tv)
                }

            } catch (_: NumberFormatException) {
                // Nếu nhập ký tự không phải số
                showError()
            }
        }
    }

    private fun showError() {
        tvError.visibility = TextView.VISIBLE
        layoutList.removeAllViews() // không hiển thị danh sách khi lỗi
    }
}
