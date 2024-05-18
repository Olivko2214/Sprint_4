package model;

import java.time.LocalDate;

public class OrderData {
    private final LocalDate deliveryDate;
    private final RentalPeriod period;

    public OrderData(LocalDate deliveryDate, RentalPeriod period) {
        this.deliveryDate = deliveryDate;
        this.period = period;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public RentalPeriod getPeriod() {
        return period;
    }
}
