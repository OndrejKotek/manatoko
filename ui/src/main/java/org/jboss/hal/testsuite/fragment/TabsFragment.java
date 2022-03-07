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
package org.jboss.hal.testsuite.fragment;

import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.jboss.arquillian.graphene.Graphene.waitGui;

/** Fragment for a tab element. */
public class TabsFragment {

    @Root private WebElement root;

    /** Switch to the pane with the specified id. */
    public void select(String id) {
        WebElement link = root.findElement(By.cssSelector("a[href='#" + id + "']"));
        WebElement pane = root.findElement(By.id(id));
        link.click();
        waitGui().until().element(pane).is().visible();
    }

    public WebElement getRoot() {
        return root;
    }
}
