package net.blakelee.homework.views

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import net.blakelee.homework.R
import org.jetbrains.anko.*

class EditClassUI : AnkoComponent<AppCompatActivity> {
    private val PICTURE_RESULT = 100

    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui) {
        verticalLayout {
            imageView {
                id = R.id.edit_class_image
                scaleType = ImageView.ScaleType.CENTER
                imageResource = R.drawable.image_placeholder
                backgroundColor = ContextCompat.getColor(ctx, R.color.primary)

                setOnClickListener {
                    val intent = Intent()
                    intent.type = "image/*"
                    intent.action = Intent.ACTION_GET_CONTENT
                    ui.owner.startActivityForResult(intent, PICTURE_RESULT)
                }
            }.lparams(width = matchParent, height = dip(200))

            verticalLayout {
                editText {
                    hint = ("Class Name")
                    hintTextColor = ContextCompat.getColor(ctx, R.color.caldroid_light_red)
                }
            }
            button("Days") {
                id = R.id.day_picker
                setAllCaps(false)
                /*val args = Bundle()
                args.putIntegerArrayList("days", daysSelected)
                with (DayPicker()) {
                    arguments = args
                    show(fragmentManager, "DAY_PICKER")
                }*/
            }
            editText { hint = "Professor Name" }
            editText { hint = "Location" }
            editText { hint = "Email Address" }
            editText { hint = "Phone Number" }
            editText { hint = "Credit Hours" }
            padding = dip(14)
        }
    }
}

/*fun AlertBuilder<DialogInterface>.multiItems(ctx: Context, items: List<CharSequence>, checked: BooleanArray, onItemSelected: (dialog: DialogInterface, index: Int, isChecked: Boolean) -> Unit) {
    val builder = AlertDialog.Builder(ctx)
    builder.setMultiChoiceItems(Array(items.size){ i -> items[i].toString() }, checked) {dialog, which, isChecked ->
        onItemSelected(dialog, which, isChecked)
    }
    builder.create()
    builder.show()
}*/

