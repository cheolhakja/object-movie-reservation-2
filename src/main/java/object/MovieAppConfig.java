package object;

import object.discountpolicy.DiscountPolicy;
import object.discountpolicy.PercentDiscountPolicy;
import object.money.Money;
import object.movie.Movie;
import object.movie.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
@Import(DiscountAppConfig.class)
public class MovieAppConfig {
    //ApplicationContext의 생성자 매개변수에 DiscountAppConfig를 넣지 않고 MovieAppConfig만 넣어도
    //자동으로 DiscountAppConfig의 설정도 사용하여 초기화

    @Autowired
    private DiscountAppConfig discountAppConfig;

    @Bean
    public Screening screening() {
        return new Screening(movie(), 1,
                LocalDateTime.of(2021,6,1,13,0));
    }

    @Bean
    public Movie movie() {
        return new Movie("movie1", Duration.ofHours(2), Money.wons(10000),
                discountAppConfig.discountPolicy());
    }
}
