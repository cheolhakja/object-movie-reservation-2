package object;

import object.discountcondition.DiscountCondition;
import object.discountcondition.SequenceCondition;
import object.discountpolicy.DiscountPolicy;
import object.discountpolicy.PercentDiscountPolicy;
import object.money.Money;
import object.movie.Movie;
import object.movie.Screening;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
public class AppConfig {
    @Bean
    public Screening screening() {
        return new Screening(this.movie(), 1,
                LocalDateTime.of(2021,6,1,13,0));
    }

    @Bean
    public Movie movie() {
        return new Movie("movie1", Duration.ofHours(2), Money.wons(10000),
                this.discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new PercentDiscountPolicy(0.1, this.discountCondition());
    }

    @Bean
    public DiscountCondition discountCondition() {
        return new SequenceCondition(1);
    }
    /*
    같은 타입인데 여러개의 메소드 필요
    @Primary 를 쓰기 or 별칭을 지정하기
    */
}
