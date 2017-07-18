package org.jackysoft.edu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.entity.Exercise;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.file.CMD;
import org.jackysoft.file.ChannelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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

    /**
     * 上传并分析答案
     *
     * @param part 答案文件
     */

    public ActionResult uploadAnswer(Part part) {
        ActionResult result = new ActionResult();
        StringBuffer answer = new StringBuffer();
        String fileName = part.getSubmittedFileName();
        //like  .doc
        String extision = fileName.substring(fileName.lastIndexOf('.'));
        if (CMD.isOffice(extision)) {
            if (!extision.toLowerCase().startsWith(".doc")) {
                result.setFlag(false);
                result.setMessage("答案必须为Word文档形式");
                return result;
            }

            String newName = UUID.randomUUID().toString();
            String newfileName = newName + CMD.PDF_EXTISION;
            try (InputStream ins = part.getInputStream()) {

                long size = Files.copy(ins, new File(baseDir, newName + extision).toPath());
                result.put("size", size);
                ChannelManager.getManager().addCMD(CMD.getCMD(extision, newName + extision), extision);

                result.put("explain", newfileName);
                StringBuffer sb = extision.toLowerCase().contains("x") ? parseDocx(ins) : parseDoc(ins);
                if (sb != null) {
                    String str = sb.toString();
                    if (str.indexOf(CHOICE_TAIL) < 0) {
                        result.setMessage("未发现选择题分隔符:" + CHOICE_TAIL);
                        result.setFlag(false);
                        return result;
                    }
                    String choiceOri = str.substring(0, str.indexOf(CHOICE_TAIL));
                    char[] chars = choiceOri.toCharArray();
                    for (char c : chars) {
                        if (FIXED_CHOICE.contains(Character.valueOf(c))) {
                            answer.append(c);
                        }
                    }
                    result.setFlag(true);
                    result.put("choice", answer.toString());
                    result.put("choicesize", answer.toString().length());
                    //end collect choice

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
        ActionResult result = new ActionResult();
        if (part == null) {
            result.setFlag(false);
            result.setMessage("习题不能为空");
            return result;
        }
        String fileName = part.getSubmittedFileName();
        String extision = fileName.substring(fileName.lastIndexOf('.'));
        String newName = UUID.randomUUID().toString();
        try (InputStream ins = part.getInputStream()) {

            Files.copy(ins, new File(baseDir, newName + extision).toPath());
            ChannelManager.getManager().addCMD(CMD.getCMD(extision, newName + extision), extision);
            result.put("filename", newName + CMD.PDF_EXTISION);
            result.put("name", fileName);
            result.setFlag(true);
        } catch (IOException e) {
            result.setFlag(false);
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }

    public ActionResult save(
            String commonStyle,
            String chapter,
            String name,
            String realpath,
            String choice,
            int choicesize,
            int explainsize,
            String explain,
            String owner
    ) {

        ActionResult result = new ActionResult();

        Chapter chp = chapterService.findById(chapter);
        if (chp == null) {
            result.setFlag(false);
            result.setMessage("章节不存在");
            return result;
        }
        Exercise bean = new Exercise();
        bean.setChapter(chapter);
        bean.setCourse(chp.getCourse());
        bean.setGrade(chp.getGrade());
        bean.setRealpath(realpath);
        bean.setCommonType(commonStyle);
        bean.setName(name);
        bean.setChoice(choice);
        bean.setExplain(explain);
        bean.setChoicesize(choicesize);
        bean.setExplainsize(explainsize);
        bean.setOwner(owner);
        dataStore.save(bean);
        return result;
    }
}
