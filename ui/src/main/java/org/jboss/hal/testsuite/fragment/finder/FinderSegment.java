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
package org.jboss.hal.testsuite.fragment.finder;

/** A segment inside a {@link FinderPath}. */
public class FinderSegment {

    /** Separator is used in URL tokens. Please choose a string which is safe to use in URLs */
    private static final String SEPARATOR = "~";

    private final String columnId;
    private final String itemId;

    FinderSegment(String columnId, String itemId) {
        this.columnId = columnId;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinderSegment)) {
            return false;
        }

        FinderSegment that = (FinderSegment) o;
        if (!columnId.equals(that.columnId)) {
            return false;
        }
        return itemId.equals(that.itemId);

    }

    @Override
    public int hashCode() {
        int result = columnId.hashCode();
        result = 31 * result + itemId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        // Do not change this implementation as the place management relies on it!
        return columnId + SEPARATOR + itemId;
    }

    public String getColumnId() {
        return columnId;
    }

    public String getItemId() {
        return itemId;
    }
}