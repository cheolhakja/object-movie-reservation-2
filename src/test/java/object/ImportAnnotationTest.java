package object;

import object.money.Money;
import object.movie.Customer;
import object.movie.Reservation;
import object.movie.Screening;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportAnnotationTest {
    @Test
    void manyAppConfigUsingImport() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MovieAppConfig.class);
        //설정정보에서 DiscountPolicyAppConfig를 안넘겨줘도 되는구나
        Screening screening = ac.getBean("screening", Screening.class);
        Customer customer1 = new Customer(1L, "nameA");
        Reservation reservation = screening.reserve(1, customer1);

        Assertions.assertThat(reservation.getFee().isGreaterThanOrEqual(Money.wons(9000L))).isTrue();
        Assertions.assertThat(reservation.getFee().isLesserThan(Money.wons(9001L))).isTrue();
    }

    @Test
    void findAllBeansUsingImportAnnotation() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MovieAppConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String name : beanDefinitionNames) {
            if(ac.getBeanDefinition(name).getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println(ac.getBean(name));
            }
        }
        //DiscountAppConfig의 설정정보도 빈으로 등록되는구나
    }
}
