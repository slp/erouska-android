package cz.covid19cz.app.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class ResourceProvider(val context: Context) {
    fun getString(@StringRes resId: Int): String = context.getString(resId)
    fun getDrawable(@DrawableRes resId: Int): Drawable = context.getDrawable(resId)!!
}
