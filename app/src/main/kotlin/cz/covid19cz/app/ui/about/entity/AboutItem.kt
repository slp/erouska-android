package cz.covid19cz.app.ui.about.entity

import androidx.databinding.ObservableArrayList

sealed class AboutItem

object AboutIntroItem : AboutItem()

data class AboutProfileItem(
    val name: String,
    val surname: String,
    val photoUrl: String,
    val linkedin: String
) : AboutItem()

data class AboutRoleItem(val title: String) : AboutItem() {
    val items = ObservableArrayList<AboutProfileItem>()

    fun add(item: AboutProfileItem) {
        items.add(item)
    }
}
