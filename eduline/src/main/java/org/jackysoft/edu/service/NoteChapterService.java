package org.jackysoft.edu.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jackysoft.edu.entity.NoteChapter;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;


@Service
public class NoteChapterService extends AbstractMongoService<NoteChapter> {


	static final Log logger = LogFactory.getLog(NoteChapterService.class);


	@Override
	public void update(NoteChapter t) {
		if(t==null) return;

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


}
