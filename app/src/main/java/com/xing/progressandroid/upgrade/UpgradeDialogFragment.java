package com.xing.progressandroid.upgrade;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.utils.PackageUtil;

import java.io.File;

/**
 * 下载提示框
 */
public class UpgradeDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String ARGUMENT_KEY = "argument_key";
    private Context context;
    private TextView cancelTxtView;
    private TextView okTxtView;
    private static final String TAG = "UpgradeDialogFragment";
    private static final int REQUEST_WRITE_STORAGE = 0x10;
    private Handler handler = new Handler(Looper.getMainLooper());

    private ServiceConnection conn = new ServiceConnection() {
        /**
         * 服务连接成功的回调
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 开始下载 apk
            startDownloadApk((UpgradeDownloadService.DownloadBinder) service);
        }

        /**
         * 服务断开连接的回调
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private UpgradeAppBean upgradeAppBean;

    public static UpgradeDialogFragment newInstance(UpgradeAppBean upgradeAppBean) {
        UpgradeDialogFragment upgradeDialogFragment = new UpgradeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_KEY, upgradeAppBean);
        upgradeDialogFragment.setArguments(bundle);
        return upgradeDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppUpgradeDialogStyle);

        Bundle bundle = getArguments();
        if (bundle != null) {
            upgradeAppBean = (UpgradeAppBean) bundle.getSerializable(ARGUMENT_KEY);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_upgrade_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cancelTxtView = view.findViewById(R.id.tv_upgrade_cancel);
        okTxtView = view.findViewById(R.id.tv_upgrade_ok);
        setListener();
    }

    private void setListener() {
        cancelTxtView.setOnClickListener(this);
        okTxtView.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        //点击window外的区域 是否消失
        getDialog().setCanceledOnTouchOutside(false);
        //是否可以取消,会影响上面那条属性
        setCancelable(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });

        Window dialogWindow = getDialog().getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        lp.width = (int) (displayMetrics.widthPixels * 0.8f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_upgrade_cancel) {
            dismiss();
        } else if (v.getId() == R.id.tv_upgrade_ok) {
            // 开始下载前检查是否有写 sdcard 权限
            checkWriteStoragePermission();
        }
    }

    /**
     * 检查写 scared 权限
     */
    private void checkWriteStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission == PackageManager.PERMISSION_GRANTED) {
                startDownloadService();
                dismiss();
            } else if (permission == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
            }
        } else {
            startDownloadService();
            dismiss();
        }
    }

    /**
     * 开启下载服务
     */
    private void startDownloadService() {
        UpgradeDownloadService.bindService(context.getApplicationContext(), conn);
    }

    /**
     * 开始下载 apk
     *
     * @param binder
     */
    private void startDownloadApk(UpgradeDownloadService.DownloadBinder binder) {
        // 开始下载，并获取下载监听
        if (upgradeAppBean != null) {
            binder.start(upgradeAppBean, new OnDownloadListener() {

                @Override
                public void onDownloadStart() {
                    Log.e(TAG, "onDownloadStart: ");
                }

                @Override
                public void onDownloadComplete(final File apkFile) {
                    Log.e(TAG, "onDownloadComplete: " + apkFile.getAbsolutePath());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            PackageUtil.installApk(context, apkFile);
                        }
                    });

                }

                @Override
                public void onDownloadFailure(String msg) {
                    Log.e(TAG, "onDownloadFailure: " + msg);
                }

                @Override
                public void onDownloadProgress(int progress, long totalSize) {
                    Log.e(TAG, "onDownloadProgress: " + Thread.currentThread().getName());
                    Log.e(TAG, "onDownloadProgress: progress" + progress + ",totalSize = " + totalSize);
                }
            });
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            if (manager.isDestroyed()) {
                return;
            }
        }
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startDownloadService();
                dismiss();
            }
        }
    }
}
