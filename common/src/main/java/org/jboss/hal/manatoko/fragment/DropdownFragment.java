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
package org.jboss.hal.manatoko.fragment;

import org.jboss.arquillian.graphene.findby.ByJQuery;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.jboss.hal.manatoko.Selectors.contains;

/** Fragment for a PatternFly dropdown */
public class DropdownFragment {

    @Root private WebElement root;

    /** Returns if the dropdown contains option with the specified title */
    public boolean containsItem(String title) {
        return root.findElements(itemSelector(title)).size() > 0;
    }

    /** Clicks on the item with the specified title */
    public void click(String title) {
        root.findElement(itemSelector(title)).click();
    }

    private By itemSelector(String title) {
        return ByJQuery.selector("li a" + contains(title));
    }
}
