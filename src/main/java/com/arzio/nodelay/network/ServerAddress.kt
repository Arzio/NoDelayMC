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

import com.sun.jndi.dns.DnsContextFactory
import java.util.*
import javax.naming.Context
import javax.naming.directory.InitialDirContext

data class ServerAddress constructor(val ip: String, val port: String){

    companion object {
        private const val defaultPort = "25565"

        fun getFrom(address: String): ServerAddress {
            var addressArray = address.split(":".toRegex()).toTypedArray()

            val host = addressArray[0]
            val port = if (addressArray.size > 1) addressArray[1] else defaultPort

            return if (port == defaultPort) getTranslatedAddress(host) else ServerAddress(host, port)
        }

        private fun getTranslatedAddress(address: String): ServerAddress {
            return try {
                val hashtable = Hashtable<String, String>()

                hashtable[Context.INITIAL_CONTEXT_FACTORY] = DnsContextFactory::class.qualifiedName
                hashtable[Context.PROVIDER_URL] = "dns:"

                val srvAttributes = InitialDirContext(hashtable)
                        .getAttributes("_minecraft._tcp.$address", arrayOf("SRV"))
                        .get("srv").get().toString().split(" ".toRegex()).toTypedArray()

                ServerAddress(srvAttributes[3], srvAttributes[2])
            } catch (t: Throwable) {
                ServerAddress(address, defaultPort)
            }
        }
    }
}
