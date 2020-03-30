package cz.covid19cz.app.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import cz.covid19cz.app.AppConfig
import cz.covid19cz.app.db.SharedPrefsRepository
import cz.covid19cz.app.ui.base.BaseVM
import cz.covid19cz.app.utils.L
import cz.covid19cz.app.utils.toText
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONObject

class LoginVM(
    private val sharedPrefsRepository: SharedPrefsRepository
) : BaseVM() {

    private val mutableState = MutableLiveData<LoginState>(EnterPhoneNumber(false))
    val state = mutableState as LiveData<LoginState>

    private lateinit var verificationId: String
    private lateinit var phoneNumber: String

    init {
        if (sharedPrefsRepository.getDeviceBuid() == null) {
            //registerDevice()
        } else {
            getUser()
        }
    }

    fun phoneNumberEntered(phoneNumber: String) {
        this.phoneNumber = phoneNumber
        if (phoneNumber.length == 12) {
            mutableState.postValue(StartVerification)
        } else {
            mutableState.postValue(EnterPhoneNumber(true))
        }
    }

    fun codeEntered(code: String) {
        if (code.trim().length != 6) {
            mutableState.postValue(EnterCode(true, phoneNumber))
        } else {
            mutableState.postValue(SigningProgress)
            //signInWithPhoneAuthCredential(credential)
        }
    }

    fun backPressed() {
        mutableState.postValue(EnterPhoneNumber(false))
    }

    private fun handleError(e: Exception) {
        L.e(e)
        mutableState.postValue(LoginError(e.message?.toText()))
    }

    fun registerPhone(requestQueue: RequestQueue, phoneNumber: String) {
        val jsonObject = JSONObject()
        jsonObject.put("phone_number", phoneNumber)
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, AppConfig.registerPhone,
            jsonObject,
            Response.Listener<JSONObject> { response ->
                L.d("verifyPhoneNumber success")
                if (response.has("buid")) {
                    val buid = response.getString("buid")
                    if (buid != "") {
                        L.d("verifyPhoneNumber: buid=$buid")
                        sharedPrefsRepository.putDeviceBuid(buid)
                        mutableState.postValue(SignedIn(buid))
                        return@Listener
                    }
                }
                L.e("verifyPhoneNumber: invalid answer")
                mutableState.postValue(LoginError("Invalid answer".toText()))
            },
            Response.ErrorListener {
                L.d("verifyPhoneNumber error")
            }
        )
        requestQueue.add(jsonRequest)
    }

    private fun getUser() {
        val buid = checkNotNull(sharedPrefsRepository.getDeviceBuid())
        mutableState.postValue(SignedIn(buid))
    }
}
