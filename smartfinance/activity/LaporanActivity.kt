package pemberkel9.smartfinance.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.adapter.TransaksiAdapter
import pemberkel9.smartfinance.database.Transaksi
import pemberkel9.smartfinance.viewmodel.KategoriViewModel
import kotlinx.android.synthetic.main.activity_laporan.*
//import kotlinx.android.synthetic.main.toolbar_view.*

class LaporanActivity : BaseActivity(), TransaksiAdapter.transactionEvent {

    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var kategoriViewModel: KategoriViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        transparentStatusBar()

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView(){

        //Setting up RecyclerView
        rvTransaction.layoutManager = LinearLayoutManager(this)
        transaksiAdapter = TransaksiAdapter(this)
        rvTransaction.adapter = transaksiAdapter

        //Setting up ViewModel and LiveData
        kategoriViewModel = ViewModelProviders.of(this).get(KategoriViewModel::class.java)
        kategoriViewModel.getAllTransaction().observe(this, Observer {
            transaksiAdapter.setAllTransItems(it)
        })

        ivBack.setOnClickListener {
            finish()
        }

    }

    override fun onDeleteClicked(transaksi: Transaksi) {
        kategoriViewModel.deleteTrans(transaksi)
    }

}