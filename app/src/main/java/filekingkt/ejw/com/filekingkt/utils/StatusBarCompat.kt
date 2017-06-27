package filekingkt.ejw.com.filekingkt.utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup


/**
 * Created by Administrator on 2017/6/27.
 */
object StatusBarCompat {
    private val INVALID_VAL = -1
    private val COLOR_DEFAULT = Color.parseColor("#20000000")

    internal fun compat(activity: Activity, statusColor: Int) {

        //当前手机版本为5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                activity.window.statusBarColor = statusColor
            }
            return
        }

        //当前手机版本为4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            var color = COLOR_DEFAULT
            val contentView = activity.findViewById(android.R.id.content) as ViewGroup
            if (statusColor != INVALID_VAL) {
                color = statusColor
            }
            val statusBarView = View(activity)
            val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity))
            statusBarView.setBackgroundColor(color)
            contentView.addView(statusBarView, lp)
        }

    }


    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}