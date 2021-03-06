/*
 * Copyright (c) 2018 Wave Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wavesoftware.eid;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wavesoftware.eid.api.ConfigurationBuilder;
import pl.wavesoftware.eid.api.Configurator;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 2018-12-18
 */
@State(Scope.Benchmark)
public class DisableValidatorState {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(DisableValidatorState.class);
    private ConfigurationContext context;

    @Setup
    public void setup() {
        context =
            new ConfigurationContext(new Configurator() {
                @Override
                public void configure(ConfigurationBuilder configuration) {
                    configuration.validator(null);
                    LOGGER.debug("Disable validation of Eid numbers");
                }
            });
    }

    @TearDown
    public void tearDown() {
        context.close();
        LOGGER.debug("Restoring configuration context");
    }
}
