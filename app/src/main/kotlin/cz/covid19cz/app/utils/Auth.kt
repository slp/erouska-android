package cz.covid19cz.app.utils

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import cz.covid19cz.app.R
import cz.covid19cz.app.db.SharedPrefsRepository
import cz.covid19cz.app.service.CovidService
import cz.covid19cz.app.ui.base.BaseFragment
import org.koin.core.KoinComponent
import org.koin.core.inject

object Auth: KoinComponent {

    private val prefs: SharedPrefsRepository by inject()

    fun isSignedIn(): Boolean {
        return prefs.getDeviceBuid() != null
    }

    fun getFuid(): String {
        //return checkNotNull(auth.currentUser?.uid)
        return "fuid"
    }

    fun getPhoneNumber(): String {
        //return checkNotNull(auth.currentUser?.phoneNumber)
        return "phoneNumber"
    }

    fun signOut() {
        //auth.signOut()
    }
}

fun BaseFragment<*,*>.logoutWhenNotSignedIn() {
    with(requireContext()){
        startService(CovidService.stopService(this))
    }
    //Auth.signOut()

    val nav = findNavController()
    nav.popBackStack(R.id.nav_graph, false)
    nav.navigate(R.id.nav_welcome_fragment)
    Toast.makeText(this.context, getString(R.string.automatic_logout_warning), Toast.LENGTH_LONG).show()
}
