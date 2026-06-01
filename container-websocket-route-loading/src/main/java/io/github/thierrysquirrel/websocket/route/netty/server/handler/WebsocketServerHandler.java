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

import io.github.thierrysquirrel.websocket.route.netty.client.handler.core.factory.HttpClientHandlerFactory;
import io.github.thierrysquirrel.websocket.route.netty.core.factory.HandlerFactory;
import io.github.thierrysquirrel.websocket.route.netty.server.handler.core.factory.execution.WebsocketServerHandlerExecution;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * ClassName: WebsocketServerHandler
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private Channel clientChannel;

    public WebsocketServerHandler(Channel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) {
        WebsocketServerHandlerExecution.businessExecution(clientChannel, msg.retain());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        HandlerFactory.userEventTriggered(((IdleStateEvent) evt).state(), clientChannel, ctx.channel());
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        HttpClientHandlerFactory.closeChannel(clientChannel, ctx.channel());
        super.exceptionCaught(ctx, cause);
    }

    public Channel getClientChannel() {
        return clientChannel;
    }

    public void setClientChannel(Channel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public String toString() {
        return "WebsocketServerHandler{" +
                "clientChannel=" + clientChannel +
                '}';
    }
}
