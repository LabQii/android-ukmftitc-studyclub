package pemberkel9.smartfinance.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.pojo.User
import pemberkel9.smartfinance.util.AppConstant
import pemberkel9.smartfinance.util.PreferenceUtil
import pemberkel9.smartfinance.util.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import pemberkel9.smartfinance.fragment.ProfilFragment
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transparentStatusBar()

        initView()
    }

    private fun initView(){

        llTransaction.setOnClickListener(this)
        llWallet.setOnClickListener(this)
        llReport.setOnClickListener(this)
        tvDetailDesc.setOnClickListener(this)
        ivAvatar.setOnClickListener(this)

        // set default saldo 2.500.000
        val saldoValue: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.SALDO, 0)
        if (saldoValue == 0){
            PreferenceUtil.getEditor(application)?.putInt(PreferenceUtil.SALDO, AppConstant.SALDO_MASUK)?.commit()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        // set default saldo 2.500.000
        val saldoValue: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.SALDO, 0)
        if (saldoValue == 0){
            PreferenceUtil.getEditor(application)?.putInt(PreferenceUtil.SALDO, AppConstant.SALDO_MASUK)?.commit()
        }

        val formatter: NumberFormat = DecimalFormat("#,###")

        // set uang masuk and uang keluar dari transaction
        val uangMasuk: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.UANG_MASUK, 0)
        val uangKeluar: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.UANG_KELUAR, 0)
        tvSumIncome.text = "Rp. " + formatter.format(uangMasuk)
        tvSumOutcome.text = "Rp. " + formatter.format(uangKeluar)

        // set deskripsi dengan nama yang sudah login
        if (SessionManager.getProfile(application) == null) return
        val own: User? = SessionManager.getProfile(application)

        // set format angka untuk saldo saat ini
        tvTitleSuccess.text = "Rp. " +  formatter.format(saldoValue)
    }

    override fun onClick(p0: View?) {
        when (p0) {

            llTransaction -> {
                startActivity(Intent(this, TransaksiActivity::class.java))
            }
            llWallet -> {
                startActivity(Intent(this, KategoriActivity::class.java))
            }
            llReport -> {
                startActivity(Intent(this, LaporanActivity::class.java))
            }
            tvDetailDesc -> {
                startActivity(Intent(this, LaporanActivity::class.java))
            }
            ivAvatar -> {
                startActivity(Intent(this, MenuActivity::class.java))
            }
        }
    }
}