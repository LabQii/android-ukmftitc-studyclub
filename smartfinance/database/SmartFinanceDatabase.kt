package pemberkel9.smartfinance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Kategori::class, Transaksi::class], version = 3, exportSchema = false)
abstract class SmartFinanceDatabase : RoomDatabase() {

    abstract fun walletDao(): KategoriDao

    companion object {
        private var INSTANCE: SmartFinanceDatabase? = null

        fun getInstance(context: Context): SmartFinanceDatabase? {
            if (INSTANCE == null) {
                synchronized(SmartFinanceDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                            SmartFinanceDatabase::class.java,
                            "sf_db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }

}