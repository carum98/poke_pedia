package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable

data class PokemonType (
    var genus: String,
    var language: PokemonLanguage
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readParcelable(PokemonLanguage::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(genus)
        parcel.writeParcelable(language, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonType> {
        override fun createFromParcel(parcel: Parcel): PokemonType {
            return PokemonType(parcel)
        }

        override fun newArray(size: Int): Array<PokemonType?> {
            return arrayOfNulls(size)
        }
    }

}