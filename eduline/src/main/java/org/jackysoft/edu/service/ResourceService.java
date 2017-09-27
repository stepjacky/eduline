package org.jackysoft.edu.service;

import com.google.common.base.Strings;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.*;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.utils.EdulineConstant;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.List;

@Service
public class ResourceService extends AbstractMongoService<Resource> {

    static final Log logger = LogFactory.getLog(ResourceService.class);


    @Autowired
    ChapterService chapterService;
    @Autowired
    SysUserService userService;
    @Autowired
    TextbookService textbookService;

    @Override
    public ActionResult save(Resource bean) {

        ActionResult result = new ActionResult();
        Chapter chp = chapterService.findById(bean.getChapter());
        bean.setModifyDate(Instant.now().toEpochMilli());

        if (chp == null) {
            result.setFlag(false);
            result.setMessage("章节不存在");
            return result;
        }
        bean.setChapterName(chp.getName());
        result = super.save(bean);
        if(EdulineConstant.Commontype.common.getKey().equals(bean.getCommontype())){
            Resource target = new Resource();
            try {
                BeanUtils.copyProperties(target,bean);
            } catch (IllegalAccessException |InvocationTargetException  e) {
                e.printStackTrace();
            }
            target.setId(null);
            target.setCommontype(EdulineConstant.Commontype.personal.getKey());
            target.setModifyDate(Instant.now().toEpochMilli());
            super.save(bean);
        }
        return result;

    }
    /**
     * 共享特定资源分页
     *
     * @param commontype while personal is true,persent a teacher,else shared
     * @param styletype
     * @param filetype 内容类型
     * @param owner  用户
     * @param page 页码
     */
    public Pager<Resource> findSpecialPager(
            int page,
            String owner,
            String chapter,
            String commontype,
            String styletype,
            String filetype
    ) {

        if(Strings.isNullOrEmpty(chapter)) return Pager.EMPTY_PAGER();
        Query<Resource> query = query();
        if(!Strings.isNullOrEmpty(commontype)) {
            query.field("commontype").equal(commontype);
        }
        if(EdulineConstant.Commontype.personal.getKey().equals(commontype)){
            query.field("owner.value").equal(owner);
        }
        if(!Strings.isNullOrEmpty(styletype)) {
            query.field("styletype").equal(styletype);
        }

        if(!Strings.isNullOrEmpty(filetype)){
            query.field("filetype").equal(filetype);
        }

        query.field("chapter").equal(chapter)
             .order("-modifyDate");

        long count = dataStore.getCount(query);

        List<Resource> dataList = query
                .asList(new FindOptions()
                        .skip(page * Pager.DEFAULT_OFFSET)
                        .limit(Pager.DEFAULT_OFFSET));
        Pager<Resource> pager = Pager.build(page, Pager.DEFAULT_OFFSET, count, dataList, false);

        return pager;
    }


    public List<Resource> findforStudent(String teacher,int grade,int course,int page){
           return query().field("owner.value").equal(teacher)
                    .field("grade").equal(grade)
                    .field("course").equal(course)
                    .order("chapter")
                    .asList(
                            new FindOptions().skip(page* Pager.DEFAULT_OFFSET).limit( Pager.DEFAULT_OFFSET)

                    );
    }


    @Override
    public void beforeInput(ModelAndView mav) {
        super.beforeInput(mav);
        List<Textbook> books =  textbookService.findAll();
        mav.addObject("books",books);
    }

    @Override
    public boolean beforeRemoveKey(String s) {
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication();
        if (user == null) {
            logger.error("illegal access ,denied");
            return false;
        }
        Resource bean = queryById(s).get();
        if (bean == null) {
            logger.error("illegal access ,denied");
            return false;
        }
        if (user.getUsername().equals(bean.getOwner().getValue())) {
            return true;
        }

        return false;


    }
}
