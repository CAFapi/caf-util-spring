/*
 * Copyright 2024 Open Text.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cafapi.util.spring.propertysource;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

public final class CafConfigEnvironmentListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>
{
    @Override
    public void onApplicationEvent(final ApplicationEnvironmentPreparedEvent event)
    {
        final ConfigurableEnvironment environment = event.getEnvironment();
        final CafConfigEnvironmentPostProcessor cafConfigEnvironmentPostProcessor = new CafConfigEnvironmentPostProcessor();
        cafConfigEnvironmentPostProcessor.postProcessEnvironment(environment, null);
    }
}
