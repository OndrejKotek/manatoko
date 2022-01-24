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
package org.jboss.hal.manatoko.configuration.systemproperty;

import org.jboss.hal.manatoko.CrudConstants;
import org.jboss.hal.manatoko.Random;
import org.jboss.hal.resources.Ids;
import org.wildfly.extras.creaper.core.online.operations.Address;

import static org.jboss.hal.dmr.ModelDescriptionConstants.NAME;
import static org.jboss.hal.dmr.ModelDescriptionConstants.SYSTEM_PROPERTY;
import static org.jboss.hal.dmr.ModelDescriptionConstants.VALUE;

public interface SystemPropertyFixtures {

    String READ_NAME = Ids.build(SYSTEM_PROPERTY, CrudConstants.READ, NAME, Random.name());
    String READ_VALUE = Ids.build(SYSTEM_PROPERTY, CrudConstants.READ, VALUE, Random.name());

    static Address systemPropertyAddress(String name) {
        return Address.of(SYSTEM_PROPERTY, name);
    }
}
