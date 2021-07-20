package object;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppConfigTest {
    Screening screening;

    @Test
    void addScreeningSchedule() {
        AppConfig appConfig = new AppConfig();
        screening = appConfig.screening();
        Customer customer1 = new Customer(1L, "nameA");
        Reservation reservation = screening.reserve(1, customer1);

        Assertions.assertThat(reservation.getFee().isGreaterThanOrEqual(Money.wons(9000L))).isTrue();
        Assertions.assertThat(reservation.getFee().isLesserThan(Money.wons(9001L))).isTrue();
    }
}
