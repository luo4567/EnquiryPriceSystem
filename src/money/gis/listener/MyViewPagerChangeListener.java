package money.gis.listener;

import java.util.List;

import money.gis.customview.ColorIconTextView;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MyViewPagerChangeListener implements OnPageChangeListener {

	private List<ColorIconTextView> myTabIndicator;

	public MyViewPagerChangeListener(List<ColorIconTextView> tabIndicator) {
		myTabIndicator = tabIndicator;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if (positionOffset > 0) {
			ColorIconTextView left = myTabIndicator.get(position);
			ColorIconTextView right = myTabIndicator.get(position + 1);

			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);
		}

	}

	@Override
	public void onPageSelected(int arg0) {

	}

}
