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
import org.jboss.hal.testsuite.fragment.EmptyState;
import org.jboss.hal.testsuite.fragment.FormFragment;
import org.jboss.hal.testsuite.fragment.TabsFragment;
import org.jboss.hal.testsuite.page.BasePage;
import org.jboss.hal.testsuite.page.Place;
import org.openqa.selenium.support.FindBy;

import static org.jboss.hal.dmr.ModelDescriptionConstants.STORE;

@Place(NameTokens.LOCAL_CACHE)
public class LocalCachePage extends BasePage {

    @FindBy(id = Ids.LOCAL_CACHE + "-" + Ids.FORM) private FormFragment configurationForm;
    @FindBy(id = Ids.LOCAL_CACHE + "-" + Ids.TAB_CONTAINER) private TabsFragment localCacheTabs;
    @FindBy(id = Ids.LOCAL_CACHE + "-" + Ids.CACHE_COMPONENT_EXPIRATION + "-" + Ids.FORM) private FormFragment expirationForm;
    @FindBy(id = Ids.LOCAL_CACHE + "-" + Ids.CACHE_COMPONENT_LOCKING + "-" + Ids.FORM) private FormFragment lockingForm;
    @FindBy(id = Ids.LOCAL_CACHE + "-" + Ids.CACHE_COMPONENT_TRANSACTION + "-" + Ids.FORM) private FormFragment transactionForm;

    @FindBy(id = Ids.LOCAL_CACHE + "-" + STORE + "-" + Ids.EMPTY) private EmptyState localCacheNoStore;
    @FindBy(id = Ids.LOCAL_CACHE + "-" + Ids.CACHE_STORE_FILE + "-" + Ids.TAB_CONTAINER) private TabsFragment fileStoreTabs;
    @FindBy(id = Ids.LOCAL_CACHE + "-" + Ids.CACHE_STORE_FILE + "-" + Ids.FORM) private FormFragment fileStoreForm;

    public FormFragment getConfigurationForm() {
        return configurationForm;
    }

    public TabsFragment getLocalCacheTabs() {
        return localCacheTabs;
    }

    public FormFragment getExpirationForm() {
        return expirationForm;
    }

    public FormFragment getLockingForm() {
        return lockingForm;
    }

    public FormFragment getTransactionForm() {
        return transactionForm;
    }

    public EmptyState getLocalCacheNoStore() {
        return localCacheNoStore;
    }

    public TabsFragment getFileStoreTabs() {
        return fileStoreTabs;
    }

    public FormFragment getFileStoreForm() {
        return fileStoreForm;
    }
}
