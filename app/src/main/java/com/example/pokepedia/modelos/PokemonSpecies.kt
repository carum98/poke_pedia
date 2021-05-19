package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable

data class PokemonSpecies(var name:String, var url:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonSpecies> {
        override fun createFromParcel(parcel: Parcel): PokemonSpecies {
            return PokemonSpecies(parcel)
        }

        override fun newArray(size: Int): Array<PokemonSpecies?> {
            return arrayOfNulls(size)
        }
    }
}
