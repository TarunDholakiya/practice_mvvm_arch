package com.tarun.myapplication.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    var users: ArrayList<User>
)

data class Info(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: Int,
    @SerializedName("seed")
    var seed: String,
    @SerializedName("version")
    var version: String
)

@Entity(tableName = "user")
data class User(
    @Expose
    @PrimaryKey(autoGenerate = true) var _id: Int,

    @SerializedName("cell")
    @ColumnInfo(name = "cell")
    var cell: String,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String,

    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    var gender: String,

    @SerializedName("name")
    @Embedded var name: Name,

    @SerializedName("nat")
    @ColumnInfo(name = "nat")
    var nat: String,

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    var phone: String,

    @SerializedName("picture")
    @Embedded var picture: Picture,

    @ColumnInfo(name = "status")
    var status: Boolean? = null
)

data class Dob(
    @SerializedName("age")
    var age: Int,
    @SerializedName("date")
    var date: String
)

data class UserId(
    @SerializedName("name")
    var name: String,
    @SerializedName("varue")
    var varue: String
)


data class Location(
    @SerializedName("city")
    var city: String,
    @SerializedName("coordinates")
    var coordinates: Coordinates,
    @SerializedName("country")
    var country: String,
    @SerializedName("postcode")
    var postcode: Any,
    @SerializedName("state")
    var state: String,
    @SerializedName("street")
    var street: Street,
    @SerializedName("timezone")
    var timezone: Timezone
)

data class Login(
    @SerializedName("md5")
    var md5: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("salt")
    var salt: String,
    @SerializedName("sha1")
    var sha1: String,
    @SerializedName("sha256")
    var sha256: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("uuid")
    var uuid: String
)

data class Name(
    @SerializedName("first")
    var first: String,
    @SerializedName("last")
    var last: String,
    @SerializedName("title")
    var title: String
)

data class Picture(
    @SerializedName("large")
    var large: String,
    @SerializedName("medium")
    var medium: String,
    @SerializedName("thumbnail")
    var thumbnail: String
)

data class Registered(
    @SerializedName("age")
    var age: Int,
    @SerializedName("date")
    var date: String
)

data class Coordinates(
    @SerializedName("latitude")
    var latitude: String,
    @SerializedName("longitude")
    var longitude: String
)

data class Street(
    @SerializedName("name")
    var name: String,
    @SerializedName("number")
    var number: Int
)

data class Timezone(
    @SerializedName("description")
    var description: String,
    @SerializedName("offset")
    var offset: String
)