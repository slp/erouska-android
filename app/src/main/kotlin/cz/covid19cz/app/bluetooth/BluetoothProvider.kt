package cz.covid19cz.app.bluetooth

interface BluetoothProvider {
    fun isBluetoothEnabled(): Boolean
}