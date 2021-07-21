package object;

import object.discountcondition.PeriodCondition;
import object.discountpolicy.PercentDiscountPolicy;
import object.money.Money;
import object.movie.Movie;
import object.movie.Screening;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MovieTest {
    @Test
    @DisplayName("영화 및 스케쥴 추가하고 기간 조건을 만족시킬때 할인되는지 테스트")
    void addMovieAndDiscountTest() {
        Movie harryPotter = new Movie("Harry Potter", Duration.ofMinutes(120), Money.wons(10000), new PercentDiscountPolicy(0.1,
                new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10,0), LocalTime.of(11,0))));
        Screening screening = new Screening(harryPotter, 1, LocalDateTime.of(2021,6,7,10,30,0));
        Money money = harryPotter.calculateMovieFee(screening);
        System.out.println(money.isGreaterThanOrEqual(Money.wons(9000)));
        System.out.println(money.isLesserThan(Money.wons(9001)));
    }
}
