/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.websocket.route.netty.client.init;

import com.github.thierrysquirrel.websocket.route.core.exception.WebsocketRouteException;
import com.github.thierrysquirrel.websocket.route.netty.client.core.constant.ClientConstant;
import com.github.thierrysquirrel.websocket.route.netty.client.handler.HttpClientHandler;
import com.github.thierrysquirrel.websocket.route.netty.client.handler.HttpClientInitChannelHandler;
import com.github.thierrysquirrel.websocket.route.netty.core.factory.SocketAddressFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: WebsocketClientInit
 * Description:
 * date: 2020/8/18 5:32
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class WebsocketClientInit {
    private String url;
    private Channel serverChannel;
    private WebSocketClientHandshaker handshake;
    private int readTimeoutMilli;

    public WebsocketClientInit(String url, Channel serverChannel, WebSocketClientHandshaker handshake, int readTimeoutMilli) {
        this.url = url;
        this.serverChannel = serverChannel;
        this.handshake = handshake;
        this.readTimeoutMilli = readTimeoutMilli;
    }

    public Channel init() throws WebsocketRouteException {
        HttpClientHandler httpClientHandler = new HttpClientHandler (serverChannel, handshake, readTimeoutMilli);

        try {
            Channel clientChannel = new Bootstrap ().group (ClientConstant.CLIENT_EVENT_LOOP_GROUP)
                    .channel (NioSocketChannel.class)
                    .handler (new HttpClientInitChannelHandler (httpClientHandler))
                    .connect (SocketAddressFactory.getSocketAddress (url))
                    .sync ()
                    .channel ();
            httpClientHandler.getHandshakeFuture ().get (ClientConstant.TIMEOUT, TimeUnit.MILLISECONDS);
            return clientChannel;
        } catch (Exception e) {
            throw new WebsocketRouteException ("initError", e);
        }
    }
}
