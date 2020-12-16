package com.codeartist.androidpractice.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "property_table")
data class Property(@PrimaryKey
                    @SerializedName("id")
                    @Expose var id: Int,
                    @SerializedName("price")
                    @Expose var price: Int,
                    @SerializedName("type")
                    @Expose var type: String,
                    @SerializedName("img_src")
                    @Expose var imgSrc: String)