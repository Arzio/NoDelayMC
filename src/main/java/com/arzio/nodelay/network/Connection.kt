/*
 * This file is part of NoDelayMC.
 *
 * NoDelayMC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoDelayMC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NoDelayMC.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.arzio.nodelay.network

import com.arzio.nodelay.MainFrame
import java.io.IOException
import java.net.Socket

class Connection(private val main: MainServer, private val client: Socket, private val server: Socket) {

    @Throws(IOException::class)
    fun start() {
        val clientToServer = NoDelayThread(this, client.getInputStream(), server.getOutputStream())
        clientToServer.start()

        val serverToClient = NoDelayThread(this, server.getInputStream(), client.getOutputStream())
        serverToClient.start()
    }

    fun closeSafely() {
        MainFrame.setStatusMessage("Connection closed.")
        try {
            this.client.close()
        } catch (e: Exception) {
            //
        }

        try {
            this.server.close()
        } catch (e: Exception) {
            //
        }
    }

    fun closeAndRemove() {
        this.closeSafely()
        main.removeConnection(this)
    }
}
