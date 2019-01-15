package danilem.app.com.exchangerates.fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import danilem.app.com.exchangerates.ApiService;
import danilem.app.com.exchangerates.ExchangeRatesAdapter;
import danilem.app.com.exchangerates.Module;
import danilem.app.com.exchangerates.R;
import danilem.app.com.exchangerates.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {

    TextView extraR;
    TextView nationalCode;
    ProgressBar loading;
    RecyclerView recyclerView;
    Context context;

    String json = "json";
    String exchange = "exchange";
    int coursid = 5;

    public BottomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiService service = RetroClient.getApiService();

        Call <List<Module>> call = service.getExchangeRates(json, exchange, coursid);

        call.enqueue(new Callback<List<Module>>() {
            @Override
            public void onResponse(Call<List<Module>> call, Response<List<Module>> response) {
                List<Module> infoResponse = response.body();

                if(response.isSuccessful()) {

//                    ArrayList<Double> buy = new ArrayList<>();
//                    ArrayList<Double> sale = new ArrayList<>();
//
//                    for(int i = 0; i<infoResponse.size(); i++){
//                        double buyAround = Double.parseDouble(infoResponse.get(i).getBuy());
//                        round(buyAround, 2);
//                        buy.add(buyAround);
//
//                        double saleAround = Double.parseDouble(infoResponse.get(i).getSale());
//                        round(saleAround, 2);
//                        sale.add(saleAround);
//                    }
//
//                    String stringBilder =
//                            "Код Валюты: " + infoResponse.get(0).getCcy() + "\n" +
//                            "Курс покупки: " + buy.get(0) + "\n" +
//                            "Курс продажи: " + sale.get(0) + "\n" + "\n" +
//
//                            "Код Валюты: " + infoResponse.get(1).getCcy() + "\n" +
//                            "Курс покупки: " + buy.get(1) + "\n" +
//                            "Курс продажи: " + sale.get(1) + "\n" + "\n" +
//
//                            "Код Валюты: " + infoResponse.get(2).getCcy() + "\n" +
//                            "Курс покупки: " + buy.get(2) + "\n" +
//                            "Курс продажи: " + sale.get(2);

                    nationalCode.setText("Код национальной валюты: " + infoResponse.get(0).getBaseCcy() + "\n");
//                    extraR.setText(stringBilder);

                    ExchangeRatesAdapter exchangeRatesAdapter = new ExchangeRatesAdapter(context, infoResponse);
                    recyclerView.setAdapter(exchangeRatesAdapter);

                    loading.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Module>> call, Throwable t) {
                extraR.setText("Ошибка загрузки данных! " + "\n" +
                        "Возможны неполадки с сервером, или " +
                        "проверьте наличие интернета на устройстве и перезапустите приложение.");
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

//        extraR = view.findViewById(R.id.tv_exchangeRates_bottomfragment);
        nationalCode = view.findViewById(R.id.tv_nationalCode_bottomfragment);
        loading = view.findViewById(R.id.pb_loading_bottomfragment);
        recyclerView = view.findViewById(R.id.rc_exchangeRate);

        context = inflater.getContext();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        loading.setVisibility(View.VISIBLE);

        return view;
    }

//    private double round(double number, int scale) {
//        int pow = 10;
//        for (int i = 1; i < scale; i++)
//            pow *= 10;
//        double tmp = number * pow;
//        return (double) (int) ((tmp - (int) tmp) >= 0.5 ? tmp + 1 : tmp) / pow;
//    }

}
