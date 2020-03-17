package cz.covid19cz.app.utils

import android.bluetooth.BluetoothAdapter

val BluetoothAdapter.isDisabled: Boolean
    get() = !isEnabled