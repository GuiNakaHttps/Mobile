package com.fiat.barbershop.model

import android.os.Parcel
import android.os.Parcelable

data class Servicos(val img: Int, val nome: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(img)
        parcel.writeString(nome)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Servicos> {
        override fun createFromParcel(parcel: Parcel): Servicos {
            return Servicos(parcel)
        }

        override fun newArray(size: Int): Array<Servicos?> {
            return arrayOfNulls(size)
        }
    }
}
