package org.jackysoft.edu.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.PropertyUtils;
import org.imgscalr.Scalr;
import org.jackysoft.edu.entity.NoteChapter;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.NoteChapterService;
import org.jackysoft.file.CMD;
import org.jackysoft.file.ChannelManager;
import org.jackysoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

@Controller
@RequestMapping("/notechapter")
public class NoteChapterController extends AbstractController<String, NoteChapter> {

	@Autowired
	protected NoteChapterService service;

	@Value("${noteDir}")
	protected String noteDir;

	private static final int IMAGE_WIDTH=800;
	@ResponseBody
	@RequestMapping("/ztree")
	public List<ZtreeNode> ztree(@RequestParam("parent") String parent) {
		List<ZtreeNode> beans = service.ztree(parent);
		return beans;
	}

	@ResponseBody
	@GetMapping("/getinfo/{id}")
	public ZtreeNode getinfo(@PathVariable("id")String id){
		NoteChapter bean = service.findById(id);
		if(bean!=null) return bean.toZtreeNode();
		return null;
	}
	
	@RequestMapping(value = "/download/{type}/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> typedownload(@PathVariable("id") String id,
			@PathVariable("type") String type)
			throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		NoteChapter nc = service.findById(id);
		if ( type==null || nc == null)
			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
		if (PropertyUtils.isReadable(nc, type)) {
			Object val = PropertyUtils.getSimpleProperty(nc, type);
			if (val == null) {
				return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
			}
			String realName = val + "";
			
			InputStream ins = Files.newInputStream(new File(noteDir, realName).toPath(),
						StandardOpenOption.READ);
		
			HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentLength(ins.available());
				headers.setContentDispositionFormData("attachment", StringUtils.toDownloadFileName(
						nc.getName()+"-"+NoteChapter.TYPED_FILE.get(type) + realName.substring(realName.lastIndexOf('.'))));
				InputStreamResource isr = new InputStreamResource(ins);
				return new ResponseEntity<InputStreamResource>(isr, headers, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/viewpdf/{type}/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> pdfViewer(
			@PathVariable("id") String id,
			@PathVariable("type") String type) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException{
		
		NoteChapter nc = service.findById(id);
		if(nc==null){
			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
		}
		
		Object val = PropertyUtils.getSimpleProperty(nc, type);
		if(val==null){
			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
		}
		String fileName = val+"";
		String name = fileName.substring(0,fileName.lastIndexOf('.')+1)+"pdf";
		File file = new File(noteDir, name);
		if(!file.exists()){
			file= new File(noteDir,"no-pdf.pdf");
		}
		InputStream ins = Files.newInputStream(file.toPath(),
				StandardOpenOption.READ);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentLength(ins.available());
		headers.setPragma("public");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		//headers.setContentDispositionFormData("attachment", "filename.pdf");
		InputStreamResource isr = new InputStreamResource(ins);
		return new ResponseEntity<>(isr, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/upload/params", method = RequestMethod.POST)
	public ModelAndView uploadform(
			@RequestParam("type") String type,			
			NoteChapter bean,
			@RequestParam("file") Part part) {
		ModelAndView mav = new ModelAndView("upload");
	
		try {
			if(bean==null)bean = new NoteChapter();
			PropertyUtils.setSimpleProperty(bean, type, type);
			proceedUpload(bean, part);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return mav;
	}

	@GetMapping("/update/heads/{id}/{heads}")
	public ModelAndView updateHeads(
			@PathVariable("id")String id,
			@PathVariable("heads")String heads){
		ModelAndView mav = new ModelAndView("upload");
		service.updateSimple(id, "anwserHead", heads);
		return mav;
	}

	
	protected void proceedUpload(NoteChapter bean, Part part) throws Exception {

		
		String[] props = new String[] { "pptFile", "mp3File", "keysFile", "exerciseFile", "anwserFile" };
		String fileName = part.getSubmittedFileName();
		String ext = fileName.substring(fileName.lastIndexOf('.'));
		String fileId = UUID.randomUUID().toString();
		fileName = fileId + ext;
		try (InputStream ins = part.getInputStream()) {
			
			File file = new File(noteDir, fileName);			
			if(ext.toLowerCase().equals(".jpg")||ext.toLowerCase().equals(".png")){
				BufferedImage imgins = ImageIO.read(ins);
				BufferedImage target = Scalr.resize(imgins, IMAGE_WIDTH);
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				ImageIO.write(target, ext.substring(1), output);
				Files.copy(new ByteArrayInputStream(output.toByteArray()), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}else{
				
				
				Files.copy(ins, file.toPath(),StandardCopyOption.REPLACE_EXISTING);
			}
			
			
			for (String prop : props) {
				Object val = PropertyUtils.getProperty(bean, prop);
				if("mp3File".equals(prop)){
					service.updateSimple(bean.getId(), prop, fileName);
					continue;
				}
				if (val != null && !Strings.isNullOrEmpty(val.toString())) {
					if(!".pdf".equalsIgnoreCase(ext)){
						ChannelManager.getManager().addCMD(CMD.withType(ext).get(fileId));				    
					}
				    service.updateSimple(bean.getId(), prop, fileName);
					if("anwserFile".equals(prop)){
						service.updateSimple(bean.getId(), "anwserNum", bean.getAnwserNum()+"");
					}
					break;
				}
			}
		}

	}

	@Override
	public AbstractService<String, NoteChapter> getService() {
		return service;
	}

}