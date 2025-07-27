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
package io.github.thierrysquirrel.websocket.route.init;

import io.github.thierrysquirrel.websocket.route.autoconfigure.WebsocketRouteProperties;
import io.github.thierrysquirrel.websocket.route.init.core.factory.execution.WebsocketRelayInitFactoryExecution;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * ClassName: WebsocketRelayInit
 * Description:
 * date: 2020/8/18 4:14
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class WebsocketRelayInit implements ApplicationContextAware {
    @Resource
    private WebsocketRouteProperties websocketRouteProperties;
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {

        WebsocketRelayInitFactoryExecution.init (applicationContext, websocketRouteProperties.getRelays ());
    }


    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
