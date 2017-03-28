package utils;

import java.util.List;

import model.QuoteBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 89003337 on 2017/3/27.
 */

public interface IQuote {
    //get quote and set params with @query
    @GET("wp-json/posts/")
    Call<List<QuoteBean>> getQuote(@Query("filter[orderby]") String orderBy, @Query("filter[posts_per_page]") String page);
}
