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
            relativeLayout() {
                imageView() {
                    id = R.id.class_icon
                    lparams(width = dip(72), height = dip(72))
                    leftPadding = dip(16)
                    rightPadding = dip(16)
                    topPadding = dip(8)
                    bottomPadding = dip(8)
                    isClickable = true
                }

                verticalLayout() {
                    lparams(width = matchParent, height = matchParent) {
                        leftMargin = dip(72)
                    }
                    gravity = verticalGravity

                    textView {
                        id = R.id.class_name
                        text = "Sample Class Name"
                        textSize = 16f
                        textColor = ContextCompat.getColor(ctx, R.color.primary_text_default_material_light)
                        gravity = Gravity.CENTER_VERTICAL
                        maxLines = 1
                    }

                    textView {
                        id = R.id.class_times
                        text = "MTWThF 8:00a - 3:00p"
                        textSize = 14f
                        textColor = ContextCompat.getColor(ctx, R.color.secondary_text_default_material_light)
                        gravity = Gravity.CENTER_VERTICAL
                        maxLines = 1
                    }
                }
            }
        }
    }
}