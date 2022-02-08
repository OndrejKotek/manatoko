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
package org.jboss.hal.testsuite.page.configuration;

import org.jboss.hal.meta.token.NameTokens;
import org.jboss.hal.resources.Ids;
import org.jboss.hal.testsuite.fragment.FormFragment;
import org.jboss.hal.testsuite.page.BasePage;
import org.jboss.hal.testsuite.page.Place;
import org.openqa.selenium.support.FindBy;

@Place(NameTokens.JMX)
public class JmxPage extends BasePage {

    @FindBy(id = Ids.JMX_CONFIGURATION_FORM) private FormFragment configurationForm;
    @FindBy(id = Ids.JMX_AUDIT_LOG_FORM) private FormFragment auditForm;
    @FindBy(id = Ids.JMX_REMOTING_CONNECTOR_FORM) private FormFragment remotingConnectorForm;

    public FormFragment getConfigurationForm() {
        return configurationForm;
    }

    public FormFragment getAuditForm() {
        return auditForm;
    }

    public FormFragment getRemotingConnectorForm() {
        return remotingConnectorForm;
    }
}
