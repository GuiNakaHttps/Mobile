package com.fiat.barbershop.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fiat.barbershop.databinding.ServicosItemBinding

data class Servicos(val img: Int, val nome: String) {
    companion object {
        val CREATOR: Parcelable.Creator<Servicos>
            get() {
                TODO()
            }
    }
}

class ServicosAdapter(private val context: Context, private val listaServicos: MutableList<Servicos>) :
    RecyclerView.Adapter<ServicosAdapter.ServicosViewHolder>(), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Context::class.java.classLoader)!!,
        parcel.createTypedArrayList(Servicos.CREATOR)!!
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicosViewHolder {
        val itemLista = ServicosItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ServicosViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: ServicosViewHolder, position: Int) {
        val servico = listaServicos[position]
        holder.imgServicos.setImageResource(servico.img)
        holder.txtServicos.text = servico.nome
    }

    override fun getItemCount() = listaServicos.size

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(context, flags)
        parcel.writeTypedList(listaServicos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ServicosAdapter> {
        override fun createFromParcel(parcel: Parcel): ServicosAdapter {
            return ServicosAdapter(parcel)
        }

        override fun newArray(size: Int): Array<ServicosAdapter?> {
            return arrayOfNulls(size)
        }
    }

    class ServicosViewHolder(binding: ServicosItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgServicos = binding.imgServicos
        val txtServicos = binding.txtTituloServicos
    }
}

private fun Parcel.writeParcelable(context: Context, flags: Int) {
    TODO("Not yet implemented")
}

private fun Parcel.writeTypedList(listaServicos: MutableList<Servicos>) {
    TODO("Not yet implemented")
}
