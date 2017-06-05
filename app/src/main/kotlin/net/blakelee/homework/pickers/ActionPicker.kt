package net.blakelee.homework.pickers

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import com.yarolegovich.lovelydialog.LovelyChoiceDialog
import net.blakelee.homework.R
import net.blakelee.homework.activities.EditClassActivity
import net.blakelee.homework.models.Classes
import net.blakelee.homework.viewmodels.MainActivityViewModel
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity

class ActionPicker(val clazz: Classes, val vm: MainActivityViewModel): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return LovelyChoiceDialog(ctx)
                .setTitle("Select action for ${clazz.name}")
                .setTopColor(clazz.icon_color)
                .setIcon(R.drawable.ic_info)
                .setItems(arrayOf("Edit", "Delete"), { pos, _ ->
                    when (pos) {
                        0 -> startActivity<EditClassActivity>("class_id" to clazz.id)
                        1 -> vm.deleteClass(clazz.id)
                    }
                })
                .create()
    }
}