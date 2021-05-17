package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class PokemonDetail(
    var id: String,
    val name: String,
    val url: String,
    var urlImagen: String,
    var flavor_text_entries: List<PokemonDescription>,
    var genera: List<PokemonType>
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.createTypedArrayList(PokemonDescription)!!,
        parcel.createTypedArrayList(PokemonType)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(urlImagen)
        parcel.writeTypedList(flavor_text_entries)
        parcel.writeTypedList(genera)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonDetail> {
        override fun createFromParcel(parcel: Parcel): PokemonDetail {
            return PokemonDetail(parcel)
        }

        override fun newArray(size: Int): Array<PokemonDetail?> {
            return arrayOfNulls(size)
        }
    }

}