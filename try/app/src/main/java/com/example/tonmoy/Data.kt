package com.example.tonmoy

import io.realm.RealmObject

open class Data(var place:String? = null, var date:String?=null, var time:String? = null):RealmObject() {
}