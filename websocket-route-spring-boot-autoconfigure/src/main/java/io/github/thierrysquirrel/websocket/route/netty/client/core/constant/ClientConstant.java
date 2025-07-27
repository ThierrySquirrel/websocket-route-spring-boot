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
package io.github.thierrysquirrel.websocket.route.netty.client.core.constant;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * ClassName: ClientConstant 
 * Description: 
 * date: 2020/8/18 5:35
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class ClientConstant {
    public static final EventLoopGroup CLIENT_EVENT_LOOP_GROUP = new NioEventLoopGroup ();
    public static final int TIMEOUT = 16000;

    private ClientConstant() {
    }
}
