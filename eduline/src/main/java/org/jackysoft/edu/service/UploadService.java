package org.jackysoft.edu.service;


import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.file.CMD;
import org.jackysoft.file.ChannelManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class UploadService {

    @Value("${uploaded.location}")
    protected String baseDir;

    public ActionResult upload(Part part){

        ActionResult result = new ActionResult();
        if (part == null) {
            result.setFlag(false);
            result.setMessage("文件不能为空");
            return result;
        }
        String fileName = part.getSubmittedFileName();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newName = UUID.randomUUID().toString();
        try (InputStream ins = part.getInputStream()) {

            long size = Files.copy(ins, new File(baseDir, newName + suffix).toPath());
            result.put("filename",newName);
            result.put("realpath", newName + suffix);
            result.put("name", fileName.substring(0,fileName.lastIndexOf('.')));
            result.put("size",size);
            result.put("suffix",suffix);
            result.setFlag(true);
        } catch (IOException e) {
            result.setFlag(false);
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }



}
