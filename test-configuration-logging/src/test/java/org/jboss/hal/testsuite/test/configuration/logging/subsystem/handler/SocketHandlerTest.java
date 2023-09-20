/*
 *  Copyright 2022 Red Hat
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.jboss.hal.testsuite.test.configuration.logging.subsystem.handler;

import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.hal.testsuite.Console;
import org.jboss.hal.testsuite.container.WildFlyContainer;
import org.jboss.hal.testsuite.fixtures.LoggingFixtures;
import org.jboss.hal.testsuite.fragment.FormFragment;
import org.jboss.hal.testsuite.fragment.TableFragment;
import org.jboss.hal.testsuite.page.configuration.LoggingConfigurationPage;
import org.jboss.hal.testsuite.page.configuration.LoggingSubsystemConfigurationPage;
import org.jboss.hal.testsuite.test.Manatoko;
import org.jboss.hal.testsuite.test.configuration.logging.AbstractSocketHandlerTest;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.wildfly.extras.creaper.core.online.OnlineManagementClient;
import org.wildfly.extras.creaper.core.online.operations.Address;
import org.wildfly.extras.creaper.core.online.operations.Operations;
import org.wildfly.extras.creaper.core.online.operations.Values;

import static org.jboss.hal.dmr.ModelDescriptionConstants.HOST;
import static org.jboss.hal.dmr.ModelDescriptionConstants.OUTBOUND_SOCKET_BINDING_REF;
import static org.jboss.hal.dmr.ModelDescriptionConstants.PORT;
import static org.jboss.hal.testsuite.container.WildFlyConfiguration.DEFAULT;
import static org.jboss.hal.testsuite.fixtures.LoggingFixtures.LOGGING_HANDLER_ITEM;
import static org.jboss.hal.testsuite.fixtures.LoggingFixtures.NAMED_FORMATTER;
import static org.jboss.hal.testsuite.fixtures.LoggingFixtures.SocketHandler;
import static org.jboss.hal.testsuite.fixtures.SecurityFixtures.CLIENT_SSL_READ;
import static org.jboss.hal.testsuite.fixtures.SecurityFixtures.clientSslContextAddress;
import static org.jboss.hal.testsuite.fixtures.SocketBindingFixtures.LOCALHOST;
import static org.jboss.hal.testsuite.fixtures.SocketBindingFixtures.OUTBOUND_REMOTE_PORT;
import static org.jboss.hal.testsuite.fixtures.SocketBindingFixtures.OUTBOUND_REMOTE_READ;
import static org.jboss.hal.testsuite.fixtures.SocketBindingFixtures.STANDARD_SOCKETS;
import static org.jboss.hal.testsuite.fixtures.SocketBindingFixtures.outboundRemoteAddress;

@Manatoko
@Testcontainers
class SocketHandlerTest extends AbstractSocketHandlerTest {

    @Container static WildFlyContainer wildFly = WildFlyContainer.standalone(DEFAULT);

    @BeforeAll
    static void setupModel() throws Exception {
        OnlineManagementClient client = wildFly.managementClient();
        ops = new Operations(client);

        ops.add(outboundRemoteAddress(STANDARD_SOCKETS, OUTBOUND_REMOTE_READ),
                Values.of(HOST, LOCALHOST).and(PORT, OUTBOUND_REMOTE_PORT))
                .assertSuccess();
        ops.add(clientSslContextAddress(CLIENT_SSL_READ)).assertSuccess();

        Values params = Values.of(NAMED_FORMATTER, "PATTERN").and(OUTBOUND_SOCKET_BINDING_REF, "mail-smtp");
        ops.add(LoggingFixtures.SocketHandler.socketHandlerAddress(SocketHandler.SOCKET_HANDLER_UPDATE), params)
                .assertSuccess();
        ops.add(LoggingFixtures.SocketHandler.socketHandlerAddress(SocketHandler.SOCKET_HANDLER_DELETE), params)
                .assertSuccess();
        ops.add(LoggingFixtures.XmlFormatter.xmlFormatterAddress(XML_FORMATTER)).assertSuccess();
    }

    @Inject Console console;
    @Page LoggingSubsystemConfigurationPage page;

    @Override
    protected void navigateToPage() {
        page.navigate();
        console.verticalNavigation().selectSecondary(LOGGING_HANDLER_ITEM,
                "logging-handler-socket-item");
    }

    @Override
    protected LoggingConfigurationPage getPage() {
        return page;
    }

    @Override
    protected Address socketHandlerAddress(String name) {
        return LoggingFixtures.SocketHandler.socketHandlerAddress(name);
    }

    @Override
    protected TableFragment getHandlerTable() {
        return page.getSocketHandlerTable();
    }

    @Override
    protected FormFragment getHandlerForm() {
        return page.getSocketHandlerForm();
    }
}
