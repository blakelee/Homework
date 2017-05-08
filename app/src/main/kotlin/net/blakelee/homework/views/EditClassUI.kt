package net.blakelee.homework.views

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.models.ClassDetails
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EditClassUI(var classDetails: ClassDetails) : AnkoComponent<AppCompatActivity> {
    private val PICTURE_RESULT = 100

    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui) {

        verticalLayout {

            appBarLayout {
                toolbar {
                    setTitleTextColor(ContextCompat.getColor(ctx, R.color.icons))
                    backgroundColor = ContextCompat.getColor(ctx, R.color.primary)
                    id = R.id.toolbar_edit
                }
            }.lparams(width = matchParent)

            scrollView {
                lparams(width = matchParent, height = matchParent)


                verticalLayout {
                    imageView {
                        id = R.id.edit_class_image
                        scaleType = ImageView.ScaleType.CENTER
                        imageResource = R.drawable.image_placeholder
                        backgroundColor = ContextCompat.getColor(ctx, R.color.divider)
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
                        }.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                            override fun afterTextChanged(s: Editable?) { classDetails.name = s.toString() }
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                        textView("Class Days")
                        recyclerView {
                            id = R.id.days_recycler
                        }.lparams(height = wrapContent, width = matchParent)

                        textView("Professor Name")
                        editText {
                            id = R.id.professor_name
                            setText(classDetails.professor)
                        }.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                            override fun afterTextChanged(s: Editable?) { classDetails.professor = s.toString() } //Update model for everything
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                        textView("Location")
                        editText {
                            id = R.id.location
                            setText(classDetails.location)
                        }.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                            override fun afterTextChanged(s: Editable?) { classDetails.location = s.toString() } //Update model for everything
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                        textView("Email Address")
                        editText {
                            id = R.id.email_address
                            setText(classDetails.email)
                        }.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                            override fun afterTextChanged(s: Editable?) { classDetails.email = s.toString() } //Update model for everything
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                        textView("Phone Number")
                        editText {
                            id = R.id.phone_number
                            inputType = android.text.InputType.TYPE_CLASS_PHONE
                            setText(classDetails.phone)
                        }.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                            override fun afterTextChanged(s: Editable?) { classDetails.phone = s.toString() } //Update model for everything
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                        textView("Credit Hours")
                        editText {
                            id = R.id.credit_hours
                            setText(classDetails.hours?.toString())
                            inputType = android.text.InputType.TYPE_CLASS_NUMBER + android.text.InputType.TYPE_NUMBER_FLAG_SIGNED
                        }.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                            override fun afterTextChanged(s: Editable?) {
                                if (s.toString() == "")
                                        classDetails.hours = null
                                else
                                    classDetails.hours = s.toString().toInt()
                            }
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                        textView("Final")
                        linearLayout {
                            button {
                                setAllCaps(false)
                                id = R.id.final_day
                                text = classDetails.finals.getDay()
                            }.lparams(weight = 0.5f)

                            button {
                                id = R.id.final_start
                                text = classDetails.finals.getStartTime()
                            }.lparams(weight = 0.25f)

                            button {
                                id = R.id.final_end
                                text = classDetails.finals.getEndTime()
                            }.lparams(weight = 0.25f)
                        }

                        padding = dip(14)
                    }.lparams(height = matchParent, width = matchParent)
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

