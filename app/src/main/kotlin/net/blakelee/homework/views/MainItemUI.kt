package net.blakelee.homework.views

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*

class MainItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {
                imageView {
                    id = R.id.class_icon
                    leftPadding = dip(16)
                    rightPadding = dip(16)
                    topPadding = dip(8)
                    bottomPadding = dip(8)
                    isClickable = true
                }.lparams(width = dip(72), height = dip(72))

                verticalLayout {
                    lparams(width = matchParent, height = matchParent) {
                        leftMargin = dip(72)
                    }
                     gravity = Gravity.VERTICAL_GRAVITY_MASK

                    textView {
                        id = R.id.class_name
                        textResource = R.string.sample_class_name
                        textSize = 16f
                        textColor = R.color.primary_text_material_light
                        gravity = Gravity.CENTER_VERTICAL
                        maxLines = 1
                    }

                    textView {
                        id = R.id.class_times
                        textResource = R.string.sample_class_time
                        textSize = 14f
                        textColor = R.color.secondary_text_material_light
                        gravity = Gravity.CENTER_VERTICAL
                        maxLines = 1
                    }
                }
            }
        }
    }
}