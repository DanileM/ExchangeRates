package danilem.app.com.exchangerates.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import danilem.app.com.exchangerates.R;

public class TopFragment extends Fragment {

    TextView tvDateTopfragment;

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        tvDateTopfragment = view.findViewById(R.id.tv_date_topfragment);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM yyyy");
        String datetime = dateformat.format(c.getTime());

        tvDateTopfragment.setText(datetime);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
