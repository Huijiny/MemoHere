package com.basic.appjam

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

open class Memo: RealmObject() {
    open var lat: Double = 0.0
    open var lng: Double = 0.0
    open var title: String = ""
    open var content:  String = ""
}

class API {
    companion object {
        @JvmStatic fun addMemo(lat: Double, lng: Double, title: String, content: String) {
            val realm = Realm.getDefaultInstance()
            val current = realm.where(Memo::class.java)

            realm.beginTransaction()
            var new = realm.createObject(Memo::class.java)
            new.lat = lat
            new.lng = lng
            new.title = title
            new.content = content
            realm.commitTransaction()
        }

        @JvmStatic fun getMemo(): RealmResults<Memo> {
            val realm = Realm.getDefaultInstance()

            return realm.where(Memo::class.java).findAll()
        }
    }
}