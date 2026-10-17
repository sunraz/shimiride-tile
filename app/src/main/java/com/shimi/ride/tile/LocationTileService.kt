package com.shimi.ride.tile

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class LocationTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        updateTile()
    }

    override fun onClick() {
        super.onClick()
        val running = LocationForegroundService.isRunning
        if (running) {
            LocationForegroundService.stop(this)
        } else {
            LocationForegroundService.start(this)
        }
        updateTile()
    }

    private fun updateTile() {
        val tile = qsTile ?: return
        val granted = LocationForegroundService.hasLocationPermission(this)
        val running = LocationForegroundService.isRunning
        if (!granted) {
            tile.state = Tile.STATE_UNAVAILABLE
        } else {
            tile.state = if (running) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        }
        tile.subtitle = getString(if (running) R.string.tile_on else R.string.tile_off)
        tile.updateTile()
    }
}
