package money.gis.listener;

import java.util.List;

import money.gis.customview.ColorIconTextView;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

public class TabClickListener implements OnClickListener {

	private List<ColorIconTextView> colorIconTextViews;
	private ViewPager myViewPager;

	public TabClickListener(List<ColorIconTextView> colorIconTextViews, ViewPager viewPager) {
		this.colorIconTextViews = colorIconTextViews;
		myViewPager = viewPager;
	}

	@Override
	public void onClick(View v) {
		resetOtherTabs();
		int index = colorIconTextViews.indexOf(v);
		ColorIconTextView colorIconTextView = (ColorIconTextView) v;
		colorIconTextView.setIconAlpha(1.0f);
		myViewPager.setCurrentItem(index, false);
	}

	private void resetOtherTabs() {
		for (int i = 0; i < colorIconTextViews.size(); i++) {
			colorIconTextViews.get(i).setIconAlpha(0);
		}
	}

}
