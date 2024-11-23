package com.grupo05.alarmedica.controller

import com.grupo05.alarmedica.model.Medicamento
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo05.alarmedica.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdicionar: Button
    private lateinit var editTextNome: EditText
    private lateinit var editTextDosagem: EditText
    private lateinit var editTextFrequencia: EditText
    private lateinit var rvMedicamentos: RecyclerView

    private lateinit var medicamentoAdapter: MedicamentoAdapter
    private val medicamentosList = mutableListOf<Medicamento>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização dos elementos da UI
        btnAdicionar = findViewById(R.id.btnAdicionar)
        editTextNome = findViewById(R.id.editTextNome)
        editTextDosagem = findViewById(R.id.editTextDosagem)
        editTextFrequencia = findViewById(R.id.editTextFrequencia)
        rvMedicamentos = findViewById(R.id.rvMedicamentos)

        // Configuração do RecyclerView
        rvMedicamentos.layoutManager = LinearLayoutManager(this)
        medicamentoAdapter = MedicamentoAdapter(medicamentosList)
        rvMedicamentos.adapter = medicamentoAdapter

        // Lógica do botão Adicionar
        btnAdicionar.setOnClickListener {
            // Captura dos dados inseridos nos campos
            val nomeMedicamento = editTextNome.text.toString().trim()
            val dosagemMedicamento = editTextDosagem.text.toString().trim()
            val frequenciaMedicamento = editTextFrequencia.text.toString().trim()

            // Validação simples dos dados
            if (nomeMedicamento.isNotEmpty() && dosagemMedicamento.isNotEmpty() && frequenciaMedicamento.isNotEmpty()) {
                // Criação de um novo objeto Medicamento
                val medicamento = Medicamento(nomeMedicamento, dosagemMedicamento, frequenciaMedicamento)

                // Adicionar o medicamento à lista
                medicamentosList.add(medicamento)

                // Notifica o adapter para atualizar a RecyclerView
                medicamentoAdapter.notifyDataSetChanged()
                medicamentoAdapter.notifyItemInserted(medicamentosList.size - 1)


                // Limpa os campos de entrada
                editTextNome.text.clear()
                editTextDosagem.text.clear()
                editTextFrequencia.text.clear()

                // Exibe uma mensagem de sucesso
                Toast.makeText(this, "Medicamento adicionado!", Toast.LENGTH_SHORT).show()
            } else {
                // Exibe uma mensagem de erro caso algum campo esteja vazio
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
