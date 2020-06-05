package com.iu.s1.board.qna;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.s1.board.BoardVO;

@SpringBootTest
public class QnaRepositoryTest {
	@Autowired
	private QnaRepository qnaRepository;
	
	
	void setInsertTest()throws Exception{
		for(int i=0; i<30; i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setTitle("title"+i);
			qnaVO.setWriter("writer"+i);
			qnaVO.setContents("contents"+i);
			int result = qnaRepository.setInsert(qnaVO);
			int num = qnaRepository.getSelectNum();
			qnaVO.setNum(num);
			result = qnaRepository.setReplyUpdate(qnaVO);
		}
	}
	@Test
	void setDeleteTest()throws Exception{
		BoardVO boardVO = new  BoardVO();
		int result = qnaRepository.setDelete(boardVO);
	}

}
