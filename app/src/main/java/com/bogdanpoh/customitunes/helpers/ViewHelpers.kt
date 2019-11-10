package com.bogdanpoh.customitunes.helpers

import android.content.Context
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class ViewHelpers {
    object Font {
        fun setFontTextViews(font_id: Int, context: Context, listView: ArrayList<TextView>) {

            val font = ResourcesCompat.getFont(context, font_id)

            listView.forEach {
                it.typeface = font
            }

        }
    }
}