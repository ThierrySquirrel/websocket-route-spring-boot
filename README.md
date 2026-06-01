# container-websocket-route

Websocket Route

[中文](./README_zh_CN.md)

Support Function:

- [x] WebsocketRoute

# WebsocketRoute:

Make Websocket Cluster More Optional
Do not use Websocket routing for traffic filtering, resources are precious
Websocket Route is used for dedicated line communication after token acquisition, allowing characters to monopolize a
room on their own
Websocket Route is used to obtain a pass, enter a dedicated service tunnel, and use dedicated service content
After using Websocket Route for authentication, roles can use unique skills

## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
<dependency>
    <artifactId>container-websocket-route-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
``` 

### configuration file

 ```properties
 ## Java.ClassLoading
Class.forName=io.github.thierrysquirrel.websocket.route.loading.WebsocketRouteLoading
Method.setUrl.String=127.0.0.1:9191
Method.setMaxFramePayloadLength.int=65536
Method.setReadTimeoutMilli.int=8192
Method.setRelayBeanClass.String=io.github.thierrysquirrel.websocket.route.demo.text.UserRelay
Method.setMatchPathPrefixAndBuilder.String=/user
 ```

 ```java

@ScannerPackage(packageName = "com.hello.world.websocket.route")
public class WebsocketRouteRegistrationImpl implements InterfaceManualRegistration {
    @Override
    public void scannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        WebsocketRouteRegistration.websocketRouteRegistrationScannerAll(scannerClassList, registrationMap);
    }
}
 ```

# Start Websocket

 ```java
public class WebsocketRouteMain {
    public static void main(String[] args) {
        WebsocketRouteInit.init(WebsocketRouteMain.class);
    }
}
 ```

# Getting Started WebsocketRoute

```java

@Component
public class UserRelay implements WebsocketRelayTemplate {
    public static List<String> websocketServerUrl = new ArrayList<>();

    static {
        /**
         * You Can Use The RPC Tool To Obtain The Cluster URL Address Periodically
         * For Example, Use HTTP To Request URL Storage Server (HttpServer+redis)
         */
        websocketServerUrl.add("127.0.0.1:8080");
        websocketServerUrl.add("127.0.0.1:8081");
        websocketServerUrl.add("127.0.0.1:8082");
    }

    @Override
    public HttpUpgradeMessage relay(HttpRequestMessage requestMessage) throws WebsocketRouteException {
        String uriPath = requestMessage.getUri();
        Map<String, String> uriParam = requestMessage.getUriParam();
        HttpHeaders headers = requestMessage.getHeaders();
//        String hello = uriParam.get ("hello");
//        String localUrl = getLocalUrl (Integer.parseInt (hello));
        String headersUrl = getHeadersUrl(headers);
        headers.set("ConnectUrl", headersUrl);
        return HttpUpgradeMessageBuilder.builderHttpUpgradeMessage(headersUrl, uriPath, headers);

    }

    public static String getHeadersUrl(HttpHeaders headers) {
        /**
         * There Are Many Things That Can Be Done Here
         * Example 1.
         * Team up to join the same server
         * The Team Leader Requests From The URL Server And Gets The URL (The Request Here Can Use A Variety Of Protocols, Such As HTTP + Redis)
         * The Team Leader Assigns The URL To His Teammates.
         * Each Person's Request Header carries The URL And Connects To The Same Server
         * Example 2.
         * Join Friends Server
         * Request Ro Store The URL Of A Friend's Connected Server,(The Request Here Can Use A Variety Of Protocols, Such As HTTP + Redis)
         * The Request Header Carries The URL And Connects To The Same Server
         *
         */
        return headers.get("world");
    }

    public static String getLocalUrl(int offset) {
        return websocketServerUrl.get(offset);
    }
}
```

