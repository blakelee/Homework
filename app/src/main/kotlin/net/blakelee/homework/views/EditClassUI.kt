package net.blakelee.homework.views

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.adapters.WeeksAdapter
import net.blakelee.homework.models.ClassDetails
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.percent.percentRelativeLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EditClassUI(var classDetails: ClassDetails, var weeksAdapter: WeeksAdapter) : AnkoComponent<AppCompatActivity> {
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

                    percentRelativeLayout {
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
                        }.lparams(width = matchParent, height = 0) {
                            percentLayoutInfo.aspectRatio = 16/9f
                            percentLayoutInfo.widthPercent = 1f
                        }
                    }

                    verticalLayout {
                        textInputLayout {
                            editText {
                                id = R.id.class_name
                                setText(classDetails.name)
                                hint = "Class Name *"
                            }.addTextChangedListener(object : TextWatcher {
                                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                override fun afterTextChanged(s: Editable?) { classDetails.name = s.toString() }
                                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })
                        }

                        textView("Class Days *") { textSize = 12f }
                        recyclerView {
                            id = R.id.days_recycler
                            adapter = weeksAdapter
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(height = wrapContent, width = matchParent)

                        textInputLayout {
                            editText {
                                id = R.id.professor_name
                                setText(classDetails.professor)
                                hint = "Professor Name"
                            }.addTextChangedListener(object : TextWatcher {
                                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                override fun afterTextChanged(s: Editable?) { classDetails.professor = s.toString() }
                                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })
                        }

                        textInputLayout {
                            editText {
                                id = R.id.location
                                setText(classDetails.location)
                                hint = "Location"
                            }.addTextChangedListener(object : TextWatcher {
                                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                override fun afterTextChanged(s: Editable?) { classDetails.location = s.toString() }
                                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })
                        }

                        textInputLayout {
                            editText {
                                id = R.id.email_address
                                setText(classDetails.email)
                                hint = "Email Address"
                            }.addTextChangedListener(object : TextWatcher {
                                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                override fun afterTextChanged(s: Editable?) { classDetails.email = s.toString() }
                                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })
                        }

                        percentRelativeLayout {
                            lparams(width = matchParent, height = wrapContent)
                            textInputLayout {
                                id = R.id.phone_number_parent
                                editText {
                                    id = R.id.phone_number
                                    inputType = android.text.InputType.TYPE_CLASS_PHONE
                                    setText(classDetails.phone)
                                    hint = "Phone Number"
                                }.addTextChangedListener(object : TextWatcher {
                                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                    override fun afterTextChanged(s: Editable?) { classDetails.phone = s.toString() }
                                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })
                            }.lparams {
                                percentLayoutInfo.widthPercent = 0.6f
                            }

                            textInputLayout {
                                editText {
                                    id = R.id.credit_hours
                                    setText(classDetails.hours?.toString())
                                    inputType = android.text.InputType.TYPE_CLASS_NUMBER + android.text.InputType.TYPE_NUMBER_FLAG_SIGNED
                                    hint = "Credit Hours"
                                }.addTextChangedListener(object : TextWatcher {
                                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                                    override fun afterTextChanged(s: Editable?) {
                                        if (s.toString() == "")
                                            classDetails.hours = null
                                        else
                                            classDetails.hours = s.toString().toInt()
                                    }
                                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                                })
                            }.lparams {
                                rightOf(R.id.phone_number_parent)
                                percentLayoutInfo.widthPercent = 0.4f
                            }
                        }

                        textView("Icon") { textSize = 12f }.setOnClickListener {

                        }

                        textView("In class ringer mode") { textSize = 12f }
                        //TODO: Get enum of ringer mode selected

                        textView("Color") { textSize = 12f }
                        //TODO: Get color of icon if not user icon

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

