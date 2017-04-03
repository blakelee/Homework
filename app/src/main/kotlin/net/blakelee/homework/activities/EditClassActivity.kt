package net.blakelee.homework.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import net.blakelee.homework.R
import net.blakelee.homework.views.EditClassUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class EditClassActivity : AppCompatActivity() {

    private val PICTURE_RESULT = 100

    val imgView by lazy {
       find<ImageView>(R.id.edit_class_image)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EditClassUI().setContentView(this)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            PICTURE_RESULT -> when(resultCode) {
                Activity.RESULT_OK -> {
                    val selectedImageUri = data?.data
                    if (null != selectedImageUri) {
                        imgView.setImageURI(selectedImageUri)
                        imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                }

                Activity.RESULT_CANCELED -> {
                    //TODO: For some reason it's not detecting if it's the placeholder right. However, it's not throwing errors.
                    if (imgView.drawable == getDrawable(R.drawable.image_placeholder))
                         imgView.scaleType = ImageView.ScaleType.CENTER
                    else
                         imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                }
            }
        }
    }

}