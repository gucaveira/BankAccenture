package com.bankaccenture.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bankaccenture.R
import com.bankaccenture.model.Transaction
import com.bankaccenture.ui.extensions.formatDataForBrazilian
import com.bankaccenture.ui.extensions.formatForCoinBrazilian
import kotlinx.android.synthetic.main.item_transacoes.view.*

class HomeRecyclerAdapter(
    Transactions: List<Transaction> = listOf(),
    var onItemClickListener: (Transaction: Transaction) -> Unit = {}
) :
    RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    private val transactions = Transactions.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : HomeRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transacoes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: HomeRecyclerAdapter.ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    fun add(transactions: List<Transaction>) {
        notifyItemRangeRemoved(0, this.transactions.size)
        this.transactions.clear()
        this.transactions.addAll(transactions)
        notifyItemRangeInserted(0, this.transactions.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var transaction: Transaction
        private val title by lazy { itemView.item_transaction_title }
        private val desc by lazy { itemView.item_transaction_desc }
        private val date by lazy { itemView.item_transaction_date }
        private val value by lazy { itemView.item_transaction_value }

        init {
            itemView.setOnClickListener {
                if (::transaction.isInitialized) {
                    onItemClickListener(transaction)
                }
            }
        }

        fun bind(Transaction: Transaction) {
            title.text = Transaction.title
            desc.text = Transaction.desc
            date.text = Transaction.date?.formatDataForBrazilian()
            value.text = Transaction.value?.formatForCoinBrazilian()
        }
    }
}