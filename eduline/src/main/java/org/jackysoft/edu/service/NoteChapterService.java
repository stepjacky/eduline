package org.jackysoft.edu.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jackysoft.edu.entity.NoteChapter;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.formbean.Ztreeable;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.NoteChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;


@Service
@Transactional("transactionManager")
public class NoteChapterService extends AbstractSQLService<String, NoteChapter> {

	
	@Autowired
	private NoteChapterMapper mapper;
	
	
	@Autowired
	private HomeWorkService homeService;
	
	@Override
	public PreResult save(NoteChapter t) {
	    if(Strings.isNullOrEmpty(t.getId())){
	    	super.save(t);
	    }else{
	        mapper.insert(t);	
	    }
		return null;
		
	}



	public List<NoteChapter> findByLecture(String noteId){
		List<NoteChapter> beans = mapper.findByLecture(noteId);
		return beans;
	}
	


	@Override
	public void removeById(String s) {
		super.removeById(s);
		List<NoteChapter> children = mapper.findByParent(s);
	    if(children==null || children.isEmpty()){
	    	mapper.updatePartial(s, "SET isParent='false' ");
	    }
	}


	
	public  void refreshHomeWork(String id){
		NoteChapter bean = this.findById(id);
		if(bean==null)return;
		homeService.updateHeads(id, bean.getAnwserHead());
		
	}
	
	
	

	String parseDoc(Path file){
		String head = "";
		try(WordExtractor extractor = 
				new WordExtractor(new FileInputStream(file.toFile()))){
		   
			String[] texts = extractor.getParagraphText();
			logger.info(extractor.getText());
			if(texts==null||texts.length==0){
				logger.error("没有有效答案");
				return "";
			}
			return texts[0];
		} catch (IOException e) {
			logger.error(e);
		}
		
		return head;
	
	}
	
	public String parseDocx(Path file){
		
		String head = "";
		try(XWPFWordExtractor extractor = 
				new XWPFWordExtractor(new XWPFDocument(new FileInputStream(file.toFile())))){
			
			String text = extractor.getText();
			if(Strings.isNullOrEmpty(text)) return "";
			logger.info(text);
			head = text.substring(0, text.indexOf('^'));
			
		} catch (IOException e) {
			logger.error(e);
		}
		
		return head;
	}


	@Override
	public List<ZtreeNode> ztree(String parent) {
		
		List<NoteChapter> beans = mapper.findByParent(parent);
		List<ZtreeNode> nodes = new ArrayList<>();
		for(NoteChapter t : beans){
			if(t instanceof Ztreeable){
				nodes.add(Ztreeable.class.cast(t).toZtreeNode());
			}
		}
		return nodes;
	}





	@Override
	public AbstractMapper<String, NoteChapter> getMapper() {
		return mapper;
	}

}
