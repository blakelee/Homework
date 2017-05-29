package net.blakelee.homework.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import net.blakelee.homework.activities.EditClassActivity
import net.blakelee.homework.models.Classes
import net.blakelee.homework.viewmodels.MainActivityViewModel
import org.jetbrains.anko.startActivity

class ActionDialog(val clazz: Classes, val vm: MainActivityViewModel): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        with(builder) {
            setTitle("Select action for ${clazz.name}")
            setItems(arrayOf("Edit", "Delete"), { _, item ->
                when(item) {
                    0 -> { startActivity<EditClassActivity>("class_id" to clazz.id)}
                    1 -> { vm.deleteClass(clazz.id) }
                }
            })
        }
        return builder.create()
    }
}