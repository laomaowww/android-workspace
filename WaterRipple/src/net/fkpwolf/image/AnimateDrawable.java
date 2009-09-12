/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fkpwolf.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;

public class AnimateDrawable extends ProxyDrawable {
    
	WaterFilter f = new WaterFilter();
	Bitmap bm, dst;
    private Animation mAnimation;
    private Transformation mTransformation = new Transformation();

    public AnimateDrawable(Drawable target) {
        super(target);
    }
    
    public AnimateDrawable(Drawable target, Animation animation, Bitmap oldmap) {
        super(target);
        f.setCentreX(0.73399997F);
		f.setCentreY(0.465F);
		f.setAmplitude(1.0F);
		//f.setPhase(4.34587F);
		f.setPhase(1);
		f.setRadius(77.92F);
		f.setWavelength(17.78F);
		
		bm = oldmap;
		dst = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), bm
				.getConfig());
        mAnimation = animation;
    }
    
    public Animation getAnimation() {
        return mAnimation;
    }
    
    public void setAnimation(Animation anim) {
        mAnimation = anim;
    }

    public boolean hasStarted() {
        return mAnimation != null && mAnimation.hasStarted();
    }
    
    public boolean hasEnded() {
        return mAnimation == null || mAnimation.hasEnded();
    }
    
    @Override
    public void draw(Canvas canvas) {
        Drawable dr = getProxy();
        if (dr != null) {
            //int sc = canvas.save();
            Animation anim = mAnimation;
            if (anim != null) {
                anim.getTransformation(
                                    AnimationUtils.currentAnimationTimeMillis(),
                                    mTransformation);
                //canvas.concat(mTransformation.getMatrix());
                //float[] values = new float[9];
                //for (int i = 0; i< values.length; i++){
                //	System.out.println(values[i]);
                //}
				//mTransformation.getMatrix().getValues(values);
                
                float p = f.getPhase();
                p++;
                f.setPhase(p);
                
                System.out.println("one setp");
                f.filter(bm, dst);
                Paint paint = new Paint();
                canvas.drawBitmap(dst, canvas.getMatrix(), paint);//FIXME why need so many parameters
            }
            //dr.draw(canvas);
            //canvas.restoreToCount(sc);
        }
    }
}
    
