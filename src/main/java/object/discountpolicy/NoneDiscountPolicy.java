package object.discountpolicy;

import object.Money;
import object.Screening;

public class NoneDiscountPolicy implements DiscountPolicy{
    @Override
    public Money calculateDiscountAmount(Screening screening){
        return Money.ZERO;
    }
}
