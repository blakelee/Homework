package net.blakelee.homework.pickers

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.yarolegovich.lovelydialog.LovelyCustomDialog
import net.blakelee.homework.R
import net.blakelee.homework.interfaces.EditClassInterface
import org.jetbrains.anko.ctx
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding

class IconPicker(val act: EditClassInterface): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.icon_picker, null, false)
        val flexbox = view.findViewById(R.id.icon_picker) as com.google.android.flexbox.FlexboxLayout
        val size = dip(71)

        val array = context.resources.obtainTypedArray(R.array.icons)

        for(i in 0..array.length() - 1) {
            val imageView: ImageView = ImageView(ctx)
            val resource = array.getResourceId(i, 0)
            imageView.setImageResource(resource)
            imageView.layoutParams = ViewGroup.LayoutParams(size, size)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.padding = dip(5)
            imageView.setOnClickListener {
                act.setIcon(resource)
                dialog.dismiss()
            }
            flexbox.addView(imageView)
        }

        return LovelyCustomDialog(ctx)
                .setView(flexbox)
                .setTitle("Select Icon")
                .setIcon(R.drawable.ic_info)
                .setTopColorRes(R.color.accent)
                .create()
    }
}