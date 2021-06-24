package cn.wolfcode.domain;

import lombok.Data;

@Data
public class Employee {
    /** */
    private Long id;

    /** */
    private String username;

    /** */
    private String name;

    /** */
    private String password;

    /** */
    private String email;

    /** */
    private Integer age;

    /** */
    private Boolean admin = false;

    /** */
    private Long dept_id;

    private Department department;


}