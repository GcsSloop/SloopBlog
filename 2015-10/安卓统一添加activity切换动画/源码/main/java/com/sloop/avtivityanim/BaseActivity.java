package com.sloop.avtivityanim;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


/**
 * 基类 用于提供一些基础方法和属性
 *
 * @author admin
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * 打开activity
     */
    protected void openActivity(Class<?> cls) {
        openActivity(this, cls);
        overridePendingTransition(R.anim.create_zoomin, R.anim.create_zoomout);
    }

    /**
     * 打开activity
     */
    public static void openActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.finish_zoomin, R.anim.finish_zoomout);
    }

}
