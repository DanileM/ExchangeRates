package danilem.app.com.exchangerates.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import danilem.app.com.exchangerates.ApiService;
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

                    ArrayList<Double> buy = new ArrayList<>();
                    ArrayList<Double> sale = new ArrayList<>();

                    for(int i = 0; i<infoResponse.size(); i++){
                        double buyAround = Double.parseDouble(infoResponse.get(i).getBuy());
                        buyAround = Math.round(buyAround * 100.0) / 100.0;
                        buy.add(buyAround);

                        double saleAround = Double.parseDouble(infoResponse.get(i).getSale());
                        saleAround = Math.round(saleAround * 100.0) / 100.0;
                        sale.add(saleAround);
                    }

                    String stringBilder =
                            "Код Валюты: " + infoResponse.get(0).getCcy() + "\n" +
                            "Курс покупки: " + buy.get(0) + "\n" +
                            "Курс продажи: " + sale.get(0) + "\n" + "\n" +

                            "Код Валюты: " + infoResponse.get(1).getCcy() + "\n" +
                            "Курс покупки: " + buy.get(1) + "\n" +
                            "Курс продажи: " + sale.get(1) + "\n" + "\n" +

                            "Код Валюты: " + infoResponse.get(2).getCcy() + "\n" +
                            "Курс покупки: " + buy.get(2) + "\n" +
                            "Курс продажи: " + sale.get(2);

                    nationalCode.setText("Код национальной валюты: " + infoResponse.get(0).getBaseCcy() + "\n");
                    extraR.setText(stringBilder);

                    loading.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Module>> call, Throwable t) {
                extraR.setText("Ошибка загрузки данных! Проверьте наличие интернета на устройстве и перезапустите приложение.");
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        extraR = view.findViewById(R.id.tv_exchangeRates_bottomfragment);
        nationalCode = view.findViewById(R.id.tv_nationalCode_bottomfragment);
        loading = view.findViewById(R.id.pb_loading_bottomfragment);

        loading.setVisibility(View.VISIBLE);

        return view;
    }

}
