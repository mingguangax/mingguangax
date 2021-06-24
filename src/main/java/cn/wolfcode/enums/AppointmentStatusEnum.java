package cn.wolfcode.enums;

public enum AppointmentStatusEnum {
    PEND("待确认",0),
    PERFORM("履行中",1),
    CONSUME("消费中",2),
    FINISH("归档",3),
    FAILURE("废弃",4);

    private String name;
    private int status;
    AppointmentStatusEnum(String name,int status){
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }
}
