package object;

import object.discountpolicy.DiscountPolicy;
import object.money.Money;
import object.movie.Customer;
import object.movie.Movie;
import object.movie.Reservation;
import object.movie.Screening;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class TestScreeningAppConfig {

    @Test
    void seperateScreeningAppConfigAndFindAllBeans () {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                DiscountAppConfig.class, MovieAppConfigTests.class, ScreeningAppConfigTest.class
        );
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String name : beanDefinitionNames) {
            System.out.println("name = " + name);
        }
    }

    @Test
    void testDiscount () {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                DiscountAppConfig.class, MovieAppConfigTests.class, ScreeningAppConfigTest.class
        );
        Screening screening = ac.getBean("screening_09_50_cruella", Screening.class);
        Customer customer1 = new Customer(1L, "nameA");
        Reservation reservation = screening.reserve(1, customer1);

        assertThat(reservation.getFee().isGreaterThanOrEqual(Money.wons(9000L))).isTrue();
        assertThat(reservation.getFee().isLesserThan(Money.wons(9001L))).isTrue();
    }

    @Test
    @DisplayName(value = "Screening 객체에 의존관게가 잘 주입되었을까")
    void testInjection () {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                DiscountAppConfig.class, MovieAppConfigTests.class, ScreeningAppConfigTest.class
        );
        Screening screening = ac.getBean("screening_09_50_cruella", Screening.class);

        assertThat(screening.getMovie()).isInstanceOf(Movie.class);
        assertThat(screening.getMovie().getDiscountPolicy()).isInstanceOf(DiscountPolicy.class);
    }
}
