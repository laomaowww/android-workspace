package com.fan;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class nihao extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout v1 = (LinearLayout) findViewById(R.id.LinearLayout01);
        
        WaterViewWidget widget = new WaterViewWidget(this);
		v1.addView(widget);
        
        Button leftButton = (Button) findViewById(R.id.Button01);
        
        leftButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	//System.out.println("on clicking.....");
            	//transfomedViewWidget.start();
            }
        });
    }
    
    
}