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
package com.github.thierrysquirrel.websocket.route.autoconfigure;

import com.github.thierrysquirrel.websocket.route.init.WebsocketInit;
import com.github.thierrysquirrel.websocket.route.init.WebsocketRelayInit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: WebsocketRouteAutoConfiguration
 * Description:
 * date: 2020/8/18 3:41
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Configuration
@EnableConfigurationProperties(WebsocketRouteProperties.class)
public class WebsocketRouteAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(WebsocketRelayInit.class)
    public WebsocketRelayInit websocketRelayInit(){
       return new WebsocketRelayInit ();
    }

    @Bean
    @ConditionalOnMissingBean(WebsocketInit.class)
    public WebsocketInit websocketInit(){
        return new WebsocketInit ();
    }
}
