package filekingkt.ejw.com.filekingkt.ui

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionListener
import filekingkt.ejw.com.filekingkt.R
import filekingkt.ejw.com.filekingkt.entity.FileEntity
import filekingkt.ejw.com.filekingkt.utils.*


class FileKingMainActivity : AppCompatActivity() {
    var fl_back: FrameLayout? = null
    var rv_file_list: RecyclerView? = null
    var rl_title_bg: RelativeLayout? = null
    var tv_title: TextView? = null
    var tv_file_chose: TextView? = null
    var dialog: AlertDialog? = null
    var indexMap = HashMap<Int, ArrayList<FileEntity>>()
    var index = 0
    var fileListAdapter: FileListAdapter? = null
    var choseFileList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fileking_main)
        initView()
        showDailog()
        checkPermission()
    }

    private fun initView() {
        rv_file_list = findViewById(R.id.rv_file_list) as RecyclerView
        fl_back = findViewById(R.id.fl_back) as FrameLayout
        rl_title_bg = findViewById(R.id.rl_title_bg) as RelativeLayout
        tv_title = findViewById(R.id.tv_title) as TextView
        tv_file_chose = findViewById(R.id.tv_file_chose) as TextView
        fl_back?.setOnClickListener { fileKingRoute() }
        StatusBarCompat.compat(this, Color.parseColor(if (TextUtils.isEmpty(FileKingManager.titleColor)) "#10B6F2" else FileKingManager.titleColor))
        rl_title_bg?.setBackgroundColor(Color.parseColor(if (TextUtils.isEmpty(FileKingManager.titleColor)) "#10B6F2" else FileKingManager.titleColor))
        tv_title?.text = if (TextUtils.isEmpty(FileKingManager.title)) "选择文件" else FileKingManager.title
        tv_file_chose?.setOnClickListener { setResultData() }

    }

    private fun setResultData() {
        var intent: Intent = Intent()
        intent.putStringArrayListExtra("file", choseFileList)
        setResult(FileKingManager.requestCode, intent)
        finish()
    }

    fun checkPermission() {
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .rationale { requestCode, rationale ->
                    AndPermission.rationaleDialog(this, rationale).show()
                }
                .callback(permissionListener)
                .start()
    }

    private val permissionListener: PermissionListener = object : PermissionListener {
        override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {
            getFileList(getSDPath(), object : FileCallBack {
                override fun getFile(fileList: ArrayList<FileEntity>) {
                    dialog!!.dismiss()
                    setData(fileList)
                    indexMap.put(++index, fileList)
                }
            }, if (null == FileKingManager.type) arrayOf("all") else FileKingManager.type)
        }

        override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {
            finish()
        }
    }

    private fun setData(fileList: ArrayList<FileEntity>?) {
        runOnUiThread {
            rv_file_list?.layoutManager = LinearLayoutManager(this)
            if (null == fileListAdapter) {
                fileListAdapter = FileListAdapter(R.layout.item_file_king_list, fileList)
                rv_file_list?.adapter = fileListAdapter
                fileListAdapter?.setOnItemClickListener { adapter, view, position ->
                    val item = adapter.getItem(position) as FileEntity
                    if (TextUtils.equals("dir", item.type)) {
                        dialog!!.show()
                        getFileList(item.path, object : FileCallBack {
                            override fun getFile(fileList: ArrayList<FileEntity>) {
                                dialog!!.dismiss()
                                setData(fileList)
                                indexMap.put(++index, fileList)
                            }
                        }, arrayOf("all"))
                    } else {
                        if (item.isChecked) {
                            item.isChecked = false
                            var a = view.findViewById(R.id.iv_file_check) as ImageView
                            a.setBackgroundResource(R.drawable.btn_checkbox_off)
                            choseFileList.remove(item.path)
                        } else {
                            item.isChecked = true
                            var a = view.findViewById(R.id.iv_file_check) as ImageView
                            a.setBackgroundResource(R.drawable.btn_checkbox_on)
                            choseFileList.add(item.path)
                        }
                    }
                }
            } else {
                fileListAdapter?.setNewData(fileList)
            }
        }
    }

    private fun showDailog() {
        var view = View.inflate(this, R.layout.dialog_fileking_loading, null) as RelativeLayout
        var builder = AlertDialog.Builder(this)
        builder.setView(view)
        dialog = builder.show()
        dialog?.setCanceledOnTouchOutside(false)
        val params = dialog?.getWindow()?.attributes
        params?.width = dp2px(this, 150)
        params?.height = dp2px(this, 150)
        dialog?.getWindow()?.attributes = params
    }


    fun fileKingRoute() {
        val size = indexMap.size
        if (size > 1) {
            setData(indexMap.get(index - 1))
            indexMap.remove(index)
            index--
            return
        } else {
            finish()
        }
    }

    override fun onBackPressed() {
        fileKingRoute()
    }
}
