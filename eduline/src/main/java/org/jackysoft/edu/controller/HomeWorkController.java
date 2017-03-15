package org.jackysoft.edu.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.imgscalr.Scalr;
import org.jackysoft.edu.entity.GroupMember;
import org.jackysoft.edu.entity.HomeWork;
import org.jackysoft.edu.entity.LectureNotes;
import org.jackysoft.edu.entity.NoteChapter;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.service.AbstractService;
import org.jackysoft.edu.service.GroupMemberService;
import org.jackysoft.edu.service.HomeWorkService;
import org.jackysoft.edu.service.LectureNotesService;
import org.jackysoft.edu.service.NoteChapterService;
import org.jackysoft.edu.service.PreResult;
import org.jackysoft.file.CMD;
import org.jackysoft.file.ChannelManager;
import org.jackysoft.query.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;


@Controller
@RequestMapping("/homework")
public class HomeWorkController extends AbstractController<String,HomeWork>{

	
	@Autowired
	protected HomeWorkService service;
		
	@Autowired
	protected GroupMemberService groupService;
	
	@Autowired
	protected NoteChapterService chapterService;
	
	
	@Autowired
	protected LectureNotesService lectureService;
	
	@Value("${noteDir}")
	protected String noteDir;
	private static final int IMAGE_WIDTH=800;
		
	//老师历次作业
	@RequestMapping("/teacher/homeworks/{page}")
	public String teacherHomeworks(
			@AuthenticationPrincipal SysUser user,
			@PathVariable("page")int page,
			Model model
			){
		
		Pager<HomeWork> pager = service.teacherHomeworks(page, user.getName());
		model.addAttribute("pager", pager);
		return "teacher-homeworks-pager";
	}
	
	@RequestMapping("/homework/prehomework")
	public String preHome(@AuthenticationPrincipal SysUser user,Model model){
		List<GroupMember> groups = groupService.findTeacherGroups(user.getName());
		model.addAttribute("groups", groups);
		
		return "prehomework";
	}
	
	@RequestMapping("/homework/lecture/{course}/{groupId}/{page}")
	public String lectures(
			@PathVariable("course")int course,
			@PathVariable("page")int page,
			@PathVariable("groupId")String groupId,
			@AuthenticationPrincipal SysUser user,
			Model model){
		Pager<LectureNotes> pager = lectureService.findPagerForTeacher(page, user.getName(), course);
		model.addAttribute("lectures", pager);
		model.addAttribute("groupId", groupId);
		return "lectures";
	}
	
	@RequestMapping("/homework/chapters/{groupId}/{lecture}")
	public String chapters(
			@PathVariable("lecture")String lectureId,
			@PathVariable("groupId")String groupId,
			Model model){
		LectureNotes bean = lectureService.findById(lectureId);
		model.addAttribute("bean", bean);
		model.addAttribute("groupId", groupId);
		model.addAttribute("group", groupService.findGroupFeature(groupId));
		return "chapters";
	}
	
	//老师布置作业
	@RequestMapping("/teacher/homework/assign/{groupId}/{lecture}/{chapter}/{offset}")
	@ResponseBody
	public boolean assignHomework(
			@PathVariable("offset") int offset,
			@PathVariable("groupId") String groupId,
         	@PathVariable("lecture") String lecture,
			@PathVariable("chapter") String chapter,
			@AuthenticationPrincipal SysUser user,
			Model model
			){
		NoteChapter chp = chapterService.findById(chapter);
		List<GroupMember> members = groupService.findMembersOfGroup(groupId);
		List<HomeWork> beans = new ArrayList<>();
		String workId = UUID.randomUUID().toString();
		for(GroupMember member : members){
			HomeWork hw = new HomeWork();
			hw.setTeacher(user.getName());
			hw.setTeacherName(user.getNickname());			
			hw.setStudent(member.getStudent());
			hw.setStudentName(member.getStudentName());
			hw.setCourse(member.getCourse());
			hw.setCourseName(member.getCourseName());
			hw.setLecture(lecture);
			hw.setChapter(chp.getId());
			hw.setChapterName(chp.getName());			
			hw.setModelAnswer(chp.getAnwserHead());
			hw.setAnswerDoc(chp.getAnwserFile());
			hw.setExerciseFile(chp.getExerciseFile());
			hw.setFiretime(Instant.now().toEpochMilli());
			hw.setOffset(offset);
			hw.setGroupId(groupId);
			hw.setGroupName(member.getName());
			hw.setSubmitStatus(HomeWork.UNSUBMITED);
			hw.setScoreStatus(HomeWork.UNSCORED);
			hw.setWorkId(workId);
			beans.add(hw);
		}
		List<PreResult> results = service.saveAll(beans);
		
		for(PreResult r:results){
			if(r==null)continue;
			if(!r.isFlag()){
				return false;
				
			}
		}
		return true;
		
	}
	
	//老师布置过但是学生还没有提交的作业
	@RequestMapping("/teacher/unsubmited/{workId}/{page}")
	public String teacheUnsubmits(
			@PathVariable("page")int page,
			@PathVariable("workId")String workId,
			@AuthenticationPrincipal SysUser user,
			Model model
			){
		Pager<HomeWork> pager = service.teacherUnsubmits(page, workId, user.getName());
		model.addAttribute("pager", pager);
		model.addAttribute("workId", workId);
		model.addAttribute("type","unsubmited");
		return "teacher-homework-details-pager";
	}
	
	//学生提交过但是还没有批阅的作业
	@RequestMapping("/teacher/unscored/{workId}/{page}")
	public String teacheUnscored(
			@PathVariable("page")int page,
			@PathVariable("workId")String workId,
			@AuthenticationPrincipal SysUser user,
			Model model
			){
		Pager<HomeWork> pager = service.teacherUnscored(page, workId, user.getName());
		model.addAttribute("pager", pager);
		model.addAttribute("workId", workId);
		model.addAttribute("type","unscored");
		return "teacher-homework-details-pager";
	}
	
	
	//老师披阅过的作业
	@RequestMapping("/teacher/scored/{workId}/{page}")
	public String teacheScored(
			@PathVariable("page")int page,
			@PathVariable("workId")String workId,
			@AuthenticationPrincipal SysUser user,
			Model model
			){
		Pager<HomeWork> pager = service.teacherScored(page, workId, user.getName());
		model.addAttribute("pager", pager);
		model.addAttribute("workId", workId);
		model.addAttribute("type","scored");
		return "teacher-homework-details-pager";
	}
	
	//学生自己还没有提交过的作业
	@RequestMapping("/student/unsubmited/{page}")
	public String studentUnsubmit(@PathVariable("page")int page,
		    @AuthenticationPrincipal SysUser user,
			Model model){
		Pager<HomeWork> pager = service.studentUnsubmits(page, user.getName());
		model.addAttribute("pager", pager);
		model.addAttribute("type", "unsubmited");
		return "student-homework-details-pager";
	}
	
	
	//学生提交过老师还没有批阅的作业
	@RequestMapping("/student/unscored/{page}")
	public String studentUnscore(@PathVariable("page")int page,
			@AuthenticationPrincipal SysUser user,
			Model model){
		Pager<HomeWork> pager = service.studentUnScored(page, user.getName());
		model.addAttribute("pager", pager);
		model.addAttribute("type", "unscored");
		return "student-homework-details-pager";
	}
	
	//学生已经被批阅的作业
	@RequestMapping("/student/scored/{page}")
	public String studentScored(@PathVariable("page")int page,
			@AuthenticationPrincipal SysUser user,
			Model model){
		Pager<HomeWork> pager = service.studentScored(page, user.getName());
		model.addAttribute("pager", pager);

		model.addAttribute("type", "scored");
		return "student-homework-details-pager";
	}
	
	//学生开始做某次作业
	@RequestMapping(value="/student/homework/card/{id}")
	public String preSubmit(
			@PathVariable("id")String id,
			@AuthenticationPrincipal SysUser user,
			Model model){
		
		HomeWork bean = service.findById(id);
		String modelAnswer = bean.getModelAnswer();
		if(Strings.isNullOrEmpty(modelAnswer)){
			modelAnswer = "";
		}
		model.addAttribute("modelAnswers",modelAnswer.toCharArray());
		model.addAttribute("hwid", bean.getId());
		model.addAttribute("url", "/notechapter/viewpdf/exerciseFile/"+bean.getChapter());
		return "pre-do-homework";
	
	}
	
	@PostMapping("/student/preview/homework/{id}")
	@ResponseBody
	public String preUpload(@PathVariable("id")String id,@RequestParam("answerDoc") Part part)
			throws IOException{
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
			ChannelManager.getManager().addCMD(CMD.withType(ext).get(fileId));
			
		}
		if(ext.toLowerCase().equals(".jpg") || ext.toLowerCase().equals(".png")){
			return fileName;
		}
		return fileId+".pdf";
		
	}
	
	//学生更新某次作业
	@PostMapping(value="/student/answerdoc/update")
	@ResponseBody
	public String studentUpdate(
			@RequestParam("id")String id,
			@RequestParam("answerDoc") Part part
			) throws IOException {	
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
			ChannelManager.getManager().addCMD(CMD.withType(ext).get(fileId));
			
		}		
		service.updateAnswerDoc(id, fileId+".pdf");
		if(ext.toLowerCase().equals(".jpg")||ext.toLowerCase().equals(".png")){
			return fileName;
		}
		return fileId+".pdf";
	}
	//学生提交某次作业
	@RequestMapping(value="/student/homework/submit/{id}",method=RequestMethod.POST)
	public String submitHomework(
			@PathVariable("id")String id,
			@RequestParam("answer")String answer,
			@RequestParam("fileId")String fileId
			) {
			
		service.submitHomeWork(id, answer, fileId+".pdf");
		return "homework";
	}
	
	//老师提交批阅某次作业
	@RequestMapping(value="/teahcer/homework/doscore/{id}")
	public String scoreTheHomework(
			@PathVariable("id")String id,
			HomeWork work
			) throws IOException{
		service.scoredHomeWork(id, work);
		return "homework";
	}
	
	//老师开始批阅某次作业
	@RequestMapping(value="/teacher/homework/card/{id}")
	public String preDoscore(
			@PathVariable("id")String id,
			@AuthenticationPrincipal SysUser user,
			Model model){
		
		HomeWork bean = service.findById(id);
		
		String modelAnswer = bean.getModelAnswer().toUpperCase();
		String answer      = bean.getAnswer().toUpperCase();
	
		char[] modelChars  = modelAnswer.toCharArray();
		char[] answerChars = answer.toCharArray();
		
		int len = Math.min(modelChars.length, answerChars.length);
		
		int diff = Math.abs(modelChars.length-answer.length());
		
		int score = 0;
		for(int i=0;i<len;i++){
			if(modelChars[i]==answerChars[i])score++;
		}
					
		NoteChapter chp = chapterService.findById(bean.getChapter());		
		model.addAttribute("numText",chp.getAnwserNum());
		model.addAttribute("modelAnswers",modelAnswer);
		model.addAttribute("answer",answer);
		model.addAttribute("score",score+diff);
		model.addAttribute("hwid", bean.getId());
		model.addAttribute("url", "/homework/pdfview/answerdoc/"+bean.getId());
		return "pre-doscore-homework";
	
	}
	
	@RequestMapping("/forall/card/scored/{id}")
	public String allScored(
			@PathVariable("id")String id,
			Model model){
		
		HomeWork bean = service.findById(id);	
		model.addAttribute("bean", bean);
		model.addAttribute("url", "/homework/pdfview/answerdoc/"+bean.getId());
		model.addAttribute("modelUrl", "/notechapter/viewpdf/anwserFile/"+bean.getChapter());
		return "scored-homework";
	
	}
	
	
	@RequestMapping("/pdfview/answerdoc/{id}")
	public ResponseEntity<InputStreamResource> answerDoc(	
			@PathVariable("id") String id) throws IOException{
		HomeWork  hw = service.findById(id);
		if(hw==null){
			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
		}
		
		String pdfName = hw.getAnswerDoc();
		
		File file = new File(noteDir, pdfName.substring(0, pdfName.lastIndexOf('.'))+".pdf");
		if(!file.exists()){
			file= new File(noteDir,"no-pdf.pdf");
		}
		InputStream ins = Files.newInputStream(file.toPath(),
				StandardOpenOption.READ);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentLength(ins.available());
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		//headers.setContentDispositionFormData("attachment", "filename.pdf");
		InputStreamResource isr = new InputStreamResource(ins);
		return new ResponseEntity<>(isr, headers, HttpStatus.OK);
	}
	
	@Override
	public AbstractService<String,HomeWork> getService() {
		return service;
	}

	
}