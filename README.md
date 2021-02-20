# NoDelayMC
### Play with slightly less ping on 1.7 (or older) Minecraft Servers
#### Note: This is for clients only.

#### [Download it on the releases page NOW!](https://github.com/Arzio/NoDelayMC/releases)

## How this software works?
This software opens a secondary TCP connection with TCP_NODELAY option set to true.
By connecting through your 'localhost' address, you will be using this NODELAY connection to connect to the Minecraft server.

## Why this does not work on 1.8+?
Minecraft 1.8+ already have TCP_NODELAY option set to true.

## How can I use it?
The instructions are already in the software.
