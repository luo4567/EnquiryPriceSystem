package money.gis.fragment;

import money.gis.welcome.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ManualFragment extends Fragment {

	public static ManualFragment newInstance() {
		ManualFragment manualFragmentFragment = new ManualFragment();
		return manualFragmentFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.manual_fragment, container, false);
		return view;
	}

}
