package org.jackysoft.edu.service;


import org.jackysoft.edu.entity.Textbook;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class TextbookService extends AbstractMongoService<Textbook> {


    @Autowired
    protected GradeService gradeService;

    @Autowired
    protected CourseService courseService;

    public void setChapter(String id,String chapter){
        dataStore.update(queryById(id),updates().set("chapter",chapter));
    }

    @Override
    public void beforeInput(ModelAndView mav) {
        mav.addObject("grades", gradeService.findAll());
        mav.addObject("courses", courseService.findAll());
        super.beforeInput(mav);
    }
}
