package money.gis.main;

import java.util.ArrayList;
import java.util.List;

import money.gis.adapter.MainFragmentAdapter;
import money.gis.customview.ColorIconTextView;
import money.gis.customview.UnScrollViewPager;
import money.gis.fragment.ManualFragment;
import money.gis.fragment.QuickFragment;
import money.gis.listener.MyViewPagerChangeListener;
import money.gis.listener.TabClickListener;
import money.gis.welcome.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {

	private UnScrollViewPager myViewPager;
	private FragmentPagerAdapter myPagerAdapter;
	private List<Fragment> fragments = new ArrayList<Fragment>();
	private List<ColorIconTextView> myTabIndicator = new ArrayList<ColorIconTextView>();
	private LinearLayout tabLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initEnvent();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		myViewPager = (UnScrollViewPager) findViewById(R.id.viewpager);
		myViewPager.setIsScrollable(false);
		// 初始化底部导航栏
		tabLinearLayout = (LinearLayout) findViewById(R.id.tab_footer);
		int childrenCount = tabLinearLayout.getChildCount();
		for (int i = 0; i < childrenCount; i++) {
			ColorIconTextView iconTextView = (ColorIconTextView) tabLinearLayout.getChildAt(i);
			myTabIndicator.add(iconTextView);
		}
		// 设置第一个控件为选中效果
		myTabIndicator.get(0).setIconAlpha(1.0f);
		fragments.add(QuickFragment.newInstance());
		fragments.add(ManualFragment.newInstance());
		myPagerAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments);
		myViewPager.setAdapter(myPagerAdapter);
	}

	/***
	 * 初始化事件
	 */
	private void initEnvent() {
		for (int i = 0; i < myTabIndicator.size(); i++) {
			myTabIndicator.get(i).setOnClickListener(new TabClickListener(myTabIndicator, myViewPager));
		}
		myViewPager.setOnPageChangeListener(new MyViewPagerChangeListener(myTabIndicator));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
