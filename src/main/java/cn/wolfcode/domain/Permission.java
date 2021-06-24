package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Permission {

    private Integer id;

    private String name;

    private String expression;

    public String toJson(){
        return JSON.toJSONString(this);
    }

    public Permission() {
    }

    public Permission(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return expression.equals(that.expression);
    }
    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}