# NoDelayMC

### Play with slightly less delay on 1.7 (or older) Minecraft Servers
#### Note: This is for clients only.

## How this software works?
This software opens an TCP connection with TCP_NODELAY option set to true.
By connecting through your 'localhost' address, you will be using this connection with this option set to true.

## Why this does not work on 1.8+?
Minecraft 1.8+ already have TCP_NODELAY option set to true.

## Where can I download it?
You will need to build it.

First, clone or download this repository. Personally, I recommend you to use IntelliJ IDEA and clone it. 

By supposing you are using IntelliJ IDEA, after cloning the repository, select the "Export Jar" gradle task and run it.
This will build and generate the .jar file in the build/libs/ folder. The build/libs/ folder is in the same folder of your project.

## How can I use it?
The instructions are already in the software. It's very easy to use.
