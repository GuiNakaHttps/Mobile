package com.fiat.barbershop.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.fiat.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class Agendamento : AppCompatActivity() {
    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dia = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth.toString()
            val mes = if (monthOfYear < 9) "0${monthOfYear + 1}" else (monthOfYear + 1).toString()

            data = "$dia/$mes/$year"
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val minuto = if (minute < 10) "0$minute" else minute.toString()
            hora = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener {
            val barbeiro1 = binding.Barbeiro1
            val barbeiro2 = binding.Barbeiro2
            val barbeiro3 = binding.Barbeiro3

            when {
                hora.isEmpty() -> {
                    mensagem(it, "Preencha o horário", "#FF0000")
                }
                hora < "08:00" || hora > "19:00" -> {
                    mensagem(it, "Fechado", "#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it, "Coloque uma data", "#FF0000")
                }
                (barbeiro1.isChecked || barbeiro2.isChecked || barbeiro3.isChecked) && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, "Agendamento realizado com sucesso", "#FF03DAC5")
                }
                else -> {
                    mensagem(it, "Escolha um Profissional", "#FF0000")
                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String) {
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
}
