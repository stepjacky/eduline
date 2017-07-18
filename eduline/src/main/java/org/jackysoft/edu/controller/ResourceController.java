package org.jackysoft.edu.controller;


import org.jackysoft.edu.entity.Resource;
import org.jackysoft.edu.service.base.AbstractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractController<String, Resource>{



    @Override
    public AbstractService<String, Resource> getService() {
        return null;
    }
}
