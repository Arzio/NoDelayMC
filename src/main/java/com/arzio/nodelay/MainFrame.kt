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

package com.arzio.nodelay

import com.arzio.nodelay.network.MainServer
import java.awt.Toolkit


object MainFrame : javax.swing.JFrame() {

    val address: String
        get() = this.txtServerIP.text ?: ""

    private var labelStatus: javax.swing.JLabel = javax.swing.JLabel()
    private var labelStatusMessage: javax.swing.JLabel = javax.swing.JLabel()
    private var labelTut0: javax.swing.JLabel = javax.swing.JLabel()
    private var labelTut1: javax.swing.JLabel = javax.swing.JLabel()
    private var labelTut2: javax.swing.JLabel = javax.swing.JLabel()
    private var labelTut3: javax.swing.JLabel = javax.swing.JLabel()
    private var labelTut4: javax.swing.JLabel = javax.swing.JLabel()
    private var txtServerIP: javax.swing.JTextField = javax.swing.JTextField()
    private var btnSocket: javax.swing.JToggleButton = javax.swing.JToggleButton()

    init {
        initComponents()

        // Centers the frame
        val dim = Toolkit.getDefaultToolkit().getScreenSize()
        this.setLocation(dim.width / 2 - this.size.width / 2, dim.height / 2 - this.size.height / 2)

        btnSocket.addActionListener {
            if (btnSocket.isSelected) {
                MainServer.start()
                btnSocket.text = "Stop Socket"
                txtServerIP.isEditable = false
            } else {
                MainServer.stop()
                txtServerIP.isEditable = true
                btnSocket.text = "Start NoDelay Socket"
            }
        }
    }

    fun setStatusMessage(text: String) {
        labelStatusMessage.text = text
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code by NetBeans WindowBuilder">
    private fun initComponents() {

        defaultCloseOperation = javax.swing.WindowConstants.EXIT_ON_CLOSE
        isResizable = false

        txtServerIP.text = "Server IP"

        labelStatus.text = "Status:"

        labelStatusMessage.text = "Waiting for start"

        btnSocket.text = "Start NoDelay Socket"

        labelTut0.text = "How to use:"

        labelTut1.text = "1 - Put the server IP you want to connect to"

        labelTut2.text = "2 - Start the NoDelay Socket (button above)"

        labelTut3.text = "3 - Go to Minecraft and connect to  \"localhost\" (without \")"

        labelTut4.text = "4 - Done. Keep this software open while playing."

        val layout = javax.swing.GroupLayout(contentPane)
        contentPane.layout = layout
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(labelStatus)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(labelStatusMessage))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtServerIP, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27))
                                                        .addComponent(labelTut1)
                                                        .addComponent(labelTut2)
                                                        .addComponent(labelTut3)
                                                        .addComponent(labelTut4)
                                                        .addComponent(labelTut0)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addComponent(btnSocket)))
                                .addContainerGap(27, java.lang.Short.MAX_VALUE.toInt()))
        )
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelStatus)
                                        .addComponent(labelStatusMessage))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtServerIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSocket)
                                .addGap(26, 26, 26)
                                .addComponent(labelTut0)
                                .addGap(18, 18, 18)
                                .addComponent(labelTut1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTut2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTut3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTut4)
                                .addGap(19, 19, 19))
        )

        pack()
    }// </editor-fold>
}
