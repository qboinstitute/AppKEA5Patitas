package com.qbo.appkea5patitas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qbo.appkea5patitas.R
import com.qbo.appkea5patitas.apirest.response.ResponseLogin
import com.qbo.appkea5patitas.databinding.ActivityLoginBinding
import com.qbo.appkea5patitas.databinding.ActivityMainBinding
import com.qbo.appkea5patitas.utilitarios.AppMensaje
import com.qbo.appkea5patitas.utilitarios.TipoMensaje
import com.qbo.appkea5patitas.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        binding.btnlogin.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)
        authViewModel.responseLogin.observe(this, Observer {
                response -> obtenerDatosLogin(response)
        })
    }

    private fun obtenerDatosLogin(response: ResponseLogin) {
        binding.btnregistrar.isEnabled = true
        binding.btnlogin.isEnabled = true
        if(response.rpta){
            startActivity(Intent(applicationContext,
                HomeActivity::class.java))
        }else{
            AppMensaje.enviarMensaje(binding.root,
                response.mensaje, TipoMensaje.ERROR)
        }
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
        binding.btnregistrar.isEnabled = false
        binding.btnlogin.isEnabled = false
        if(validarUsuarioPassword()){
            authViewModel.autenticarUsuario(binding.etusuario.text.toString(),
                binding.etpassword.text.toString())
        }else{
            AppMensaje.enviarMensaje(binding.root,
            getString(R.string.msgloginincompleto),
            TipoMensaje.ERROR)
            binding.btnregistrar.isEnabled = true
            binding.btnlogin.isEnabled = true
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