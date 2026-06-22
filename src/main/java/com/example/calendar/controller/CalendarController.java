package com.example.calendar.controller;

import com.example.calendar.model.CalendarDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalendarController {

    @GetMapping("/")
    public String index(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            Model model) {

        Calendar today = Calendar.getInstance();

        int displayYear = year != null ? year : today.get(Calendar.YEAR);
        int displayMonth = month != null ? month : today.get(Calendar.MONTH) + 1;

        if (displayMonth < 1) {
            displayMonth = 1;
        } else if (displayMonth > 12) {
            displayMonth = 12;
        }

        model.addAttribute("year", displayYear);
        model.addAttribute("month", displayMonth);
        model.addAttribute("calendarWeeks", createCalendarWeeks(displayYear, displayMonth, today));

        return "calendar";
    }

    private List<List<CalendarDay>> createCalendarWeeks(int year, int month, Calendar today) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month - 1, 1);

        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        List<List<CalendarDay>> weeks = new ArrayList<>();
        List<CalendarDay> week = new ArrayList<>();

        for (int i = Calendar.SUNDAY; i < firstDayOfWeek; i++) {
            week.add(new CalendarDay(0, false, false));
        }

        for (int day = 1; day <= lastDay; day++) {
            boolean sunday = week.size() == 0;
            boolean saturday = week.size() == 6;
            boolean currentDay = isToday(year, month, day, today);

            week.add(new CalendarDay(day, currentDay, sunday || saturday));

            if (week.size() == 7) {
                weeks.add(week);
                week = new ArrayList<>();
            }
        }

        if (!week.isEmpty()) {
            while (week.size() < 7) {
                week.add(new CalendarDay(0, false, false));
            }
            weeks.add(week);
        }

        return weeks;
    }

    private boolean isToday(int year, int month, int day, Calendar today) {
        return year == today.get(Calendar.YEAR)
                && month == today.get(Calendar.MONTH) + 1
                && day == today.get(Calendar.DAY_OF_MONTH);
    }
}
