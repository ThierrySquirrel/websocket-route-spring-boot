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

import io.github.thierrysquirrel.websocket.route.netty.core.constant.IdleStateConstant;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: HttpClientInitChannelHandler
 * Description:
 * date: 2020/8/18 4:38
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Getter
@Setter
public class HttpClientInitChannelHandler extends ChannelInitializer<SocketChannel> {
    private HttpClientHandler httpClientHandler;

    public HttpClientInitChannelHandler(HttpClientHandler httpClientHandler) {
        this.httpClientHandler = httpClientHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline ().addLast (new IdleStateHandler (IdleStateConstant.HTTP_READER_IDLE_TIME,
                IdleStateConstant.HTTP_WRITER_IDLE_TIME,
                IdleStateConstant.HTTP_ALL_IDLE_TIME))
                .addLast (new HttpClientCodec ())
                .addLast (new HttpObjectAggregator (IdleStateConstant.HTTP_MAX_CONTENT_LENGTH))
                .addLast (httpClientHandler);
    }
}
