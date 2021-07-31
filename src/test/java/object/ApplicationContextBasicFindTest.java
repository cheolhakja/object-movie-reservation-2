package object;

import object.movie.Screening;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    @Test
    void findBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Screening screening = ac.getBean("screening", Screening.class);
        System.out.println("screening = " + screening);

        assertThat(screening).isInstanceOf(Screening.class);
    }

    @Test
    void singletonTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Screening screening1 = ac.getBean("screening", Screening.class);
        Screening screening2 = ac.getBean("screening", Screening.class);

        System.out.println("screening1 = " + screening1);
        System.out.println("screening2 = " + screening2);
        //테스트 메소드 하나마다 새로운 메모리공간에 새롭게 객체를 할당하는구나
    }

    @Test
    @DisplayName("find all bean name")
    void findAllBeans() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for(String name : beanDefinitionNames) {
            System.out.println("이름으로 모든 빈 찾기 : " + ac.getBean(name));
        }
        //AppConfig$$EnhancerBySpring
    }
}
