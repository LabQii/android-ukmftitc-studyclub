package pemberkel9.smartfinance.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_transaksi")
@Parcelize
data class Transaksi(@PrimaryKey(autoGenerate = true) val id: Long?,
                     @ColumnInfo(name = "nominal") val nominal: String,
                     @ColumnInfo(name = "type") val type: String,
                     @ColumnInfo(name = "type_kategori") val type_kategori: String,
                     @ColumnInfo(name = "keterangan") val keterangan: String
) : Parcelable


