package com.pwdgame.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class XYViewPager extends ViewPager{

	private boolean touchEnable=true;
	
	public XYViewPager(Context context) {
		super(context);
	}
	
    public XYViewPager(Context context, AttributeSet attrs) { 
    	super(context, attrs);
    	
    } 
  //触摸没有反应就可以了
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.touchEnable) {
            return super.onTouchEvent(event);
        }
  
        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.touchEnable) {
            return super.onInterceptTouchEvent(event);
        }
 
        return false;
    }
    
	public void setTouchEnable(boolean touchEnable) {
		this.touchEnable = touchEnable;
	}

}