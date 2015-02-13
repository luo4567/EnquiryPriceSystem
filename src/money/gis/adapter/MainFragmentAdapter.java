package money.gis.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> tabFragments;

	public MainFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		tabFragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return tabFragments.get(position);
	}

	@Override
	public int getCount() {
		return tabFragments.size();
	}
}
