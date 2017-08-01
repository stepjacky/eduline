package org.jackysoft.edu.service;


import org.jackysoft.edu.entity.NameValue;
import org.jackysoft.edu.entity.Textbook;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TextbookService extends AbstractMongoService<Textbook> {


    @Autowired
    protected GradeService gradeService;

    @Autowired
    protected CourseService courseService;


    public Textbook getDefaultTextbook(String id,String username){

        Textbook textbook = findById(id);
        return textbook;
    }

    @Override
    public void beforeInput(ModelAndView mav) {
        mav.addObject("grades", gradeService.findAll());
        mav.addObject("courses", courseService.findAll());
        super.beforeInput(mav);
    }

    public Map<NameValue,List<Textbook>> findGroupedTextbook(){
        Map<NameValue,List<Textbook>> books =
        dataStore.find(Textbook.class).order("grade.value").asList()
                .stream().collect(Collectors.groupingBy(Textbook::getGrade));
        return books;

    }
}
