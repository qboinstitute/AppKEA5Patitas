package com.qbo.appkea5patitas.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qbo.appkea5patitas.apirest.response.ResponseMascota
import com.qbo.appkea5patitas.databinding.ItemMascotaBinding

class MascotaAdapter(private var listmascotas: List<ResponseMascota>)
    : RecyclerView.Adapter<MascotaAdapter.ViewHolder>()
{
    inner class ViewHolder(val binding: ItemMascotaBinding)
        : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaAdapter.ViewHolder {
        val binding = ItemMascotaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MascotaAdapter.ViewHolder, position: Int) {
        with(holder){
            with(listmascotas[position]){
                binding.tvnommascota.text = nommascota
                binding.tvlugar.text = lugar
                binding.tvfechaperdida.text = fechaperdida
                binding.tvcontacto.text = contacto
                Glide.with(holder.itemView.context)
                    .load(urlimagen)
                    .into(binding.ivmascota)
            }
        }
    }
    override fun getItemCount() = listmascotas.size

}