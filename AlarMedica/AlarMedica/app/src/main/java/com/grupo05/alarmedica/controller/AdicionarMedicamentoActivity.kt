package com.grupo05.alarmedica.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.grupo05.alarmedica.R
import com.grupo05.alarmedica.model.Medicamento

class AdicionarMedicamentoActivity : AppCompatActivity() {

    private lateinit var etNomeMedicamento: EditText
    private lateinit var etFrequencia: EditText
    private lateinit var etDose: EditText
    private lateinit var etPeriodoUso: EditText
    private lateinit var btnSalvarMedicamento: Button
    private lateinit var database: FirebaseDatabase
    private lateinit var referenciaMedicamentos: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var usuarioId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_medicamento)

        // Inicialização dos componentes
        auth = FirebaseAuth.getInstance()
        usuarioId = auth.currentUser?.uid.toString()
        database = FirebaseDatabase.getInstance()
        referenciaMedicamentos = database.reference.child("usuarios").child(usuarioId).child("medicamentos")

        // Referências dos campos de entrada
        etNomeMedicamento = findViewById(R.id.et_nome_medicamento)
        etFrequencia = findViewById(R.id.et_frequencia)
        etDose = findViewById(R.id.et_dose)
        etPeriodoUso = findViewById(R.id.et_periodo_uso)
        btnSalvarMedicamento = findViewById(R.id.btn_salvar_medicamento)

        // Evento de clique no botão "Salvar"
        btnSalvarMedicamento.setOnClickListener {
            val nomeMedicamento = etNomeMedicamento.text.toString().trim()
            val frequencia = etFrequencia.text.toString().trim()
            val dose = etDose.text.toString().trim()
            val periodoUso = etPeriodoUso.text.toString().trim()

            // Validação dos campos
            if (nomeMedicamento.isNotEmpty() && frequencia.isNotEmpty() && dose.isNotEmpty() && periodoUso.isNotEmpty()) {
                salvarMedicamento(nomeMedicamento, frequencia, dose, periodoUso)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Função para salvar o medicamento no Firebase
    private fun salvarMedicamento(nome: String, frequencia: String, dose: String, periodo: String) {
        val idMedicamento = referenciaMedicamentos.push().key // Gerar um ID único para o medicamento

        if (idMedicamento != null) {
            val medicamento = Medicamento(nome, frequencia, dose, periodo)

            // Salvar o medicamento no Firebase
            referenciaMedicamentos.child(idMedicamento).setValue(medicamento)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Medicamento adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                        finish() // Voltar para a tela principal após salvar
                    } else {
                        Toast.makeText(this, "Falha ao adicionar medicamento", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
