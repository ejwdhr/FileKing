package filekingkt.ejw.com.filekingkt.ui

import android.support.annotation.LayoutRes
import android.text.TextUtils
import android.util.Log

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import filekingkt.ejw.com.filekingkt.R

import filekingkt.ejw.com.filekingkt.entity.FileEntity

/**
 * Created by Administrator on 2017/6/21.
 */

class FileListAdapter(@LayoutRes layoutResId: Int, data: List<FileEntity>?) : BaseQuickAdapter<FileEntity, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: FileEntity) {
        helper.setText(R.id.tv_file_time, "${item.createTime}")
                .setText(R.id.tv_file_name, "${item.name}")
                .setText(R.id.tv_file_size, "${item.fileLength}")
                .setVisible(R.id.iv_file_check, !TextUtils.equals("dir", item.type))
                .setVisible(R.id.tv_file_size, !TextUtils.equals("dir", item.type))
                .setBackgroundRes(R.id.iv_file_check, if (item.isChecked) R.drawable.btn_checkbox_on else R.drawable.btn_checkbox_off)
        when (item.type) {
            "dir" -> helper.setBackgroundRes(R.id.iv_file_icon, R.drawable.doc_ico_list_folder)
            "txt" -> helper.setBackgroundRes(R.id.iv_file_icon, R.drawable.txt)
            "mp3" -> helper.setBackgroundRes(R.id.iv_file_icon, R.drawable.doc_ico_bg_music)
            "mp4" -> helper.setBackgroundRes(R.id.iv_file_icon, R.drawable.doc_ico_bg_video)
            "doc" -> helper.setBackgroundRes(R.id.iv_file_icon, R.drawable.doc_ico_bg_doc)
            "docx" -> helper.setBackgroundRes(R.id.iv_file_icon, R.drawable.doc_ico_bg_doc)
            "xls" -> helper.setBackgroundRes(R.id.iv_file_icon, R.drawable.doc_ico_bg_xls)
            else -> helper.setBackgroundRes(R.id.iv_file_icon, R.drawable.doc_ico_bg_unknown)
        }
    }
}
