# identity-serializer-fst
Fast serialization implementation that can be used instead of default java serializer, which gives better DB performance
## Instructions to integrate with wso2 identity server

- Add the build jar file to <IS-HOME>/repository/components/dropins
- Download javassist [3.21.0-GA](https://repo1.maven.org/maven2/org/javassist/javassist/3.21.0-GA/javassist-3.21.0-GA.jar) and place in <IS-HOME>/lib
  Javassist is a library to instrument the bytecode. This library need to be on parent class loader than the OSGI framework classloader. 
  Hence it need to be placed in common lib folder.
- Add the following set of lines in between **com.sun.tools.internal.ws.spi** and  **org.wso2.carbon.bootstrap** in <IS-HOME>/repository/conf/etc/launch.ini
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
  