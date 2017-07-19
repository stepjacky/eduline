package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.Textbook;
import org.jackysoft.edu.service.CourseService;
import org.jackysoft.edu.service.GradeService;
import org.jackysoft.edu.service.TextbookService;
import org.jackysoft.edu.service.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/textbook")
public class TextbookController extends AbstractController<String, Textbook> {


    @Autowired
    protected TextbookService service;



    @Override
    public AbstractService<String, Textbook> getService() {
        return service;
    }
}
