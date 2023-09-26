package pemberkel9.smartfinance.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.adapter.KategoriAdapter
import pemberkel9.smartfinance.database.Kategori
import pemberkel9.smartfinance.viewmodel.KategoriViewModel
import kotlinx.android.synthetic.main.activity_kategori.*

class KategoriActivity : BaseActivity(), KategoriAdapter.walletEvents {

    private lateinit var kategoriAdapter: KategoriAdapter
    private lateinit var kategoriViewModel: KategoriViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        // set status bar color white
        transparentStatusBar()
        initView()
    }

    private fun initView(){

        //Setting up RecyclerView
        rvWallet.layoutManager = LinearLayoutManager(this)
        kategoriAdapter = KategoriAdapter(this)
        rvWallet.adapter = kategoriAdapter

        //Setting up ViewModel and LiveData
        kategoriViewModel = ViewModelProviders.of(this).get(KategoriViewModel::class.java)
        kategoriViewModel.getAllTodoList().observe(this, Observer {
            kategoriAdapter.setAllTodoItems(it)
        })

        ivBack.setOnClickListener {
            finish()
        }

        tvTitleWallet.setOnClickListener{
            startActivity(Intent(this, TambahKategoriActivity::class.java))
        }
    }

    /*
    * listener for detele walled
    * */
    override fun onMenuClicked(kategori: Kategori) {
        kategoriViewModel.deleteTodo(kategori)
    }

    /*
    * listener for udpate kategori status active or not
    * */
    override fun onSwitchClicked(kategori: Kategori, ischeck: Boolean) {
        if (ischeck){
            kategoriViewModel.udpateWalletStatus(1, kategori)
        }else{
            kategoriViewModel.udpateWalletStatus(0, kategori)
        }
    }

}