package nyc.c4q.MovieDBUserTest.Service;

import nyc.c4q.MovieDBUserTest.API.MovieDBService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c4q on 1/8/18.
 */

public class MovieDatabaseServiceGenerator {

  private static final String BASE_URL = "https://api.themoviedb.org/";

  private static Retrofit.Builder builder =
      new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

  private static Retrofit retrofit = builder.build();

  private static HttpLoggingInterceptor loggingInterceptor =
      new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

  private static OkHttpClient.Builder okhttpclient = new OkHttpClient.Builder();

  public static MovieDBService createService() {
    if (!okhttpclient.interceptors().contains(loggingInterceptor)) {
      okhttpclient.addInterceptor(loggingInterceptor);
      builder.client(okhttpclient.build());
      retrofit = builder.build();
    }
    return retrofit.create(MovieDBService.class);
  }

}
