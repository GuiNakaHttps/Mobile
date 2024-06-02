package com.fiat.barbershop.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.fiat.barbershop.R
import com.fiat.barbershop.adapter.Servicos
import com.fiat.barbershop.adapter.ServicosAdapter
import com.fiat.barbershop.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem-Vindo(a), $nome"
        val recyclerViewServicos = binding.recycleViewServicos
        recyclerViewServicos.layoutManager = GridLayoutManager(this, 2)
        servicosAdapter = ServicosAdapter(this, listaServicos)
        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewServicos.adapter = servicosAdapter
        getServicos()
        binding.btAgendar.setOnClickListener{
            val intent = Intent(this, Agendamento::class.java)
            intent.putExtra("nome",nome)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getServicos() {
        val servico1 = Servicos(R.drawable.img1, "Corte de Cabelo")
        listaServicos.add(servico1)

        val servico2 = Servicos(R.drawable.img2, "Corte de Barba")
        listaServicos.add(servico2)

        val servico3 = Servicos(R.drawable.img3, "Lavagem de Cabelo")
        listaServicos.add(servico3)

        val servico4 = Servicos(R.drawable.img4, "Tratamento de Cabelo")
        listaServicos.add(servico4)

        servicosAdapter.notifyDataSetChanged()
    }
}
