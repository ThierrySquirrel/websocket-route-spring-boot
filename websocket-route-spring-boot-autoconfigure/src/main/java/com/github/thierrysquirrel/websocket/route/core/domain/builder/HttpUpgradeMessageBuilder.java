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
package com.github.thierrysquirrel.websocket.route.core.domain.builder;

import com.github.thierrysquirrel.websocket.route.core.domain.HttpUpgradeMessage;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * ClassName: HttpUpgradeMessageBuilder
 * Description:
 * date: 2020/8/18 8:54
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class HttpUpgradeMessageBuilder {
    private HttpUpgradeMessageBuilder() {
    }

    public static HttpUpgradeMessage builderHttpUpgradeMessage(String uri,
                                                               String path,
                                                               HttpHeaders headers) {

        HttpUpgradeMessage upgradeMessage = new HttpUpgradeMessage ();
        upgradeMessage.setUrl (uri);
        upgradeMessage.setPath (path);
        upgradeMessage.setHeaders (headers);
        return upgradeMessage;
    }
}
