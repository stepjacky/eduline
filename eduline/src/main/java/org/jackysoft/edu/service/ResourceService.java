package org.jackysoft.edu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.entity.Resource;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            String detailType,
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
        bean.setDetailType(detailType);
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
     * @param detailType 内容类型
     * @param personal   是否个人资源
     */
    public Pager<Resource> findSpecPager(
            int page,
            String commonType,
            String styleType,
            String detailType,
            boolean personal
    ) {

        Query<Resource> query = query();
        if (personal) {
            query.field("owner").equal(commonType);
        } else {
            query.field("commonType").equal(commonType);
        }

        if (styleType != null) {
            query.field("styleType").equal(styleType);
        }

        if (detailType != null) {
            query.field("detailType").equal(detailType);
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


}
