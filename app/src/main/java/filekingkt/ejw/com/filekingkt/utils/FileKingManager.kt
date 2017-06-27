package filekingkt.ejw.com.filekingkt.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import filekingkt.ejw.com.filekingkt.ui.FileKingMainActivity

object FileKingManager {

    var titleColor: String? = ""
    var title: String? = ""
    var type: Array<String>? = null
    var requestCode: Int = 1024

    internal fun setTitleColor(color: String?): FileKingManager {

        titleColor = color
        return this
    }

    internal fun setTitle(title: String?): FileKingManager {
        this.title = title
        return this
    }


    internal fun fileType(type: Array<String>): FileKingManager {
        this.type = type
        return this
    }

    internal fun start(activity: Activity) {

        activity.startActivityForResult(Intent(activity, FileKingMainActivity::class.java), 1234)
    }
}