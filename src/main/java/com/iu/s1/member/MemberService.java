package com.iu.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public int memberJoin(MemberVO memberVO)throws Exception{
		return memberRepository.memberJoin(memberVO);
	}
	
	public MemberVO memberLogin(MemberVO memberVO)throws Exception{
		return memberRepository.memberLogin(memberVO);
	}
	
	//검증 메서드 추가
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean result=false; // false = 에러가 없음

		//1. 기본 제공 검증
//		result = bindingResult.hasErrors();
		if(bindingResult.hasErrors()) {
			result = true;
		}
		
		if(memberVO.getId()!=null) {
			
			//2. password 일치하는 검증
			if(!memberVO.getPassword().equals(memberVO.getPwCheck())) {
				bindingResult.rejectValue("pwCheck", "memberVO.password.notSame");
				result=true;
			}
			
			//3. id 중복 검증
			MemberVO idCheck = memberRepository.memberIdCheck(memberVO);
			if(idCheck!=null) {
				if(idCheck.getId().equals(memberVO.getId())) {
					bindingResult.rejectValue("id", "memberVO.id.same");
					result=true;
				}
			}else {
				result = true;
			}
		}else {
			result = true;
		}
		
		return result;
	}

}
