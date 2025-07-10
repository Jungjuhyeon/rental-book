package com.member.framwork;


import com.member.application.usecase.AddMemberUsecase;
import com.member.application.usecase.InquiryMermberUsecase;
import com.member.framwork.web.dto.MemberInfoDTO;
import com.member.framwork.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final AddMemberUsecase addMemberUsecase;
    private final InquiryMermberUsecase inquiryMermberUsecase;

    @PostMapping("/Member/")
    public ResponseEntity<MemberOutPutDTO> addMember(@RequestBody MemberInfoDTO
                                                             memberInfoDTO) {
        MemberOutPutDTO addedMember =
                addMemberUsecase.addMember(memberInfoDTO);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    @GetMapping("/Member/{no}")
    public ResponseEntity<MemberOutPutDTO> getMember(@PathVariable("no") long no) {
        MemberOutPutDTO member = inquiryMermberUsecase.getMember(no);
        return member != null
                ? new ResponseEntity<>(member,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

