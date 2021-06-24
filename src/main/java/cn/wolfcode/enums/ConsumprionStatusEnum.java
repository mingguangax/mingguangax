package cn.wolfcode.enums;

public enum ConsumprionStatusEnum {
    CONSUME("待结算",0),
    AUDIT("待审核",1),
    FINISH("归档",2),
    FAILURE("坏账",3);

    private String name;
    private int status;
    ConsumprionStatusEnum(String name, int status){
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
