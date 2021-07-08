package io.nats.hello;

import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamManagement;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.nats.client.api.StorageType;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;
import io.nats.client.support.JsonUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Properties;

public class NatsSimple
{
    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.setProperty(Options.PROP_USERNAME, "hello");
        props.setProperty(Options.PROP_PASSWORD, "world");

        Options o = new Options.Builder(props).server("nats://localhost:4222").build();
        try (Connection nc = Nats.connect(o)) {
            nc.publish("subject", "hello dsdsd".getBytes(StandardCharsets.UTF_8));

            //nc.flush(Duration.ofSeconds(5));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
