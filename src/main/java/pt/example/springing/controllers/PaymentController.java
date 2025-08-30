package pt.example.springing.controllers;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.example.springing.model.PaymentEvent;
import pt.example.springing.model.PaymentEventRequest;
import pt.example.springing.services.PaymentStreamService;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentStreamService paymentStreamService;

    public PaymentController(PaymentStreamService paymentStreamService) {
        this.paymentStreamService = paymentStreamService;
    }

    @GetMapping(value = "/{id}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PaymentEvent> streamPaymentStatus(@PathVariable("id") UUID id) {
        return paymentStreamService.getPaymentStream(id)
                .startWith(new PaymentEvent("STREAM_STARTED")) // Optional: initial event
                .timeout(Duration.ofSeconds(30)) // Optional: auto-close after 30s timeout
                .onErrorReturn(new PaymentEvent("ERROR: Stream timed out"));
    }


    @PostMapping("/{id}/trigger")
    public String triggerEvent(@PathVariable("id") UUID id, @RequestBody(required = false) PaymentEventRequest request) {
        String status = request != null && request.status() != null ? request.status() : "UNKNOWN_EVENT";
        boolean sent = paymentStreamService.sendEvent(id, new PaymentEvent(status));
        return sent ? "Event sent: " + status : "Failed to send event (no subscribers)";
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<String> completeStream(@PathVariable("id") UUID id) {
        paymentStreamService.closeStream(id);
        return ResponseEntity.ok("Stream completed for payment: " + id);
    }
}