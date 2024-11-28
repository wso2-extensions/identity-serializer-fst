# identity-serializer-fst

This is an extension that can be used to improve the DB performance by leveraging fast-serialization instead of the default Java serializer.

[![Stackoverflow](https://img.shields.io/badge/Ask%20for%20help%20on-Stackoverflow-orange)](https://stackoverflow.com/questions/tagged/wso2is)
[![Discord](https://img.shields.io/badge/Join%20us%20on-Discord-%23e01563.svg)](https://discord.gg/wso2)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/wso2/product-is/blob/master/LICENSE)
[![Twitter](https://img.shields.io/twitter/follow/wso2.svg?style=social&label=Follow)](https://twitter.com/intent/follow?screen_name=wso2)

## Building from the Source

1. Install Java SE Development Kit 11.
2. Install Apache Maven 3.x.x (https://maven.apache.org/download.cgi).
3. Get a clone or download the source from this repository (https://github.com/wso2-extensions/identity-serializer-fst).
4. Run the Maven command `mvn clean install` from the identity-serializer-fst directory.
5. Once the build completes, navigate to identity-serializer-fst/org.wso2.carbon.identity.serializer.fst/target to view the jar. 

## Instructions to Integrate the Extension with WSO2 Identity Server

### Step 1: Obtaining and adding the extension to WSO2 Identity Server
1. Download the latest released version of the jar from https://github.com/wso2-extensions/identity-serializer-fst/releases.
2. Alternatively, the jar can be built from the source as described above in [Building from the Source](#building-from-the-source).
3. Add the jar file to \<IS-HOME\>/repository/components/dropins.

### Step 2: Adding the Javassist libary
1. Download javassist [3.21.0-GA](https://repo1.maven.org/maven2/org/javassist/javassist/3.21.0-GA/javassist-3.21.0-GA.jar).
2. Place the downloaded javassist jar in \<IS-HOME\>/lib.

Javassist is a library used for bytecode instrumentation. To function correctly in an OSGi environment, it must reside on the parent classloader rather than the OSGi framework classloader. Therefore, the library should be placed in the common lib folder, ensuring it is accessible to the parent classloader.

### Step 3: Updating the launch.ini file
1. Add the following set of lines in between `com.sun.tools.internal.ws.spi` and  `org.wso2.carbon.bootstrap` in \<IS-HOME\>/repository/conf/etc/launch.ini.

  ```
  sun.misc,\
  sun.nio.ch,\
  sun.reflect,\
  com.sun.jdi,\
  com.sun.jdi.connect,\
  com.sun.jdi.event,\
  com.sun.jdi.request,\
  com.sun.tools.attach,\
  ```
