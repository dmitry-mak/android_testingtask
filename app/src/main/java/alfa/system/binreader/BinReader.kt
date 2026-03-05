package alfa.system.binreader

import alfa.system.binreader.di.appModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BinReader : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@BinReader)
            modules(appModule)
        }
    }
}
