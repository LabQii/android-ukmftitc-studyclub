package pemberkel9.smartfinance.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_kategori")
@Parcelize
data class Kategori(@PrimaryKey(autoGenerate = true) val id: Long?,
                    @ColumnInfo(name = "judul") val judul: String,
                    @ColumnInfo(name = "deskripsi") val deskripsi: String,
                    @ColumnInfo(name = "uang") val uang: String,
                    @ColumnInfo(name = "status") val status: Int
) : Parcelable




