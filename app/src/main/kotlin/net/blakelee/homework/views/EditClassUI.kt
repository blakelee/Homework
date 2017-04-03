package net.blakelee.homework.views

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import net.blakelee.homework.R
import org.jetbrains.anko.*

class EditClassUI : AnkoComponent<AppCompatActivity> {

    private val PICTURE_RESULT = 100

    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui){
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
                val className = editText {
                    hint = ("Class Name")
                }
                val professorName = editText {
                    hint = "Professor Name"
                }
                padding = dip(14)
            }
        }
    }
}