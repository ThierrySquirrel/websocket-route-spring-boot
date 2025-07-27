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
package io.github.thierrysquirrel.websocket.route.init.core.factory;

import io.github.thierrysquirrel.websocket.route.autoconfigure.Relay;
import io.github.thierrysquirrel.websocket.route.core.container.WebsocketRelayConstant;
import io.github.thierrysquirrel.websocket.route.core.template.WebsocketRelayTemplate;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: WebsocketRelayInitFactory
 * Description:
 * date: 2020/8/18 4:19
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class WebsocketRelayInitFactory {
    private WebsocketRelayInitFactory() {
    }

    public static void addWebsocketRelayTemplate(ApplicationContext applicationContext, Relay relay) {
        WebsocketRelayConstant.putWebsocketRelayTemplate (relay.getMatchPathPrefix (), (WebsocketRelayTemplate) applicationContext.getBean (relay.getRelayBeanName ()));
    }
}
