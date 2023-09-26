package pemberkel9.smartfinance.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.database.Transaksi
import kotlinx.android.synthetic.main.item_transaction.view.*
import kotlinx.android.synthetic.main.item_wallet.view.tvDescWallet
import kotlinx.android.synthetic.main.item_wallet.view.tvTitleWallet
import java.text.DecimalFormat
import java.text.NumberFormat

class TransaksiAdapter(transaction: transactionEvent) : RecyclerView.Adapter<TransaksiAdapter.ViewHolder>() {

    private var todoList: List<Transaksi> = arrayListOf()
    private var filteredTodoList: List<Transaksi> = arrayListOf()
    private val listener: transactionEvent = transaction

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredTodoList[position], listener)

    }

    override fun getItemCount(): Int = filteredTodoList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(transaksi: Transaksi, listener: transactionEvent) {
            itemView.tvTitleWallet.text = transaksi.type_kategori

            val formatter: NumberFormat = DecimalFormat("#,###")
            val saldoValue: Int? = transaksi.nominal.toInt()
            itemView.tvDescWallet.text = transaksi.type + " " + formatter.format(saldoValue) + " " + "(" + transaksi.keterangan + ")"

            if (transaksi.type.equals("Uang Keluar")){
                itemView.tvDescWallet.setTextColor(ContextCompat.getColor(itemView.context, R.color.red_bg))
            }

            itemView.ivDelete.setOnClickListener {
                listener.onDeleteClicked(transaksi)
            }

        }

    }

    fun setAllTransItems(transaksi: List<Transaksi>) {
        this.todoList = transaksi
        this.filteredTodoList = transaksi
        notifyDataSetChanged()
    }

    interface transactionEvent{
        fun onDeleteClicked(transaksi: Transaksi)
    }

}