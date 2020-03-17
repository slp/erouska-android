package cz.covid19cz.app.di

import cz.covid19cz.app.bluetooth.BluetoothProvider
import cz.covid19cz.app.bluetooth.BluetoothProviderImpl
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME = "App Module"

val appModule = Module(MODULE_NAME, false) {
    bind<BluetoothProvider>() with singleton { BluetoothProviderImpl(instance()) }
}