package object;

import object.discountpolicy.DiscountPolicy;
import object.money.Money;
import object.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MovieAppConfigTests {
    @Bean
    @Qualifier(value = "Cruella")
    public Movie movieCruella (@Autowired DiscountPolicy discountPolicy) {
        return new Movie("Cruella", Duration.ofMinutes(133), Money.wons(10000),
                discountPolicy);
    }
    //Duration이 헷갈림

    @Bean
    @Qualifier(value = "TheAmazingSpiderMan2")
    public Movie movieTheAmazingSpiderMan2 (@Autowired DiscountPolicy discountPolicy) {
            return new Movie("The Amazing Spider-Man", Duration.ofMinutes(142), Money.wons(8000),
                    discountPolicy);
    }
}
