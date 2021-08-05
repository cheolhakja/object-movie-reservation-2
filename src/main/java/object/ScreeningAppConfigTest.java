package object;

import object.movie.Movie;
import object.movie.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class ScreeningAppConfigTest {
    @Bean
    public Screening screening_09_50_cruella (@Autowired @Qualifier("Cruella") Movie movie) {
        return new Screening(movie, 1,
                LocalDateTime.of(2021,8,24,9,50));
    }

    @Bean
    public Screening screening_13_00_cruella (@Autowired @Qualifier(value = "Cruella") Movie movie) {
        return new Screening(movie, 2,
                LocalDateTime.of(2021,8,24,13,0));
    }

    @Bean
    public Screening screening_16_00_the_amazing_spider_man_2 (@Autowired @Qualifier(value = "TheAmazingSpiderMan2") Movie movie) {
        return new Screening(movie, 3,
                LocalDateTime.of(2021,8,24,16,0));
    }

    @Bean
    public Screening screening_20_10_the_amazing_spider_man_2 (@Autowired @Qualifier(value = "TheAmazingSpiderMan2") Movie movie) {
        return new Screening(movie, 4,
                LocalDateTime.of(2021,8,24,20,10));
    }
}
