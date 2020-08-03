package com.bankaccenture.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bankaccenture.R
import com.bankaccenture.model.Transacao
import com.bankaccenture.ui.extensions.formataDataParaBrasileiro
import com.bankaccenture.ui.extensions.formataParaMoedaBrasileira
import kotlinx.android.synthetic.main.item_transacoes.view.*

class HomeListaAdapter(
    private val context: Context,
    val trasacoes: List<Transacao> = listOf(),
    var onItemClickListener: (transacao: Transacao) -> Unit = {}
) :
    RecyclerView.Adapter<HomeListaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListaAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_transacoes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trasacoes.size
    }

    override fun onBindViewHolder(holder: HomeListaAdapter.ViewHolder, position: Int) {
        val trasacao = trasacoes[position]
        holder.vincula(trasacao)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var transacao: Transacao
        private val title by lazy { itemView.item_transacoes_descricao }
        private val desc by lazy { itemView.item_transacoes_data }
        private val date by lazy { itemView.item_transacoes_data }
        private val value by lazy { itemView.item_transacoes_data }

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
            date.text = Transacao.date.formataDataParaBrasileiro()
            value.text = Transacao.value.formataParaMoedaBrasileira()
        }
    }
}