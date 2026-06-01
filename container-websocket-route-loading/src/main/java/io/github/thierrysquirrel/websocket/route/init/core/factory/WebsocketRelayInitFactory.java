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
package io.github.thierrysquirrel.websocket.route.init.core.factory;

import io.github.thierrysquirrel.websocket.route.core.container.WebsocketRelayConstant;
import io.github.thierrysquirrel.websocket.route.core.template.WebsocketRelayTemplate;
import io.github.thierrysquirrel.websocket.route.loading.Relay;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: WebsocketRelayInitFactory
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketRelayInitFactory {

    private static final Logger logger = Logger.getLogger(WebsocketRelayInitFactory.class.getName());

    private WebsocketRelayInitFactory() {
    }

    public static void addWebsocketRelayTemplate(Map<Class<?>, Object> registrationMap, Relay relay) {
        Class<?> templateClass = classForName(relay.getRelayBeanClass());
        WebsocketRelayTemplate websocketRelayTemplate = (WebsocketRelayTemplate) registrationMap.get(templateClass);

        WebsocketRelayConstant.putWebsocketRelayTemplate(relay.getMatchPathPrefix(), websocketRelayTemplate);
    }

    private static Class<?> classForName(String relayBeanClass) {
        Class<?> beanClass = null;
        try {
            beanClass = Class.forName(relayBeanClass);
        } catch (ClassNotFoundException e) {
            String logMsg = "classForName Error";
            logger.log(Level.WARNING, logMsg, e);
        }
        return beanClass;
    }
}
