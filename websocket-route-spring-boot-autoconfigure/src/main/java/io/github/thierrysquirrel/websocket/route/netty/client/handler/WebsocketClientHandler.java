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
package io.github.thierrysquirrel.websocket.route.netty.client.handler;

import io.github.thierrysquirrel.websocket.route.netty.core.factory.HandlerFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: WebsocketClientHandler
 * Description:
 * date: 2020/8/18 5:20
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Getter
@Setter
public class WebsocketClientHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private Channel serverChannel;

    public WebsocketClientHandler(Channel serverChannel) {
        this.serverChannel = serverChannel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        serverChannel.writeAndFlush (msg.retain ());
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        HandlerFactory.userEventTriggered (((IdleStateEvent) evt).state (), ctx.channel (), serverChannel);
        super.userEventTriggered (ctx, evt);
    }
}
