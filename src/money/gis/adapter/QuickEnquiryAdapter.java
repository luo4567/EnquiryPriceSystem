package money.gis.adapter;

import java.util.ArrayList;
import com.money.persistent.BasePrice;
import com.money.persistent.QuickEnquiryInfo;

import money.gis.welcome.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QuickEnquiryAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<QuickEnquiryInfo> data;

	public QuickEnquiryAdapter(Context context, ArrayList<QuickEnquiryInfo> data) {
		this.context = context;
		this.data = data;
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		private ImageView thumb_image;
		private TextView price;
		private TextView address;
		private TextView time;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.quick_listview_item, null);
			holder = new ViewHolder();
			holder.price = (TextView) convertView.findViewById(R.id.price); // 价格
			holder.address = (TextView) convertView.findViewById(R.id.address); // 地址
			holder.time = (TextView) convertView.findViewById(R.id.time); // 时间
			holder.thumb_image = (ImageView) convertView.findViewById(R.id.list_image); // 图片
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		QuickEnquiryInfo enquiryInfo = data.get(position);
		String priceInfo = "该地址没有价格";
		if (enquiryInfo.getPrice()!=null) {
			BasePrice basePrice = enquiryInfo.getPrice();
			priceInfo = String.format("用途：%s   价格：%s", basePrice.getUseType(), basePrice.getPrice());
		}

		holder.price.setText(priceInfo);
		holder.address.setText(enquiryInfo.getAddress());
		holder.time.setText(enquiryInfo.getManualTime().toString());
		holder.thumb_image.setImageBitmap(enquiryInfo.getPhoto());
		return convertView;
	}
}