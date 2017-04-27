package net.blakelee.homework.models

import io.realm.annotations.RealmModule

@RealmModule(classes = arrayOf(ClassDetails::class, Week::class, Day::class))
class ClassDetailsModule