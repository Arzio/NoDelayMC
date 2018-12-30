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

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class NoDelayThread(private val connection: Connection, private val from: InputStream, private val to: OutputStream) : Thread() {

    init {
        this.priority = Thread.MAX_PRIORITY
    }

    override fun run() {
        try {
            val input = from
            val output = to
            val b = ByteArray(4096)

            while (true) {
                val len = input.read(b)
                output.write(b, 0, len)
            }

        } catch (e: IOException) {
            //
        } catch (e: ArrayIndexOutOfBoundsException){
            //
        }
        this.connection.closeAndRemove()
    }

}
