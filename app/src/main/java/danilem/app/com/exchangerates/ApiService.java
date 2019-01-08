package danilem.app.com.exchangerates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/p24api/pubinfo?")
    Call<List<Module>> getExchangeRates(
            @Query("json") String json,
            @Query("exchange") String exchange,
            @Query("coursid") int coursid
    );

}
