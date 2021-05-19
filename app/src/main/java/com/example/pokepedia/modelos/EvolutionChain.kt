package com.example.pokepedia.modelos

import android.os.Parcel
import android.os.Parcelable

data class EvolutionChain(
    var url:String
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EvolutionChain> {
        override fun createFromParcel(parcel: Parcel): EvolutionChain {
            return EvolutionChain(parcel)
        }

        override fun newArray(size: Int): Array<EvolutionChain?> {
            return arrayOfNulls(size)
        }
    }
}
