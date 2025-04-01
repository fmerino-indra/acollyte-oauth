package org.fmm.acollyte.common.model;

public enum ServiceTypeEnum {
    SUNDAY(1,"Sunday"),
    SOLEMNITY(2,"Solemnity");
    
    ServiceTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    private int id;
    private String name;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
