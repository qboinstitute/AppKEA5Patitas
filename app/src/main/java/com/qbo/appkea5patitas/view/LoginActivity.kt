package com.qbo.appkea5patitas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qbo.appkea5patitas.R
import com.qbo.appkea5patitas.apirest.response.ResponseLogin
import com.qbo.appkea5patitas.databinding.ActivityLoginBinding
import com.qbo.appkea5patitas.databinding.ActivityMainBinding
import com.qbo.appkea5patitas.db.entity.PersonaEntity
import com.qbo.appkea5patitas.utilitarios.AppMensaje
import com.qbo.appkea5patitas.utilitarios.Constantes
import com.qbo.appkea5patitas.utilitarios.SharedPreferencesManager
import com.qbo.appkea5patitas.utilitarios.TipoMensaje
import com.qbo.appkea5patitas.viewmodel.AuthViewModel
import com.qbo.appkea5patitas.viewmodel.PersonaViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)

        if(verificarCheckRecordarDatos()){
            binding.cbrecordar.isChecked = true
            binding.etusuario.isEnabled = false
            binding.etpassword.isEnabled = false
            binding.cbrecordar.text = "Quitar el check para ingresar con otro usuario"
            personaViewModel.obtener()
                .observe(this, Observer { persona ->
                    persona.let {
                        binding.etusuario.setText(it.usuario)
                        binding.etpassword.setText(persona.password)
                    }
                })
        }else{
            personaViewModel.eliminartodo()
        }
        binding.cbrecordar.setOnClickListener(this)
        binding.btnlogin.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)
        authViewModel.responseLogin.observe(this, Observer {
                response -> obtenerDatosLogin(response)
        })
    }

    fun verificarCheckRecordarDatos(): Boolean{
        return SharedPreferencesManager().getSomeBooleanValue(Constantes().PREF_RECORDAR)
    }

    private fun obtenerDatosLogin(response: ResponseLogin) {
        binding.btnregistrar.isEnabled = true
        binding.btnlogin.isEnabled = true
        if(response.rpta){
            val personaEntity = PersonaEntity(
                response.idpersona.toInt(), response.apellidos, response.celular,
                response.email, response.esvoluntario, response.nombres,
                response.usuario, response.password)
            if(verificarCheckRecordarDatos()){
                personaViewModel.actualizar(personaEntity)
            }else{
                personaViewModel.insertar(personaEntity)
                if(binding.cbrecordar.isChecked){
                    SharedPreferencesManager().setSomeBooleanValue(
                        Constantes().PREF_RECORDAR, true)
                }
            }
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
            R.id.cbrecordar -> setearValoresRecordar(vista)
        }
    }

    private fun setearValoresRecordar(vista: View) {
        if(vista is CheckBox){
            val checked = vista.isChecked
            if(!checked){
                if(verificarCheckRecordarDatos()){
                    SharedPreferencesManager().deletePreference(
                        Constantes().PREF_RECORDAR)
                    personaViewModel.eliminartodo()
                    binding.etusuario.isEnabled = true
                    binding.etpassword.isEnabled = true
                    binding.cbrecordar.text = getString(R.string.recordarlogin)
                }
            }
        }
    }
}