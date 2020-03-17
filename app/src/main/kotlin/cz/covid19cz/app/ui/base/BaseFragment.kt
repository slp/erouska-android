package cz.covid19cz.app.ui.base

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import cz.covid19cz.app.di.fragmentModule
import cz.covid19cz.app.utils.REQUEST_ENABLE_BT
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

abstract class BaseFragment : Fragment(), KodeinAware {
    override val kodeinContext: KodeinContext<*> get() = kcontext(activity)
    private val _parentKodein by closestKodein()

    override val kodein: Kodein = Kodein.lazy {
        extend(_parentKodein, copy = Copy.All)
        import(fragmentModule)
    }

    protected val viewModelFactory: ViewModelProvider.Factory by instance()

    protected fun showSnackbar(@StringRes stringRes: Int) {
        showSnackbar(getString(stringRes))
    }

    protected fun showSnackbar(message: String) {
        activity?.let {
            Snackbar.make(it.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
        }
    }

    protected fun enableBluetooth() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
    }


}