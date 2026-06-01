# container-websocket-route

Websocket Route

[English](./README.md)

支持功能:

- [x] Websocket路由

# Websocket路由:

使websocket群集更具可选性
不要使用Websocket路由进行流量过滤,资源很宝贵
Websocket Route用于令牌获取后的专用线路通信,允许角色自己独占一个房间
Websocket Route用于获取通行证,进入专用服务隧道,并使用专用服务内容
使用Websocket Route进行身份验证后,角色可以使用独特的技能

## Quick Start

```xml
<!--在pom.xml中添加依赖-->
<dependency>
    <artifactId>container-websocket-route-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
``` 

### 配置文件

 ```properties
 ## Java.ClassLoading
Class.forName=io.github.thierrysquirrel.websocket.route.loading.WebsocketRouteLoading
Method.setUrl.String=127.0.0.1:9191
Method.setMaxFramePayloadLength.int=65536
Method.setReadTimeoutMilli.int=8192
Method.setRelayBeanClass.String=com.hello.world.websocket.route.demo.text.UserRelay
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

# 启动 Websocket

 ```java
public class WebsocketRouteMain {
    public static void main(String[] args) {
        WebsocketRouteInit.init(WebsocketRouteMain.class);
    }
}
 ```

# 开始使用 WebsocketRoute

```java

@Registration
public class UserRelay implements WebsocketRelayTemplate {
    public static List<String> websocketServerUrl = new ArrayList<>();

    static {
        /**
         * 您可以使用RPC工具定期获取集群URL地址,
         * 例如,使用http去请求Url存储服务器 (HttpServer+redis)
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
         * 在这里可以做很多事情
         * 举例 1.
         * 组队加入同一个服务器
         * 队长向url服务器请求,获得url.这里的请求可以使用多种协议例如:Http+Redis
         * 队长将url分配给队友.
         * 每个人的请求头携带URL并连接到同一个服务器
         * 举例 2.
         * 加入好友服务器
         * 请求存放好友已连接服务器的URL,这里的请求可以使用多种协议例如:Http+Redis
         * 请求头携带WebsocketUrl,连接同一服务器
         *
         */
        return headers.get("world");
    }

    public static String getLocalUrl(int offset) {
        return websocketServerUrl.get(offset);
    }
}
```

