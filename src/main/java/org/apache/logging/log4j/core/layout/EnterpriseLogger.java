package org.apache.logging.log4j.core.layout;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.util.StringBuilderWriter;
import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Plugin(name = "EnterpriseLogger", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)
public class EnterpriseLogger extends AbstractStringLayout {

    private static final EnterpriseLogger enterpriseLogger = new EnterpriseLogger(StandardCharsets.UTF_8);

    protected EnterpriseLogger(Charset charset) {
        super(charset);
    }

    @PluginFactory
    public static EnterpriseLogger builder() {
        return enterpriseLogger;
    }

    @Override
    public String toSerializable(LogEvent event) {
        final StringBuilderWriter writer = new StringBuilderWriter();
        try {
            Map<String, String> entLogEvent = new HashMap<String, String>();

            if (event.getContextData() != null)
                entLogEvent.putAll(event.getContextData().toMap());
            entLogEvent.put("host_name", InetAddress.getLocalHost().getHostName());
            entLogEvent.put("event_time", DateTime.now(DateTimeZone.UTC).toString());
            entLogEvent.put("event_time_local", DateTime.now().toLocalDateTime().toString());
            entLogEvent.put("logger", event.getLoggerName());

            entLogEvent.put("severity", event.getLevel().name());
            entLogEvent.put("thread", event.getThreadName() + "-" + String.valueOf(event.getThreadId()));
            if (event.getMessage() != null) {
                entLogEvent.put("message", event.getMessage().getFormattedMessage().trim());
                if (event.getMessage().getThrowable() != null)
                    entLogEvent.put("exception", event.getMessage().getThrowable().toString());
            }

            new ObjectMapper().writeValue(writer, entLogEvent);
            return writer.toString();
        } catch (final IOException e) {
            LOGGER.error(e);
            return Strings.EMPTY;
        }
    }
}
