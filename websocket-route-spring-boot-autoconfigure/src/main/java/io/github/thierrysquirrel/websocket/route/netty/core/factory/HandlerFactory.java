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
package io.github.thierrysquirrel.websocket.route.netty.core.factory;

import io.netty.channel.Channel;
import io.netty.handler.timeout.IdleState;

/**
 * ClassName: HandlerFactory
 * Description:
 * date: 2020/8/18 5:58
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class HandlerFactory {
    private HandlerFactory() {
    }

    public static void userEventTriggered(IdleState state, Channel clientChannel, Channel serverChannel) {
        if (state == IdleState.READER_IDLE) {
            clientChannel.close ();
            serverChannel.close ();
        }
    }

    public static void userEventTriggered(IdleState state, Channel channel) {
        if (state == IdleState.READER_IDLE) {
            channel.close ();
        }
    }
}
