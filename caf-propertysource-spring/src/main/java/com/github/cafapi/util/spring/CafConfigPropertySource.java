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
package com.github.cafapi.util.spring;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.env.PropertySource;

import com.hpe.caf.secret.SecretUtil;

final class CafConfigPropertySource extends PropertySource<Object>
{
    private static final String SECRET_PROPERTY_PREFIX = "secret:";
    private static final Pattern SECRET_PROPERTY_PATTERN = Pattern.compile(SECRET_PROPERTY_PREFIX + "([^:]+)(?::(.+))?");

    public CafConfigPropertySource()
    {
        super("CafConfigEnvironmentPostProcessor");
    }

    @Override
    public Object getProperty(final String name)
    {
        if (name.startsWith(SECRET_PROPERTY_PREFIX)) {
            final Matcher matcher = SECRET_PROPERTY_PATTERN.matcher(name);

            if (matcher.find()) {
                final String key = matcher.group(1);
                final String defaultValue = matcher.group(2);
                try {
                    return SecretUtil.getSecret(key, defaultValue);
                } catch (final IOException e) {
                    throw new UncheckedIOException(String.format("Unable to read secret '%s'", key), e);
                }
            } else {
                throw new RuntimeException(String.format("Secret specified in configuration has unexpected format. "
                    + "Expected 'secret:KEY' or 'secret:KEY:DEFAULT_VALUE' but was '%s'", name));
            }
        }

        return null;
    }
}
