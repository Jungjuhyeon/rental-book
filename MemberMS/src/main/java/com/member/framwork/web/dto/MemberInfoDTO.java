package com.member.framwork.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberInfoDTO {
    private String id;
    private String Name;
    private String passWord;
    private String email;

}
