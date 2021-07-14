package object;

import object.discountpolicy.DefaultDiscountPolicy;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningtime;
    private Money fee;
    private DefaultDiscountPolicy discountPolicy;

    public Movie(String title, Duration runningtime, Money fee, DefaultDiscountPolicy discountPolicy) {
        this.title = title;
        this.runningtime = runningtime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return this.fee;
    }

    public Money calculateMovieFee(Screening screening){
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
