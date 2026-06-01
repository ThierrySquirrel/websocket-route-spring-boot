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

import io.github.thierrysquirrel.container.scanner.annotation.ScannerPackage;
import io.github.thierrysquirrel.container.scanner.registration.InterfaceManualRegistration;
import io.github.thierrysquirrel.websocket.route.loading.builder.RelayBuilder;
import io.github.thierrysquirrel.websocket.route.loading.constant.WebsocketRouteLoadingConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: WebsocketRouteLoading
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
@ScannerPackage(packageName = "io.github.thierrysquirrel.websocket.route.loading")
public class WebsocketRouteLoading implements InterfaceManualRegistration {
    /**
     * ServerUrl
     * 服务Url
     */
    private String url = WebsocketRouteLoadingConstant.DEFAULT_URL;
    /**
     * MaxFramePayloadLength
     * 最大帧载荷长度
     */
    private int maxFramePayloadLength = WebsocketRouteLoadingConstant.DEFAULT_MAX_FRAME_PAYLOAD_LENGTH;
    /**
     * ReadTimeoutMilli
     * 读取超时
     */
    private int readTimeoutMilli = WebsocketRouteLoadingConstant.DEFAULT_READ_TIMEOUT_MILLI;
    /**
     * Relays
     * 转发集合
     */
    private List<Relay> relays = new ArrayList<>();

    private String matchPathPrefix;
    private String relayBeanClass;

    @Override
    public void scannerAll(List<Class<?>> list, Map<Class<?>, Object> map) {

    }

    public void setMatchPathPrefixAndBuilder(String matchPathPrefix) {
        this.matchPathPrefix = matchPathPrefix;
        builder();
    }

    private void builder() {
        Relay relay = RelayBuilder.builderRelay(matchPathPrefix, relayBeanClass);
        this.relays.add(relay);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMaxFramePayloadLength() {
        return maxFramePayloadLength;
    }

    public void setMaxFramePayloadLength(int maxFramePayloadLength) {
        this.maxFramePayloadLength = maxFramePayloadLength;
    }

    public int getReadTimeoutMilli() {
        return readTimeoutMilli;
    }

    public void setReadTimeoutMilli(int readTimeoutMilli) {
        this.readTimeoutMilli = readTimeoutMilli;
    }

    public List<Relay> getRelays() {
        return relays;
    }

    public void setRelays(List<Relay> relays) {
        this.relays = relays;
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
        return "WebsocketRouteLoading{" +
                "url='" + url + '\'' +
                ", maxFramePayloadLength=" + maxFramePayloadLength +
                ", readTimeoutMilli=" + readTimeoutMilli +
                ", relays=" + relays +
                ", matchPathPrefix='" + matchPathPrefix + '\'' +
                ", relayBeanClass='" + relayBeanClass + '\'' +
                '}';
    }
}
