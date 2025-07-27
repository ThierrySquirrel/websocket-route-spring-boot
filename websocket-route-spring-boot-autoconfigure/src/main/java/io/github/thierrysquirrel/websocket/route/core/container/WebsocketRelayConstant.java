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
package io.github.thierrysquirrel.websocket.route.core.container;

import io.github.thierrysquirrel.websocket.route.core.exception.WebsocketRouteException;
import io.github.thierrysquirrel.websocket.route.core.factory.UriFactory;
import io.github.thierrysquirrel.websocket.route.core.template.WebsocketRelayTemplate;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * ClassName: WebsocketRelayConstant
 * Description:
 * date: 2020/8/18 3:56
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class WebsocketRelayConstant {
    private static final Map<String, WebsocketRelayTemplate> TEMPLATE_MAP = Maps.newConcurrentMap ();

    private WebsocketRelayConstant() {
    }

    public static void putWebsocketRelayTemplate(String matchPathPrefix, WebsocketRelayTemplate template) {
        TEMPLATE_MAP.put (matchPathPrefix, template);
    }

    public static WebsocketRelayTemplate getWebsocketRelayTemplate(String requestUri) throws WebsocketRouteException {
        for (Map.Entry<String, WebsocketRelayTemplate> entry : TEMPLATE_MAP.entrySet ()) {
            if (UriFactory.matchPrefix (entry.getKey (),requestUri)) {
                return entry.getValue ();
            }
        }
        throw new WebsocketRouteException ("UnExistent Path:"+requestUri);
    }

}
