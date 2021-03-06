package object;

import object.discountcondition.DiscountCondition;
import object.discountcondition.SequenceCondition;
import object.discountpolicy.DiscountPolicy;
import object.discountpolicy.PercentDiscountPolicy;
import object.money.Money;
import object.movie.Customer;
import object.movie.Movie;
import object.movie.Reservation;
import object.movie.Screening;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDateTime;

public class MovieAppConfigTest {
    @Test
    void seperateAppConfig() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MovieAppConfig.class, DiscountAppConfig.class);
        Screening screening = ac.getBean("screening", Screening.class);
        Customer customer1 = new Customer(1L, "nameA");
        Reservation reservation = screening.reserve(1, customer1);

        Assertions.assertThat(reservation.getFee().isGreaterThanOrEqual(Money.wons(9000L))).isTrue();
        Assertions.assertThat(reservation.getFee().isLesserThan(Money.wons(9001L))).isTrue();
    }

    @Test
    void singletonTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MovieAppConfig.class, DiscountAppConfig.class);
        Screening screening1 = ac.getBean("screening", Screening.class);
        Screening screening2 = ac.getBean("screening", Screening.class);

        System.out.println("screening1.getMovie() = " + screening1.getMovie());
        System.out.println("screening2.getMovie() = " + screening2.getMovie());
        Assertions.assertThat(screening1.getMovie()).isSameAs(screening2.getMovie());
    }

    @Test
    void findDiscountPolicy() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MovieAppConfig.class, DiscountAppConfig.class);
        Movie movie = ac.getBean("movie", Movie.class);
        System.out.println(movie.getDiscountPolicy());
    }

    @Configuration
    static class MovieAppConfig {
        @Bean
        public Screening screening(@Autowired DiscountPolicy discountPolicy) {
            return new Screening(movie(null), 1,
                    LocalDateTime.of(2021,6,1,13,0));
            //(??????)autowired??? ????????? movie()??? ??????????????? ????????? ???????
            //(??????) ??????????????? ???????????? ???????????? ????????? autowired??? ??????????????? ?????????????
        }

        @Bean
        public Movie movie(@Autowired DiscountPolicy discountPolicy) {
            return new Movie("movie1", Duration.ofHours(2), Money.wons(10000),
                    discountPolicy);
        }
    }

    @Configuration
    static class DiscountAppConfig {
        @Bean
        public DiscountPolicy discountPolicy() {
            return new PercentDiscountPolicy(0.1, this.discountCondition());
            //????????? ??????????????? ?????????
        }

        @Bean
        public DiscountCondition discountCondition() {
            return new SequenceCondition(1);
            // AppConfig ????????? ???????????? ????????? ????????? ???????????? ????????? ?????? ????????? ???????????? ????????? ????????????????????? ????????? ??? ?????? ?????????
        }
    }
}
