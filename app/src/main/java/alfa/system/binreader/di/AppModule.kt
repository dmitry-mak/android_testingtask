package alfa.system.binreader.di

import alfa.system.binreader.data.remote.api.BinApiService
import alfa.system.binreader.data.repository.BinRepositoryImpl
import alfa.system.binreader.data.repository.MockBinRepository
import alfa.system.binreader.domain.repository.BinRepository
import alfa.system.binreader.domain.usecase.BinSearchUseCase
import alfa.system.binreader.uiscreens.search.BinSearchViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    // single { OkHttpClient.Builder()...build() }
    // single { Retrofit.Builder()...build() }
    // single<BinlistApi> { get<Retrofit>().create(BinlistApi::class.java) }
    // single { Room.databaseBuilder(...).build() }
    // single { get<AppDatabase>().dao() }
    // single<BinRepository> { BinRepositoryImpl(get(), get()) }
    // factory { LookupBinUseCase(get()) }
    // viewModel { BinSearchViewModel(get()) }
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
//    создание логгера OkHttp для записи request/response в logcat
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

//    создание HTTP клиента. .addInterceptor- подключение логирования ко всем полям
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

//    создание retrofit-instance
    single {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/") // базовый url
            .client(get())                            // использование созданного OkHttpClient
            .addConverterFactory(get<Json>().asConverterFactory(contentType)) // конвертирует Json -> Dto через kotlinx.serialization
            .build()
    }

// создание реализации интерфейса BinApiService через retrofit
    single { get<Retrofit>().create(BinApiService::class.java) }

//  при запросе BinRepository выдает BinRepositoryImpl и передает в него BinApiService
    single<BinRepository> { BinRepositoryImpl(api = get()) }

//    создает новый объект UseCase. В конструктор передается BinRepository
    factory { BinSearchUseCase(binRepository = get()) }

//    Для Koin: регистрирует BinSearchViewModel, чтобы "жила" как ViewModel. Можно получить в Compose через koinViewModel
    viewModel { BinSearchViewModel(binSearchUseCase = get()) }

//    single<BinRepository> { MockBinRepository(json = get()) }
//    factory { BinSearchUseCase(binRepository = get()) }
//    viewModel { BinSearchViewModel(binSearchUseCase = get()) }
}