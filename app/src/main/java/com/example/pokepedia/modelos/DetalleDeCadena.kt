package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonArray


data class DetalleDeCadena(
    val evolves_to:List<DetalleDeCadena>,
    val species:PokemonSpecies
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(CREATOR)!!,
        parcel.readParcelable(PokemonSpecies::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(evolves_to)
        parcel.writeParcelable(species, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetalleDeCadena> {
        override fun createFromParcel(parcel: Parcel): DetalleDeCadena {
            return DetalleDeCadena(parcel)
        }

        override fun newArray(size: Int): Array<DetalleDeCadena?> {
            return arrayOfNulls(size)
        }
    }
}
