package com.shimi.ride.tile

object Constants {
    // החוזה הקיים של שימי ניווט (אומת חי 17/10)
    const val REPORT_PRIMARY = "https://base44.app/api/apps/6a9292482a7b8be0f73f5a28/functions/reportLocation"
    const val REPORT_FALLBACK = "https://my.shimi.land/location/report.php"
    const val TOKEN = "shimi-gps-8427-nes-ziona"
    const val SOURCE = "tile"
    const val INTERVAL_MS = 5000L
    const val MIN_DISTANCE_M = 3f
    const val NOTIF_ID = 1
    const val CHANNEL_ID = "shimiride_location"
}
