package com.mob.demo.mobpush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.Set;
public class JumpActivity extends Activity {

	private LinearLayout mainLayout;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setContentView(mainLayout);
		try {
			initData(getIntent());
		} catch (Throwable t){
			t.printStackTrace();
		}
	}

	private void initView(){
		mainLayout = new LinearLayout(this);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mainLayout.setLayoutParams(layoutParams);

		textView = new TextView(this);
		textView.setPadding(20, 50, 20, 50);
		textView.setTextSize(20);
		textView.setGravity(Gravity.CENTER);
		layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(20, 0, 20, 0);
		layoutParams.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
		mainLayout.addView(textView, layoutParams);
	}

	private void initData(Intent intent){
		StringBuilder data = new StringBuilder("Callback Data:\n");
		if(intent != null){
			Bundle bundle = intent.getExtras();
			if(bundle != null){
				Set<String> set = bundle.keySet();
				for(String key : set){
					Object obj = bundle.get(key);
					if(obj instanceof String){
						data.append("key:").append(key).append("   value:").append(bundle.getString(key)).append("\n");
					} else if(obj instanceof Integer){
						data.append("key:").append(key).append("   value:").append(bundle.getInt(key)).append("\n");
					} else if(obj instanceof Float){
						data.append("key:").append(key).append("   value:").append(bundle.getFloat(key)).append("\n");
					} else if(obj instanceof Double){
						data.append("key:").append(key).append("   value:").append(bundle.getDouble(key)).append("\n");
					} else if(obj instanceof Long){
						data.append("key:").append(key).append("   value:").append(bundle.getLong(key)).append("\n");
					} else if(obj instanceof Short){
						data.append("key:").append(key).append("   value:").append(bundle.getShort(key)).append("\n");
					} else if(obj instanceof Byte){
						data.append("key:").append(key).append("   value:").append(bundle.getByte(key)).append("\n");
					} else if(obj instanceof Boolean){
						data.append("key:").append(key).append("   value:").append(bundle.getBoolean(key)).append("\n");
					}
				}
			}
		}
		textView.setText(data.toString());
	}
}
