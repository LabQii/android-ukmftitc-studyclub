package pemberkel9.smartfinance.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pemberkel9.smartfinance.R
import pemberkel9.smartfinance.database.Kategori
import kotlinx.android.synthetic.main.item_wallet.view.*
import java.text.DecimalFormat
import java.text.NumberFormat


class KategoriAdapter(walletEvents: walletEvents) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {

    private var todoList: List<Kategori> = arrayListOf()
    private var filteredTodoList: List<Kategori> = arrayListOf()
    private val listener: walletEvents = walletEvents

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredTodoList[position], listener)

    }

    override fun getItemCount(): Int = filteredTodoList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(kategori: Kategori, listener: walletEvents) {
            itemView.tvTitleWallet.text = kategori.judul
            itemView.tvDescWallet.text = kategori.deskripsi

            try {
                val formatter: NumberFormat = DecimalFormat("#,###")
                val saldoValue: Int? = kategori.uang.toInt()
                itemView.tvSumWallet.text = "Rp. " + formatter.format(saldoValue)
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }

            itemView.ivMenu.setOnClickListener {
                listener.onMenuClicked(kategori)
            }

            if (kategori.status == 1){
                itemView.swWallet.isChecked = true
            }else{
                itemView.swWallet.isChecked = false
            }

            itemView.swWallet.setOnCheckedChangeListener({ buttonView, isChecked ->
                if (isChecked){
                    itemView.swWallet.isChecked = true
                    listener.onSwitchClicked(kategori,true)
                }else{
                    itemView.swWallet.isChecked = false
                    listener.onSwitchClicked(kategori,false)
                }
            })

        }

    }

    fun setAllTodoItems(todoItems: List<Kategori>) {
        this.todoList = todoItems
        this.filteredTodoList = todoItems
        notifyDataSetChanged()
    }

    interface walletEvents{
        fun onMenuClicked(kategori: Kategori)
        fun onSwitchClicked(kategori: Kategori, ischeck: Boolean)
    }

}