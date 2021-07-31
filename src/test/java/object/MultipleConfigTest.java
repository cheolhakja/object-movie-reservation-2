package object;

import object.money.Money;
import object.movie.Customer;
import object.movie.Movie;
import object.movie.Reservation;
import object.movie.Screening;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MultipleConfigTest {
    @Test
    public void fieldInjectionTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MovieAppConfig.class, DiscountAppConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String name : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);
            System.out.println("beanDefinition = " + beanDefinition);
        }
    }

    @Test
    public void reserveTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MovieAppConfig.class, DiscountAppConfig.class);
        Screening screening = ac.getBean("screening", Screening.class);
        Customer customer1 = new Customer(1L, "nameA");
        Reservation reservation = screening.reserve(1, customer1);

        Assertions.assertThat(reservation.getFee().isGreaterThanOrEqual(Money.wons(9000L))).isTrue();
        Assertions.assertThat(reservation.getFee().isLesserThan(Money.wons(9001L))).isTrue();
    }

    @Test
    public void signletonTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MovieAppConfig.class, DiscountAppConfig.class);
        Screening screening1 = ac.getBean("screening", Screening.class);
        Screening screening2 = ac.getBean("screening", Screening.class);
        Movie movie1 = ac.getBean("movie", Movie.class);
        Movie movie2 = ac.getBean("movie", Movie.class);

        Assertions.assertThat(screening1.getMovie()).isSameAs(screening2.getMovie());
        Assertions.assertThat(movie1.getDiscountPolicy()).isSameAs(movie2.getDiscountPolicy());

        System.out.println("screening1.getMovie() = " + screening1.getMovie());
        System.out.println("screening2.getMovie() = " + screening2.getMovie());
        System.out.println("movie1.getDiscountPolicy() = " + movie1.getDiscountPolicy());
        System.out.println("movie2.getDiscountPolicy() = " + movie2.getDiscountPolicy());
    }
}
