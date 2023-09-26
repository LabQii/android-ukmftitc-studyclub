package pemberkel9.smartfinance.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pemberkel9.smartfinance.database.Transaksi
import pemberkel9.smartfinance.database.Kategori
import pemberkel9.smartfinance.repository.KategoriRepository

class KategoriViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: KategoriRepository = KategoriRepository(application)
    private val allKategoriList: LiveData<List<Kategori>> = repository.getWalletList()
    private val allKategoriActive: LiveData<List<Kategori>> = repository.getWalletActive()
    private val allTransaksiList: LiveData<List<Transaksi>> = repository.getTransactionList()

    fun saveTodo(kategori: Kategori) {
        repository.saveWallet(kategori)
    }

    fun deleteTodo(kategori: Kategori) {
        repository.deleteWallets(kategori)
    }

    fun udpateWalletStatus(status: Int, kategori: Kategori) {
        repository.updateWalletStatus(status, kategori)
    }

    fun udpateWalletSaldo(saldo: String, kategori: Kategori) {
        repository.updateWalletSaldo(saldo, kategori)
    }

    fun getAllTodoList(): LiveData<List<Kategori>> {
        return allKategoriList
    }

    fun getWalletActive(): LiveData<List<Kategori>> {
        return allKategoriActive
    }

    fun saveTransaction(transaksi: Transaksi) {
        repository.saveTransaction(transaksi)
    }

    fun getAllTransaction(): LiveData<List<Transaksi>> {
        return allTransaksiList
    }

    fun deleteTrans(transaksi: Transaksi) {
        repository.deleteTrans(transaksi)
    }

}