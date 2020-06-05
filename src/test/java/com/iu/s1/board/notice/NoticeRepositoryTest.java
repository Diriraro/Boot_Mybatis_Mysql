package com.iu.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@Transactional
//@Rollback(value = true)
class NoticeRepositoryTest {
	@Autowired
	private NoticeRepository noticeRepository;

	@Test
	void setInsertTest()throws Exception{
		for(int i=0;i<100;i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setTitle("title"+i);
			noticeVO.setWriter("writer"+i);
			noticeVO.setContents("contents"+i);
			int result = noticeRepository.setInsert(noticeVO);
		}
		//assertEquals(1, result);
		
	}

}
