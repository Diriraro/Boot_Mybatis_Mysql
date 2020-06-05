package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.s1.board.BoardVO;
import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("qnaDelete")
	public ModelAndView setDelete(BoardVO boardVO, RedirectAttributes rd)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setDelete(boardVO);
		rd.addFlashAttribute("result", result);
		 mv.addObject("result", "Deleted");
		 mv.addObject("path", "./qnaList");
		 mv.setViewName("common/result");
		return mv;
	}
	
	@GetMapping("qnaUpdate")
	public ModelAndView setUpdate(ModelAndView mv, BoardVO boardVO)throws Exception{
		boardVO = qnaService.getSelectOne(boardVO);
		mv.addObject("vo", boardVO);
		mv.addObject("path", "Update");
		mv.setViewName("board/boardWrite");
		
		return mv;
	}
	
	@PostMapping("qnaUpdate")
	public ModelAndView setUpdate(BoardVO boardVO, RedirectAttributes rd)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setUpdate(boardVO);
		rd.addFlashAttribute("result", result);
		mv.addObject("result", "Updated");
		mv.addObject("path", "./qnaList");
		mv.setViewName("common/result");
		return mv;
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView setInsert()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("path", "Write");
		
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public ModelAndView setInsert(BoardVO boardVO, MultipartFile [] files, RedirectAttributes rd)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setInsert(boardVO, files);
		rd.addFlashAttribute("result", result);
		mv.addObject("result", "Success");
		mv.addObject("path", "./qnaList");
		mv.setViewName("common/result");
		return mv;
	}
	
	@GetMapping("qnaList")
	public ModelAndView getSelectList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = qnaService.getSelectList(pager);
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	
	@GetMapping("qnaSelect")
	public ModelAndView getSelectOne(BoardVO boardVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = qnaService.getSelectOne(boardVO);
		mv.addObject("vo", boardVO);		
		mv.setViewName("board/boardSelect");
		
		return mv;
	}
	
	@GetMapping("qnaReply")
	public ModelAndView qnaReply(ModelAndView mv, BoardVO boardVO)throws Exception{
		mv.addObject("vo", boardVO);
		mv.addObject("path", "Reply");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public ModelAndView qnaReplyInsert(BoardVO boardVO, RedirectAttributes rd)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.qnaReplyInsert(boardVO);
		rd.addFlashAttribute("result", result);
		mv.addObject("result", "Reply success");
		mv.addObject("path", "./qnaList");
		mv.setViewName("common/result");
		return mv;
	}
}
