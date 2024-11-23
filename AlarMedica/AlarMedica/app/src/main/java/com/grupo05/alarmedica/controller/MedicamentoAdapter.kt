package com.grupo05.alarmedica.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo05.alarmedica.R
import com.grupo05.alarmedica.model.Medicamento

class MedicamentoAdapter(private val medicamentosList: List<Medicamento>) :
    RecyclerView.Adapter<MedicamentoAdapter.MedicamentoViewHolder>() {

    // Cria um ViewHolder para inflar o layout do item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicamento, parent, false)
        return MedicamentoViewHolder(view)
    }

    // Liga os dados do medicamento aos componentes do layout
    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        val medicamento = medicamentosList[position]
        holder.bind(medicamento)
    }

    // Retorna o tamanho da lista
    override fun getItemCount(): Int {
        return medicamentosList.size
    }

    // Classe ViewHolder para armazenar as referências dos elementos de layout
    inner class MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNomeMedicamento: TextView = itemView.findViewById(R.id.tvNomeMedicamento)
        private val tvDosagem: TextView = itemView.findViewById(R.id.tvDosagem)
        private val tvFrequencia: TextView = itemView.findViewById(R.id.tvFrequencia)

        fun bind(medicamento: Medicamento) {
            tvNomeMedicamento.text = medicamento.nome
            tvDosagem.text = medicamento.doses
            tvFrequencia.text = medicamento.frequencia
        }
    }
}

