package pemberkel9.smartfinance.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KategoriDao {

    @Insert
    suspend fun saveWallet(kategori: Kategori)

    @Delete
    suspend fun deleteWallet(kategori: Kategori)

    @Delete
    suspend fun deleteTrans(transaksi: Transaksi)

    @Query("SELECT * FROM tb_kategori ORDER BY id DESC")
    fun getAllTodoList(): LiveData<List<Kategori>>

    @Query("SELECT * FROM tb_kategori WHERE status = :status")
    fun getWalletActive(status: String): LiveData<List<Kategori>>

    @Insert
    suspend fun saveTransaction(transaksi: Transaksi)

    @Query("SELECT * FROM tb_transaksi ORDER BY id DESC")
    fun getAllTransaction(): LiveData<List<Transaksi>>

    @Query("UPDATE tb_kategori SET uang=:uang WHERE id = :id")
    fun updateWalletSaldo(uang: String, id: Long)

    @Query("UPDATE tb_kategori SET status=:status WHERE id = :id")
    fun updateWalletStatus(status: Int, id: Long)
}