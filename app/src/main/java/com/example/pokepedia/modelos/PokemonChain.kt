package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable

data class PokemonChain(
    val chain: DetalleDeCadena
    ):Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable(DetalleDeCadena::class.java.classLoader)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(chain, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonChain> {
        override fun createFromParcel(parcel: Parcel): PokemonChain {
            return PokemonChain(parcel)
        }

        override fun newArray(size: Int): Array<PokemonChain?> {
            return arrayOfNulls(size)
        }
    }
}

