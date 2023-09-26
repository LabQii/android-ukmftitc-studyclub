package pemberkel9.smartfinance.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.database.Kategori
import pemberkel9.smartfinance.util.PreferenceUtil
import pemberkel9.smartfinance.viewmodel.KategoriViewModel
import kotlinx.android.synthetic.main.activity_tambah_kategori.*
import kotlinx.android.synthetic.main.activity_tambah_kategori.ivBack
import kotlinx.android.synthetic.main.activity_transaksi.*

class TambahKategoriActivity : BaseActivity() {

    /*
    * Init view model
    * */
    private lateinit var kategoriViewModel: KategoriViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kategori)

        kategoriViewModel = ViewModelProviders.of(this).get(KategoriViewModel::class.java)

        // set white status bar
        transparentStatusBar()

        btnCreate.setOnClickListener {
            saveWallet()
        }

        ivBack.setOnClickListener {
            finish()
        }
    }

    /*
    * save kategori to room database and re-count saldo
    * */
    private fun saveWallet(){
        if (validateFields()){
            val getSaldo: String = etSaldo.text.toString()
            val saldoTotal: Int? = PreferenceUtil.getPref(applicationContext)?.getInt(PreferenceUtil.SALDO, 0)?.plus(getSaldo.toInt())
            if (saldoTotal != null) {
                PreferenceUtil.getEditor(applicationContext)?.putInt(PreferenceUtil.SALDO, saldoTotal)?.commit()
            }
            val kategori = Kategori(id = null, judul = etTitle.text.toString(), deskripsi = etDesc.text.toString(), uang = getSaldo, status = 1)
            kategoriViewModel.saveTodo(kategori)
            finish()
        }
    }

    /**
     * Validation of EditText
     * */
    private fun validateFields(): Boolean {
        if (etTitle.text.isEmpty()) {
            etTitle.error = getString(R.string.pleaseEnterTitle)
            etTitle.requestFocus()
            return false
        }
        if (etDesc.text.isEmpty()) {
            etDesc.error = getString(R.string.pleaseEnterDesc)
            etDesc.requestFocus()
            return false
        }
        if (etSaldo.text.isEmpty()) {
            etSaldo.error = getString(R.string.pleaseEnterSaldo)
            etSaldo.requestFocus()
            return false
        }
        if (etSaldo.text.toString().equals("0")){
            etSaldo.error = getString(R.string.pleaseEnterSaldo)
            etSaldo.requestFocus()
            return false
        }
        return true
    }


}