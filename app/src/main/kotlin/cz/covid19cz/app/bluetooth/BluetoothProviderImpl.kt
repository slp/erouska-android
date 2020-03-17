package cz.covid19cz.app.bluetooth

import android.Manifest
import android.app.Application
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import cz.covid19cz.app.utils.isDisabled

class BluetoothProviderImpl(private val app: Application) : BluetoothProvider {

    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = app.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    override fun isBluetoothEnabled() = bluetoothAdapter?.isEnabled == true

    override fun isBluetoothDisabled() = bluetoothAdapter?.isDisabled == true

    override fun isBluetoothLESupported() =
        app.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

    override fun disableBluetooth() {
        bluetoothAdapter?.disable()
    }
}