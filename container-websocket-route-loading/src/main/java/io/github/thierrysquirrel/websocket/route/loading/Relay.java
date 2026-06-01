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

package io.github.thierrysquirrel.websocket.route.loading;

/**
 * ClassName: Relay
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class Relay {
    /**
     * matchPathPrefix
     * 匹配路径前缀
     */
    private String matchPathPrefix;
    /**
     * relayBeanClass
     * 转发be9an class
     */
    private String relayBeanClass;

    public Relay(String matchPathPrefix, String relayBeanClass) {
        this.matchPathPrefix = matchPathPrefix;
        this.relayBeanClass = relayBeanClass;
    }

    public String getMatchPathPrefix() {
        return matchPathPrefix;
    }

    public void setMatchPathPrefix(String matchPathPrefix) {
        this.matchPathPrefix = matchPathPrefix;
    }

    public String getRelayBeanClass() {
        return relayBeanClass;
    }

    public void setRelayBeanClass(String relayBeanClass) {
        this.relayBeanClass = relayBeanClass;
    }

    @Override
    public String toString() {
        return "Relay{" +
                "matchPathPrefix='" + matchPathPrefix + '\'' +
                ", relayBeanClass='" + relayBeanClass + '\'' +
                '}';
    }
}
