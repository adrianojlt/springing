package pt.example.springing;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

@Log4j2
public class ReactiveProgrammingTests {

    @Test
    public void messageExampleTest() {
        Sinks.Many<String> chatSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<String> chatFlux = chatSink.asFlux();

        // Use StepVerifier to test
        StepVerifier.create(chatFlux)
                .then(() -> {
                    chatSink.tryEmitNext("Hellooooo!");
                    chatSink.tryEmitNext("How are you!?");
                })
                .expectNext("Hellooooo!")
                .expectNext("How are you!?")
                .thenCancel() // because multicast flux doesn't complete
                .verify();
    }

    @Test
    @Disabled
    public void messageExample() {

        Sinks.Many<String> chatSink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<String> chatFlux = chatSink.asFlux();

        // Subscribers
        chatFlux.subscribe(msg -> log.info("User 1 received: {}" , msg));
        chatFlux.subscribe(msg -> log.info("User 2 received: {}" , msg));

        // Emit messages
        chatSink.tryEmitNext("Hellooooo!");
        chatSink.tryEmitNext("How are you!?");
    }

    @Test
    @Disabled
    public void temperatureExample() {

        Sinks.Many<Integer> temperatureSink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<Integer> temperatureFlux = temperatureSink.asFlux();

        temperatureFlux.subscribe(temp -> log.info("Display received: {}", temp));
        temperatureFlux.subscribe(temp -> log.info("Alert system received: {}", temp));

        temperatureSink.tryEmitNext(25);
        temperatureSink.tryEmitNext(26);
        temperatureSink.tryEmitNext(27);
    }
}
