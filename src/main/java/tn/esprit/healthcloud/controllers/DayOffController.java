package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.config.CustomUserDetails;
import tn.esprit.healthcloud.entities.DayOff;
import tn.esprit.healthcloud.exceptions.ErrorResponse;
import tn.esprit.healthcloud.repositories.DayOffRepository;
import tn.esprit.healthcloud.services.IDayOff;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/day-offs")
@AllArgsConstructor

public class DayOffController {

    private IDayOff dayOffService;
    private DayOffRepository dayOffRepository;

    @GetMapping("")
    public ResponseEntity<List<DayOff>> getAllDayOffs() {
        List<DayOff> dayOffs = dayOffService.getAllDayOffs();
        return new ResponseEntity<>(dayOffs, HttpStatus.OK);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<DayOff>> getDayOffsByUser(@PathVariable long idUser) {
        List<DayOff> dayOff = dayOffService.getDayOffsByUser(idUser);
        return new ResponseEntity<>(dayOff, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DayOff> getDayOffById(@PathVariable int id) {
        DayOff dayOff = dayOffService.getDayOffById(id);
        return new ResponseEntity<>(dayOff, HttpStatus.OK);
    }
    @PutMapping("/{id}/status")
    public void updateDayOffStatus(@PathVariable int id, @RequestParam String newStatus) {
        dayOffService.updateDayOffStatus(id, newStatus);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DayOff> updateDayOff(@PathVariable int id, @RequestBody DayOff dayOff) {
        Optional<DayOff> optionalDayOff = Optional.ofNullable(dayOffRepository.findById(id));
        if (optionalDayOff.isPresent()) {
            dayOff.setId(id);
            DayOff updatedDayOff = dayOffRepository.save(dayOff);
            return ResponseEntity.ok(updatedDayOff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pending")
    public List<DayOff> getPendingDayOffRequests() {
        return dayOffService.getPendingDayOffRequests();
    }

    @PostMapping("")
    public ResponseEntity<DayOff> createDayOff(@RequestBody DayOff dayOff) {
        DayOff createdDayOff = dayOffService.createDayOff(dayOff);
        return new ResponseEntity(createdDayOff.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/request")
    public ResponseEntity<DayOff> request(@RequestBody DayOff dayOff) {
        DayOff createdDayOff = dayOffService.request(dayOff);
        return new ResponseEntity(createdDayOff.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDayOff(@PathVariable int id) {
        try{
        dayOffService.deleteDayOff(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);        }
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<DayOff>> getDayOffsBetweenDates(
            @RequestParam("start-date") String startDate,
            @RequestParam("end-date") String endDate)
    {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<DayOff> dayOffs = dayOffService.getDayOffsBetweenDates(start, end);
        return new ResponseEntity<>(dayOffs, HttpStatus.OK);
    }
}
