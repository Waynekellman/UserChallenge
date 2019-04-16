package wayne.com.userchallenge.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import wayne.com.userchallenge.data.UserRepository
import wayne.com.userchallenge.viewmodels.MainViewModel


val retrofitModule = module {
    single {Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()}
    single { UserRepository(get()) }
    viewModel {MainViewModel(get())}
}
