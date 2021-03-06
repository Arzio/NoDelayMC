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
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.UnknownHostException
import java.util.ArrayList
import java.util.Collections

object MainServer {

    private var mainSocket: ServerSocket? = null
    private val ACTIVE_CONNECTIONS = Collections.synchronizedList(ArrayList<Connection>())

    fun stop() {
        mainSocket!!.close()
        ACTIVE_CONNECTIONS.forEach{ it.closeSafely() }
        ACTIVE_CONNECTIONS.clear()

        MainFrame.setStatusMessage("Waiting for start")
    }

    fun removeConnection(connection: Connection) {
        if (ACTIVE_CONNECTIONS.remove(connection)){
            println("One connection has been terminated, there are " + ACTIVE_CONNECTIONS.size + " active connections now.")
        }
    }

    fun start() {
        MainFrame.setStatusMessage("Waiting for connection")
        mainSocket = ServerSocket(25565)
        Thread {
            val keepRunning = true

            try {
                while (keepRunning) {
                    val client = mainSocket!!.accept()
                    MainFrame.setStatusMessage("Connecting...")

                    val server = Socket()
                    client.tcpNoDelay = true
                    server.tcpNoDelay = true

                    val address = ServerAddress.getFrom(MainFrame.address)

                    server.connect(InetSocketAddress(InetAddress.getByName(address.ip), address.port.toInt()))
                    MainFrame.setStatusMessage("Connected!")

                    val connection = Connection(this, client, server)
                    ACTIVE_CONNECTIONS.add(connection)
                    println("Established new connection, now we have " + ACTIVE_CONNECTIONS.size + " active connections")
                    connection.start()
                }
            } catch (e: UnknownHostException) {
                MainFrame.setStatusMessage("Invalid IP!")
            } catch (e: IOException) {
                MainFrame.setStatusMessage("Connection closed.")
            }

            try {
                mainSocket!!.close()
            } catch (e: IOException) {
                //
            }
        }.start()
    }
}
