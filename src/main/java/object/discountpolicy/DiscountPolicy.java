package object.discountpolicy;

import object.money.Money;
import object.movie.Screening;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
