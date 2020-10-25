import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public final class ExampleBot {

    public static void main(final String[] args) {
        final String token = "Bob says you no get token.";
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
