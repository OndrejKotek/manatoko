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

import org.openqa.selenium.support.FindBy;

import static org.jboss.hal.resources.CSS.formSection;
import static org.jboss.hal.resources.CSS.modalBody;

/** Page fragment for an add resource dialog with a add-only form. */
public class AddResourceDialogFragment extends DialogFragment {

    @FindBy(css = "." + modalBody + " ." + formSection) private FormFragment form;

    public FormFragment getForm() {
        return form;
    }

    /** Shortcut for {@link DialogFragment#primaryButton()} */
    public void add() {
        primaryButton();
    }
}
