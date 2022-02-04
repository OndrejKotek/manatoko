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

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.jboss.arquillian.graphene.Graphene.waitGui;
import static org.jboss.hal.resources.CSS.btnDefault;
import static org.jboss.hal.resources.CSS.btnPrimary;
import static org.jboss.hal.resources.CSS.modalFooter;

/** Page fragment for modal dialogs. Use {@link org.jboss.hal.testsuite.Console#dialog()} to get an instance. */
public class DialogFragment {

    @Root protected WebElement root;

    /** Clicks on the primary button and waits until the dialog has been closed. */
    public void primaryButton() {
        WebElement primaryButton = getPrimaryButton();
        primaryButton.click();
        waitGui().until().element(root).is().not().visible();
    }

    public WebElement getPrimaryButton() {
        return root.findElement(By.cssSelector("." + modalFooter + " ." + btnPrimary));
    }

    public WebElement getSecondaryButton() {
        return root.findElement(By.cssSelector("." + modalFooter + " ." + btnDefault));
    }

    public WebElement getRoot() {
        return root;
    }

    public FormFragment getForm(String id) {
        WebElement formElement = root.findElement(By.id(id));
        waitGui().until().element(formElement).is().visible();
        return Graphene.createPageFragment(FormFragment.class, formElement);
    }
}
