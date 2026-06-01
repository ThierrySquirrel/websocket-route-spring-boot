/**
 * Copyright 2026/6/2 ThierrySquirrel
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
 **/
package io.github.thierrysquirrel.websocket.route.netty.server.handler;

import io.github.thierrysquirrel.websocket.route.core.exception.WebsocketRouteException;
import io.github.thierrysquirrel.websocket.route.netty.core.factory.HandlerFactory;
import io.github.thierrysquirrel.websocket.route.netty.server.handler.core.factory.HttpServerHandlerFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: HttpServerHandler
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = Logger.getLogger(HttpServerHandler.class.getName());


    private int maxFramePayloadLength;
    private int readTimeoutMilli;

    public HttpServerHandler(int maxFramePayloadLength, int readTimeoutMilli) {
        this.maxFramePayloadLength = maxFramePayloadLength;
        this.readTimeoutMilli = readTimeoutMilli;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {
        try {
            HttpServerHandlerFactory.upgradeWebsocket(ctx.channel(), msg, maxFramePayloadLength, readTimeoutMilli);
        } catch (WebsocketRouteException e) {
            String logMsg = "upgradeWebsocket Error";
            logger.log(Level.WARNING, logMsg, e);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        HandlerFactory.userEventTriggered(((IdleStateEvent) evt).state(), ctx.channel());
        super.userEventTriggered(ctx, evt);
    }

    public int getMaxFramePayloadLength() {
        return maxFramePayloadLength;
    }

    public void setMaxFramePayloadLength(int maxFramePayloadLength) {
        this.maxFramePayloadLength = maxFramePayloadLength;
    }

    public int getReadTimeoutMilli() {
        return readTimeoutMilli;
    }

    public void setReadTimeoutMilli(int readTimeoutMilli) {
        this.readTimeoutMilli = readTimeoutMilli;
    }

    @Override
    public String toString() {
        return "HttpServerHandler{" +
                "maxFramePayloadLength=" + maxFramePayloadLength +
                ", readTimeoutMilli=" + readTimeoutMilli +
                '}';
    }
}
