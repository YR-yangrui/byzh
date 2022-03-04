package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Applyer {
    private String LeaderId;
    private Integer teamId;
    private Integer state;
}
