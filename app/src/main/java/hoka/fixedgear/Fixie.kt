package hoka.fixedgear

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fixie(
    val name: String,
    val description: String,
    val photo: Int
)   : Parcelable
