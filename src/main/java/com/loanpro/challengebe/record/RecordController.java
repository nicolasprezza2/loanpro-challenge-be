package com.loanpro.challengebe.record;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping
    public ResponseEntity<Page<Record>> getRecordsForUser(Pageable pageable) {
        return ResponseEntity.ok()
                .body(this.recordService.findActiveRecordsForCurrentUser(pageable));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteRecord(@PathVariable Long id) {
        this.recordService.delete(id);
        return ResponseEntity.ok().build();
    }
}
