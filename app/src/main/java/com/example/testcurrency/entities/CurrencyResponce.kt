package com.example.testcurrency.entities

import com.google.gson.annotations.SerializedName


class CurrencyResponce(

    @SerializedName("Cur_ID") val id: Long,
    @SerializedName("Date") val numCod: String,
    @SerializedName("Cur_Abbreviation") val charCode: String,
    @SerializedName("Cur_Scale") val scale: Int,
    @SerializedName("Cur_Name") val name: String,
    @SerializedName("Cur_OfficialRate") val rate: Double
)
