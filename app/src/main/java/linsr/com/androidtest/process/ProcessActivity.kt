package linsr.com.androidtest.process

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import linsr.com.androidtest.R
import linsr.com.androidtest.base.BaseActivity

open class ProcessActivity : BaseActivity() {

    override fun init() {
        val tv = findViewById<TextView>(R.id.process_tv);
        val btn = findViewById<Button>(R.id.process_btn);
        val btn2 = findViewById<Button>(R.id.process_btn2);
        val btn3 = findViewById<Button>(R.id.process_btn3);


        btn.setOnClickListener {
            val process = getProcessName();
            tv.text = process + "\r\n" + System.currentTimeMillis();
        };
        btn2.setOnClickListener { startActivity(Intent(this, ProcessRemoteActivity::class.java)); }
        btn3.setOnClickListener { startActivity(Intent(this, ProcessOtherActivity::class.java)) }
        val btnService = findViewById<Button>(R.id.process_service);
        btnService.setOnClickListener { startService(Intent(this, RemoteService::class.java)) }
        val btnSend = findViewById<Button>(R.id.process_add);
        btnSend.setOnClickListener {
            val intent = Intent(this, RemoteService::class.java);
            intent.putExtra("cmd", 0);
            intent.putExtra("msg", getProcessName())
            startService(intent);
        }
        val btnSubtract = findViewById<Button>(R.id.process_subtract);
        btnSubtract.setOnClickListener {
            val intent = Intent(this, RemoteService::class.java);
            intent.putExtra("cmd", 1);
            intent.putExtra("msg", getProcessName())
            startService(intent);
        }
        val btnCrash = findViewById<Button>(R.id.process_crash);
        btnCrash.setOnClickListener {
            val intent = Intent(this, RemoteService::class.java);
            intent.putExtra("cmd", 2);
            intent.putExtra("msg", getProcessName())
            startService(intent);
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_process;
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
}