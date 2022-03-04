package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BufferList {
    private int id;
    private String uid;
    private String tid;
    private int state;

}
