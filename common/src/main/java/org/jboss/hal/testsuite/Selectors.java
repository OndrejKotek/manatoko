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
package org.jboss.hal.testsuite;

/** Helper methods for common CSS / JQuery selectors */
public class Selectors {

    public static final String WRAPPER = "_wrapper"; // ID suffix of DataTable wrapper div elements

    /** returns the JQuery selector {@code :contains('<value>')} <strong>w/ the colon</strong>. */
    public static String contains(String value) {
        return ":contains('" + value + "')";
    }

    private Selectors() {
    }
}
