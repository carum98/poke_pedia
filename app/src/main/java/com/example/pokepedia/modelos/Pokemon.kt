package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable
import com.example.pokepedia.BuildConfig

data class Pokemon(
    var id: String,
    val name: String,
    val url: String?,
    var evoluciones: MutableList<Pokemon>?
)  : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString(),
        parcel.createTypedArrayList(CREATOR)
    ) {
        this.id=id
    }
    fun getImage():String{
       return  "${BuildConfig.URLIMAGENPOKEMON}${id}.png"
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeTypedList(evoluciones)
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