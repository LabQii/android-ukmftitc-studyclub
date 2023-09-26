package pemberkel9.smartfinance.repository

import android.app.Application
import androidx.lifecycle.LiveData
import pemberkel9.smartfinance.database.SmartFinanceDatabase
import pemberkel9.smartfinance.database.Transaksi
import pemberkel9.smartfinance.database.Kategori
import pemberkel9.smartfinance.database.KategoriDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class KategoriRepository(application: Application) {

    private val kategoriDao: KategoriDao
    private val allWallets: LiveData<List<Kategori>>
    private val allWalletsWhereStatus: LiveData<List<Kategori>>
    private val allTransaksi: LiveData<List<Transaksi>>

    init {
        val database = SmartFinanceDatabase.getInstance(application.applicationContext)
        kategoriDao = database!!.walletDao()
        allTransaksi = kategoriDao.getAllTransaction()
        allWallets = kategoriDao.getAllTodoList()
        allWalletsWhereStatus = kategoriDao.getWalletActive("1")
    }

    fun saveWallet(kategori: Kategori) = runBlocking {
        this.launch(Dispatchers.IO) {
            kategoriDao.saveWallet(kategori)
        }
    }

    fun deleteWallets(kategori: Kategori) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                kategoriDao.deleteWallet(kategori)
            }
        }
    }

    fun updateWalletStatus(status: Int, kategori: Kategori) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                kategori.id?.let { kategoriDao.updateWalletStatus(status, it) }
            }
        }
    }

    fun updateWalletSaldo(saldo: String, kategori: Kategori) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                kategori.id?.let { kategoriDao.updateWalletSaldo(saldo, it) }
            }
        }
    }

    fun getWalletList(): LiveData<List<Kategori>> {
        return allWallets
    }

    fun getWalletActive(): LiveData<List<Kategori>> {
        return allWalletsWhereStatus
    }

    fun saveTransaction(transaksi: Transaksi) = runBlocking {
        this.launch(Dispatchers.IO) {
            kategoriDao.saveTransaction(transaksi)
        }
    }

    fun getTransactionList(): LiveData<List<Transaksi>> {
        return allTransaksi
    }

    fun deleteTrans(transaksi: Transaksi) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                kategoriDao.deleteTrans(transaksi)
            }
        }
    }

}