package com.example.projectA.service;
import com.example.projectA.dto.MemberDTO;
import com.example.projectA.entity.MemberEntity;
import com.example.projectA.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service // 스프링에서 서비스 계층을 나타내는 어노테이션. 비즈니스 로직을 처리
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        // 회원 정보 저장 로직
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        // DTO를 Entity로 변환
        // 왜?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Entity는 직접적으로 DB TABLE에 매핑되기때문에 저장하기위해선 Entity로 변환해야됨니다.
                                           // 침고! alt 엔터하면 좌변 만들어줌
        memberRepository.save(memberEntity);
        // 변환된 Entity를 Repository를 통해 데이터베이스에 저장
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
          -로그인 로직-
          1. 사용자가 입력한 ID로 데이터베이스에서 회원 정보 조회
          2. 조회한 회원 정보의 비밀번호와 사용자가 입력한 비밀번호 비교
         */
        Optional<MemberEntity> byMemberID = memberRepository.findByMemberID(memberDTO.getMemberID());
        // ID로 회원 정보 조회
        // 엔티티객체를 옵셔널로 한번더 감싼다.
        if(byMemberID.isPresent()){
            // 조회된 회원 정보가 있을 경우
            MemberEntity memberEntity = byMemberID.get(); // Optional에서 Entity 추출
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                // 비밀번호가 일치할 경우
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);  // Entity를 DTO로 변환하여 리턴

                /* 왜...굳이 dto로 바꿈? => 컨트롤러 계층으로 보내는데 컨트롤러는 보통 dto를 사용한다.
                계층 간의 결합도를 낮추고 각 계층이 필요로하는 데이터만 전달할수 있게 해준다.*/
                return dto;
            }
            else{ // 비밀번호가 일치하지 않을 경우
                return null; // 로그인 실패
            }
        }
        else{ // 조회된 회원 정보가 없을 경우 (ID가 존재하지 않음)
            return null; // 로그인 실패
        }
    }
}
