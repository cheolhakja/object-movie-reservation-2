package object.discountpolicy;

import object.Money;
import object.Screening;
import object.discountcondition.DiscountCondition;

public class PercentDiscountPolicy extends DefaultDiscountPolicy{
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
