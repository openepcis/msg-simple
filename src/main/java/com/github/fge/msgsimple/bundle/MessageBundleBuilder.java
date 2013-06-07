/*
 * Copyright (c) 2013, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.fge.msgsimple.bundle;

import com.github.fge.Thawed;
import com.github.fge.msgsimple.provider.MessageSourceProvider;
import com.github.fge.msgsimple.provider.StaticMessageSourceProvider;
import com.github.fge.msgsimple.source.MessageSource;
import com.github.fge.msgsimple.serviceloader.MessageBundles;
import com.github.fge.msgsimple.serviceloader.MsgSimpleMessageBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Builder class for a message bundle
 *
 * <p>You cannot instantiate this class directly: use {@link
 * MessageBundle#newBuilder()}.</p>
 */
public final class MessageBundleBuilder
    implements Thawed<MessageBundle>
{
    private static final MessageBundle BUNDLE
        = MessageBundles.forClass(MsgSimpleMessageBundle.class);

    final List<MessageSourceProvider> providers
        = new ArrayList<MessageSourceProvider>();

    MessageBundleBuilder()
    {
    }

    MessageBundleBuilder(final MessageBundle bundle)
    {
        providers.addAll(bundle.providers);
    }

    /**
     * Append a message provider
     *
     * @param provider the provider
     * @throws NullPointerException provider is null
     * @return this
     */
    public MessageBundleBuilder appendProvider(
        final MessageSourceProvider provider)
    {
        if (provider == null)
            throw new NullPointerException(BUNDLE.getMessage("cfg.nullProvider"));
        providers.add(provider);
        return this;
    }

    /**
     * Prepend a message provider
     *
     * @param provider the provider
     * @throws NullPointerException provider is null
     * @return this
     */
    public MessageBundleBuilder prependProvider(
        final MessageSourceProvider provider)
    {
        if (provider == null)
            throw new NullPointerException(BUNDLE.getMessage("cfg.nullProvider"));
        providers.add(0, provider);
        return this;
    }

    /**
     * Convenience method to append a single-source provider
     *
     * @param source the message source
     * @return this
     * @see StaticMessageSourceProvider#withSingleSource(MessageSource)
     */
    public MessageBundleBuilder appendSource(final MessageSource source)
    {
        final MessageSourceProvider provider
            = StaticMessageSourceProvider.withSingleSource(source);
        providers.add(provider);
        return this;
    }

    /**
     * Convenience method to prepend a single-source provider
     *
     * @param source the message source
     * @return this
     * @see StaticMessageSourceProvider#withSingleSource(MessageSource)
     */
    public MessageBundleBuilder prependSource(final MessageSource source)
    {
        final MessageSourceProvider provider
            = StaticMessageSourceProvider.withSingleSource(source);
        providers.add(0, provider);
        return this;
    }

    /**
     * Convenience method to append a single-source provider for a given locale
     *
     * @param source the message source
     * @return this
     * @see StaticMessageSourceProvider#withSingleSource(Locale, MessageSource)
     */
    public MessageBundleBuilder appendSource(final Locale locale,
        final MessageSource source)
    {
        final MessageSourceProvider provider
            = StaticMessageSourceProvider.withSingleSource(locale, source);
        providers.add(provider);
        return this;
    }

    /**
     * Convenience method to prepend a single-source provider for a given locale
     *
     * @param source the message source
     * @return this
     * @see StaticMessageSourceProvider#withSingleSource(Locale, MessageSource)
     */
    public MessageBundleBuilder prependSource(final Locale locale,
        final MessageSource source)
    {
        final MessageSourceProvider provider
            = StaticMessageSourceProvider.withSingleSource(locale, source);
        providers.add(0, provider);
        return this;
    }

    @Override
    public MessageBundle freeze()
    {
        return new MessageBundle(this);
    }
}
