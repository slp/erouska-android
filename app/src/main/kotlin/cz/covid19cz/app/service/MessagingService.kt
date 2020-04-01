package cz.covid19cz.app.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import cz.covid19cz.app.bt.BluetoothRepository
import cz.covid19cz.app.db.SharedPrefsRepository
import cz.covid19cz.app.ext.execute
import cz.covid19cz.app.ext.isLocationEnabled
import cz.covid19cz.app.utils.L
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class MessagingService : FirebaseMessagingService() {
    private var bleAdvertisingDisposable: Disposable? = null
    private var bleScanningDisposable: Disposable? = null
    private val btUtils by inject<BluetoothRepository>()
    private val prefs by inject<SharedPrefsRepository>()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        L.d("From: ${remoteMessage.from}")

        prefs.getDeviceBuid()?.let { buid ->
            if (remoteMessage.data.isNotEmpty()) {
                L.d("Message data payload: " + remoteMessage.data)
                var scanDuration: Long = 60
                remoteMessage.data["scanDuration"]?.let {
                    scanDuration = it.toLong()
                }

                var advertisingDuration: Long = 90
                remoteMessage.data["advertisingDuration"]?.let {
                    advertisingDuration = it.toLong()
                }

                startBleAdvertising(buid, advertisingDuration)
                startBleScanning(scanDuration)
            }
        }
    }

    override fun onNewToken(token: String) {
        L.d("Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        L.d("sendRegistrationTokenToServer($token)")
    }

    private fun startBleScanning(scanDuration: Long) {
        bleScanningDisposable = Observable.just(true)
            .doOnNext {
                if (btUtils.isBtEnabled() && isLocationEnabled()) {
                    btUtils.startScanning()
                } else {
                    bleScanningDisposable?.dispose()
                }
            }
            .delay(scanDuration, TimeUnit.SECONDS)
            .doOnNext {
                btUtils.stopScanning()
            }
            .execute(
                { L.d("BLE scanning stopped") },
                {
                    CovidService.pause(this)
                    L.e(it)
                }
            )
    }

    private fun startBleAdvertising(deviceBuid: String, advertisingDuration: Long) {
        bleAdvertisingDisposable = Observable.just(true)
            .doOnNext {
                if (btUtils.isBtEnabled()) {
                    btUtils.startAdvertising(deviceBuid)
                } else {
                    bleAdvertisingDisposable?.dispose()
                }
            }
            .delay(advertisingDuration, TimeUnit.SECONDS)
            .doOnNext { btUtils.stopAdvertising() }
            .execute(
                { L.d("BLE advertising stopped") },
                { L.e(it) }
            )
    }
}