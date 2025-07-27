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
package io.github.thierrysquirrel.websocket.route.netty.thread.execution;

import io.github.thierrysquirrel.websocket.route.netty.thread.AbstractWebsocketBusinessThread;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * ClassName: WebsocketBusinessThreadExecution
 * Description:
 * date: 2020/8/18 8:16
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class WebsocketBusinessThreadExecution extends AbstractWebsocketBusinessThread {
    public WebsocketBusinessThreadExecution(Channel channel, WebSocketFrame webSocketFrame) {
        super (channel, webSocketFrame);
    }

    @Override
    protected void businessExecution(Channel channel, WebSocketFrame webSocketFrame) {
        channel.writeAndFlush (webSocketFrame);
    }
}
