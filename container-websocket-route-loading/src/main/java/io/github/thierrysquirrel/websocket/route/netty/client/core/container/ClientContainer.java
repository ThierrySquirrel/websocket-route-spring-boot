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

package io.github.thierrysquirrel.websocket.route.netty.client.core.container;

import io.github.thierrysquirrel.jellyfish.concurrency.map.hash.ConcurrencyHashMap;
import io.github.thierrysquirrel.websocket.route.core.constant.MapConstant;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;


/**
 * ClassName: ClientContainer
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class ClientContainer {
    private static final ConcurrencyHashMap<String, ThreadLocal<EventLoopGroup>> EVENT_LOOP_GROUP_MAP = new ConcurrencyHashMap<>(MapConstant.DEFAULT_MAP_SIZE);

    private ClientContainer() {
    }

    public static EventLoopGroup getEventLoopGroup(String url) {
        ThreadLocal<EventLoopGroup> threadLocal = EVENT_LOOP_GROUP_MAP.getIfAbsent(url, key -> new ThreadLocal<>());
        EventLoopGroup loopGroup = threadLocal.get();
        if (loopGroup == null) {
            loopGroup = new MultiThreadIoEventLoopGroup(1, NioIoHandler.newFactory());
            threadLocal.set(loopGroup);
        }
        return loopGroup;
    }
}
