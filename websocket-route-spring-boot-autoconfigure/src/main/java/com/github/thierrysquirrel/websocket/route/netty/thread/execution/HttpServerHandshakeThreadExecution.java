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
package com.github.thierrysquirrel.websocket.route.netty.thread.execution;

import com.github.thierrysquirrel.websocket.route.core.exception.WebsocketRouteException;
import com.github.thierrysquirrel.websocket.route.netty.server.handler.core.factory.HttpServerHandlerFactory;
import com.github.thierrysquirrel.websocket.route.netty.thread.AbstractHttpServerHandshakeThread;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: HttpServerHandshakeThreadExecution
 * Description:
 * date: 2020/8/18 7:30
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class HttpServerHandshakeThreadExecution extends AbstractHttpServerHandshakeThread {
    public HttpServerHandshakeThreadExecution(Channel serverChannel, FullHttpRequest request, int maxFramePayloadLength, int readTimeoutMilli) {
        super (serverChannel, request, maxFramePayloadLength, readTimeoutMilli);
    }

    @Override
    protected void handshake(Channel serverChannel, FullHttpRequest request, int maxFramePayloadLength, int readTimeoutMilli) {
        try {
            HttpServerHandlerFactory.upgradeWebsocket (serverChannel, request, maxFramePayloadLength, readTimeoutMilli);
        } catch (WebsocketRouteException routeException) {
            log.error ("httpServer Handshake Error", routeException);
        }
    }
}
