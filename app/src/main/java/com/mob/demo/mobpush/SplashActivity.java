package com.mob.demo.mobpush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mob.MobSDK;
import com.mob.pushsdk.MobPushUtils;

import org.json.JSONArray;

/**
* @Description: 闪屏页
* @Date: 2022年12月31日 Saturday
* @Author liuyuqi.gov@msn.
*/
public class SplashActivity extends Activity {
	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_splash);
		
		handler.sendEmptyMessageDelayed(0, 3000);

		dealPushResponse(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		dealPushResponse(getIntent());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeMessages(0);
	}

	private void dealPushResponse(Intent intent) {
		if (intent != null) {
			//获取厂商打开首页点击数据
			JSONArray jsonArray = MobPushUtils.parseMainPluginPushIntent(intent);
			if (jsonArray.length() > 0) {
				System.out.println("parseMainPluginPushIntent:" + jsonArray);
			}
		}
	}
}
