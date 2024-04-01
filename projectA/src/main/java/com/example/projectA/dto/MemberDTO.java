package com.example.projectA.dto;

import com.example.projectA.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // 모든 필드에 대한 getter 메소드를 자동 생성 (편하다!)
@Setter // 모든 필드에 대한 setter 메소드를 자동 생성
@NoArgsConstructor // 기본 생성자를 자동으로 생성
@ToString // 객체의 정보를 문자열로 반환하는 toString() 메서드를 자동으로 생성
public class MemberDTO {
    private Long id; // 회원의 고유 번호
    private String memberID; // 회원의 아이디. 로그인 시 사용
    private String memberName; // 회원의 이름
    private String memberPassword; // 회원의 비밀번호

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        // MemberEntity 객체를 받아 해당 객체의 정보를 기반으로 MemberDTO 객체를 생성하고 반환하는 메소드
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberID(memberEntity.getMemberID());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());

        return memberDTO;
    }
}
