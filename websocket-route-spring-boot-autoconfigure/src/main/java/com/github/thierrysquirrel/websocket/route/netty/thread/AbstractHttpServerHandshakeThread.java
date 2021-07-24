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
package com.github.thierrysquirrel.websocket.route.netty.thread;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: AbstractHttpServerHandshakeThread
 * Description:
 * date: 2020/8/18 6:20
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Getter
@Setter
public abstract class AbstractHttpServerHandshakeThread implements Runnable {
    private Channel serverChannel;
    private FullHttpRequest request;
    private int maxFramePayloadLength;
    private int readTimeoutMilli;

    public AbstractHttpServerHandshakeThread(Channel serverChannel, FullHttpRequest request, int maxFramePayloadLength, int readTimeoutMilli) {
        this.serverChannel = serverChannel;
        this.request = request;
        this.maxFramePayloadLength = maxFramePayloadLength;
        this.readTimeoutMilli = readTimeoutMilli;
    }

    /**
     * handshake
     *
     * @param serverChannel         serverChannel
     * @param request               request
     * @param maxFramePayloadLength maxFramePayloadLength
     * @param readTimeoutMilli      readTimeoutMilli
     */
    protected abstract void handshake(Channel serverChannel, FullHttpRequest request, int maxFramePayloadLength, int readTimeoutMilli);

    @Override
    public void run() {
        handshake (this.serverChannel,
                this.request,
                this.maxFramePayloadLength,
                this.readTimeoutMilli);
    }
}
