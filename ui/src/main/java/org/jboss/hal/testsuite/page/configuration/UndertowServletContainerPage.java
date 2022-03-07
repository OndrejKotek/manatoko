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
package org.jboss.hal.testsuite.page.configuration;

import org.jboss.hal.resources.Ids;
import org.jboss.hal.testsuite.fragment.FormFragment;
import org.jboss.hal.testsuite.fragment.TabsFragment;
import org.jboss.hal.testsuite.page.BasePage;
import org.jboss.hal.testsuite.page.Place;
import org.openqa.selenium.support.FindBy;

@Place(Ids.UNDERTOW_SERVLET_CONTAINER)
public class UndertowServletContainerPage extends BasePage {

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_TAB_CONTAINER) private TabsFragment configurationTab;

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_CONFIGURATION_FORM) private FormFragment attributesForm;

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_MIME_MAPPING_FORM) private FormFragment mimeMappingForm;

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_WELCOME_FILE_FORM) private FormFragment welcomeFileForm;

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_JSP + "-form") private FormFragment jspForm;

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_WEBSOCKET + "-form") private FormFragment webSocketsForm;

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_SESSION + "-form") private FormFragment sessionsForm;

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_COOKIE + "-form") private FormFragment cookiesForm;

    @FindBy(id = Ids.UNDERTOW_SERVLET_CONTAINER_CRAWLER + "-form") private FormFragment crawlerForm;

    public FormFragment getJspForm() {
        return jspForm;
    }

    public FormFragment getWebSocketsForm() {
        return webSocketsForm;
    }

    public FormFragment getSessionsForm() {
        return sessionsForm;
    }

    public FormFragment getCookiesForm() {
        return cookiesForm;
    }

    public FormFragment getCrawlerForm() {
        return crawlerForm;
    }

    public TabsFragment getConfigurationTab() {
        return configurationTab;
    }

    public FormFragment getAttributesForm() {
        configurationTab.select(Ids.UNDERTOW_SERVLET_CONTAINER_CONFIGURATION_TAB);
        return attributesForm;
    }

    public FormFragment getMimeMappingForm() {
        configurationTab.select(Ids.UNDERTOW_SERVLET_CONTAINER_MIME_MAPPING_TAB);
        return mimeMappingForm;
    }

    public FormFragment getWelcomeFileForm() {
        configurationTab.select(Ids.UNDERTOW_SERVLET_CONTAINER_WELCOME_FILE_TAB);
        return welcomeFileForm;
    }
}
