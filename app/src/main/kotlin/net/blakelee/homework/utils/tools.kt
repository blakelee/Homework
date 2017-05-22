package net.blakelee.homework.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AlertBuilder
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun AlertBuilder<DialogInterface>.multiItems(ctx: Context, items: List<CharSequence>, checked: BooleanArray, onItemSelected: (dialog: DialogInterface, index: Int, isChecked: Boolean) -> Unit) {
    val builder = AlertDialog.Builder(ctx)
    builder.setMultiChoiceItems(Array(items.size){ i -> items[i].toString() }, checked) {dialog, which, isChecked ->
        onItemSelected(dialog, which, isChecked)
    }
    builder.create()
    builder.show()
}

fun BitmapToByteArray(bitmap: Bitmap) : ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}

fun ByteArrayToBitmap(byteArray: ByteArray) : Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

enum class ClassValidation {
    SUCCESS, EMPTY, CONFLICT, DAYS, TIME
}

fun getHour(hour : Int, minute : Int) : Date {
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR_OF_DAY,   hour)
    cal.set(Calendar.MINUTE,        minute)
    cal.set(Calendar.YEAR,          cal.getActualMinimum(Calendar.YEAR))
    cal.set(Calendar.DAY_OF_YEAR,   cal.getActualMinimum(Calendar.DAY_OF_YEAR))
    cal.set(Calendar.SECOND,        cal.getActualMinimum(Calendar.SECOND))
    cal.set(Calendar.MONTH,         cal.getActualMinimum(Calendar.MONTH))
    cal.set(Calendar.MILLISECOND,   cal.getActualMinimum(Calendar.MILLISECOND))

    return cal.time
}