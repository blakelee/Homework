package net.blakelee.homework.views.items

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*

class AssignmentItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                id = R.id.list_item

                val size = dip(72)
                val left = verticalLayout {
                    id = R.id.color
                    textView {
                        textSize = 18f
                        id = R.id.date
                        setTextColor(Color.WHITE)
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    textView {
                        textSize = 13f
                        id = R.id.time
                        setTextColor(Color.WHITE)
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    gravity = Gravity.CENTER
                }.lparams(width = size, height = size)

                verticalLayout {
                    leftPadding = dip(5)
                    textView {
                        id = R.id.name
                        textSize = 18f
                        maxLines = 1
                        typeface = Typeface.DEFAULT_BOLD
                    }
                    textView {
                        id = R.id.details
                        textSize = 13f
                        maxLines = 3
                    }
                    gravity = Gravity.CENTER
                }.lparams (width = matchParent){
                    height = size
                    rightOf(left)
                }
            }
        }
    }
}