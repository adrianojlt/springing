package pt.example.springing.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/api/logs")
@RequiredArgsConstructor
public class LogController {

    @GetMapping("/info")
    public ResponseEntity<Void> infoLogger() {
        log.info("info message");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/warn")
    public ResponseEntity<Void> warnLogger() {
        log.warn("warn message");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/error")
    public ResponseEntity<Void> errorLogger() {
        log.error("error message");
        return ResponseEntity.ok().build();
    }
}
