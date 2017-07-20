package org.jackysoft.edu.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.lang.StringUtils;
import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.entity.ExamScore;
import org.jackysoft.edu.entity.GroupMember;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.extend.jasperreport.JRRawDataSource;
import org.jackysoft.edu.formbean.CourseScore;
import org.jackysoft.edu.formbean.InYearScoreCard;
import org.jackysoft.edu.formbean.InYearScoreDetail;
import org.jackysoft.edu.formbean.Monthly;
import org.jackysoft.edu.formbean.ScoreCard;
import org.jackysoft.edu.formbean.ScoreReport;
import org.jackysoft.edu.formbean.Semester;
import org.jackysoft.edu.formbean.SortedTotalScore;
import org.jackysoft.edu.formbean.StudentScoreCard;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.CourseInGradeService;
import org.jackysoft.edu.service.ExamScoreService;
import org.jackysoft.edu.service.GroupMemberService;
import org.jackysoft.edu.service.SysUserService;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.query.QueryParser;
import org.jackysoft.utils.DateUtils;
import org.jackysoft.utils.SchoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.google.common.base.Strings;

import net.sf.jasperreports.engine.JRDataSource;

@Controller
@RequestMapping("/examscore")
public class ExamScoreController extends AbstractController<String, ExamScore> {

	@Autowired
	protected ExamScoreService service;

	@Autowired
	protected SysUserService userService;

	@Autowired
	protected GroupMemberService groupService;

	@RequestMapping("/scoreinput")
	public ModelAndView scoreinput(@RequestParam("groupId") String groupId) {
		ModelAndView mav = new ModelAndView("scoreinput");

		Semester s = SchoolUtils.getSemester(0);

		GroupMember groupFeature = groupService.findGroupFeature(groupId);
		mav.addObject("semester", s.getSemester());
		mav.addObject("semesterName", s.getSemesterName());
		mav.addObject("groupId", groupId);
		mav.addObject("bean", groupFeature);
		return mav;
	}

	@RequestMapping("/scoredpanel/{groupId}/{monthly}")
	public ModelAndView getExamScoredInfo(@PathVariable("groupId") String groupId,
			@PathVariable("monthly") int monthly) {
		ModelAndView mav = new ModelAndView("scoredpanel");
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("groupId`" + groupId);
		// 取出该班级所有学生
		List<GroupMember> members = groupService.find4ScorePanel(groupId);

		if (members == null || members.isEmpty()) {
			mav.addObject("message", "参数错误,没有足够的群成员可以操作");
			return new ModelAndView("error");
		}

		// 该班级成绩特征
		GroupMember groupFeature = groupService.findGroupFeature(groupId);
		List<ExamScore> scores = service.findExamScoredStudent(groupFeature, SchoolUtils.getSemester(0), monthly);

		List<GroupMember> unscores = members.stream().filter(sc -> {
			for (ExamScore es : scores) {
				if (sc.getStudent().equalsIgnoreCase(es.getStudent())) {
					return false;
				}
				continue;
			}

			return true;

		})

				.collect(Collectors.toList());
		mav.addObject("unscores", unscores);

		mav.addObject("scores", scores);
		if (members.size() > 0) {
			mav.addObject("bean", members.get(0));
		}

		return mav;
	}

	@RequestMapping(value = "/query/{page}")
	@Override
	public ModelAndView query(@RequestParam(value = "query", required = false, defaultValue = "") String query,
			@RequestParam(value = "group", required = false, defaultValue = "") String group,
			@RequestParam(value = "order", required = false, defaultValue = "") String order,
			@PathVariable("page") int page,
			@RequestParam(value = "offset", required = false, defaultValue = "10") int offset,
			@RequestParam(value = "ajax", required = false, defaultValue = "true") boolean ajax) {
		ModelAndView mav = new ModelAndView("query");
		String ordern = "`grade` desc,`semester` desc,`monthly` desc,`course` desc";
		Pager<ExamScore> pager = queryPager(query, group, ordern, page, offset, ajax);
		mav.addObject("pager", pager);
		mav.addObject("qstring", String.format("query=%s&group=%s&order=%s", query, group, order));
		mav.addObject("ajax", ajax);
		return mav;
	}


	@RequestMapping(value = "/score/{page}")
	public ModelAndView scoreindex(@PathVariable("page") int page) {
		ModelAndView mav = new ModelAndView("scoreindex");
		Authentication author = SecurityContextHolder.getContext().getAuthentication();
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("teacher`" + author.getName());
		qc.setGroupBy("groupId");
		Pager<GroupMember> pager = groupService.findPager(qc, page, 10);
		mav.addObject("pager", pager);
		return mav;
	}

	@RequestMapping({ "/student/score/{user}/{page}" })
	public ModelAndView studentindex(@PathVariable("user") String user, @PathVariable("page") int page) {

		ModelAndView mav = new ModelAndView("studentindex");
		SysUser ou = userService.findById(user);
		StudentScoreCard bean = service.findStudentScoreCard(ou.getUsername(), page);
		mav.addObject("student", ou);
		mav.addObject("bean", bean);

		return mav;
	}

	@RequestMapping(value = "/graduates/inyear", method = RequestMethod.GET)
	public ModelAndView graduatesinyear() {
		ModelAndView mav = new ModelAndView("inyearinfo");

		return mav;
	}

	@RequestMapping(value = "/graduates/examinfo/{inyear}", method = RequestMethod.GET)
	public ModelAndView graduatesExamInfo(@PathVariable("inyear") int inyear) {
		ModelAndView mav = new ModelAndView("graduatesinfo");
		List<ExamScore> infos = service.findGraduatesExamInfo(inyear);
		mav.addObject("infos", infos);
		return mav;
	}

	@RequestMapping(value = "/score/report/{user}/{page}/scorereport", method = RequestMethod.GET)
	public ModelAndView scorereport(@PathVariable("user") String user, @PathVariable("page") int page) {
		ModelAndView mav = new ModelAndView("scorereport");
		ScoreReport scd = service.findScoreReport(user, page);
		mav.addObject("card", scd);
		return mav;
	}

	@Autowired
	@Named("scorereport")
	protected JasperReportsPdfView scorereport;

	@RequestMapping(value = "/score/single/report/scorereport", method = RequestMethod.GET)
	public ModelAndView scoreSingleReport(@RequestParam("user") String user, @RequestParam("grade") int grade,
			@RequestParam("semester") int semester, @RequestParam("monthly") int monthly) {

		ModelAndView mav = new ModelAndView("scorereport");
		if (Strings.isNullOrEmpty(user))
			return mav;
		String ly = user.substring(2, 4);
		int yl = 0;
		try {
			yl = Integer.parseInt(ly);
		} catch (Exception e) {
			return mav;
		}
		ExamScore info = new ExamScore();
		info.setStudent(user);
		info.setInyear(yl + 2000);
		info.setGrade(grade);
		info.setSemester(semester);
		info.setMonthly(monthly);
		ScoreReport scd = service.findSingleScoreReport(info);
		mav.addObject("card", scd);
		// if(scd==null) return mav;
		// JRDataSource ds = new JRRawDataSource<ScoreReport>(scd);

		// mav.addObject("datasource", ds);

		return mav;
	}

	@Autowired
	CourseInGradeService cigService;

	@RequestMapping("/gradeuser")
	public ModelAndView gradeuser(@RequestParam("inyear") int inyear,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		ModelAndView mav = new ModelAndView("gradeuser");
		InYearScoreCard card = gradeInyarScore(inyear, page);
		if (card.getScoreDetails() != null && !card.getScoreDetails().isEmpty()) {
			mav.addObject("courseHead", card.getScoreDetails().get(0).getCourseHtml());
		} else {
			mav.addObject("courseHead", "");
		}

		mav.addObject("examinfo", card);

		return mav;
	}

	@RequestMapping("/gradeuser/excel/{inyear}/{page}")
	public ResponseEntity<InputStreamResource> gradeuserExcel(@PathVariable("inyear") int inyear,
			@PathVariable("page") int page) throws UnsupportedEncodingException {

		Map.Entry<InYearScoreCard, ByteArrayOutputStream> entry = service.downloadInYearGrade(inyear, page);
		ByteArrayOutputStream bous = entry.getValue();
		byte[] data = bous.toByteArray();
		InYearScoreCard card = entry.getKey();
		ExamScore info = card.getScoreInfo();
		String fileName = String.format("%s届%s%s学期%s成绩总分表.xls", info.getInyear(), info.getGradeName(),
				info.getSemester() == 0 ? "上" : "下", info.getMonthlyName());
		// fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		// fileName = new String(fileName.getBytes("GBK"),"GBK");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentLength(data.length);

		headers.setContentDispositionFormData("attachment", fileName);
		InputStreamResource isr = new InputStreamResource(new ByteArrayInputStream(data));
		return new ResponseEntity<InputStreamResource>(isr, headers, HttpStatus.OK);
	}

	InYearScoreCard gradeInyarScore(int inyear, int page) {
		InYearScoreCard obeans = service.findInYearGrade(inyear, page);
		List<InYearScoreDetail> beans;
		List<InYearScoreDetail> noscore = new ArrayList<>();

		beans = obeans.getScoreDetails().stream().filter(b -> b.getTotalSorted() != 0).collect(Collectors.toList());
		noscore = obeans.getScoreDetails().stream().filter(b -> b.getTotalSorted() == 0).collect(Collectors.toList());
		beans.addAll(noscore);
		return new InYearScoreCard(obeans.getScoreInfo(), beans, obeans.getPage(), obeans.getCounts());
	}

	@RequestMapping("/gradeexport")
	public ModelAndView gradeexport() {
		ModelAndView mav = new ModelAndView("");

		return mav;
	}

	@RequestMapping("/series/avgtotal/{inyear}/{grade}/{semester}/{monthly}")
	public ModelAndView avgseries(@PathVariable("inyear") int inyear, @PathVariable("grade") int grade,
			@PathVariable("semester") int semester, @PathVariable("monthly") int monthly

	) {
		ModelAndView mav = new ModelAndView("avgseries");
		ExamScore es = new ExamScore();
		es.setInyear(inyear);
		es.setGrade(grade);
		es.setSemester(semester);
		es.setMonthly(monthly);
		List<SortedTotalScore> datas = service.findGradedSeries(es);

		List<String> names = new ArrayList<>();
		List<String> scores = new ArrayList<>();
		for (SortedTotalScore d : datas) {
			names.add(d.getStudentName());
			scores.add(d.getAvgScore() + "");
		}
		mav.addObject("names", names);
		mav.addObject("scores", scores);
		mav.addObject("title", String.format("%d届%d年级%s%s", es.getInyear(), es.getGrade(),
				new Semester(es.getSemester()).getSemesterName(), new Monthly(es.getMonthly()).getMonthlyName()));
		return mav;
	}

	@Autowired
	@Named("scorepaper")
	protected JasperReportsPdfView scorepaper;

	@RequestMapping(value = "/reporter/scorepaper.jasper", method = RequestMethod.GET)
	public ModelAndView reporter(@RequestParam("query") String query) {
		Map<String, Object> model = new HashMap<>();
		JRDataSource ds = this.retriveJRDataSource(query);
		model.put("datasource", ds);
		ModelAndView mav = new ModelAndView(scorepaper, model);
		return mav;
	}

	@Override
	JRDataSource retriveJRDataSource(String query) {
		QueryParser qb = new QueryParser(query);
		String sid = qb.findValue("student");
		String sjors = qb.findValue("jors");
		String sstyle = qb.findValue("style");
		if (Strings.isNullOrEmpty(sid))
			return new JRRawDataSource<ScoreCard>(new ScoreCard());
		SysUser user = userService.findById(sid);
		if (user == null)
			return new JRRawDataSource<ScoreCard>(new ScoreCard());
		int grade = user.getGrade();
		int jors = Strings.isNullOrEmpty(sjors) ? (grade <= 9 ? 0 : 1) : Integer.valueOf(sjors);
		int style = Strings.isNullOrEmpty(sstyle) ? 0 : Integer.parseInt(sstyle);
		logger.info(style+" is coutry style ");
		List<ExamScore> items = service.reportCard(user.getUsername());
		List<Course> courses = service.reportCourse(jors, user.getUsername());
		ScoreCard sc = new ScoreCard();
		String[] levels = { "International Junior High & IGCSE Courses (国际初中/英国IGCSE)", "IGCSE and GCE A-Levels" };
		String grands = String.format(
				"A*=90-100%% %s; A=80-89%% %s; B=70-79%% %s; C=60-69%%  %s;  D=50-59%% %s;	E=40-49%% %s",
				style == 0 ? "" : "(GPA 4.0)", style == 0 ? "" : "(GPA 3.5)", style == 0 ? "" : "(GPA 3.0)",
				style == 0 ? "" : "(GPA 2.5)", style == 0 ? "" : "(GPA 2.0)", style == 0 ? "" : "(GPA 1.5)");
		sc.setName(
				String.format("%s %s", user.getFirstName().toUpperCase(), StringUtils.capitalize(user.getLastName())));
		sc.setLocalName(user.getNickname());

		LocalDateTime ldt = DateUtils.fromMillis(user.getBirthday());
		DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.ENGLISH);

		sc.setBob(df.format(ldt));

		df = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.CHINA);
		sc.setLocalBob(df.format(ldt));

		sc.setGender(StringUtils.capitalize(user.getSex() == 0 ? "female" : "male"));
		sc.setLocalGender(user.getSex() == 0 ? "女" : "男");
		sc.setLevel(levels[jors]);
		String groupa = "", groupb = "", groupc = "";
		Map<Integer, List<Float>> scoresByGrade = new HashMap<>(); 
		if (jors == 0) {
			groupa = String.format("Year 7 （Junior High）\n 7年级（国际初中）\n  Sept %d- July %d", user.beginYear(),
					user.beginYear() + 1);
			groupb = String.format("Year 8 （Junior High）\n 8年级（国际初中）\n  Sept %d- July %d", user.beginYear() + 1,
					user.beginYear() + 2);
			groupc = String.format("Year 9 （IGCSE）\n 9年级（英国IGCSE） \n  Sept %d- July %d", user.beginYear() + 2,
					user.beginYear() + 3);
			scoresByGrade.put(7, new ArrayList<>());
			scoresByGrade.put(8, new ArrayList<>());
			scoresByGrade.put(9, new ArrayList<>());
			

		} else if (jors == 1) {

			groupa = String.format("Year 10 (IGCSE) \n Sept %d- July %d \n   10年级（英国IGCSE）", user.beginYear() + 3,
					user.beginYear() + 4);
			groupb = String.format("Year 11 (AS Level) \n Sept %d- July %d \n  11年级（英国 AS Level）", user.beginYear() + 4,
					user.beginYear() + 5);
			groupc = String.format("Year 12 (A Level) \n Sept %d- July %d \n  12年级（英国A Level）", user.beginYear() + 5,
					user.beginYear() + 6);
			scoresByGrade.put(10, new ArrayList<>());
			scoresByGrade.put(11, new ArrayList<>());
			scoresByGrade.put(12, new ArrayList<>());
		}
		/**
		 * 7 8 9 0 1 0 1 0 1 a b c d e f
		 * 
		 **/
		
		
		StringBuilder gpaInfo = new StringBuilder();
		
		if (jors == 0) {

			for (Course c : courses) {
				CourseScore cs = new CourseScore();
				cs.setStyle(style);
				cs.setCourseName(c.getName());
				cs.setGroupNamea(groupa);
				cs.setGroupNameb(groupb);
				cs.setGroupNamec(groupc);
				items.stream().filter(f -> f.getCourse() == c.getId() && f.getEnabled() == 1).forEach(f -> {

					if (f.getGrade() == 7) {
						
						if (f.getSemester() == 0) {
							cs.setA(f.getScoreValue());
							
						}
						if (f.getSemester() == 1) {
							cs.setB(f.getScoreValue());
						}
						scoresByGrade.get(7).add(cs.gpaScore(Math.round(f.getScoreValue())));
						
					}
					if (f.getGrade() == 8) {
						if (f.getSemester() == 0) {
							cs.setC(f.getScoreValue());

						}
						if (f.getSemester() == 1) {
							cs.setD(f.getScoreValue());
						}
						scoresByGrade.get(8).add(cs.gpaScore(Math.round(f.getScoreValue())));
					}
					if (f.getGrade() == 9) {
						if (f.getSemester() == 0) {
							cs.setE(f.getScoreValue());

						}
						if (f.getSemester() == 1) {
							cs.setF(f.getScoreValue());
						}
						scoresByGrade.get(9).add(cs.gpaScore(Math.round(f.getScoreValue())));
					}

				});

				if (cs.hasAny())
					sc.getScores().add(cs);
			}
		} else {
			for (Course c : courses) {

				CourseScore cs = new CourseScore();
				cs.setStyle(style);
				cs.setCourseName(c.getName());
				cs.setGroupNamea(groupa);
				cs.setGroupNameb(groupb);
				cs.setGroupNamec(groupc);
				items.stream().filter(f -> f.getCourse() == c.getId() && f.getEnabled() == 1).forEach(f -> {

					if (f.getGrade() == 10) {
						if (f.getSemester() == 0) {
							cs.setA(f.getScoreValue());

						}
						if (f.getSemester() == 1) {
							cs.setB(f.getScoreValue());
						}
						scoresByGrade.get(10).add(cs.gpaScore(Math.round(f.getScoreValue())));
					}
					if (f.getGrade() == 11) {
						if (f.getSemester() == 0) {
							cs.setC(f.getScoreValue());

						}
						if (f.getSemester() == 1) {
							cs.setD(f.getScoreValue());
						}
						scoresByGrade.get(11).add(cs.gpaScore(Math.round(f.getScoreValue())));
					}
					if (f.getGrade() == 12) {
						if (f.getSemester() == 0) {
							cs.setE(f.getScoreValue());

						}
						if (f.getSemester() == 1) {
							cs.setF(f.getScoreValue());
						}
						scoresByGrade.get(12).add(cs.gpaScore(Math.round(f.getScoreValue())));
					}
					

				});

				if (cs.hasAny())
					sc.getScores().add(cs);
				
			}
		}
		sc.setGrandSystem(grands);
		scoresByGrade.forEach((k,v)->{
			int size = 0;
			float fv = 0;
			for(Float val:v){
				if(val>0){
					fv+=val;
					size++;
				}	
			}
			
			
			gpaInfo.append(String.format("Year %d GPA %.1f", k, size!=0 ?fv/size:0)).append("    ");
			
		});
		sc.setGpaInfo(style==0?"":gpaInfo.toString());
		JRDataSource ds = new JRRawDataSource<ScoreCard>(sc);

		return ds;
	}

	@RequestMapping(value = "/score/print")
	public ModelAndView print(@AuthenticationPrincipal Authentication user) {

		// if(((SysUser)user).getUserType()!=2) return new
		// ModelAndView("redirect:/error");
		return new ModelAndView("print");
	}

	@RequestMapping(value = "/score/resorted/{year}/{grade}/{semester}/{monthly}", method = RequestMethod.GET)
	public ModelAndView resulted(@PathVariable("year") int year, @PathVariable("grade") int grade,
			@PathVariable("semester") int semester, @PathVariable("monthly") int monthly) {

		service.updateScoreState(year, grade, semester, monthly);
		return new ModelAndView("index");
	}

	@Override
	protected void proceedUpload(ExamScore bean, Part part) throws Exception {
		service.upload(bean, part);
	}

	@Override
	public AbstractService<String, ExamScore> getService() {
		return service;
	}

}