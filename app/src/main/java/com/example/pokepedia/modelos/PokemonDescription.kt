package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable

data class PokemonDescription (
    var flavor_text: String,
    var language: PokemonLanguage
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readParcelable(PokemonLanguage::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(flavor_text)
        parcel.writeParcelable(language, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonDescription> {
        override fun createFromParcel(parcel: Parcel): PokemonDescription {
            return PokemonDescription(parcel)
        }

        override fun newArray(size: Int): Array<PokemonDescription?> {
            return arrayOfNulls(size)
        }
    }
}