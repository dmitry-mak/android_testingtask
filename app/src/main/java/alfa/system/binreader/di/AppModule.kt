package alfa.system.binreader.di

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
}