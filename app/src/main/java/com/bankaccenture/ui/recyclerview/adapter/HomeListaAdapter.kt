package com.bankaccenture.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bankaccenture.R
import com.bankaccenture.model.Transacao
import com.bankaccenture.ui.extensions.formataParaMoedaBrasileira
import kotlinx.android.synthetic.main.item_transacoes.view.*

class HomeListaAdapter(
    transacoes: List<Transacao> = listOf(),
    var onItemClickListener: (transacao: Transacao) -> Unit = {}
) :
    RecyclerView.Adapter<HomeListaAdapter.ViewHolder>() {

    private val transacoes = transacoes.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transacoes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transacoes.size
    }

    override fun onBindViewHolder(holder: HomeListaAdapter.ViewHolder, position: Int) {
        val trasacao = transacoes[position]
        holder.vincula(trasacao)
    }

    fun add(transacoes: List<Transacao>) {
        notifyItemRangeRemoved(0, this.transacoes.size)
        this.transacoes.clear()
        this.transacoes.addAll(transacoes)
        notifyItemRangeInserted(0, this.transacoes.size)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var transacao: Transacao
        private val title by lazy { itemView.item_transacoes_titulo }
        private val desc by lazy { itemView.item_transacoes_descricao }
        private val date by lazy { itemView.item_transacoes_data }
        private val value by lazy { itemView.item_transacoes_valor }

        init {
            itemView.setOnClickListener {
                if (::transacao.isInitialized) {
                    onItemClickListener(transacao)
                }
            }
        }

        fun vincula(Transacao: Transacao) {
            title.text = Transacao.title
            desc.text = Transacao.desc
            date.text = Transacao.date//.formataDataParaBrasileiro()
            value.text = Transacao.value?.formataParaMoedaBrasileira()
        }
    }
}