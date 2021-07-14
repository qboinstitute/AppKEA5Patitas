package com.qbo.appkea5patitas.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qbo.appkea5patitas.R
import com.qbo.appkea5patitas.apirest.response.ResponseRegistro
import com.qbo.appkea5patitas.databinding.FragmentVoluntarioBinding
import com.qbo.appkea5patitas.db.entity.PersonaEntity
import com.qbo.appkea5patitas.utilitarios.AppMensaje
import com.qbo.appkea5patitas.utilitarios.TipoMensaje
import com.qbo.appkea5patitas.viewmodel.MascotaViewModel
import com.qbo.appkea5patitas.viewmodel.PersonaViewModel


class VoluntarioFragment : Fragment() {

    private var _binding: FragmentVoluntarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var mascotaViewModel: MascotaViewModel
    private lateinit var personaViewModel: PersonaViewModel
    private lateinit var personaEntity: PersonaEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoluntarioBinding.inflate(inflater, container,
            false)
        mascotaViewModel = ViewModelProvider(requireActivity())
            .get(MascotaViewModel::class.java)
        personaViewModel = ViewModelProvider(requireActivity())
            .get(PersonaViewModel::class.java)

        personaViewModel.obtener()
            .observe(viewLifecycleOwner, Observer { persona ->
                persona?.let {
                    if(persona.esvoluntario == "1"){
                        actualizarFragment()
                    }else{
                        personaEntity = persona
                    }
                }
            })

        binding.btnregvoluntario.setOnClickListener {
            if(binding.cbaceptar.isChecked){
                binding.btnregvoluntario.isEnabled = false
                mascotaViewModel.registrarVoluntario(personaEntity.id)
            }else{
                AppMensaje.enviarMensaje(binding.root,
                "Acepte los términos y condiciones para ser voluntario",
                TipoMensaje.ADVERTENCIA)
            }
        }
        mascotaViewModel.responseRegistro.observe(viewLifecycleOwner,
        Observer {
            obtenerDatosRegistroVoluntario(it)
        })

        return binding.root
    }

    private fun obtenerDatosRegistroVoluntario(responseRegistro: ResponseRegistro) {
        if(responseRegistro.rpta){
            val nuevaPersonaEntity = PersonaEntity(
                personaEntity.id,
                personaEntity.apellidos,
                personaEntity.celular,
                personaEntity.email,
                "1",
                personaEntity.nombres,
                personaEntity.password,
                personaEntity.usuario
            )
            personaViewModel.actualizar(nuevaPersonaEntity)
            actualizarFragment()
        }
        AppMensaje.enviarMensaje(binding.root,
        responseRegistro.mensaje, TipoMensaje.EXITO)
        binding.btnregvoluntario.isEnabled = true
    }

    private fun actualizarFragment() {
        binding.tvtextovoluntario.visibility = View.GONE
        binding.btnregvoluntario.visibility = View.GONE
        binding.cbaceptar.visibility = View.GONE
        binding.tvtitulovoluntario.text = getString(R.string.valtvesvoluntario)
    }

}