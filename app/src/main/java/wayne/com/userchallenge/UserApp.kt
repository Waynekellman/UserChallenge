package wayne.com.userchallenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import wayne.com.userchallenge.modules.retrofitModule

class UserApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UserApp)
            modules(retrofitModule)
        }
    }
}