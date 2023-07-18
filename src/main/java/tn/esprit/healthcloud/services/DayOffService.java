package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.config.CustomUserDetails;
import tn.esprit.healthcloud.entities.DayOffStatus;
import tn.esprit.healthcloud.entities.DayOff;
import tn.esprit.healthcloud.entities.NotificationMessage;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.exceptions.DayOffNotFoundException;
import tn.esprit.healthcloud.exceptions.DeleteForbiddenException;
import tn.esprit.healthcloud.repositories.DayOffRepository;
import tn.esprit.healthcloud.repositories.UserRepository;

import java.time.LocalDate;
import java.util.logging.Logger;

import java.util.List;


@Service
@AllArgsConstructor
public class DayOffService implements IDayOff {
    private static final Logger logger = Logger.getLogger(DayOffService.class.getName());

    DayOffRepository dayOffRepository;
    private final UserRepository userRepository;

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @Override
    public DayOff createDayOff(DayOff dayOff) {
        return dayOffRepository.save(dayOff);
    }

    @Override
    public DayOff request(DayOff dayOff) {
            dayOff.setStatus(DayOffStatus.pending);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
            User user = userRepository.findById(currentUser.getId()).get();
            dayOff.setUser(user);
        return dayOffRepository.save(dayOff);
    }


    @Override
    public DayOff getDayOffById(int id) {
        DayOff dayOff = dayOffRepository.findById(id);
        if (dayOff == null) {
            throw new DayOffNotFoundException("Day off request with id " + id + " not found");
        }
        return dayOff;
    }



    @Override
    public List<DayOff> getAllDayOffs() {
        return dayOffRepository.findAll();
    }

    public List<DayOff> getPendingDayOffRequests() {
        return dayOffRepository.findByStatus(DayOffStatus.pending);
    }

    public void updateDayOffStatus(int id, String newStatus) {
        DayOff dayOff = getDayOffById(id);
        dayOff.setStatus(DayOffStatus.valueOf(newStatus.toLowerCase()));
        dayOffRepository.save(dayOff);
        System.out.println("approved".equals(newStatus)
        );
        if ("approved".equals(newStatus)) {
            NotificationMessage notif = new NotificationMessage();
            notif.setTitle("Your dayoff request is accepted");
            notif.setBody("Your dayoff starts from " + dayOff.getStartDate().toString() + " to " + dayOff.getEndDate().toString());
            firebaseMessagingService.sendNotificationByToken(notif, dayOff.getUser().getIdUser());
        }
    }

    @Override
    public void deleteDayOff(int id) {
        DayOff dayOff = getDayOffById(id);
        DayOffStatus status = dayOff.getStatus();
        if (status.equals(DayOffStatus.approved)) {
            throw new DeleteForbiddenException("Cannot delete day off request with status " + status);
        }
        dayOffRepository.deleteById(id);
    }

    @Override
    public List<DayOff> getDayOffsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return dayOffRepository.findDayOffsBetweenDates(startDate, endDate);
    }

    @Override
    public List<DayOff> getDayOffsByUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        List<DayOff> dayOffs = dayOffRepository.findByUser(user);
        return dayOffs;
    }
}
