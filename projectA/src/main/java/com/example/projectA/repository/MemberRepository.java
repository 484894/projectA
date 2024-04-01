package com.example.projectA.repository;

import com.example.projectA.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속받아 MemberEntity에 대한 기본적인 CRUD 연산을 수행할 수 있게 함
// JpaRepository<엔티티 타입, ID 타입>을 상속받음으로써 Spring Data JPA의 기능을 사용할 수 있음
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 회원 아이디(memberID)로 회원 정보를 조회하는 메소드. Spring Data JPA에서 제공하는 쿼리 메소드 기능을 사용
    // 메소드 이름만으로도 쿼리를 유추하여 실행. findBy + 필드명(MemberID) 형태로 메소드를 정의
    Optional<MemberEntity> findByMemberID(String memberID);
    // JPA가 메소드 이름을 분석하여 "SELECT * FROM member_table WHERE member_id=?"와 같은 쿼리를 실행
    // Optional<MemberEntity>를 반환 타입으로 사용하여, 결과가 없을 경우(null)를 방지하고 Optional.empty()를 반환할 수 있게 함
}
