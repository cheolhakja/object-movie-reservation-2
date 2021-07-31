package object.movie;

import object.money.Money;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Money getMovieFee() {
        return this.movie.getFee();
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    private Money calculateFee(int audienceCount){
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public Reservation reserve(int audienceCount, Customer...customer){
        return new Reservation(this, calculateFee(audienceCount),audienceCount, customer);
    }

    //----------
    public Movie getMovie() {
        return movie;
    }
}
