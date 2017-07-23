package org.jackysoft.edu.controller;

import org.jackysoft.edu.service.UploadService;
import org.jackysoft.edu.view.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Part;

@Controller
@RequestMapping("/fileupload")
public class UploadController {

    @Autowired
    UploadService service;

    @ResponseBody
    @PostMapping("/upload")
    public ActionResult upload(@RequestParam("file") Part part){
        ActionResult result = service.upload(part);
        return result;
    }

}
