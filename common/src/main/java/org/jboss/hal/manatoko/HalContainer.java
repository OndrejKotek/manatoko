/*
 *  Copyright 2022 Red Hat
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.jboss.hal.manatoko;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatException;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

public class HalContainer extends GenericContainer<HalContainer> {

    private static final int PORT = 9090;
    private static final String IMAGE = "quay.io/halconsole/hal";
    private static final Logger LOGGER = LoggerFactory.getLogger(HalContainer.class);

    public static HalContainer instance() {
        return new HalContainer().withNetwork(Network.INSTANCE)
                .withNetworkAliases(Network.HAL).withExposedPorts(PORT)
                .waitingFor(Wait.forListeningPort());
    }

    private String managementEndpoint;
    private final TokenFormatter tokenFormatter;

    private HalContainer() {
        super(DockerImageName.parse(IMAGE));
        this.tokenFormatter = new HalTokenFormatter();
    }

    public void connectTo(final WildFlyContainer wildFly) {
        this.managementEndpoint = wildFly.managementEndpoint();
    }

    public void navigate(WebDriver driver, String nameToken) {
        try {
            var placeRequest = new PlaceRequest.Builder().nameToken(nameToken).build();
            var fragment = tokenFormatter.toPlaceToken(placeRequest);
            var query = managementEndpoint != null ? "connect=" + managementEndpoint : null;
            var url = new URI("http", null, Network.HAL, PORT, "/", query, fragment).toString();
            LOGGER.debug("Navigate to {}", url);
            driver.get(url);
        } catch (URISyntaxException e) {
            LOGGER.error("Unable to navigate to '{}': {}", nameToken, e.getMessage(), e);
        }
    }

    private static class HalTokenFormatter implements TokenFormatter {

        @Override
        public String toHistoryToken(List<PlaceRequest> placeRequestHierarchy) throws TokenFormatException {
            throw new UnsupportedOperationException();
        }

        @Override
        public PlaceRequest toPlaceRequest(String placeToken) throws TokenFormatException {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<PlaceRequest> toPlaceRequestHierarchy(String historyToken) throws TokenFormatException {
            throw new UnsupportedOperationException();
        }

        @Override
        public String toPlaceToken(PlaceRequest placeRequest) throws TokenFormatException {
            var builder = new StringBuilder();
            builder.append(placeRequest.getNameToken());
            var params = placeRequest.getParameterNames();
            if (params != null) {
                for (var param : params) {
                    builder.append(";").append(param).append("=").append(placeRequest.getParameter(param, null));
                }
            }
            return builder.toString();
        }
    }
}
