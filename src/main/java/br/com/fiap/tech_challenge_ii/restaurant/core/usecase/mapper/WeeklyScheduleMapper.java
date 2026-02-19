package br.com.fiap.tech_challenge_ii.restaurant.core.usecase.mapper;

import br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.BusinessHours;
import br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.DailySchedule;
import br.com.fiap.tech_challenge_ii.restaurant.core.domain.valueObjects.WeeklySchedule;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.DailyScheduleDTO;
import br.com.fiap.tech_challenge_ii.restaurant.core.dto.WeeklyScheduleDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class WeeklyScheduleMapper {

    public static WeeklySchedule from(WeeklyScheduleDTO weeklyScheduleDTO) {
        List<DailySchedule> dailySchedules = weeklyScheduleDTO.dailySchedules().stream()
                .map(day -> new DailySchedule(
                        DayOfWeek.valueOf(day.dayOfWeek().trim().toUpperCase()),
                        BusinessHours.of(
                                LocalTime.parse(day.openingHour()),
                                LocalTime.parse(day.closingHour())
                        )
                ))
                .toList();

        return WeeklySchedule.of(dailySchedules);
    }

    public static WeeklyScheduleDTO toDTO(WeeklySchedule weeklySchedule) {
        List<DailyScheduleDTO> dailySchedules = weeklySchedule.getWeeklyOpeningTimes()
                .entrySet()
                .stream()
                .map(entry -> {
                    DayOfWeek dayOfWeek = entry.getKey();
                    BusinessHours businessHours = entry.getValue();

                    return new DailyScheduleDTO(dayOfWeek.name(),
                            businessHours.opensAt().toString(),
                            businessHours.closesAt().toString());
                })
                .toList();

        return new WeeklyScheduleDTO(dailySchedules);
    }
}
