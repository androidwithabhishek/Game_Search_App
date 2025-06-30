package abhishek.gupta.coreDatabase.di

import abhishek.gupta.coreDatabase.AppDatabase
import abhishek.gupta.coreDatabase.SqlDriverFactory
import app.cash.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getCoreDatabaseModule(): Module {
    return module {
        single { SqlDriverFactory().getSqlDriver() }

        single { AppDatabase.invoke(get<SqlDriver>()) }


    }
}