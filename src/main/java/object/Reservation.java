package object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reservation {
    private List<Customer> customers = new ArrayList<>();
    private Screening screening;
    private Money fee;
    private int audienceCount;

    public Reservation(Screening screening, Money fee, int audienceCount, Customer...customer) {
        this.customers.addAll(Arrays.asList(customer));
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }

    //----------
    public Money getFee() {
        return fee;
    }
}
