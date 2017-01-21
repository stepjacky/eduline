package org.jackysoft.edu.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.beanutils.PropertyUtils;
import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.entity.Grade;
import org.jackysoft.edu.entity.HomeWork;
import org.jackysoft.edu.entity.LectureNotes;
import org.jackysoft.edu.entity.NoteChapter;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.service.AbstractService;
import org.jackysoft.edu.service.CourseService;
import org.jackysoft.edu.service.GradeService;
import org.jackysoft.edu.service.HomeWorkService;
import org.jackysoft.edu.service.LectureNotesService;
import org.jackysoft.file.CMD;
import org.jackysoft.file.ChannelManager;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;


@Controller
@RequestMapping("/lecturenotes")
public class LectureNotesController extends AbstractController<String,LectureNotes>{

	
	@Autowired
	protected LectureNotesService service;
	
	@Autowired
	protected CourseService courseService;
	
	@Autowired
	protected GradeService gradeService;
	
	@Autowired
	protected HomeWorkService workService;
	
	
	
	@RequestMapping("/lectures/{page}")
	public ModelAndView myLectures(
			@AuthenticationPrincipal SysUser user,
			@PathVariable("page")int page){
		ModelAndView mav = new ModelAndView("pager");
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("owner`"+user.getName());
		qc.setOrders("firetime desc,course desc");
		Pager<LectureNotes> pager = service.findPager(qc, page, 10);
		mav.addObject("pager", pager);
		return mav;
	}
	@RequestMapping("/lectures/student/{page}")
	public String studentLectures(
			@AuthenticationPrincipal SysUser user,
			@PathVariable("page")int page,
			Model model){
		
		List<HomeWork> works = workService.studentLectures(user.getUsername());
		Set<String> ids = new HashSet<>();
		works.forEach(w->{
			if(!Strings.isNullOrEmpty(w.getLecture())){
				ids.add(w.getLecture());
			}
					
		});
		
		String idz = Joiner.on("','").join(ids);
		Pager<LectureNotes> pager = service.findPagerForStudent(page, idz);
	    model.addAttribute("pager", pager);
		return "student-pager";
	}
	
	@RequestMapping("/shared/{id}")
	public ModelAndView shared(
			@PathVariable("id")String id,
			@RequestParam("users")List<String> users){
		ModelAndView mav = new ModelAndView("persiste");
		for(String user:users){
			service.sharedLecture(id, user);
		}
		return mav;
 	}

	@GetMapping("/set/publiced/{shared}/{id}")
	public ModelAndView setpubliced(
			@PathVariable("id")String id,
			@PathVariable("shared")int shared
			){
		service.updatePartial(id, "shared:"+shared);
		return new ModelAndView("persiste");
	}
	
	
	@GetMapping("/public/{course}/{page}")
	public ModelAndView publiced(
			@PathVariable("course")int cid,
			@PathVariable("page")int page			
			){
		ModelAndView mav = new ModelAndView("pager-shared");	
		List<Course> courses = courseService.findAll();
		mav.addObject("courses",courses);
		Course course = courseService.findById(cid);
		mav.addObject("course",course);
		Pager<LectureNotes> pager = service.findShared(cid,page);
		mav.addObject("pager", pager);
		List<Grade> grades =gradeService.findAll();
		mav.addObject("grades",grades);
		return mav;
	}
	
	
		
	@Value("${noteDir}")
	protected String noteDir;
	@Override
	protected void proceedUpload(LectureNotes bean, Part part) throws Exception {
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		String oraName = part.getSubmittedFileName();
		String fileName = oraName;
		String ext = fileName.substring(fileName.lastIndexOf('.'));
		String fileId = UUID.randomUUID().toString();
		fileName = fileId + ext;
		try (InputStream ins = part.getInputStream()) {
			File file = new File(noteDir, fileName);			
			Files.copy(ins, file.toPath(),StandardCopyOption.REPLACE_EXISTING);			
			bean.setShared(1);
			bean.setSharedFile(fileName);
			bean.setOwner(user.getName());
			bean.setFiretime(Instant.now().toEpochMilli());
			bean.setName(oraName.substring(0,oraName.lastIndexOf('.')));
			service.save(bean);
			if(!".pdf".equalsIgnoreCase(ext)){
				ChannelManager.getManager().addCMD(CMD.withType(ext).get(fileId));				    
			}
		}
	}
	
	@GetMapping(value = "/public/viewpdf/{id}")
	public ResponseEntity<InputStreamResource> pdfViewer(
			@PathVariable("id") String id) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException{
		
		LectureNotes bean = service.findById(id);
		if(bean==null){
			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
		}
				
		String name = bean.getSharedFile().substring(0,bean.getSharedFile().lastIndexOf('.')+1)+"pdf";
		File file = new File(noteDir, name);
		if(!file.exists()){
			file= new File(noteDir,"no-pdf.pdf");
		}
		InputStream ins = Files.newInputStream(file.toPath(),
				StandardOpenOption.READ);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentLength(ins.available());
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentDispositionFormData("attachment", "filename.pdf");
		InputStreamResource isr = new InputStreamResource(ins);
		return new ResponseEntity<InputStreamResource>(isr, headers, HttpStatus.OK);
	}
	
	
	@Override
	public AbstractService<String,LectureNotes> getService() {
		return service;
	}

}