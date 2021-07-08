package com.qbo.appkea5patitas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.qbo.appkea5patitas.R
import com.qbo.appkea5patitas.databinding.ActivityLoginBinding
import com.qbo.appkea5patitas.databinding.ActivityMainBinding
import com.qbo.appkea5patitas.utilitarios.AppMensaje
import com.qbo.appkea5patitas.utilitarios.TipoMensaje

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnlogin.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)

    }

    fun validarUsuarioPassword(): Boolean{
        var respuesta  = true
        if(binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            respuesta = false
        }else if (binding.etpassword.text.toString().trim().isEmpty()){
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun autenticacion(){
        if(validarUsuarioPassword()){
            startActivity(Intent(applicationContext,
            HomeActivity::class.java))
        }else{
            AppMensaje.enviarMensaje(binding.root,
            getString(R.string.msgloginincompleto),
            TipoMensaje.ERROR)
        }
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnlogin -> autenticacion()
            R.id.btnregistrar -> startActivity(Intent(applicationContext,
                RegistroActivity::class.java))
        }
    }
}