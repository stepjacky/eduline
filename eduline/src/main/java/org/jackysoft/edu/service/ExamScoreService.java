package org.jackysoft.edu.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.Part;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.entity.ExamScore;
import org.jackysoft.edu.entity.GroupMember;
import org.jackysoft.edu.formbean.InYearScoreCard;
import org.jackysoft.edu.formbean.InYearScoreDetail;
import org.jackysoft.edu.formbean.ScoreInfo;
import org.jackysoft.edu.formbean.ScoreReport;
import org.jackysoft.edu.formbean.Semester;
import org.jackysoft.edu.formbean.SortedTotalScore;
import org.jackysoft.edu.formbean.StudentScoreCard;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.ExamScoreMapper;
import org.jackysoft.edu.service.base.AbstractSQLService;
import org.jackysoft.edu.service.base.PreResult;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Service
@Transactional("transactionManager")
public class ExamScoreService extends AbstractSQLService<String, ExamScore> implements ResourceLoaderAware {
	static final Logger logger = LoggerFactory.getLogger(ExamScoreService.class);
	@Autowired
	private ExamScoreMapper mapper;

	public List<ExamScore> reportCard(String user) {
		if (Strings.isNullOrEmpty(user))
			return null;
		return mapper.reportCard(user);
	}

	public List<Course> reportCourse(int jors, String user) {
		if (Strings.isNullOrEmpty(user))
			return null;
		return mapper.reportCourse(jors, user);
	}

	public InYearScoreCard findInYearGrade(int inyear, int page) {

		long cnt = mapper.findInYearExamInfoCount(inyear);
		if (page < 0 || page > cnt)
			page = 1;
		ExamScore info = mapper.findInYearExamInfo(inyear, page);
		if (info == null)
			return new InYearScoreCard();

		List<InYearScoreDetail> list = mapper.findInYearDetails(info);

		return new InYearScoreCard(info, list, page, cnt);
	}

	public Map.Entry<InYearScoreCard, ByteArrayOutputStream> downloadInYearGrade(int inyear, int page) {

		ByteArrayOutputStream bous = new ByteArrayOutputStream();

		InYearScoreCard card = findInYearGrade(inyear, page);

		Resource temp = loader.getResource("classpath:excel/inyearscore.xls");
		try {
			if (temp == null) {
				logger.error("resource in classpath:excel/inyearscore.xls  can not be load");
				throw new FileNotFoundException("resource in classpath:excels/secondtemp.xls  can not be load");

			}
			if (card.getScoreDetails() == null || card.getScoreDetails().isEmpty()) {
				Files.copy(temp.getFile().toPath(), bous);
				return new HashMap.SimpleEntry<>(card, bous);
			}

			InputStream fin = temp.getInputStream();

			// create a new org.apache.poi.poifs.filesystem.Filesystem
			POIFSFileSystem poifs = new POIFSFileSystem(fin);
			HSSFWorkbook wb = new HSSFWorkbook(poifs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow head = sheet.createRow(0);
			InYearScoreDetail hd = card.getScoreDetails().get(0);

			head.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("序号");
			head.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("学生");
			head.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("排名");
			head.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("总分");
			head.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("总均分");
			int i = 5;
			for (String c : hd.getCourseSet()) {
				head.createCell(i++, Cell.CELL_TYPE_STRING).setCellValue(c);
			}
			int r = 1;
			for (InYearScoreDetail di : card.getScoreDetails()) {

				HSSFRow row = sheet.createRow(r++);
				row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(r - 1);
				row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(di.getStudentName());
				row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(di.getTotalSorted() + "");
				row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(String.format("%.2f", di.getTotalScore()));
				row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue(String.format("%.2f", di.getAvgScore()));
				i = 5;
				for (String c : di.getScoreSet()) {
					row.createCell(i++, Cell.CELL_TYPE_STRING).setCellValue(c);
				}

			}
			wb.write(bous);
			wb.close();
			fin.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new HashMap.SimpleEntry<>(card, bous);
		}
		return new HashMap.SimpleEntry<>(card, bous);
	}

	public StudentScoreCard findStudentScoreCard(String user, int page) {
		if (page < 0)
			page = 0;
		StudentScoreCard bean;
		List<ExamScore> infos = mapper.findStudentExamInfo(user);
		logger.info(page+"");
		int pages = (infos != null && !infos.isEmpty()) ? infos.size() : 0;
		logger.info(pages+"");
		if(page<0||page>=pages) page=0;
		ExamScore info = (infos != null && !infos.isEmpty()) ? infos.get(page) : null;
		logger.info(infos+"");
		if (info == null)
			return new StudentScoreCard();
		
		bean = new StudentScoreCard(info, page);

		SortedTotalScore sts = mapper.findExamTotalDetail(info);
		logger.info(sts+"");
		if (sts == null)
			return bean;
		bean.setTotalScore(sts.getTotalScore());
		bean.setAvgTotalScore(sts.getAvgScore());
		bean.setTotalSorted(sts.getTotalSorted());
		bean.setDetails(Lists.newArrayList(mapper.findExamDetail(info)));
		return bean;
	}

	public ScoreReport findScoreReport(String user, int page) {
		ScoreReport bean = new ScoreReport();
		List<ExamScore> infos = mapper.findStudentExamInfo(user);
		ExamScore info = (infos != null && !infos.isEmpty()) ? infos.get(page) : null;
		if (info == null)
			return bean;
		bean.setTitle(getScoreReportTitle(info));
		bean.setDetails(findScoreInfo(info));
		return bean;
	}

	public ScoreReport findSingleScoreReport(ExamScore info) {
		ScoreReport bean = new ScoreReport();
		ExamScore tinfo = mapper.findSingleExamScore(info.getInyear(), info.getGrade(), info.getSemester(),
				info.getMonthly(), info.getStudent());
		bean.setTitle(getScoreReportTitle(tinfo));
		bean.setDetails(findScoreInfo(tinfo));
		return bean;
	}

	String getScoreReportTitle(ExamScore info) {
		if (info == null)
			return "no user";

		int start = info.getInyear() + info.getGrade() - 7;
		String yearinfo = String.format("%s-%s年度 ", start, start + 1);
		return String.format("%s 剑桥国际中心 %s 成绩单 姓名:%s", yearinfo, info.getMonthlyName(), info.getStudentName());
	}

	public List<ScoreInfo> findScoreInfo(ExamScore info) {
		List<ScoreInfo> details = mapper.findScoreInfo(info);
		List<ScoreInfo> fdetails = details.stream().filter(s -> Float.parseFloat(s.getScoreValue()) > 0)
				.collect(Collectors.toList());
		return fdetails;
	}

	public List<ExamScore> findGraduatesExamInfo(int inyear) {

		return mapper.findGraduatesExamInfo(inyear);

	}

	/**
	 * 找到某次考试有成绩的学生
	 */
	public List<ExamScore> findExamScoredStudent(GroupMember gfeature, Semester s, int monthly) {
		ExamScore param = new ExamScore();
		param.setInyear(gfeature.getInyear());
		param.setGrade(gfeature.getGrade());
		param.setSemester(s.getSemester());
		param.setMonthly(monthly);
		param.setCourse(gfeature.getCourse());
		List<ExamScore> beans = mapper.findScoredStudent(param);
		for (ExamScore es : beans) {
			es.setCourseType(gfeature.getCourseType() + "");
		}
		return beans;
	}

	public List<SortedTotalScore> findGradedSeries(ExamScore es) {
		return mapper.findGradedSeries(es);
	}

	///// *************************************************************

	//// **************************************************************

	@Override
	@PreAuthorize("hasAnyRole('ROLE_TEACHER','ROLE_ADMIN')")
	public PreResult save(ExamScore t) {
		if (t.getGrade() >= 10)
			t.setJors(1);
		return super.save(t);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public void update(ExamScore t) {
		super.update(t);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public void update(QueryBuilder qc) {
		super.update(qc);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public void remove(QueryBuilder qc) {
		super.remove(qc);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public void removeById(String s) {
		super.removeById(s);
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_TEACHER','ROLE_ADMIN')")
	public void updatePartial(String id, String props) {
		super.updatePartial(id, props);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updateScoreState(int year, int grade, int semester, int monthly) {
		logger.info(String.format("year %d,grade %d ,semester %d,monthly %d 成绩重排中", year, grade, semester, monthly));
		mapper.updateScoreState(year, grade, semester, monthly);
		logger.info(String.format("year %d,grade %d,semester %d,monthly %d 重排完成", year, grade, semester, monthly));
	}

	private @Autowired CourseService courseService;
	private @Autowired GradeService gradeService;
	private @Autowired SysUserService userService;
	protected static final Map<Integer, String> mmap = new HashMap<>();
	static {
		mmap.put(0, "第一次月考");
		mmap.put(1, "期中考试");
		mmap.put(2, "第二次月考");
		mmap.put(3, "期末考试");
	}

	@Override
	public void upload(ExamScore bean, Part part) {
		List<ExamScore> datas = new ArrayList<>();
		try {
			logger.info("准备处理文件 " + part.getSubmittedFileName());
			Workbook wb = WorkbookFactory.create(part.getInputStream());
		
			// 年级-学期-月考-学生-课程
			for (int s = 0; s < wb.getNumberOfSheets(); s++) {
				Sheet sheet = wb.getSheetAt(s);
				logger.info("准备处理表 {} ",sheet.getSheetName());
				Iterator<Row> rows = sheet.rowIterator();

				if (!rows.hasNext()) {
					logger.info("没有成绩表");
					continue;
				}

				String[] tags = sheet.getSheetName().split("-");

				int grade = Integer.parseInt(tags[0]);
				int jors = grade <= 9 ? 0 : 1;
				String gradeName = gradeService.findById(grade).getName();
				int semester = Integer.parseInt(tags[1]);
				int monthly = Integer.parseInt(tags[2]);
				String monthlyName = mmap.get(monthly);

				Row headrow = rows.next();
				Iterator<Cell> cells = headrow.cellIterator();
				Cell cell = cells.next();// 略过第一个cell
				List<Integer> courses = Lists.newArrayList();
				List<String> conames = Lists.newArrayList();
                List<Integer> courseTypes = new ArrayList<>(); 
				
				while (cells.hasNext()) {
					cell = cells.next();
					Integer course = Double.valueOf(cell.getNumericCellValue()).intValue();
					Course cb = courseService.findById(course);
					if(cb==null){
						logger.info("{} is not a course ",course);
						continue;
					}
					courses.add(course);					
					conames.add(cb.getName());
					courseTypes.add(cb.getCtype());

				}
				logger.info("数据 {} 总共有:{} 行 " ,sheet.getSheetName(), sheet.getLastRowNum());
				while (rows.hasNext()) {
					Row row = rows.next();

					cells = row.cellIterator();
					cell = cells.next();
					String studentName = StringUtils.trimAllWhitespace(cell.getStringCellValue());
					String student = userService.retriveCachedUsername(studentName);
					if (Strings.isNullOrEmpty(student)){
						logger.info(studentName+" does not exists in system");
						continue;
					}
					
					Iterator<Integer> courseItr = courses.iterator();
					Iterator<String> cnameItr = conames.iterator();
                    Iterator<Integer> ctypeItr = courseTypes.iterator();
					while (cells.hasNext() && courseItr.hasNext() && cnameItr.hasNext() && ctypeItr.hasNext()) {
						cell = cells.next();
						logger.info("文件:" + part.getSubmittedFileName() + " ,表格:" + sheet.getSheetName() + ",行:"
								+ (row.getRowNum() + 1) + ",列:" + cell + ",列类型:" + cell.getCellType());

						int courseNo = courseItr.next();
						String courseName = cnameItr.next();
						int ctype = ctypeItr.next();
						double scoreValue = StringUtils.getCellNumberValue(cell);
						int inyear = Integer.parseInt(student.substring(2, 4)) + 2000;
						ExamScore data = new ExamScore();
						data.setId(UUID.randomUUID().toString());
						data.setGrade(grade);
						data.setGradeName(gradeName);
						data.setJors(jors);
						data.setSemester(semester);
						data.setMonthly(monthly);
						data.setMonthlyName(monthlyName);
						data.setCourse(courseNo);
						data.setCourseName(courseName);
						data.setCourseType(ctype+"");
						data.setInyear(inyear);
						data.setStudent(student);
						data.setStudentName(studentName);
						data.setScoreValue(new Double(scoreValue).floatValue());
						data.setGroupId("system-group");
						data.setRemark("");
						datas.add(data);
					}

				}
				

			}
			
			this.saveAll(datas);
			logger.info("有效成绩导入:{}",datas.size());
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			logger.error("{}",e);
		}

	}

	@Override
	public AbstractMapper<String, ExamScore> getMapper() {
		return mapper;
	}

	ResourceLoader loader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		loader = resourceLoader;
	}

}
