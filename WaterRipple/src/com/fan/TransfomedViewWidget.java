package com.fan;

import net.fkpwolf.image.AnimateDrawable;
import net.fkpwolf.image.WaterFilter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class TransfomedViewWidget extends View {
	Animation an;
	private AnimateDrawable mDrawable;

	

	public TransfomedViewWidget(Context context) {
		super(context);
		
		setFocusable(true);
        setFocusableInTouchMode(true);

        Drawable dr = context.getResources().getDrawable(R.drawable.flower);
        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
        
        an = new TranslateAnimation(0, 100, 0, 200);
        an.setDuration(500);
        an.setRepeatCount(-1);
        an.initialize(10, 10, 10, 10);
        
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
		R.drawable.flower);
        mDrawable = new AnimateDrawable(dr, an, bm);
        //an.startNow();
       
	}
	
	 @Override protected void onDraw(Canvas canvas) {
         canvas.drawColor(Color.BLACK);

         mDrawable.draw(canvas);
         invalidate();
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
