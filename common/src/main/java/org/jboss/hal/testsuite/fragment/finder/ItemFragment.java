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
package org.jboss.hal.testsuite.fragment.finder;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.findby.ByJQuery;
import org.jboss.arquillian.graphene.fragment.Root;
import org.jboss.hal.testsuite.fragment.DropdownFragment;
import org.jboss.hal.resources.CSS;
import org.jboss.hal.resources.Ids;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.jboss.arquillian.graphene.Graphene.waitModel;
import static org.jboss.hal.testsuite.Selectors.contains;
import static org.jboss.hal.resources.CSS.btnFinder;
import static org.jboss.hal.resources.CSS.btnGroup;
import static org.jboss.hal.resources.CSS.dropdownMenu;
import static org.jboss.hal.resources.CSS.dropdownToggle;

/** Fragment for a finder item. Use {@link ColumnFragment#selectItem(String)} to get an instance. */
public class ItemFragment {

    @Drone private WebDriver browser;
    @Root private WebElement root;
    private String itemId;
    private static final String DOT = ".";

    /** Executes the standard view action on this item and returns the current browser URL. */
    public String view() {
        WebElement viewActionElement;
        By viewInDropdownSelector = ByJQuery.selector(DOT + btnGroup + " a." + btnFinder + contains("View"));
        By viewInAnchorSelector = ByJQuery.selector("a" + contains("View"));
        try {
            viewActionElement = root.findElement(viewInDropdownSelector);
        } catch (NoSuchElementException notInButtonGroup) {
            try {
                viewActionElement = root.findElement(viewInAnchorSelector);
            } catch (NoSuchElementException notInAnchor) {
                throw new NoSuchElementException("Could not invoke \"View\" action, as the element doesn't exist");
            }
        }
        viewActionElement.click();
        waitModel().until().element(By.id(Ids.FINDER)).is().not().present();
        return browser.getCurrentUrl();
    }

    public String viewDefault() {
        By selector = ByJQuery.selector("a" + contains("View"));
        root.findElement(selector).click();
        waitModel().until().element(By.id(Ids.FINDER)).is().not().present();
        return browser.getCurrentUrl();
    }

    /**
     * Selects the default action in the dropdown
     */
    public void defaultAction() {
        By selector = ByJQuery.selector(DOT + btnGroup + " a." + btnFinder);
        root.findElement(selector).click();
    }

    /**
     * Check the default action name in the dropdown and select this default action
     *
     * @throws IllegalStateException if actual default action name differs from the expected one
     */
    public void defaultAction(String expectedDefaultActionName) {
        By selector = ByJQuery.selector(DOT + btnGroup + " a." + btnFinder);
        WebElement defaultButton = root.findElement(selector);
        String actualDefaultActionName = defaultButton.getText();
        if (!actualDefaultActionName.contentEquals(expectedDefaultActionName)) {
            throw new IllegalStateException("Expected '" + expectedDefaultActionName
                    + "' default action but actually there is '" + actualDefaultActionName + "'.");
        }
        defaultButton.click();
    }

    public DropdownFragment dropdown() {
        By toggleSelector = By.cssSelector(DOT + btnGroup + " button." + dropdownToggle);
        root.findElement(toggleSelector).click();

        By dropdownSelector = By.cssSelector(DOT + btnGroup + " ul." + dropdownMenu);
        WebElement dropdownElement = root.findElement(dropdownSelector);
        return Graphene.createPageFragment(DropdownFragment.class, dropdownElement);
    }

    public WebElement getRoot() {
        return root;
    }

    /**
     * Useful to determine icon type.
     *
     * @return content of class attribute
     * @throws NoSuchElementException if there is no icon in the item
     */
    public String getIconClasses() {
        return root.findElement(By.className(CSS.itemIcon)).getAttribute("class");
    }

    // ------------------------------------------------------ internals

    /**
     * Initializes the item with its ID. Must not be called manually. Instead use {@link ColumnFragment#selectItem(String)}
     * which calls this method automatically.
     */
    @SuppressWarnings("WeakerAccess") // public bc it's called from generated proxies
    public void initItemId(String itemId) {
        this.itemId = itemId;
    }

    private void assertItemId() {
        assert itemId != null : "No item ID available. Did you obtain the item using ColumnFragment.selectItem(String)?";
    }
}
