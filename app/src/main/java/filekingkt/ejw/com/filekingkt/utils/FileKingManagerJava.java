package filekingkt.ejw.com.filekingkt.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import filekingkt.ejw.com.filekingkt.ui.FileKingMainActivity;

/**
 * Created by Administrator on 2017/6/27.
 */

public class FileKingManagerJava {

    static FileKingManagerJava fileKingManagerJava = new FileKingManagerJava();
    public static int requestCode = 1024;

    private FileKingManagerJava() {

    }

    public static FileKingManagerJava getInstance() {
        return fileKingManagerJava;
    }

    public FileKingManagerJava setTitle(String title) {
        FileKingManager.INSTANCE.setTitle(title);
        return fileKingManagerJava;
    }

    public FileKingManagerJava setTitleColor(String color) {
        FileKingManager.INSTANCE.setTitleColor(color);
        return fileKingManagerJava;
    }

    public FileKingManagerJava fileType(String[] type) {
        FileKingManager.INSTANCE.setType(type);
        return fileKingManagerJava;
    }

    public void start(Activity activity) {
        activity.startActivityForResult(new Intent(activity, FileKingMainActivity.class), 1234);
    }
}
