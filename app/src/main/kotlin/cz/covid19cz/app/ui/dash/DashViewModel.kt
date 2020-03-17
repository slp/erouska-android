package cz.covid19cz.app.ui.dash

import cz.covid19cz.app.ui.base.BaseViewModel

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.content.Intent
import cz.covid19cz.app.bluetooth.BluetoothProvider
import cz.covid19cz.app.utils.isDisabled
import org.kodein.di.generic.instance

class DashViewModel(val app: Application) : BaseViewModel(app) {

    val bluetoothProvider: BluetoothProvider by instance()

}