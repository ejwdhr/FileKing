package filekingkt.ejw.com.filekingkt.entity

/**
 * Created by Administrator on 2017/6/19.
 */
data class FileEntity(
        var name: String,
        var type: String,
        var isFolder: Boolean,
        var fileLength: String,
        var path: String,
        var createTime: String,
        var isChecked: Boolean = false
)