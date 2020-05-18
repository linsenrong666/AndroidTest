package linsr.com.androidtest.process

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import linsr.com.androidtest.Utils
import java.lang.IllegalArgumentException

class RemoteService : Service() {
    companion object {
        const val TAG: String = "RemoteService";
    }

    override fun onCreate() {
        super.onCreate()
        Utils.i(TAG, "======RemoteService onCreate=====")
    }

    var count = 0;
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Utils.i(TAG, "======RemoteService onStartCommand=====")
        val cmd: Int? = intent?.getIntExtra("cmd", -1);
        val msg: String? = intent?.getStringExtra("msg");
        when (cmd) {
            0 -> {
                Utils.d(TAG, "cmd:0-----> from $msg")
                countAdd();
            }
            1 -> {
                Utils.d(TAG, "cmd:1-----> from $msg")
                countSubtract();
            }
            2 -> {
                Utils.d(TAG, "cmd:2-----> from $msg")
                throw IllegalArgumentException("定义好的crash");
            }
            -1 -> {
                Utils.d(TAG, "cmd:-1----->$msg")
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    private fun getProcessName(): String {
        val pid = android.os.Process.myPid();
        val manager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager;
        val runningAppProcesses = manager.runningAppProcesses;
        for (info in runningAppProcesses) {
            if (pid == info.pid) {
                return info.processName;
            }
        }
        return "";
    }

    private fun countAdd() {
        count++;
        Utils.w(TAG, "current count :$count")
    }

    private fun countSubtract() {
        count--;
        Utils.w(TAG, "current count :$count")
    }
}