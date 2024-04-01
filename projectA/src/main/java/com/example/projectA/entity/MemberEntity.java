package com.example.projectA.entity;

import com.example.projectA.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // JPA에서 엔티티로 인식하도록 하는 어노테이션
@Getter // 모든 필드에 대한 getter 메소드를 자동 생성
@Setter // 모든 필드에 대한 setter 메소드를 자동 생성
@Table(name = "member_table") // 엔티티에 대응하는 데이터베이스 테이블을 지정. 여기서는 'member_table' 테이블과 매핑됨
public class MemberEntity {
    @Id // 엔티티의 기본 키(PK)를 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키의 생성 전략을 지정(auto_increment)
    private Long id; // 회원의 고유 번호

    @Column(unique = true) // 데이터베이스의 컬럼을 나타내며, 이 필드의 값이 유니크(고유)함을 의미한다.
    private String memberID;  // 회원 아이디. 유니크로 지정해놔서 중복 불가

    @Column // 별도의 옵션 없이 컬럼을 나타냄. 필드 이름을 기반으로 데이터베이스 컬럼과 매핑
    private String memberName; // 회원 이름

    @Column // 비밀번호도 마찬가지로 컬럼과 매핑
    private String memberPassword;  // 회원 비밀번호

    public  static MemberEntity toMemberEntity(MemberDTO memberDTO){
        // MemberDTO 객체를 받아 해당 객체의 정보를 기반으로 MemberEntity 객체를 생성하고 반환하는 메소드
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMemberID(memberDTO.getMemberID());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        return memberEntity;

    }
}
