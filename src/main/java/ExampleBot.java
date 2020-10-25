import com.fasterxml.jackson.databind.annotation.JsonAppend;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class ExampleBot {



    public static void main(final String[] args) throws FileNotFoundException {
        Properties quickProp = new Properties();
        FileInputStream propRead = new FileInputStream("E:\\Work\\DiscBrainJava\\config.properties");

        try {
            quickProp.load(propRead);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String token = quickProp.getProperty("Token") ;
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if ("!ZCW".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("CODE HARDER!").block();
            }
        });

        gateway.onDisconnect().block();
    }
}
