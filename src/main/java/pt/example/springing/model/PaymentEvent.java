package pt.example.springing.model;

public record PaymentEvent(String status, long timestamp) {
    public PaymentEvent(String status) {
        this(status, System.currentTimeMillis());
    }
}
