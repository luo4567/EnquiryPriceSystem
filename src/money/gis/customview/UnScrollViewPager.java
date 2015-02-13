package money.gis.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class UnScrollViewPager extends ViewPager {

	private boolean isScrollable = true;

	public UnScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public UnScrollViewPager(Context context) {
		super(context);
	}

	public void setIsScrollable(boolean isScrollable) {
		this.isScrollable = isScrollable;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (isScrollable == false) {
			return false;
		} else {
			return super.onInterceptTouchEvent(ev);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (isScrollable == false) {
			return false;
		} else {
			return super.onTouchEvent(ev);
		}
	}
}
