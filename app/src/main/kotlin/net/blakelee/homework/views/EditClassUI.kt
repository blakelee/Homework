package net.blakelee.homework.views

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.models.ClassDetails
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EditClassUI(var classDetails: ClassDetails) : AnkoComponent<AppCompatActivity> {
    private val PICTURE_RESULT = 100

    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui) {
        scrollView {
            lparams(width = matchParent)
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

                /** TODO: Need to add onTextChanged listener for every editText to set the model Because I don't know how to do data binding
                 */

                verticalLayout {
                    textView("Class Name")
                    editText {
                        id = R.id.class_name
                        setText(classDetails.name)
                    }

                    textView("Class Days")
                    recyclerView {
                        id = R.id.days_recycler
                    }.lparams(height = wrapContent, width = matchParent)

                    textView("Professor Name")
                    editText {
                        id = R.id.professor_name
                        setText(classDetails.professor)
                    }

                    textView("Location")
                    editText {
                        id = R.id.location
                        setText(classDetails.location)
                    }

                    textView("Email Address")
                    editText {
                        id = R.id.email_address
                        setText(classDetails.email)
                    }

                    textView("Phone Number")
                    editText {
                        id = R.id.phone_number
                        inputType = android.text.InputType.TYPE_CLASS_PHONE
                        setText(classDetails.phone)
                    }

                    textView("Credit Hours")
                    editText {
                        id = R.id.credit_hours
                        setText(classDetails.hours?.toString())
                    }

                    textView("Final")
                    linearLayout {
                        button {
                            id = R.id.final_day
                            text = classDetails.finals.day
                        }.lparams(weight = 0.5f)

                        button {
                            id = R.id.final_start
                            text = classDetails.finals.startTime
                        }.lparams(weight = 0.25f)

                        button {
                            id = R.id.final_end
                            text = classDetails.finals.endTime
                        }.lparams(weight = 0.25f)
                    }

                    padding = dip(14)
                }.lparams(height = matchParent, width = matchParent)
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

