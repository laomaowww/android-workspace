package com.fan;

import net.fkpwolf.image.AnimateDrawable2;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class WaterViewWidget extends View {
	Animation an;
	private AnimateDrawable2 mDrawable;

	

	public WaterViewWidget(Context context) {
		super(context);
		
		setFocusable(true);
        setFocusableInTouchMode(true);

        Drawable dr = context.getResources().getDrawable(R.drawable.flower);
        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
        
        an = new TranslateAnimation(0, 100, 0, 200);
        an.setDuration(20);
        an.setRepeatCount(-1);
        an.initialize(10, 10, 10, 10);
        
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
		R.drawable.flower);
        mDrawable = new AnimateDrawable2(dr, an, bm);
        //an.startNow();
       
	}
	
	 @Override protected void onDraw(Canvas canvas) {
         canvas.drawColor(Color.BLACK);

         mDrawable.draw(canvas);
         invalidate();
     }
	 @Override
     public boolean onTouchEvent(MotionEvent event) {
         float x = event.getX();
         float y = event.getY();
         mDrawable.click(x, y);
		return true;
	 }

	public void start() {
		an.startNow();
		invalidate();
	}

	//@Override
//	protected void onDraw2(Canvas canvas) {
//		
//
//		canvas.drawColor(Color.WHITE);
//
//		Paint paint = new Paint();
//
//		Bitmap bm = BitmapFactory.decodeResource(getResources(),
//				R.drawable.flower);
//
//		Matrix matrix = new Matrix();
//
//		Bitmap dst = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), bm
//				.getConfig());
//		f.filter(bm, dst);
//		canvas.drawBitmap(dst, matrix, paint);
//	}

//	public void next() {
//		float p = f.getPhase();
//		p++;
//		f.setPhase(p);
//	}

}
