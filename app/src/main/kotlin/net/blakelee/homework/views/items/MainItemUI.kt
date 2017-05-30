package net.blakelee.homework.views.items

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*

class MainItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {
                id = R.id.classList

                val size = dip(72)

                imageView {
                    id = R.id.class_icon
                    leftPadding = dip(16)
                    rightPadding = dip(16)
                    topPadding = dip(8)
                    bottomPadding = dip(8)
                    isClickable = true
                }.lparams(width = size, height = size)

                verticalLayout {
                    leftPadding = dip(5)
                    lparams(width = matchParent, height = matchParent) {
                        leftMargin = size
                    }
                     gravity = Gravity.VERTICAL_GRAVITY_MASK

                    textView {
                        id = R.id.class_name
                        textSize = 18f
                        textColor = R.color.primary_text_material_light
                        gravity = Gravity.CENTER_VERTICAL
                        maxLines = 1
                        typeface = Typeface.DEFAULT_BOLD
                    }

                    textView {
                        id = R.id.class_times
                        textSize = 13f
                        textColor = R.color.secondary_text_material_light
                        gravity = Gravity.CENTER_VERTICAL
                        maxLines = 3
                    }
                }
            }
        }
    }
}