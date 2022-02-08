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
package org.jboss.hal.testsuite.fragment;

import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.hal.testsuite.Console;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Breadcrumb used in {@linkplain PagesFragment pages} to navigate to nested resources. Use {@link PagesFragment#breadcrumb()}
 * to get an instance.
 */
public class PageBreadcrumbFragment {

    @Root private WebElement root;
    @Inject private Console console;

    public String lastValue() {
        return root.findElement(By.cssSelector("li:last-child")).getText();
    }

    public WebElement firstElement() {
        return root.findElement(By.cssSelector("li:first-child"));
    }

    /**
     * Get back to the main page from the nested detail
     */
    public void getBackToMainPage() {
        console.scrollToBottom(firstElement()).click();
        Graphene.waitAjax().until().element(root).is().not().visible();
    }
}
