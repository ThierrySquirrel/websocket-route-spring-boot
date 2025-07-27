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
package io.github.thierrysquirrel.websocket.route.core.factory;

import io.github.thierrysquirrel.websocket.route.core.exception.WebsocketRouteException;
import io.github.thierrysquirrel.websocket.route.core.factory.constant.UriFactoryConstant;
import com.google.common.collect.Maps;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * ClassName: UriFactory
 * Description:
 * date: 2020/8/18 5:47
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class UriFactory {
    private UriFactory() {
    }

    public static boolean matchPrefix(String prefix, String requestUri) {
        return 0 == requestUri.indexOf (prefix) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Map<String, String> getUriParamMap(String requestUri) throws WebsocketRouteException {
        try {
            requestUri = URLDecoder.decode (requestUri, CharsetUtil.UTF_8.toString ());
        } catch (UnsupportedEncodingException e) {
            throw new WebsocketRouteException ("Uri Decoder Error", e);
        }
        if (!requestUri.contains (UriFactoryConstant.QUESTION_MARK)) {
            return Maps.newConcurrentMap ();
        }
        int index = requestUri.indexOf (UriFactoryConstant.QUESTION_MARK);
        String filterUri = requestUri.substring (index + 1);
        String[] splitUri = filterUri.split (UriFactoryConstant.AMPERSAND);
        Map<String, String> paramMap = Maps.newConcurrentMap ();
        for (String uriParam : splitUri) {
            int substringIndex = uriParam.indexOf (UriFactoryConstant.IS_EQUAL_TO);
            String key = uriParam.substring (0, substringIndex);
            String value = uriParam.substring (substringIndex + 1);
            paramMap.put (key, value);
        }
        return paramMap;
    }
}
