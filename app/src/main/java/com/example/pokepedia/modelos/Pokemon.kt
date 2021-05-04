package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable

data class Pokemon(val id:Int ,
                   val nombre:String,
                   val fotoURL: String,
                   val  descripcion:String,
                   val idEvolucion: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(fotoURL)
        parcel.writeString(descripcion)
        parcel.writeInt(idEvolucion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}


