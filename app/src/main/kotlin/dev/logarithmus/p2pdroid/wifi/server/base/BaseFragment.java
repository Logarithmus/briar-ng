package dev.logarithmus.p2pdroid.wifi.server.base;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import android.content.Context;

public class BaseFragment extends Fragment {

    protected Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
