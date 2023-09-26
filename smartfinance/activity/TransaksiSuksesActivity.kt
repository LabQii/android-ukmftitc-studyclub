package pemberkel9.smartfinance.activity

import android.annotation.SuppressLint
import android.os.Bundle
import pemberkel9.smartfinance.R
import kotlinx.android.synthetic.main.activity_transaksi_sukses.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class TransaksiSuksesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_sukses)

        transparentStatusBar()
        initView()
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun initView(){
        val intent = intent
        if (intent != null){
            val type: String = intent.getStringExtra("type").toString()
            val saldo: String = intent.getStringExtra("saldo").toString()

            // set text color base on type transaction
            if (type == "Uang Keluar"){
                tvType.setTextColor(getColor(R.color.red_bg))
                tvValue.setTextColor(getColor(R.color.red_bg))
            }else{
                tvType.setTextColor(getColor(R.color.color_black_text))
                tvValue.setTextColor(getColor(R.color.color_black_text))
            }
            tvType.text = type

            // auto number formatting
            val formatter: NumberFormat = DecimalFormat("#,###")
            val saldoValue: Int? = saldo.toInt()
            tvValue.text = "Rp. " + formatter.format(saldoValue)

            // get date now
            val sdf = SimpleDateFormat("dd MMM yyyy")
            val currentDate = sdf.format(Date())
            tvDate2.text = currentDate
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

}