package edu.feicui.contactsupdate.utils;

import android.Manifest;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import edu.feicui.contactsupdate.base.ActivityManager;

/**
 * Dialog工具类
 */
public class DialogUtil {
	Context context;

	public DialogUtil(Context context) {
		this.context = context;
	}

	/**
	 * @param name 姓名
	 * @param number 电话
	 */
	public void showDialog(String name, final String number) {
		Builder dialog = new Builder(context);

		dialog.setTitle("警告");
		dialog.setMessage("是否开始拨打" + name + "电话 ? \n\nTEL：" + number);
		dialog.setCancelable(true);
		dialog.setNegativeButton("取消", null);
		dialog.setPositiveButton("拨号", new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel://" + number));
				if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					// TODO: Consider calling
					//    ActivityCompat#requestPermissions
					// here to request the missing permissions, and then overriding
					//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
					//                                          int[] grantResults)
					// to handle the case where the user grants the permission. See the documentation
					// for ActivityCompat#requestPermissions for more details.
					return;
				}
				context.startActivity(intent);
			}
		});
		dialog.show();
	}

	public void showDialog() {
		Builder dialog = new Builder(context);
		dialog.setTitle("提示");
		dialog.setMessage("确定要退出吗?");
		dialog.setCancelable(true);
		dialog.setNegativeButton("取消",null);
		dialog.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				ActivityManager.getInstance().exit();
			}
		});
		dialog.show();
	}
}
