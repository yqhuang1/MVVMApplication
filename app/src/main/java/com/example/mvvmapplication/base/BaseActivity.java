package com.example.mvvmapplication.base;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 类说明
 *
 * @author renjialiang
 * @version [版本]
 * @see [相关类]
 * @since [模块]
 */
public class BaseActivity extends AppCompatActivity {

    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }
}
