package com.fiat.barbershop

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fiat.barbershop.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private fun mensagem(view: View, mensagem: String) {
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF3D0247"))
        snackbar.setTextColor(Color.parseColor("#FFFFFFFF"))
        snackbar.show()
    }

    private fun navegarPraHome(nome: String) {
        val intent = Intent(/* packageContext = */ this, /* cls = */ activity_home::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btLogin.setOnClickListener { it ->
            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()

            when {
                nome.isEmpty() -> {
                    mensagem(it, "Coloque seu nome")
                }
                senha.isEmpty() -> {
                    mensagem(it, "Preencha a senha")
                }
                senha.length <= 5 -> {
                    mensagem(it, "A senha precisa ter pelo menos 6 caracteres")
                }
                else -> {
                    navegarPraHome(nome)
                }
            }
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

class activity_home {

}
