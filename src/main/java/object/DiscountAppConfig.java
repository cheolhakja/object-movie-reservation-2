package object;

import object.discountcondition.DiscountCondition;
import object.discountcondition.SequenceCondition;
import object.discountpolicy.DiscountPolicy;
import object.discountpolicy.PercentDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountAppConfig {
    @Bean
    public DiscountPolicy discountPolicy() {
        return new PercentDiscountPolicy(0.1, this.discountCondition());
    }

    @Bean
    public DiscountCondition discountCondition() {
        return new SequenceCondition(1);
    }
}
