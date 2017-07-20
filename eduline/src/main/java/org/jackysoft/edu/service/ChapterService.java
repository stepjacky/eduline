package org.jackysoft.edu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChapterService extends AbstractMongoService<Chapter> {


    static final Log logger = LogFactory.getLog(ChapterService.class);

    @Autowired TextbookService textbookService;


    @Override
    public ActionResult save(Chapter chapter) {

        ActionResult result = super.save(chapter);
        if(!bookHasChapter(chapter.getTextbook())){

            textbookService.setChapter(chapter.getTextbook(),chapter.getId());
        }
        return result;
    }

    public List<Chapter> findByParent(String parent){
        List<Chapter> beans = new ArrayList<>();
        beans.addAll(query().field("parent").equal(parent).asList());
        return beans;
    }

    /**
     * @return  true has via,false
     * */
    boolean bookHasChapter(String book){
       long count =  dataStore.getCount(query().field("textbook").equal(book));
       return count>0;
    }

}
