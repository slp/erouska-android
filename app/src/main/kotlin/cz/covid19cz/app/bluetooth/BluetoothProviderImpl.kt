package cz.covid19cz.app.bluetooth

import android.Manifest
import android.app.Application
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import cz.covid19cz.app.utils.isDisabled

class BluetoothProviderImpl(private val app: Application) : BluetoothProvider {

    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = app.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    init {
        bluetoothAdapter?.takeIf { it.isDisabled }?.apply {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)

        }
    }

    override fun isBluetoothEnabled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //    private var location: Location? = null
//
//    private var geofences = HashMap<String, Geofence>()
//
//    private val geofencingClient by lazy { LocationServices.getGeofencingClient(app) }
//    private val geofencePendingIntent: PendingIntent by lazy {
//        PendingIntent.getBroadcast(
//            app,
//            0,
//            Intent(app, GeofenceBroadcastReceiver::class.java),
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//    }
//
//    override fun getLat() = location?.latitude
//
//    override fun getLon() = location?.longitude
//
//    override fun updateLocation(location: Location?) {
//        Timber.d("new location =" + location?.toString())
//        this.location = location
//    }
//
//    override fun hasGofence(uniqueName: String, geofence: Geofence) = geofences.contains(uniqueName) && geofences[uniqueName]!! == geofence
//
//    override fun createUniqueGeofence(uniqueName: String, geofence: Geofence) {
//        geofences[uniqueName] = geofence
//    }
//
//    override fun deleteGeofence(uniqueName: String) {
//        geofencingClient
//            .removeGeofences(listOf(uniqueName))
//            .addOnSuccessListener {
//                Timber.d("Geofence removed successfully")
//            }
//            .addOnFailureListener {
//                Timber.d("Geofence removed failed")
//            }
//    }
//
//    override fun deleteAllGeofences() {
//        geofences.clear()
//    }
//
//    override fun registerGeofencing(geofences: Map<String, Geofence>) {
//        if (geofences.isNotEmpty() && ContextCompat.checkSelfPermission(
//                app,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            geofencingClient
//                .addGeofences(buildGeofencingRequest(geofences), geofencePendingIntent)
//                .addOnSuccessListener {
//                    Timber.d("Geofence added successfully")
//                }
//                .addOnFailureListener {
//                    Timber.d("Geofence adding failed")
//                }
//        }
//    }
//
//    private fun buildGeofencingRequest(geofences: Map<String, Geofence>) = GeofencingRequest.Builder()
//        .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
//        .addGeofences(geofences.values.toList())
//        .build()
}