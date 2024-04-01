package com.example.projectA.controller;

import com.example.projectA.dto.MemberDTO;
import com.example.projectA.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // mvc에서 컨트롤러라는걸 명시
@RequiredArgsConstructor // 생성자를 자동으로 생성해주는 짱짱 기능

public class HomeController {

    private final MemberService memberService;
    // MemberService 타입의 객체를 final로 선언하여 변경 불가능하게 함. 생성자 주입 방식을 사용하기 위한 준비

    // 이러지말아다오!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    @GetMapping("/projectPage") // '/projectPage' 경로로 GET 요청이 오면 아래 메서드를 실행
    public String home() {
        return "index";
    } // index.html 실행

    // ------------------------ 회원 가입 처리 담당 -------------------------------------
    @PostMapping("/projectPage/register") // '/projectPage/register' 경로로 POST 요청이 오면 메서드를 실행
    public String save(@ModelAttribute MemberDTO memberDTO){
        // 모델 어트리뷰트 쓰면 @requestparam("memeberEmail") String memberEmail~ 이런거 대신 쓰기가 가능하다.
        /* 모델 어트리뷰트쓰면 html:name 값과 dto클래스의 필드값이 동일하다면 알아서 dto 객체만들어서
         setter 끌고와서 name에 작성한값을 객체에 알아서 담아준다. 그래서 매우매우편하다.
         생략가능한데 명시적으로 붙이는게 좋다. */
        memberService.save(memberDTO); // MemberService의 save 메서드를 호출하여, memberDTO에 담긴 데이터를 데이터베이스에 저장
        return "index"; // 데이터 저장 후 "index" 뷰를 반환
    }
    // ----------------------------------------------------------------------------------

    // ------------------------로그인 처리 담당 ---------------------------------------------
    @PostMapping("/projectPage/login") // '/projectPage/login' 경로로 POST 요청이 오면 메서드를 실행
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        // 로그인 정보를 담은 MemberDTO 객체와 세션 객체를 매개변수로 받음
        MemberDTO loginResult = memberService.login(memberDTO); // MemberService의 login 메서드를 호출하여 로그인 처리
        if(loginResult != null){ // 로그인 성공 시
            session.setAttribute("loginID", loginResult.getMemberID()); // 세션에 로그인된 사용자의 ID 저장
            return "main"; // 로그인 성공 시 "main" 뷰를 반환
        }
        else{ // 로그인 실패시
            return "index"; // 로그인 실패 시 "index" 뷰를 반환
        }
    }
    @GetMapping("/a") // '/projectPage' 경로로 GET 요청이 오면 아래 메서드를 실행
    public String a() {
        return "CHATBOT";
    } // index.html 실행
}
