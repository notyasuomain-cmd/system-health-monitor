package com.balazs.system_health_monitor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SystemService {
    private final List<SystemInfo> systems = new ArrayList<>();

    public SystemService() {
        systems.add(new SystemInfo(1,"nav",SystemStatus.ONLINE));

        systems.add(new SystemInfo(2,"sensor-service",SystemStatus.ONLINE));

        systems.add(new SystemInfo(3,"whatever",SystemStatus.OFFLINE));
    }

    public SystemInfo addSystem(SystemInfo system) {
        systems.add(system);
        return system;
    }

    public List<SystemInfo> getSystems() {
        return systems;
    }

    public SystemInfo getSystemById(long id) {
        for (SystemInfo system : systems) {
            if(system.getId() == id) {
                return system;
            }
        }
        return null;
    }
    
    public SystemInfo updateStatus(long id, SystemStatus status) {
        for (SystemInfo system : systems) {
            if (system.getId() == id) {
                system.setStatus(status);
                return system;
            }
        }

        return null;
    }
}