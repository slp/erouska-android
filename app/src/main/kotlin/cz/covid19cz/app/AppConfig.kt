package cz.covid19cz.app

import cz.covid19cz.app.utils.L

object AppConfig {

    const val CSV_VERSION = 3

    const val collectionSeconds: Long = 120
    const val waitingSeconds: Long = 0
    const val advertiseTxPower = 2
    const val advertiseMode = 1
    const val scanMode = 1
    const val smsTimeoutSeconds = 20
    const val advertiseRestartMinutes: Long = 60
    const val criticalExpositionRssi = -75
    // unused
    const val criticalExpositionMinutes = 0
    const val uploadWaitingMinutes = 15
    const val persistDataDays = 14
    const val shareAppDynamicLink = ""
    const val faqLink = ""
    const val importantLink = ""
    const val emergencyNumber = ""
    const val proclamationLink = ""
    const val tutorialLink = ""

    var overrideAdvertiseTxPower : Int? = null

    const val registerPhone = "http://192.168.0.115:8000/registerPhone"
}