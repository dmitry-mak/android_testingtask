package alfa.system.binreader.di

import alfa.system.binreader.data.repository.MockBinRepository
import alfa.system.binreader.domain.repository.BinRepository
import alfa.system.binreader.domain.usecase.BinSearchUseCase
import alfa.system.binreader.uiscreens.search.BinSearchViewModel
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


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
    single<BinRepository> { MockBinRepository(json = get()) }
    factory { BinSearchUseCase(binRepository = get()) }
    viewModel { BinSearchViewModel(binSearchUseCase = get())  }
}