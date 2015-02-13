package money.gis.fragment;

import java.sql.Date;
import java.util.ArrayList;

import money.gis.adapter.QuickEnquiryAdapter;
import money.gis.customview.LoadMoreListView;
import money.gis.customview.LoadMoreListView.OnLoadMoreListener;
import money.gis.welcome.R;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.money.db.DbMethod;
import com.money.db.Page;
import com.money.persistent.BasePrice;
import com.money.persistent.QuickEnquiryInfo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class QuickFragment extends Fragment {

	private Page page;
	// 总页数
	private long totalPage;

	private LoadMoreListView quickListView;
	private ArrayList<QuickEnquiryInfo> infos = new ArrayList<QuickEnquiryInfo>();
	private QuickEnquiryAdapter adapter;
	private QueryBuilder builder = new QueryBuilder(QuickEnquiryInfo.class);

	public static QuickFragment newInstance() {
		QuickFragment quickFragmentFragment = new QuickFragment();
		return quickFragmentFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.quick_fragment, container, false);
		quickListView = (LoadMoreListView) v.findViewById(R.id.pull_refresh_list);
		mockData();
//		initPageInfo();
//		// 初始化数据
//		new GetDataTask().execute();
//		adapter = new QuickEnquiryAdapter(getActivity(), infos);
//		quickListView.setAdapter(adapter);
//		// 设置事件
//		initEnvent();
//		// 设置侧滑菜单
//		initSwipeMenu();
		return v;
	}

	/**
	 * 初始化分页信息
	 */
	private void initPageInfo() {
		page = new Page();
		// 设置默认分页大小
		page.pageSize = 15;
		// 获取总页数
		long count = DbMethod.queryCount(QuickEnquiryInfo.class);
		if (count % page.pageSize == 0) {
			totalPage = count / page.pageSize;
		} else {
			totalPage = count / page.pageSize + 1;
		}
		if (totalPage == 0) {
			totalPage = 1;
		}
		if (page.currentPage == 0) {
			page.currentPage = 1;
		}
	}

	/**
	 * 根据分页信息获取数据
	 */
	private void initData() {
		builder.limit(page.getLimit());
		ArrayList<QuickEnquiryInfo> quickEnquiryInfos = DbMethod.query(builder);
		infos.addAll(quickEnquiryInfos);
	}

	/**
	 * 初始化事件
	 */
	private void initEnvent() {
		// 设置刷新事件
		quickListView.setOnLoadMoreListener(new OnLoadMoreListener() {
			@Override
			public void onLoadMore() {
				// 获取的数据是否小于当前的分页大小
				if (totalPage < page.currentPage) {

					// todo 提示没有更多数据
				} else {
					// 加载更多数据
					page.currentPage++;
					new GetDataTask().execute();
				}
			}
		});

		// 设置侧滑菜单点击事件
		quickListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
				infos.remove(position);
				// 判断当前的数据是否小于分页的大小
				if (infos.size() < page.pageSize) {
					// 如果小于则判断是否进行加载更多数据
					if (totalPage < page.currentPage) {
						// 显示没有更多数据
						quickListView.onLoadMoreComplete();
					} else {
						// 加载更多数据
						quickListView.onLoadMore();
					}
				}
				adapter.notifyDataSetChanged();
				return false;
			}
		});

		// 设置ListItem点击事件
		quickListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Toast.makeText(getActivity(), "onItemClick", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 初始化侧滑菜单
	 */
	private void initSwipeMenu() {
		SwipeMenuCreator creator = new SwipeMenuCreator() {
			@Override
			public void create(SwipeMenu menu) {
				// 创建删除按钮
				SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
				// 设置背景颜色
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
				// 设置宽度
				deleteItem.setWidth(dp2px(90));
				// 设置图标
				deleteItem.setIcon(R.drawable.ic_delete);
				// 加入到菜单中
				menu.addMenuItem(deleteItem);
			}
		};
		quickListView.setMenuCreator(creator);
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}

	private void mockData(){
		for (int i = 0; i < 30; i++) {
			QuickEnquiryInfo info=new QuickEnquiryInfo();
			info.setAddress("测试地址"+i);
			info.setManualTime(new Date(System.currentTimeMillis()));
			info.setMatchAddress("匹配地址"+i);
			info.setPhoto(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.ic_launcher));
			BasePrice basePrice=new BasePrice();
			basePrice.setUseType("用途"+i);
			basePrice.setPrice(i);
			info.setPrice(basePrice);
			DbMethod.save(info);
		}
	}
	
	private class GetDataTask extends AsyncTask<Void, Void, ArrayList<QuickEnquiryInfo>> {

		@Override
		protected ArrayList<QuickEnquiryInfo> doInBackground(Void... arg0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			initData();
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<QuickEnquiryInfo> result) {
			// 更新ListView数据
			adapter.notifyDataSetChanged();
			// 通知ListView已更新完毕
			quickListView.onLoadMoreComplete();
			super.onPostExecute(result);
		}
	}
}
