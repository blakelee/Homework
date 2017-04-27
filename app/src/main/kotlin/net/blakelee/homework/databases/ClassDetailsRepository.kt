package net.blakelee.homework.databases

import io.realm.Realm
import net.blakelee.homework.interfaces.ClassDetailsRepositoryInterface
import net.blakelee.homework.models.ClassDetails

class ClassDetailsRepository : ClassDetailsRepositoryInterface {

    override fun addClass(classDetails: ClassDetails, callback: () -> Unit) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.createObject(classDetails.javaClass)
        realm.commitTransaction()
        callback()
    }

    override fun changeClass(classDetails: ClassDetails, callback: () -> Unit) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(classDetails)
        realm.commitTransaction()
        callback()
    }

    override fun getClass(className: String, callback: (ClassDetails) -> Unit) {
        val realm = Realm.getDefaultInstance()
        val query = realm.where(ClassDetails::class.java)
        val results = query.findFirst()
        callback(results)
    }
}