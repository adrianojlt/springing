package pt.example.springing.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pt.example.springing.model.PaymentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Service
public class PaymentStreamService {

    private final Map<UUID, Sinks.Many<PaymentEvent>> sinks = new ConcurrentHashMap<>();

    public Sinks.Many<PaymentEvent> getSink(UUID paymentId) {
        return sinks.computeIfAbsent(paymentId, id -> Sinks.many().multicast().onBackpressureBuffer());
    }

    public Flux<PaymentEvent> getPaymentStream(UUID paymentId) {

        return getSink(paymentId).asFlux()
                .doOnSubscribe(s -> log.info("Client subscribed to payment: {}", paymentId))
                .doFinally(signal -> {
                    log.info("Stream ended for payment: {}, signal: {}", paymentId, signal);
                    // Optional: remove sink on complete (if no more subscribers needed)
                    // sinks.remove(paymentId); // Uncomment if you want to destroy after first complete
                });
    }

    public boolean sendEvent(UUID paymentId, PaymentEvent event) {

        Sinks.Many<PaymentEvent> sink = sinks.get(paymentId);

        if (sink == null) {
            return false;
        }

        return sink.tryEmitNext(event) == Sinks.EmitResult.OK;
    }

    public void closeStream(UUID paymentId) {

        Sinks.Many<PaymentEvent> sink = sinks.remove(paymentId);

        if (sink != null) {
            sink.tryEmitComplete();
        }
    }
}
