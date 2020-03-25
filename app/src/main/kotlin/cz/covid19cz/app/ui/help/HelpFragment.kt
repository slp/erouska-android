package cz.covid19cz.app.ui.help

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import cz.covid19cz.app.R
import cz.covid19cz.app.databinding.FragmentHelpBinding
import cz.covid19cz.app.ui.base.BaseFragment
import cz.covid19cz.app.ui.help.event.HelpCommandEvent
import kotlinx.android.synthetic.main.fragment_help.welcome_continue_btn

class HelpFragment : BaseFragment<FragmentHelpBinding, HelpVM>(R.layout.fragment_help, HelpVM::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribe(HelpCommandEvent::class) {
            when (it.command) {
                HelpCommandEvent.Command.GO_BACK -> goBack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enableUpInToolbar(arguments?.getBoolean("fullscreen") == true, IconType.CLOSE)

        if(arguments?.getBoolean("fullscreen") == true){
            welcome_continue_btn.visibility = View.VISIBLE
        } else {
            welcome_continue_btn.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.help_loggedin, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                navigate(R.id.nav_about)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goBack() {
        navController().navigateUp()
    }
}
