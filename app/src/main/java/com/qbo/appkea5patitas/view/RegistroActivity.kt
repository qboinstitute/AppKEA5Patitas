package com.qbo.appkea5patitas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qbo.appkea5patitas.R
import com.qbo.appkea5patitas.apirest.response.ResponseRegistro
import com.qbo.appkea5patitas.databinding.ActivityRegistroBinding
import com.qbo.appkea5patitas.utilitarios.AppMensaje
import com.qbo.appkea5patitas.utilitarios.TipoMensaje
import com.qbo.appkea5patitas.viewmodel.AuthViewModel

class RegistroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        binding.btnirlogin.setOnClickListener(this)
        binding.btnregistrarusuario.setOnClickListener(this)
        authViewModel.responseRegistro.observe(this, Observer {
            response-> obtenerResultadoRegistro(response)
        })
    }

    private fun obtenerResultadoRegistro(response: ResponseRegistro) {
        if(response.rpta){
            setearControles()
        }
        AppMensaje.enviarMensaje(binding.root,
            response.mensaje, TipoMensaje.EXITO)
        binding.btnregistrarusuario.isEnabled = true
        binding.btnirlogin.isEnabled = true
    }

    private fun setearControles() {
        binding.nombreregistro.setText("")
        binding.apellidoregistro.setText("")
        binding.emailregistro.setText("")
        binding.celularregistro.setText("")
        binding.usuarioregistro.setText("")
        binding.passwordregistro.setText("")
        binding.passwordrep.setText("")
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnregistrarusuario -> registrarUsuario()
            R.id.btnirlogin-> startActivity(
                Intent(applicationContext, LoginActivity::class.java))
        }
    }

    private fun registrarUsuario() {
        binding.btnregistrarusuario.isEnabled = false
        binding.btnirlogin.isEnabled = false
        if(validarFormulario()){
            authViewModel.registrarUsuario(binding.nombreregistro.text.toString(),
            binding.apellidoregistro.text.toString(), binding.emailregistro.text.toString(),
            binding.celularregistro.text.toString(), binding.usuarioregistro.text.toString(),
            binding.passwordregistro.text.toString())
        }else{
            binding.btnregistrarusuario.isEnabled = true
            binding.btnirlogin.isEnabled = true
        }
    }
    private fun validarFormulario() : Boolean{
        var respuesta = true
        var mensaje = ""
        when{
            binding.nombreregistro.text.toString().trim().isEmpty() ->{
                binding.nombreregistro.isFocusableInTouchMode = true
                binding.nombreregistro.requestFocus()
                mensaje = "Ingrese su nombre"
                respuesta = false
            }
            binding.apellidoregistro.text.toString().trim().isEmpty() ->{
                binding.apellidoregistro.isFocusableInTouchMode = true
                binding.apellidoregistro.requestFocus()
                mensaje = "Ingrese su apellido"
                respuesta = false
            }
            binding.emailregistro.text.toString().trim().isEmpty() ->{
                binding.emailregistro.isFocusableInTouchMode = true
                binding.emailregistro.requestFocus()
                mensaje = "Ingrese su email"
                respuesta = false
            }
            binding.celularregistro.text.toString().trim().isEmpty() ->{
                binding.celularregistro.isFocusableInTouchMode = true
                binding.celularregistro.requestFocus()
                mensaje = "Ingrese su celular"
                respuesta = false
            }
            binding.usuarioregistro.text.toString().trim().isEmpty() ->{
                binding.usuarioregistro.isFocusableInTouchMode = true
                binding.usuarioregistro.requestFocus()
                mensaje = "Ingrese su cuenta de usuario"
                respuesta = false
            }
            binding.passwordregistro.text.toString().trim().isEmpty() ->{
                binding.passwordregistro.isFocusableInTouchMode = true
                binding.passwordregistro.requestFocus()
                mensaje = "Ingrese su password"
                respuesta = false
            }
            binding.passwordregistro.text.toString().trim().isNotEmpty() ->{
                if(binding.passwordregistro.text.toString() != binding.passwordrep.text.toString()){
                    binding.passwordrep.isFocusableInTouchMode = true
                    binding.passwordrep.requestFocus()
                    mensaje = "Password no coincide"
                    respuesta = false
                }
            }
        }
        if (!respuesta) AppMensaje.enviarMensaje(binding.root, mensaje,
            TipoMensaje.ERROR)
        return respuesta
    }
}