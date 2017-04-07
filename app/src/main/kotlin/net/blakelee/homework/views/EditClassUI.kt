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
        scrollView {
            verticalLayout {
                imageView {
                    id = R.id.edit_class_image
                    scaleType = ImageView.ScaleType.CENTER
                    imageResource = R.drawable.image_placeholder
                    backgroundColor = ContextCompat.getColor(ctx, R.color.primary)
                    tag = "BACKGROUND"

                    setOnClickListener {
                        val intent = Intent()
                        intent.type = "image/*"
                        intent.action = Intent.ACTION_GET_CONTENT
                        ui.owner.startActivityForResult(intent, PICTURE_RESULT)
                    }
                }.lparams(width = matchParent, height = dip(200))

                verticalLayout {
                    textView("Class Name") { padding = dip(0) }
                    editText { id = R.id.class_name }

                    textView("Days")
                    button("None") {
                        id = R.id.day_picker
                        setAllCaps(false)
                    }

                    textView("Professor Name")
                    editText { id = R.id.professor_name }

                    textView("Location")
                    editText { id = R.id.location }

                    textView("Email Address")
                    editText { id = R.id.email_address }

                    textView("Phone Number")
                    editText {
                        id = R.id.phone_number
                        inputType = android.text.InputType.TYPE_CLASS_PHONE
                    }

                    textView("Credit Hours")
                    editText { id = R.id.credit_hours }

                    padding = dip(14)
                }
            }
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

