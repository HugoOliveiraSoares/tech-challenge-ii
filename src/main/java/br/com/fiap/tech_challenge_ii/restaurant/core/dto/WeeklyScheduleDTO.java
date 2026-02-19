package br.com.fiap.tech_challenge_ii.restaurant.core.dto;

import java.util.List;

public record WeeklyScheduleDTO(List<DailyScheduleDTO> dailySchedules) {
}
