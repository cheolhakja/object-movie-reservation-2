package object.discountpolicy;

import object.money.Money;
import object.movie.Screening;

public class NoneDiscountPolicy implements DiscountPolicy{
    @Override
    public Money calculateDiscountAmount(Screening screening){
        return Money.ZERO;
    }
}
