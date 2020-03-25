package cz.covid19cz.app.ui.about

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import cz.covid19cz.app.R
import cz.covid19cz.app.ui.about.entity.AboutIntroItem
import cz.covid19cz.app.ui.about.entity.AboutItem
import cz.covid19cz.app.ui.about.entity.AboutProfileItem
import cz.covid19cz.app.ui.about.entity.AboutRoleItem
import cz.covid19cz.app.ui.base.BaseVM
import cz.covid19cz.app.ui.base.UrlEvent
import cz.covid19cz.app.utils.ResourceProvider
import java.util.*


class AboutVM(private val provider: ResourceProvider) : BaseVM() {
    val items = ObservableArrayList<AboutItem>()
    private val functions = Firebase.functions("europe-west2")

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        updateTeamInfo(buildDefaultTeam(provider))
        fetchTeamInfo()
    }

    fun profileClick(item: AboutProfileItem) {
        publish(UrlEvent(item.linkedin))
    }

    fun logoClick(url : String) {
        publish(UrlEvent(url))
    }

    private fun updateTeamInfo(newItems: List<AboutItem>) {
        items.clear()
        items.add(AboutIntroItem)
        items.addAll(newItems)
    }

    private fun fetchTeamInfo() {
        val params = hashMapOf(
            "locale" to Locale.getDefault().toString()
        )
        functions.getHttpsCallable("aboutTeam").call(params).addOnSuccessListener {
            val items = it.data as? List<*> ?: return@addOnSuccessListener
            updateTeamInfo(buildTeam(items))
        }.addOnFailureListener {
            Log.e("get-team-info", it.toString())
        }
    }
}

fun buildDefaultTeam(provider: ResourceProvider): List<AboutItem> {
    val managers = AboutRoleItem(provider.getString(R.string.about_managers))
    val appDevelopers = AboutRoleItem(provider.getString(R.string.about_app_devs))
    val backendDevelopers = AboutRoleItem(provider.getString(R.string.about_backend_devs))
    val uxui = AboutRoleItem(provider.getString(R.string.about_ux_ui))

    managers.add(
        AboutProfileItem(
            "Vojtěch",
            "Komenda",
            "URL", // TODO
            "https://www.linkedin.com/in/vojtech-komenda-71365b15/"
        )
    )
    managers.add(
        AboutProfileItem(
            "Roman",
            "Pichlík",
            "URL", // TODO
            "https://www.linkedin.com/in/romanpichlik/"
        )
    )

    appDevelopers.add(
        AboutProfileItem(
            "Štěpán",
            "Šonský",
            "URL", // TODO
            "https://www.linkedin.com/in/stepansonsky/"
        )
    )
    appDevelopers.add(
        AboutProfileItem(
            "David",
            "Vávra",
            "URL", // TODO
            "https://www.linkedin.com/in/dvavra/"
        )
    )
    appDevelopers.add(
        AboutProfileItem(
            "Tomáš",
            "Chlápek",
            "URL", // TODO
            "https://www.linkedin.com/in/tom%C3%A1%C5%A1-chl%C3%A1pek-63852266/"
        )
    )

    return listOf(
        managers,
        appDevelopers,
        backendDevelopers,
        uxui
    )
}
private fun buildTeam(items: List<*>): List<AboutItem> {
    val rows = mutableListOf<AboutItem>()

    for (item in items) {
        if (item !is HashMap<*, *>) continue
        val roleName = item["name"] as? String ?: continue
        val role = AboutRoleItem(roleName)

        val people = item["people"] as? List<*> ?: continue
        for (person in people) {
            if (person !is HashMap<*, *>) continue
            val name = person["name"] as? String ?: continue
            val surname = person["surname"] as? String ?: continue
            val linkedin = person["linkedin"] as? String ?: continue
            val photoURL = person["photoUrl"] as? String ?: continue

            role.add(AboutProfileItem(name, surname, photoURL, linkedin))
        }
        rows.add(role)
    }

    return rows
}
