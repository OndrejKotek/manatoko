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
package org.jboss.hal.testsuite.test.configuration.web.services.endpoint.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.hal.resources.Ids;
import org.jboss.hal.testsuite.Console;
import org.jboss.hal.testsuite.CrudOperations;
import org.jboss.hal.testsuite.Random;
import org.jboss.hal.testsuite.container.WildFlyContainer;
import org.jboss.hal.testsuite.fixtures.WebServicesFixtures;
import org.jboss.hal.testsuite.fragment.FormFragment;
import org.jboss.hal.testsuite.model.ResourceVerifier;
import org.jboss.hal.testsuite.page.configuration.WebServicesPage;
import org.jboss.hal.testsuite.test.Manatoko;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.wildfly.extras.creaper.core.online.OnlineManagementClient;
import org.wildfly.extras.creaper.core.online.operations.Operations;

import static org.jboss.hal.testsuite.container.WildFlyConfiguration.STANDALONE;
import static org.jboss.hal.testsuite.container.WildFlyVersion._26;

@Manatoko
@Testcontainers
class EndpointConfigurationTest {

    private static final String END_POINT_CONFIGURATION_CREATE = "endpoint-configuration-to-be-created-"
            + RandomStringUtils.randomAlphanumeric(7);

    private static final String END_POINT_CONFIGURATION_EDIT = "endpoint-configuration-to-be-edited-"
            + RandomStringUtils.randomAlphanumeric(7);

    private static final String END_POINT_CONFIGURATION_REMOVE = "endpoint-configuration-to-be-removed-"
            + RandomStringUtils.randomAlphanumeric(7);

    @Container static WildFlyContainer wildFly = WildFlyContainer.version(_26, STANDALONE);
    private static OnlineManagementClient client;

    @BeforeAll
    static void setupModel() throws Exception {
        client = wildFly.managementClient();
        Operations operations = new Operations(client);
        operations.add(WebServicesFixtures.endpointConfigurationAddress(END_POINT_CONFIGURATION_EDIT));
        operations.add(WebServicesFixtures.endpointConfigurationAddress(END_POINT_CONFIGURATION_REMOVE));
    }

    @Inject Console console;
    @Inject CrudOperations crudOperations;
    @Page WebServicesPage page;

    @BeforeEach
    void prepare() {
        page.navigate();
        console.verticalNavigation().selectPrimary(Ids.WEBSERVICES_ENDPOINT_CONFIG_ITEM);
    }

    @Test
    void create() throws Exception {
        crudOperations.create(WebServicesFixtures.endpointConfigurationAddress(END_POINT_CONFIGURATION_CREATE),
                page.getEndpointConfigurationTable(), END_POINT_CONFIGURATION_CREATE);
    }

    @Test
    void editProperty() throws Exception {
        Map<String, String> properties = new HashMap<>();
        properties.put(Random.name(), Random.name());
        properties.put(Random.name(), Random.name());
        page.getEndpointConfigurationTable().select(END_POINT_CONFIGURATION_EDIT);
        FormFragment endpointConfigurationForm = page.getEndpointConfigurationForm();
        endpointConfigurationForm.edit();
        endpointConfigurationForm.properties("property").add(properties);
        endpointConfigurationForm.save();
        console.verifySuccess();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            new ResourceVerifier(
                    WebServicesFixtures.endpointConfigurationAddress(END_POINT_CONFIGURATION_EDIT)
                            .and("property", entry.getKey()),
                    client)
                            .verifyExists()
                            .verifyAttribute("value", entry.getValue());
        }
    }

    @Test
    void remove() throws Exception {
        crudOperations.delete(WebServicesFixtures.endpointConfigurationAddress(END_POINT_CONFIGURATION_REMOVE),
                page.getEndpointConfigurationTable(), END_POINT_CONFIGURATION_REMOVE);
    }

}
