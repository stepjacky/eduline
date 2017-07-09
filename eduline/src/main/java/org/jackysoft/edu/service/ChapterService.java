package org.jackysoft.edu.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;


@Service
public class ChapterService extends AbstractMongoService<Chapter> {


	static final Log logger = LogFactory.getLog(ChapterService.class);


	@Override
	public void update(Chapter t) {
		if(t==null) return;

	}




}
