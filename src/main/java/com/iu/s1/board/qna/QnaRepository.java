package com.iu.s1.board.qna;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.iu.s1.board.BoardRepository;
import com.iu.s1.board.BoardVO;

@Repository //생략가능
@Mapper
public interface QnaRepository extends BoardRepository {

	public int setReplyUpdate(BoardVO boardVO)throws Exception;
	
	public int getSelectNum()throws Exception;
	
	public int qnaReplyInsert(BoardVO boardVO)throws Exception;
	public int qnaReplyUpdate(BoardVO boardVO)throws Exception;
	
}
