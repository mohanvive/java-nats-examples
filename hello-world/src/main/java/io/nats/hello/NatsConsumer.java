package io.nats.hello;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class NatsConsumer
{
    public static void main( String[] args )
    {
        try (Connection nc = Nats.connect("nats://localhost:4222")) {
            Subscription sub = nc.subscribe("subject");
            //Message msg = sub.nextMessage(Duration.ofMillis(10000));

            Dispatcher d = nc.createDispatcher((msg) -> {
                String response = new String(msg.getData(), StandardCharsets.UTF_8);
                System.out.println(response);
            });

            d.subscribe("subject");

            Thread.sleep(600000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
