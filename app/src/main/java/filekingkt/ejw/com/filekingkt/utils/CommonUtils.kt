package filekingkt.ejw.com.filekingkt.utils

import android.content.Context

/**
 * Created by Administrator on 2017/6/21.
 */
fun dp2px(context: Context, dpValue: Int): Int {
    val scale = context.getResources().getDisplayMetrics().density
    return (dpValue * scale + 0.5f).toInt()
}