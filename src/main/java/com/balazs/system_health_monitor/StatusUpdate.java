package com.balazs.system_health_monitor;

public class StatusUpdate {

    private SystemStatus status;

    public StatusUpdate() {
    }

    public SystemStatus getStatus() {
        return status;
    }

    public void setStatus(SystemStatus status) {
        this.status = status;
    }
}