package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.bson.types.ObjectId;
import org.jackysoft.edu.entity.HomeWork;
import org.jackysoft.edu.entity.NoEntity;
import org.jackysoft.file.FileServer;
import org.jackysoft.query.Pager;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.aggregation.Sort;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;

import com.google.common.base.Strings;
import com.mongodb.MongoClient;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class TestCommons {
	final Log logger = LogFactory.getLog(TestCommons.class);

	@Test
	public void test() throws Exception {
		String fileName = "j:/test0621.doc";
	    
		try(WordExtractor extractor = 
				new WordExtractor(new FileInputStream(new File(fileName)))){
		   
			String text = extractor.getTextFromPieces();
			logger.info(text);
			
		} catch (IOException e) {
			logger.error(e);
		}

		BeanNameViewResolver resolver;

		InternalResourceViewResolver ire;
	}

	

}
