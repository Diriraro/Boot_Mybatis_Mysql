package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.board.BoardService;
import com.iu.s1.board.BoardVO;
import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class QnaService implements BoardService {
	
	@Autowired
	private QnaRepository qnaRepository;

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		int result = qnaRepository.setInsert(boardVO);
		int num = qnaRepository.getSelectNum();
		boardVO.setNum(num);
		result = result*qnaRepository.setReplyUpdate(boardVO);
		return result;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaRepository.setDelete(boardVO);
	}

	@Override
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		int hit = qnaRepository.hitUp(boardVO);
		return qnaRepository.getSelectOne(boardVO);
	}

	@Override
	public List<BoardVO> getSelectList(Pager pager) throws Exception {
		pager.makeRow();
		long totalCount = qnaRepository.qnaCount(pager);
		pager.makePage(totalCount);
		
		return qnaRepository.getSelectList(pager);
	}
	
	public int qnaReplyInsert(BoardVO boardVO)throws Exception{
		int result2 = qnaRepository.qnaReplyUpdate(boardVO);
		int result = qnaRepository.qnaReplyInsert(boardVO);
		result= result*result2;
		return result;
	}
}
