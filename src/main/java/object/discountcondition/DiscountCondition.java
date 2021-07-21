package object.discountcondition;

import object.movie.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
