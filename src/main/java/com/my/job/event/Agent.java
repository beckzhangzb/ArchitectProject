package com.my.job.event;

import java.util.Date;

/**
 * @author zhangzb
 * @date 2021/3/25 20:45
 * @desc
 */
public class Agent {

    private Integer id;

    private String phone;

    private String name;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
