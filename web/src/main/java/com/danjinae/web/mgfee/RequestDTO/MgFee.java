package com.danjinae.web.mgfee.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MgFee {
    private Integer dong;
    private Integer ho;
    private Integer aptId;
    private Integer fee;
    private Integer catId;
    private String content;
    private String date;
}