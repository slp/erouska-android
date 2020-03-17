package cz.covid19cz.app.bluetooth

interface BluetoothProvider {

    fun isBluetoothLESupported(): Boolean
    fun isBluetoothEnabled(): Boolean
    fun isBluetoothDisabled(): Boolean
    fun disableBluetooth()
}