package object.discountpolicy;

import object.Money;
import object.Screening;

public interface DiscountPolicy {
    public Money calculateDiscountAmount(Screening screening);
}
