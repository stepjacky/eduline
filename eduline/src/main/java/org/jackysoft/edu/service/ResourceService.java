package org.jackysoft.edu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.entity.Resource;
import org.jackysoft.edu.entity.Textbook;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.utils.EdulineConstant;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.util.List;

@Service
public class ResourceService extends AbstractMongoService<Resource> {

    static final Log logger = LogFactory.getLog(ResourceService.class);


    @Autowired
    ChapterService chapterService;

    public ActionResult save(
            String commonType,
            String styleType,
            String fileType,
            String name,
            String owner,
            String chapter,
            String realpath,
            int size
    ) {


        ActionResult result = new ActionResult();
        Resource bean = new Resource();
        Chapter chp = chapterService.findById(chapter);
        if (chp == null) {
            result.setFlag(false);
            result.setMessage("章节不存在");
            return result;
        }

        bean.setChapter(chapter);
        bean.setCourse(chp.getCourse());
        bean.setGrade(chp.getGrade());
        bean.setCommonType(commonType);
        bean.setStyleType(styleType);
        bean.setFileType(fileType);
        bean.setName(name);
        bean.setOwner(owner);
        bean.setRealpath(realpath);
        bean.setSize(size);
        bean.setModifyDate(Instant.now().toEpochMilli());
        dataStore.save(bean);
        result.setFlag(true);
        return result;

    }


    /**
     * 共享特定资源分页
     *
     * @param commonType while personal is true,persent a teacher,else shared
     * @param styleType
     * @param fileType 内容类型
     * @param owner  用户
     * @param page 页码
     */
    public Pager<Resource> findSpecialPager(
            int page,
            String owner,
            String commonType,
            String styleType,
            String fileType
    ) {

        Query<Resource> query = query();
        if (commonType!=null) {
            query.field("commonType").equal(commonType);
            if(EdulineConstant.CommonType.personal.getKey().equals(commonType)){
                query.field("owner").equal(owner);
            }

        }

        if (styleType != null) {
            query.field("styleType").equal(styleType);
        }

        if (fileType != null) {
            query.field("fileType").equal(fileType);
        }
        query.order("-modifyDate");

        long count = dataStore.getCount(query);

        List<Resource> dataList = query
                .asList(new FindOptions()
                        .skip(page * Pager.DEFAULT_OFFSET)
                        .limit(Pager.DEFAULT_OFFSET));
        Pager<Resource> pager = Pager.build(page, Pager.DEFAULT_OFFSET, count, dataList, false);
        return pager;
    }

    @Autowired
    TextbookService textbookService;

    @Override
    public void beforeInput(ModelAndView mav) {
        super.beforeInput(mav);
        List<Textbook> books =  textbookService.findAll();
        mav.addObject("books",books);
    }
}
