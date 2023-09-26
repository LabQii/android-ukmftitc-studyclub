package pemberkel9.smartfinance.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.database.Transaksi
import pemberkel9.smartfinance.database.Kategori
import pemberkel9.smartfinance.util.PreferenceUtil
import pemberkel9.smartfinance.viewmodel.KategoriViewModel
import kotlinx.android.synthetic.main.activity_transaksi.*
import kotlinx.android.synthetic.main.activity_transaksi.ivBack
import kotlinx.android.synthetic.main.activity_transaksi.tvTitle
import kotlinx.android.synthetic.main.activity_transaksi_sukses.*
import java.text.SimpleDateFormat
import java.util.*


class TransaksiActivity : BaseActivity() {

    private lateinit var kategoriViewModel: KategoriViewModel
    private var type: String? = null
    private var type_kategori: String? = null
    private var id_kategori: Long? = null
    private var kategori_uang: String? = null
    private var uang: String? = null
    lateinit var kategori: Kategori

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)

        // set status bar color white
        transparentStatusBar()

        //Setting up ViewModel and LiveData
        kategoriViewModel = ViewModelProviders.of(this).get(KategoriViewModel::class.java)

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView(){

        // set spinner data
        val values : Array<String> = arrayOf("Uang Masuk", "Uang Keluar")
        tvText02.adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_list_item_1,
            values
        )

        // set spinner event choose
        tvText02.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                type = adapterView.selectedItem as String?
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        //Get kategori active and set to custom spinner
        kategoriViewModel.getWalletActive().observe(this, Observer {
            val customDropDownAdapter = pemberkel9.smartfinance.adapter.NominalAdapter(this, it)
            tvText06.adapter = customDropDownAdapter
        })

        tvText06.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                kategori = adapterView.selectedItem as Kategori
                type_kategori = kategori.judul
                id_kategori = kategori.id
                kategori_uang = kategori.uang
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        ivBack.setOnClickListener {
            finish()
        }

        btnSendTrans.setOnClickListener {
            addTransaction()
        }

        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val currentDate = sdf.format(Date())
        tvTitle.text = currentDate
    }

    /*
    * Add transaction function with validate input
    * */
    private fun addTransaction(){
        if (validateFields()){

            uang = tvTitleSuccess.text.toString()

            // for update saldo to room database and preference
            if (type.equals("Uang Masuk")){

                val updateSaldo: String = kategori_uang?.toInt()?.plus(uang!!.toInt()).toString()
                kategoriViewModel.udpateWalletSaldo(updateSaldo, kategori)

                PreferenceUtil.getEditor(application)?.putInt(PreferenceUtil.UANG_MASUK, uang!!.toInt())?.commit()
                val saldoTotal: Int? = PreferenceUtil.getPref(applicationContext)?.getInt(PreferenceUtil.SALDO, 0)?.plus(uang!!.toInt())
                if (saldoTotal != null) {
                    PreferenceUtil.getEditor(applicationContext)?.putInt(PreferenceUtil.SALDO, saldoTotal)?.commit()
                }

            }else if (type.equals("Uang Keluar")){

                val updateSaldo: String = kategori_uang?.toInt()?.minus(uang!!.toInt()).toString()
                kategoriViewModel.udpateWalletSaldo(updateSaldo, kategori)

                PreferenceUtil.getEditor(application)?.putInt(PreferenceUtil.UANG_KELUAR, uang!!.toInt())?.commit()
                val saldoTotal: Int? = PreferenceUtil.getPref(applicationContext)?.getInt(PreferenceUtil.SALDO, 0)?.minus(uang!!.toInt())
                if (saldoTotal != null) {
                    PreferenceUtil.getEditor(applicationContext)?.putInt(PreferenceUtil.SALDO, saldoTotal)?.commit()
                }
            }

            // add transaction to room database and go to screen notification success
            val transaksi = Transaksi(id = null, nominal = uang!!, type = type.toString(), type_kategori = type_kategori.toString(), keterangan = tvText09.text.toString())
            kategoriViewModel.saveTransaction(transaksi)
            val intent = Intent(applicationContext, TransaksiSuksesActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("saldo", uang)
            startActivity(intent)
            finish()
        }
    }

    /*
    * Validate field input wording
    * */
    private fun validateFields(): Boolean {
        if (tvTitleSuccess.text.isEmpty()) {
            tvTitleSuccess.error = getString(R.string.pleaseEnterSaldo)
            tvTitleSuccess.requestFocus()
            return false
        }
        if (tvTitleSuccess.text.toString() == "0") {
            tvTitleSuccess.error = getString(R.string.pleaseEnterSaldo)
            tvTitleSuccess.requestFocus()
            return false
        }
        if (kategori_uang.toString() == "0") {
            tvTitleSuccess.error = getString(R.string.str_kurang)
            tvTitleSuccess.requestFocus()
            return false
        }
        if (kategori_uang.toString().isEmpty()) {
            tvTitleSuccess.error = getString(R.string.str_kurang)
            return false
        }
        return true
    }

}