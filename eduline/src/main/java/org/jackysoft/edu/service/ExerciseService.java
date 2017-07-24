package org.jackysoft.edu.service;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.Exercise;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.file.CMD;
import org.jackysoft.file.ChannelManager;
import org.jackysoft.query.Pager;
import org.jackysoft.utils.EdulineConstant;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService extends AbstractMongoService<Exercise> {

    static final Log logger = LogFactory.getLog(ExerciseService.class);

    static final List<Character> FIXED_CHOICE = Arrays.asList('A', 'B', 'C', 'D');

    static final String CHOICE_TAIL = "#";

    @Value("${uploaded.location}")
    protected String baseDir;
    @Autowired
    ChapterService chapterService;

    @Autowired
    UploadService uploadService;

    /**
     * 上传并分析答案
     *
     * @param part 答案文件
     * @return 返回结果包括, 解答题路径, 选择题答案, 选择题数目
     */

    public ActionResult uploadAnswer(Part part) {
        ActionResult result = new ActionResult();
        StringBuffer answer = new StringBuffer();
        String fileName = part.getSubmittedFileName();
        //like  .doc
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        if (CMD.isOffice(suffix)) {
            if (!suffix.toLowerCase().startsWith(".doc")) {
                result.setFlag(false);
                result.setMessage("答案必须为Word文档形式");
                return result;
            }

            String newName = UUID.randomUUID().toString();
            String newfileName = newName + CMD.PDF_EXTISION;
            try (InputStream ins = part.getInputStream()) {

                File target = new File(baseDir, newName + suffix);
                long size = Files.copy(ins, target.toPath());
                result.put("size", size);
                ChannelManager.getManager().addCMD(CMD.getCMD(suffix, newName + suffix), suffix);

                result.put("explain", newfileName);
                StringBuffer sb = parseWord(suffix, target);
                if (sb != null) {
                    String str = sb.toString();
                    if (str.indexOf(CHOICE_TAIL) < 0) {
                        result.put("message", "未发现选择题分隔符:" + CHOICE_TAIL);
                        result.setFlag(false);
                        return result;
                    }
                    String choiceOri = str.substring(0, str.indexOf(CHOICE_TAIL));
                    char[] chars = choiceOri.toCharArray();

                    for (char c : chars) {
                        if (FIXED_CHOICE.contains(Character.toUpperCase(Character.valueOf(c)))) {
                            answer.append(c);
                        }
                    }
                    result.setFlag(true);
                    result.put("choice", answer.toString());
                    result.put("csize", answer.toString().length());

                }
            } catch (IOException e) {
                result.setFlag(false);
                result.setMessage(e.getMessage());
                return result;
            }


        }


        return result;
    }

    /**
     * 上传答案
     */
    public ActionResult uploadExercise(Part part) {
        ActionResult result = uploadService.upload(part);
        ChannelManager.getManager().addCMD(CMD.getCMD(result.get("suffix") + "",
                result.get("filename") + "" + result.get("suffix")), result.get("suffix") + "");
        return result;
    }

    public Pager<Exercise> findSpecialPager(
            int page,
            String owner,
            String chapter,
            String commontype

    ) {
        Query<Exercise> query = query()
                .field("commontype").equal(commontype)
                .field("chapter").equal(chapter)
                .order("-modifyDate");

        if (EdulineConstant.Commontype.personal.getKey().equals(commontype)) {
            query.field("owner.value").equal(owner);
        }

        long count = query.count();

        List<Exercise> list = query.asList(new FindOptions()

                .skip(page * Pager.DEFAULT_OFFSET)
                .limit(Pager.DEFAULT_OFFSET)
        );

        ;
        Pager<Exercise> pager = Pager.build(page, count, list);
        return pager;
    }


    @Override
    public ActionResult save(Exercise exercise) {
        if (exercise == null) return null;
        if (EdulineConstant.Commontype.common.getKey().equals(exercise.getCommontype())) {
            Exercise bean = new Exercise();
            try {

                BeanUtils.copyProperties(bean, exercise);
                bean.setCommontype(EdulineConstant.Commontype.personal.getKey());
            } catch (IllegalAccessException | InvocationTargetException e) {
                ActionResult rst = new ActionResult();
                rst.setFlag(false);
                rst.setMessage(e.getMessage());
            }
            bean.setModifyDate(Instant.now().toEpochMilli());
            super.save(bean);

        }
        exercise.setModifyDate(Instant.now().toEpochMilli());
        return super.save(exercise);
    }


    @Override
    public boolean beforeRemoveKey(String s) {
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication();
        if (user == null) {
            logger.info("illegal access ,denied");
            return false;
        }
        Exercise bean = queryById(s).get();
        if (bean == null) {
            logger.info("illegal access ,denied");
            return false;
        }
        if (user.getUsername().equals(bean.getOwner().getValue())) {
            return true;
        }

        return false;

    }
}
