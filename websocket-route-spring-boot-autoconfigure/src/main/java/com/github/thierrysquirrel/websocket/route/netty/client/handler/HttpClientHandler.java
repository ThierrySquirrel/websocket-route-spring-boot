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
package com.github.thierrysquirrel.websocket.route.netty.client.handler;

import com.github.thierrysquirrel.websocket.route.core.exception.WebsocketRouteException;
import com.github.thierrysquirrel.websocket.route.netty.client.handler.core.factory.HttpClientHandlerFactory;
import com.github.thierrysquirrel.websocket.route.netty.client.handler.core.factory.execution.HttpClientHandlerFactoryExecution;
import com.github.thierrysquirrel.websocket.route.netty.core.factory.HandlerFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: HttpClientHandler
 * Description:
 * date: 2020/8/18 4:38
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Getter
@Setter
public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
    private Channel serverChannel;
    private WebSocketClientHandshaker handshake;
    private int readTimeoutMilli;
    private ChannelPromise handshakeFuture;

    public HttpClientHandler(Channel serverChannel, WebSocketClientHandshaker handshake, int readTimeoutMilli) {
        this.serverChannel = serverChannel;
        this.handshake = handshake;
        this.readTimeoutMilli = readTimeoutMilli;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        handshakeFuture = ctx.newPromise ();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        handshake.handshake (ctx.channel ());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
        HttpClientHandlerFactoryExecution.handshake (ctx.channel (), msg, handshake, handshakeFuture, readTimeoutMilli, serverChannel);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        HandlerFactory.userEventTriggered (((IdleStateEvent) evt).state (), ctx.channel (), serverChannel);
        super.userEventTriggered (ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        HttpClientHandlerFactory.exceptionCaught (ctx.channel (), serverChannel, handshakeFuture, new WebsocketRouteException (cause));
        super.exceptionCaught (ctx, cause);
    }
}
