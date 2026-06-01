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
package io.github.thierrysquirrel.websocket.route.netty.thread;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * ClassName: AbstractWebsocketBusinessThread
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public abstract class AbstractWebsocketBusinessThread implements Runnable {
    private Channel channel;
    private WebSocketFrame webSocketFrame;

    protected AbstractWebsocketBusinessThread(Channel channel, WebSocketFrame webSocketFrame) {
        this.channel = channel;
        this.webSocketFrame = webSocketFrame;
    }

    /**
     * businessExecution
     *
     * @param channel        channel
     * @param webSocketFrame webSocketFrame
     */
    protected abstract void businessExecution(Channel channel, WebSocketFrame webSocketFrame);

    @Override
    public void run() {
        businessExecution(this.channel, this.webSocketFrame);
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public WebSocketFrame getWebSocketFrame() {
        return webSocketFrame;
    }

    public void setWebSocketFrame(WebSocketFrame webSocketFrame) {
        this.webSocketFrame = webSocketFrame;
    }

    @Override
    public String toString() {
        return "AbstractWebsocketBusinessThread{" +
                "channel=" + channel +
                ", webSocketFrame=" + webSocketFrame +
                '}';
    }
}
