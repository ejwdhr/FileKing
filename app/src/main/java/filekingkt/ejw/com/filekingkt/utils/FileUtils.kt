package filekingkt.ejw.com.filekingkt.utils

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import filekingkt.ejw.com.filekingkt.entity.FileEntity
import filekingkt.ejw.com.filekingkt.ui.FileCallBack
import java.io.File
import java.text.DecimalFormat
import java.text.SimpleDateFormat

/**
 * Created by Administrator on 2017/6/21.
 */
internal fun haveSD() = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)

internal fun getSDPath() = if (haveSD()) Environment.getExternalStorageDirectory().absolutePath else null

internal fun getFileList(path: String?, fileCallBack: FileCallBack, type: Array<String>?) {
    Thread(Runnable {
        val file = File(path)
        val fileTreeWalk = file.walk()
        var fileList = ArrayList<FileEntity>()
        if (type!!.contains("all")) {
            fileTreeWalk.maxDepth(1).
                    filter { it.exists() }
                    .filter { !it.absolutePath.equals(path) }
                    .forEach {
                        var fileEntity = FileEntity(it.name, getFileType(it), it.isFile, FormetFileSize(it.length()), it.absolutePath, date2String(it.lastModified(), "yyyy-MM-dd HH:mm"))
                        fileList.add(fileEntity)
                    }
            fileCallBack.getFile(fileList)
        } else {
            fileTreeWalk
                    .maxDepth(100)
                    .filter { it.exists() }
                    .filter { type.contains(getFileType(it)) }
                    .filter { !it.absolutePath.equals(path) }
                    .forEach {
                        var fileEntity = FileEntity(it.name, getFileType(it), it.isFile, FormetFileSize(it.length()), it.absolutePath, date2String(it.lastModified(), "yyyy-MM-dd HH:mm"))
                        fileList.add(fileEntity)
                    }
            fileCallBack.getFile(fileList)
        }
    }).start()

}

private fun getFileType(file: File) = if (file.isFile) {
    file.name.substring(file.name.lastIndexOf(".") + 1)
} else "dir"


fun date2String(time: Long, format: String): String {
    val sdf = SimpleDateFormat(format)
    return sdf.format(time)
}

fun FormetFileSize(fileS: Long): String {//转换文件大小
    val df = DecimalFormat("#.00")
    var fileSizeString = ""
    if (fileS < 1024) {
        fileSizeString = df.format(fileS.toDouble()) + "B"
    } else if (fileS < 1048576) {
        fileSizeString = df.format(fileS.toDouble() / 1024) + "K"
    } else if (fileS < 1073741824) {
        fileSizeString = df.format(fileS.toDouble() / 1048576) + "M"
    } else {
        fileSizeString = df.format(fileS.toDouble() / 1073741824) + "G"
    }
    return fileSizeString
}
