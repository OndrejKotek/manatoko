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

import org.jboss.hal.resources.Ids;
import org.jboss.hal.testsuite.fragment.FormFragment;
import org.jboss.hal.testsuite.fragment.TableFragment;
import org.jboss.hal.testsuite.fragment.TabsFragment;
import org.jboss.hal.testsuite.page.BasePage;
import org.jboss.hal.testsuite.page.Place;
import org.openqa.selenium.support.FindBy;

@Place(Ids.UNDERTOW_APP_SECURITY_DOMAIN)
public class UndertowApplicationSecurityDomainPage extends BasePage {

    @FindBy(id = Ids.UNDERTOW_APP_SECURITY_DOMAIN_TAB_CONTAINER) private TabsFragment tab;

    @FindBy(id = Ids.UNDERTOW_APP_SECURITY_DOMAIN_FORM) private FormFragment attributesForm;

    @FindBy(id = "undertow-single-sign-on-table") private TableFragment singleSignOnTable;

    @FindBy(id = Ids.UNDERTOW_SINGLE_SIGN_ON_FORM) private FormFragment singleSignOnForm;

    @FindBy(id = Ids.UNDERTOW_APP_SECURITY_DOMAIN + "-credential-reference-form") private FormFragment credentialReferenceForm;

    public TabsFragment getTab() {
        return tab;
    }

    public FormFragment getAttributesForm() {
        getTab().select(Ids.UNDERTOW_APP_SECURITY_DOMAIN_TAB);
        return attributesForm;
    }

    public FormFragment getSingleSignOnForm() {
        getTab().select("undertow-single-sign-on-table");
        return singleSignOnForm;
    }

    public FormFragment getCredentialReferenceForm() {
        getTab().select("undertow-application-security-domain-credential-reference-tab");
        return credentialReferenceForm;
    }
}
