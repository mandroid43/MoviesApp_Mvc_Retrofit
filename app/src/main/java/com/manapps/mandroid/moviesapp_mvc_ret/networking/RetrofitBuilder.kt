
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.manapps.mandroid.moviesapp_mvc_ret.networking.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    /* If you want to print api responses to Log then use below client   */
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
   /* If you don't want to print api responses to Log then use below client   */
  //  private val client = OkHttpClient.Builder().build()

    private val gson: Gson = GsonBuilder().create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun getApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}