package com.qbo.appkea5patitas.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.qbo.appkea5patitas.R
import com.qbo.appkea5patitas.databinding.FragmentListaMascotaBinding
import com.qbo.appkea5patitas.view.adapter.MascotaAdapter
import com.qbo.appkea5patitas.viewmodel.MascotaViewModel


class ListaMascotaFragment : Fragment() {
    private var _binding: FragmentListaMascotaBinding? = null
    private val binding get() = _binding!!
    private lateinit var mascotaViewModel: MascotaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaMascotaBinding.inflate(inflater, container,
            false)
        mascotaViewModel = ViewModelProvider(requireActivity())
            .get(MascotaViewModel::class.java)
        binding.rvmascotas.layoutManager = LinearLayoutManager(requireActivity())
        listarMascotas()
        return binding.root

    }

    private fun listarMascotas() {
        mascotaViewModel.listarMascotas().observe(viewLifecycleOwner,
        Observer {
            binding.rvmascotas.adapter = MascotaAdapter(it)
        })
    }

}