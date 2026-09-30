package com.balazs.system_health_monitor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/systems")
public class SystemController {
    private final SystemService systemService;

    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }
    
    @GetMapping
    public List<SystemInfo> getSystems() {
        return systemService.getSystems();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SystemInfo> getSystemById(@PathVariable long id) {
        SystemInfo system = systemService.getSystemById(id);
        if(system == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(system);
    }

    @PostMapping
    public SystemInfo addSystem(@RequestBody SystemInfo system) {
        return systemService.addSystem(system);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SystemInfo> updateStatus(@PathVariable long id,
            @RequestBody StatusUpdate statusUpdate) {

        SystemInfo system =systemService.updateStatus(id, statusUpdate.getStatus());

        if (system == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(system);
    }

}