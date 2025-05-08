package com.example.demo.member.comtroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.board.controller.BoardControllerImpl;
import com.example.demo.board.dto.BoardDTO;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberControllerImpl implements MemberController {

    private final BoardDTO boardDTO;

    private final BoardControllerImpl boardControllerImpl;
	@Autowired
	MemberService service;
	@Autowired
	HttpSession session;

    MemberControllerImpl(BoardControllerImpl boardControllerImpl, BoardDTO boardDTO) {
        this.boardControllerImpl = boardControllerImpl;
        this.boardDTO = boardDTO;
    }
	
    @Override
	@GetMapping("/{formName:.*Form}")
	public String memberForm(String formName) {
		// TODO Auto-generated method stub
		return "member/" + formName;
	}
	
	@Override
	@PostMapping("/joinMember")
	public String joinMember(Model model, @ModelAttribute("MemberDTO")MemberDTO dto) {
		// TODO Auto-generated method stub
		service.joinMember(dto);
		model.addAttribute("message", "회원 가입이 완료되었습니다.");
        model.addAttribute("redirectUrl", "/member/loginForm");
		return "common/alert";
	}
	
	
	@Override
	@GetMapping("/memberList")
	public String memberList (Model model, @ModelAttribute("MemberDTO")MemberDTO dto) {
		List<MemberDTO> memberList = service.memberList();
		model.addAttribute("memberList", memberList);
		System.out.println(memberList);
		return "member/memberList";
	}
	
	@Override
	@GetMapping("/detailMember")
	public String getDetailMember(Model model, @RequestParam("id") String id) {
	    // 1. 특정 회원 정보 조회
	    MemberDTO memberDTO = service.detailMember1(id);
	    if (memberDTO == null) {
	        model.addAttribute("message", "해당 회원 정보가 없습니다.");
	        return "error";
	    }
	    
	    model.addAttribute("member", memberDTO);
	    
	    List<MemberDTO> memberList = service.memberList();
	    model.addAttribute("memberList", memberList);
	    
	    // 4. 상세 정보 페이지 또는 목록 페이지 반환
	    return "member/detailMember"; // 상세 페이지가 있으면 해당 페이지로
	    // 또는 목록에서 강조하는 방식이면: return "member/memberList";
	}

	@Override
	@PostMapping("/detailMember")
	public String postDetailMember(Model model, @ModelAttribute MemberDTO dto) {
	    int result = service.detailMember(dto);
	    if (result > 0) {
	        MemberDTO updatedMember = service.detailMember1(dto.getId());
	        model.addAttribute("member", updatedMember);
	        
	        List<MemberDTO> memberList = service.memberList();
	        model.addAttribute("memberList", memberList);
	        model.addAttribute("message", "회원 정보가 업데이트되었습니다.");
	        model.addAttribute("redirectUrl", "/member/memberList");
	    } else {
	        model.addAttribute("message", "회원 정보 업데이트에 실패했습니다.");
	    }
	    
	    return "common/alert";
	}
	
	@Override
	@GetMapping("/delMemberForm")
	public String delMemberForm (Model model) {
		return "member/delMemberForm";
	}
	@Override
	@GetMapping("/loginForm")
	public String loginForm (Model model) {
		String memberId = (String) session.getAttribute("loginId");
		System.out.println(memberId);
		return "member/loginForm";
	}
	@Override
	@GetMapping("/joinMemberForm")
	public String joinMemberForm (Model model) {
		return "member/joinMemberForm";
	}
	@Override
	@PostMapping("/login")
	public String login (Model model,@RequestParam("id")String id, @RequestParam("pwd")String pwd) {
		int result = service.login(id, pwd);
		
		if(result == 1) {
		session.setAttribute("loginId", id);
		model.addAttribute("message", "로그인 완료.");
		model.addAttribute("redirectUrl", "/board/insertBoard");
		
		}
	    return "common/alert";
	}
	@Override
	@GetMapping("/logout")
	public String logout (Model model,HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		session.invalidate();
		
		model.addAttribute("message", "로그이웃 완료.");
		model.addAttribute("redirectUrl", "/member/loginForm");
	    return "common/alert";
	}
	
	@Override
	@PostMapping("/delMember")
	public String delMember(Model model, @ModelAttribute MemberDTO dto,@RequestParam("id")String id, @RequestParam("pwd")String pwd) {
		int pwdResult = service.findMyPwd(pwd, id);
	    int result = service.delMember(dto);
	    if (result > 0) {
	    model.addAttribute("message", "회원이 삭제되었습니다.");
	    model.addAttribute("redirectUrl", "/member/memberList");
	    }
	    return "common/alert";
	}

	



	
}
