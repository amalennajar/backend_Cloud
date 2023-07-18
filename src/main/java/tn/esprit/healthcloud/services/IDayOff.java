package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.config.CustomUserDetails;
import tn.esprit.healthcloud.entities.DayOff;

import java.time.LocalDate;
import java.util.List;

public interface IDayOff {

    List<DayOff> getAllDayOffs();

    DayOff createDayOff(DayOff dayOff);

    DayOff getDayOffById(int id);

    void deleteDayOff(int id);

    List<DayOff> getDayOffsBetweenDates(LocalDate startDate, LocalDate endDate);
    public DayOff request(DayOff dayOff);

    List<DayOff> getDayOffsByUser(long id);

    List<DayOff> getPendingDayOffRequests();

    void updateDayOffStatus(int id, String newStatus);
}
