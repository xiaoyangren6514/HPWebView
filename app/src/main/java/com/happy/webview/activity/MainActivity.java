package com.happy.webview.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.happy.lib_common.autoservice.IWebViewService;
import com.happy.webview.R;

import java.util.ServiceLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_open).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                IWebViewService webViewService = ServiceLoader.load(IWebViewService.class).iterator().next();
                if (webViewService != null) {
//                    外部网页
//                    webViewService.startWebViewActivity(this, "http://www.baidu.com", "baidu");
//                    内置测试页面
                    webViewService.startDemoHtml(this);
                }
//                startActivity(new Intent(this, WebViewActivity.class));
                break;
        }
    }
}