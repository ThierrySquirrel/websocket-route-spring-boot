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
package com.github.thierrysquirrel.websocket.route.netty.server.handler.core.factory;

import com.github.thierrysquirrel.websocket.route.netty.core.constant.IdleStateConstant;
import com.github.thierrysquirrel.websocket.route.netty.server.handler.HttpServerHandler;
import com.github.thierrysquirrel.websocket.route.netty.server.handler.WebsocketServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * ClassName: HttpServerChannelPipelineFactory
 * Description:
 * date: 2020/8/18 4:31
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class HttpServerChannelPipelineFactory {

    private HttpServerChannelPipelineFactory() {
    }

    public static void upgradeWebsocketPipeline(ChannelPipeline pipeline, int readTimeoutMilli, Channel clientChannel) {
        pipeline.remove (IdleStateHandler.class);
        pipeline.remove (HttpServerHandler.class);
        pipeline.addFirst (new IdleStateHandler (readTimeoutMilli,
                IdleStateConstant.WEBSOCKET_WRITER_IDLE_TIME,
                IdleStateConstant.WEBSOCKET_ALL_IDLE_TIME));
        pipeline.addLast (new WebsocketServerHandler (clientChannel));
    }
}
