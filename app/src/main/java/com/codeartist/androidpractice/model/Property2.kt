package com.codeartist.androidpractice.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property_table")
data class Property2(@PrimaryKey val id: String,  ) {
}