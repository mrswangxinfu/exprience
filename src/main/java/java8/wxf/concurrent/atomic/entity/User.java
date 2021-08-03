package java8.wxf.concurrent.atomic.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String name;
    private Integer age;
    private Date birthday;
}
