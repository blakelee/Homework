package net.blakelee.homework.views.items

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*

class AssignmentItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                val size = dip(72)
                verticalLayout {
                    id = R.id.color
                    textView {
                        textSize = 18f
                        id = R.id.date
                        setTextColor(Color.WHITE)
                    }
                    textView {
                        textSize = 13f
                        id = R.id.time
                        setTextColor(Color.WHITE)
                    }
                }.lparams(width = size, height = size)

                verticalLayout {
                    textView {
                        id = R.id.name
                        textSize = 18f
                        maxLines = 1
                    }
                    textView {
                        id = R.id.details
                        textSize = 13f
                        maxLines = 1
                    }
                }
            }
        }
    }
}