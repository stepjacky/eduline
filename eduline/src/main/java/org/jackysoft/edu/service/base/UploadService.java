package org.jackysoft.edu.service.base;

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


    public ActionResult upload(Part part) {
        ActionResult result = new ActionResult();
        if(part==null){
            result.setFlag(false);
            result.setMessage("信息体为空");
            return result;
        }

        String fileName = part.getSubmittedFileName();
        //not include dot
        String extision = fileName.substring(fileName.lastIndexOf('.')+1);
        try(InputStream ins = part.getInputStream()){
            String newfileName = UUID.randomUUID().toString();
            long size = Files.copy(ins,new File(baseDir,newfileName+"."+extision).toPath());
            ChannelManager.getManager().addCMD(CMD.getCMD(extision,newfileName+"."+extision),extision);
            if(CMD.isOffice(extision)){
                result.setMessage(newfileName+CMD.PDF_EXTISION);
            }else{
                result.setMessage(newfileName+"."+extision);
            }
            result.put("size",size);
            result.setFlag(true);
            return result;
        } catch (IOException e) {
            result.setFlag(false);
            result.setMessage(e.getMessage());
            return result;
        }

    }
}
