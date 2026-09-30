package com.balazs.system_health_monitor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SystemServiceTest {

    @Test
    void getSystemsShouldReturnThreeSystems() {

        SystemService service = new SystemService();

        int numberOfSystems = service.getSystems().size();

        assertEquals(3, numberOfSystems);
    }

    @Test
    void getSystemByIdShouldReturnCorrectSystem() {

        SystemService service = new SystemService();

        SystemInfo system = service.getSystemById(2);

        assertEquals("sensor-service", system.getName());
        assertEquals(SystemStatus.ONLINE, system.getStatus());
    }

    @Test
    void getSystemByIdShouldReturnNullWhenSystemDoesNotExist() {

        SystemService service = new SystemService();

        SystemInfo system = service.getSystemById(99);

        assertNull(system);
    }

    @Test
    void updateStatusShouldChangeSystemStatus() {

        SystemService service = new SystemService();

        service.updateStatus(2, SystemStatus.ONLINE);

        SystemInfo system = service.getSystemById(2);

        assertEquals(SystemStatus.ONLINE, system.getStatus());
    }

    @Test
    void addSystemShouldAddNewSystem() {

        SystemService service = new SystemService();

        SystemInfo newSystem =
            new SystemInfo(4, "radar-service", SystemStatus.ONLINE);

        service.addSystem(newSystem);

        assertEquals(4, service.getSystems().size());

        SystemInfo addedSystem = service.getSystemById(4);

        assertEquals("radar-service", addedSystem.getName());
        assertEquals(SystemStatus.ONLINE, addedSystem.getStatus());
    }
}