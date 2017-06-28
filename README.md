# FileKing
 使用kotlin编写的文件选择工具，能够快速的遍历出你需要的文件类型
支持Java代码调用，本项目是我拿来练手kotlin的第一个项目，写的不是很好，有兴趣的同学可以看一看，有错误可以提交issue给我哦！

##导入方式：
	在项目gradle中加入：
		allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	在app的gradle中加入：
		dependencies {
	        compile 'com.github.ejwdhr:FileKing:-SNAPSHOT'
	}
 
##kotlin调用方法：

	FileKingManager.setTitle("文件选择器")
					.setTitleColor("#ffffff")
					.fileType(arrayof{"doc","txt"})
					.start(mcontext)

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FileKingManager.requestCode) {
            ArrayList<String> file = data.getStringArrayListExtra("file");
        }
    }
								
##java调用方法：
  
	FileKingManagerJava.setTitle("文件选择器")
					.setTitleColor("#ffffff")
					.fileType(new String[]{"doc","txt"})
					.start(mcontext)

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FileKingManagerJava.requestCode) {
            ArrayList<String> file = data.getStringArrayListExtra("file");
        }
    }
				 