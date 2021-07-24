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
package com.github.thierrysquirrel.websocket.route.netty.client.handler.core.factory;

import com.github.thierrysquirrel.websocket.route.core.exception.WebsocketRouteException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;

/**
 * ClassName: HttpClientHandlerFactory
 * Description:
 * date: 2020/8/18 4:59
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class HttpClientHandlerFactory {
    private HttpClientHandlerFactory() {
    }

    public static void exceptionCaught(Channel clientChannel, Channel serverChannel, ChannelPromise handshakeFuture, WebsocketRouteException routeException) {
        closeChannel (clientChannel, serverChannel);
        handshakeFuture.setFailure (routeException);
    }

    public static void closeChannel(Channel clientChannel, Channel serverChannel) {
        clientChannel.close ();
        serverChannel.close ();
    }
}
