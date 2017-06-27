package filekingkt.ejw.com.filekingkt.ui

import filekingkt.ejw.com.filekingkt.entity.FileEntity

/**
 * Created by Administrator on 2017/6/21.
 */

interface FileCallBack {
    fun getFile(fileList: ArrayList<FileEntity>)
}
