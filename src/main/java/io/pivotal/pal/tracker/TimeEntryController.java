package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;
    @Autowired
    public TimeEntryController(@Qualifier("TimeEntryRepository") TimeEntryRepository timeEntryRepository) {
     this.timeEntryRepository=timeEntryRepository;
    }
    @PostMapping(value="/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createTimeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(createTimeEntry, HttpStatus.CREATED);
    }
    @GetMapping(value="/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable(value = "id") long timeEntryId) {
        TimeEntry readTimeEntry = timeEntryRepository.find(timeEntryId);
        if(readTimeEntry!=null)
        return new ResponseEntity<>(readTimeEntry, HttpStatus.OK);
        else
        return new ResponseEntity<>(readTimeEntry, HttpStatus.NOT_FOUND);
    }
    @GetMapping(value="/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> listTimeEntry = timeEntryRepository.list();
        return new ResponseEntity<>(listTimeEntry, HttpStatus.OK);
    }
    @PutMapping(value="/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable(value = "id") long nonExistentTimeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry updateTimeEntry = timeEntryRepository.update(nonExistentTimeEntryId,timeEntry);
        if(updateTimeEntry!=null)
        return new ResponseEntity<>(updateTimeEntry, HttpStatus.OK);
        else
        return new ResponseEntity<>(updateTimeEntry, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value="/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
