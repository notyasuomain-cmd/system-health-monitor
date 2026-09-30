package com.balazs.system_health_monitor;

public class SystemInfo {
    private long id;
    private String name;
    private SystemStatus status;

    public SystemInfo() {}
    
    public SystemInfo(long id, String name, SystemStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }


    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public SystemStatus getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStatus(SystemStatus status) {
        this.status = status;
    }


}