package com.iu.s1.board.notice;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.board.BoardRepository;
import com.iu.s1.board.BoardService;
import com.iu.s1.board.BoardVO;
import com.iu.s1.board.notice.noticeFile.NoticeFileRepository;
import com.iu.s1.board.notice.noticeFile.NoticeFileVO;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService {

	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private FilePathGenerator pathGenerator; // 저장할 폴더의 경로
	@Autowired
	private FileManager fileManager;	// 실제 파일을 저장
	@Autowired
	private NoticeFileRepository noticeFileRepository;
	
	
	@Value("${board.notice.filePath}")
	private String filePath;			//application.properties에서 키 값을 받아와 value값을 집어넣음
	
	@Override
	public int setInsert(BoardVO boardVO, MultipartFile [] files) throws Exception {
		// TODO Auto-generated method stub
		
		File file = pathGenerator.getUseClassPathResource(filePath);
		int result =noticeRepository.setInsert(boardVO);
		for(MultipartFile multipartFile: files) {
			if(multipartFile.getSize()<=0) {
				continue;
			}
			String fileName= fileManager.saveFileCopy(multipartFile, file);
			NoticeFileVO vo = new NoticeFileVO();
			vo.setNum(boardVO.getNum());
			vo.setFileName(fileName);
			vo.setOriName(multipartFile.getOriginalFilename());
			
			result = noticeFileRepository.setInsert(vo);
			
			System.out.println(fileName);
		}
		
		return result; 
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.setDelete(boardVO);
	}

	@Override
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		int hit = noticeRepository.hitUp(boardVO);
		return noticeRepository.getSelectOne(boardVO);
	}

	@Override
	public List<BoardVO> getSelectList(Pager pager) throws Exception {
		pager.makeRow();
		long totalCount = noticeRepository.noticeCount(pager);
		pager.makePage(totalCount);
		
		return noticeRepository.getSelectList(pager);
	}

	public NoticeFileVO fileDown(NoticeFileVO noticeFileVO)throws Exception{
		return noticeFileRepository.fileDown(noticeFileVO);
	}
	
}
