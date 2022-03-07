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

import org.jboss.hal.meta.token.NameTokens;
import org.jboss.hal.resources.Ids;
import org.jboss.hal.testsuite.fragment.FormFragment;
import org.jboss.hal.testsuite.fragment.TableFragment;
import org.jboss.hal.testsuite.fragment.TabsFragment;
import org.jboss.hal.testsuite.page.BasePage;
import org.jboss.hal.testsuite.page.Place;
import org.openqa.selenium.support.FindBy;

import static org.jboss.hal.dmr.ModelDescriptionConstants.CREDENTIAL_REFERENCE;
import static org.jboss.hal.testsuite.Selectors.WRAPPER;

@Place(NameTokens.MAIL_SESSION)
public class MailPage extends BasePage {

    @FindBy(id = Ids.MAIL_SESSION_FORM) private FormFragment mailSessionForm;
    @FindBy(id = Ids.MAIL_SERVER_TABLE + WRAPPER) private TableFragment mailServerTable;
    @FindBy(id = Ids.MAIL_SERVER_TAB_CONTAINER) private TabsFragment mailServerTabs;
    @FindBy(id = Ids.MAIL_SERVER_FORM) private FormFragment mailServerAttributesForm;
    @FindBy(id = Ids.MAIL_SERVER + "-" + CREDENTIAL_REFERENCE + "-" + Ids.FORM) private FormFragment mailServerCrForm;

    public FormFragment getMailSessionForm() {
        return mailSessionForm;
    }

    public TableFragment getMailServerTable() {
        return mailServerTable;
    }

    public TabsFragment getMailServerTabs() {
        return mailServerTabs;
    }

    public FormFragment getMailServerAttributesForm() {
        return mailServerAttributesForm;
    }

    public FormFragment getMailServerCrForm() {
        return mailServerCrForm;
    }
}
