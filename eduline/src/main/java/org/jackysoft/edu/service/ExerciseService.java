package org.jackysoft.edu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.entity.Exercise;
import org.jackysoft.edu.entity.Resource;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService extends AbstractMongoService<Exercise> {

    static  final Log logger = LogFactory.getLog(ExerciseService.class);

    static final List<String> FIXED_CHOICE = Arrays.asList("A","B","C","D");

    static final String CHOICE_TAIL = "#";


    @Autowired
    ChapterService chapterService;

    //上传答案

    public ActionResult uploadAnswer(Part part){
        ActionResult result = new ActionResult();
        String fileName = part.getSubmittedFileName();
        String extision = fileName.substring(fileName.lastIndexOf('.'));
        if(CMD.isOffice(extision){
            if (".doc".equalsIgnoreCase(extision)){
                try(InputStream ins = part.getInputStream()){
                    StringBuffer sb = parseDoc(ins);
                    if(sb!=null) {
                        String str = sb.toString();

                    }
                } catch (IOException e) {

                    result.setMessage(e.getMessage());
                    return result;
                }
            }
        }


        return result;
    }

    public ActionResult save(
            String commonStyle,
            String chapter,
            String name,
            String realpath,
            String choice,
            int choiceNumber,
            int explainNumber,
            String explain,
            String owner
            ){

        ActionResult result = new ActionResult();

        Chapter chp = chapterService.findById(chapter);
        if(chp==null){
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
        bean.setChoiceNumber(choiceNumber);
        bean.setExplainNumber(explainNumber);
        bean.setOwner(owner);
        dataStore.save(bean);
        return result;
    }
}
