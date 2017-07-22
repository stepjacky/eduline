package org.jackysoft.edu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class ChapterService extends AbstractMongoService<Chapter> {


    static final Log logger = LogFactory.getLog(ChapterService.class);

    @Autowired TextbookService textbookService;


    @Override
    public ActionResult save(Chapter chapter) {
        if(chapter==null) return ActionResult.FAILURE;
        chapter.setTimemillis(Instant.now().toEpochMilli());
        return super.save(chapter);
    }

    public List<Chapter> findByParent(String parent, String textbook){
        List<Chapter> beans = new ArrayList<>();
        beans.addAll(query()
                .field("parent").equal(parent)
                .field("textbook").equal(textbook)
                .order("timemillis")
                .asList());
        return beans;
    }

    @Override
    public ActionResult removeById(String s) {
        Query<Chapter> query  = query().field("parent").equal(s);
        dataStore.delete(query);
        return super.removeById(s);
    }

}
