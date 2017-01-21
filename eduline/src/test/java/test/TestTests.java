package test;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.imgscalr.AsyncScalr;
import org.imgscalr.Scalr;
import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.entity.ExamHeader;
import org.jackysoft.edu.entity.Grade;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.utils.DateUtils;
import org.jackysoft.utils.StreamUtils;
import org.jackysoft.utils.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;


public class TestTests {
	static final Log logger = LogFactory.getLog(TestTests.class);
	static PasswordEncoder encoder = new BCryptPasswordEncoder();

	static Connection conn = null;

	@BeforeClass
	public static void before() throws SQLException {
		String url = "jdbc:mysql://123.57.6.254:3306/jianqiao?useUnicode=true&characterEncoding=utf8";
		String uid = "root";
		String pwd = "51jianqiao*()";
		if (DbUtils.loadDriver("com.mysql.jdbc.Driver")) {
			conn = DriverManager.getConnection(url, uid, pwd);

		}
	}

	@AfterClass
	public static void after() throws SQLException {
		DbUtils.close(conn);
	}

	@Test
	public void testMain() throws Exception {
		//genUserList(new File("F:/测试文档/剑桥一期需求-2/师生通讯录/7D学号样表 - 副本.xls"));
	    this.updateXlsScore("2011届成绩-补录.xls");
	}

	




	public void test() {
		String head = "经济,生物,技术,思品,数学,商务,音乐,英语,美术,中文,化学,物理";
		String line = "0,   98,  0,   0,  91,  94,  0,   89 , 0,  90,  95,  91";
		Map<String, String> maps = Maps.newTreeMap(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}

		});
		List<String> ths = Splitter.on(',').splitToList(head);
		List<String> tds = Splitter.on(',').splitToList(line);

		for (int i = 0; i < ths.size(); i++) {
			maps.put(ths.get(i), tds.get(i));
		}
		logger.info(maps.keySet());
		logger.info(maps.values());
		head = "中文,化学,物理,经济,生物,技术,思品,数学,商务,音乐,英语,美术";
		line = "4,14,5,15,6,16,9,2,20,12,3,13";
		maps = Maps.newTreeMap(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}

		});
		ths = Splitter.on(',').splitToList(head);
		tds = Splitter.on(',').splitToList(line);

		for (int i = 0; i < ths.size(); i++) {
			maps.put(ths.get(i), tds.get(i));
		}
		logger.info(maps.keySet());
		logger.info(maps.values());

	}

	public void addExamHeader(ExamHeader head) throws SQLException {
		String sql = "INSERT INTO jq_examheader(id,inyear,grade,semester,monthly,course) VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE course=VALUES(`course`)";

		QueryRunner runner = new QueryRunner();

		/*
		if (runner.update(conn, sql, head.getId(), head.getInyear(), head.getGrade(), head.getSemester(),
				head.getMonthly(), head.getCourse()) > 0) {
			logger.info("add examheader successfully");
		}*/
	}

	public void updateXlsScore(String fileName) throws Exception {

		Map<Integer, String> cmap = getCourseMap();

		Map<Integer, String> gmap = getGradeMap();

		Map<String, String> umap = getUsersMap();

		Map<Integer, String> mmap = Maps.newHashMap();

		mmap.put(0, "第一次月考");
		mmap.put(1, "期中考试");
		mmap.put(2, "第二次月考");
		mmap.put(3, "期末考试");

		File base = new File("F:\\测试文档\\剑桥一期需求-2\\学生成绩整理");
		
		FileWriter fw = new FileWriter(new File("J:\\sqls2.sql"));
		StringWriter sw = new StringWriter();
		File tf = new File(base,fileName);
		genscore(cmap, gmap, mmap, umap, tf, sw);
		

		fw.write(sw.toString());
		fw.close();

	}

	void genscore(Map<Integer, String> cmap, Map<Integer, String> gmap, Map<Integer, String> mmap,
			Map<String, String> umap, File path, Writer writer) throws Exception {

		logger.info("准备处理文件 " + path.getAbsolutePath());
		Workbook wb = WorkbookFactory.create(path);
		String sqlprefix = "insert into jq_examscore(" + "id,grade,gradeName,jors,semester,"
				+ "monthly,monthlyName,course,inyear," + "courseName,student,studentName,scoreValue)" + " values";
		String sqlpostfix = " on duplicate key update scoreValue=values(scoreValue);\n";
		// 年级-学期-月考-学生-课程
		for (int s = 0; s < wb.getNumberOfSheets(); s++) {
			Sheet sheet = wb.getSheetAt(s);
			Iterator<Row> rows = sheet.rowIterator();

			if (!rows.hasNext()) {
				logger.info("没有成绩表");
				continue;
			}

			String[] tags = sheet.getSheetName().split("-");

			int grade = Integer.parseInt(tags[0]);
			int jors = grade <= 9 ? 0 : 1;
			String gradeName = gmap.get(grade);
			int semester = Integer.parseInt(tags[1]);
			int monthly = Integer.parseInt(tags[2]);
			String monthlyName = mmap.get(monthly);

			Row headrow = rows.next();
			int cnum = headrow.getLastCellNum();
			
			Iterator<Cell> cells = headrow.cellIterator();
			Cell cell = cells.next();// 略过第一个cell
			List<Integer> courses = Lists.newArrayList();
			List<String> conames = Lists.newArrayList();

			while (cells.hasNext()) {
				cell = cells.next();
				Integer course = Double.valueOf(cell.getNumericCellValue()).intValue();
				courses.add(course);
				conames.add(cmap.get(course));
				
			}
			logger.info("数据行总共有:" + sheet.getLastRowNum());
			while (rows.hasNext()) {
				Row row = rows.next();

				cells = row.cellIterator();
				cell = cells.next();
				String studentName = StringUtils.trimAllWhitespace(cell.getStringCellValue());
				String student = umap.get(studentName);
				if (student == null)
					continue;
				Iterator<Integer> courseItr = courses.iterator();
				Iterator<String> cnameItr = conames.iterator();

				while (cells.hasNext() && courseItr.hasNext() && cnameItr.hasNext()) {
					cell = cells.next();
					logger.info("文件:" + path.getName() + " ,表格:" + sheet.getSheetName() + ",行:" + (row.getRowNum() + 1)
							+ ",列:" + cell + ",列类型:" + cell.getCellType());

					int courseNo = courseItr.next();
					String courseName = cnameItr.next();
					double scoreValue = StringUtils.getCellNumberValue(cell);
					int inyear = Integer.parseInt(student.substring(2, 4)) + 2000;
					String sql = String.format("('%s',%d,'%s','%d',%d,%d,'%s',%d,%d,'%s','%s','%s',%f)",
							UUID.randomUUID().toString(), grade, gradeName, jors, semester, monthly, monthlyName,
							courseNo, inyear, courseName, student, studentName, scoreValue);
					String sqs = sqlprefix.concat(sql).concat(sqlpostfix);
					writer.write(sqs);
					logger.info(sql);
				}

			}

		}

	}

	Map<Integer, String> getCourseMap() throws Exception {
		Map<Integer, String> map = Maps.newHashMap();
		String sql = "select * from jq_course";
		QueryRunner runner = new QueryRunner();
		BeanListHandler<Course> bh = new BeanListHandler<Course>(Course.class);
		runner.query(conn, sql, bh).stream().forEach(c -> {
			map.put(c.getId(), c.getName());
		});

		return map;
	}

	Map<Integer, String> getGradeMap() throws Exception {

		Map<Integer, String> map = Maps.newHashMap();
		String sql = "select * from jq_grade";
		QueryRunner runner = new QueryRunner();
		BeanListHandler<Grade> bh = new BeanListHandler<Grade>(Grade.class);
		runner.query(conn, sql, bh).stream().forEach(c -> {
			map.put(c.getId(), c.getName());
		});

		return map;
	}

	Map<String, String> getUsersMap() throws Exception {
		Map<String, String> map = Maps.newHashMap();
		String sql = "select * from jq_sysuser";
		QueryRunner runner = new QueryRunner();
		BeanListHandler<SysUser> bh = new BeanListHandler<SysUser>(SysUser.class);
		runner.query(conn, sql, bh).stream().forEach(c -> {
			map.put(StringUtils.trimAllWhitespace(c.getNickname()), c.getUsername());
		});

		return map;
	}

	
	
	
	void genUserAccount(Writer writer, File base)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		File[] files = base.listFiles();
		for (File path : files) {
			generateStudent(path, writer);
		}

	}
	
	
	PasswordEncoder pwdencoder = new BCryptPasswordEncoder();
    ObjectMapper json = new ObjectMapper();
	List<SysUser> genUserList(File path) throws EncryptedDocumentException, InvalidFormatException, IOException{
	Workbook wb = WorkbookFactory.create(path);
		List<SysUser> users = new ArrayList<>();
		for (int s = 0; s < wb.getNumberOfSheets(); s++) {
			Sheet sheet = wb.getSheetAt(s);
			Iterator<Row> rows = sheet.rowIterator();
			if (!rows.hasNext())
				continue;
			rows.next();// ignore head rows
			while (rows.hasNext()) {
				Row row = rows.next();
				if (row == null)
					continue;
				logger.debug("文件:" + path.getName() + " ,表格:" + sheet.getSheetName() + ",行:" + (row.getRowNum() + 1)
						+ ",列:" + row.getCell(5) + ",列类型:" + row.getCell(5).getCellType());
				SysUser user = new SysUser();
				String username = StringUtils.trimAllWhitespace(row.getCell(0).getStringCellValue());
				user.setUsername(username);
				String sex = row.getCell(5).getStringCellValue().equals("男") ? "1" : "0";
				user.setSex(Integer.parseInt(sex));
				
				
				String nickname = row.getCell(1).getStringCellValue();
				user.setNickname(nickname);
				
			    String bird = row.getCell(4).getStringCellValue().trim();
			    
				LocalDate ldt = LocalDate.parse(bird,DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				String surname = row.getCell(2).getStringCellValue();
				user.setSurname(surname);
				String givename = row.getCell(3).getStringCellValue();
				user.setGivename(givename);
				user.setBirthday(DateUtils.withMillis(ldt));
				users.add(user);
				
			}

		}
		wb.close();
		
		return users;
		
	}
	
	void generateStudent(File path, Writer writer)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String sqlprefix = "insert into jq_sysuser ( `password`, `nickname`, `userType`, `username`,`pyname`,"
				+ "`sex`, `birthday`, `children`) values";
		String sqlpostfix = "ON DUPLICATE KEY UPDATE sex=VALUES(`sex`), birthday=VALUES(`birthday`), nickname=VALUES(`nickname`),"
				+ "pyname=VALUES(`pyname`);\n";
		String sqltemp = "('%s','%s','%s','%s','%s','%s','%s','%s')";
		Workbook wb = WorkbookFactory.create(path);
		
		for (int s = 0; s < wb.getNumberOfSheets(); s++) {
			Sheet sheet = wb.getSheetAt(s);
			Iterator<Row> rows = sheet.rowIterator();
			if (!rows.hasNext())
				continue;
			rows.next();// ignore head rows
			while (rows.hasNext()) {
				Row row = rows.next();
				if (row == null)
					continue;
				logger.info("文件:" + path.getName() + " ,表格:" + sheet.getSheetName() + ",行:" + (row.getRowNum() + 1)
						+ ",列:" + row.getCell(5) + ",列类型:" + row.getCell(5).getCellType());
				String nickname = StringUtils.trimAllWhitespace(row.getCell(0).getStringCellValue());
				String sex = row.getCell(1).getStringCellValue().equals("男") ? "1" : "0";
				double bird = row.getCell(5).getNumericCellValue();
				String username = row.getCell(6).getStringCellValue();
				DecimalFormat decimalFormat = new DecimalFormat("#");
				String ns = decimalFormat.format(bird);
				LocalDate ldt = LocalDate.parse(ns.substring(0, 4) + "-" + ns.substring(4, 6) + "-" + ns.substring(6));

				logger.info(ns);
				String sql = sqlprefix.concat(String.format(sqltemp, pwdencoder.encode("123456"), nickname, "1",
						username, StringUtils.toPinyin(nickname), sex, DateUtils.withMillis(ldt), ""))
						.concat(sqlpostfix);
				writer.write(sql);
				logger.info(sql);
				sql = sqlprefix
						.concat(String.format(sqltemp, pwdencoder.encode("123456"), nickname + "的父亲", "3",
								username + "F", StringUtils.toPinyin(nickname) + " father", "1", "0", username))
						.concat(sqlpostfix);
				writer.write(sql);
				logger.info(sql);
				sql = sqlprefix
						.concat(String.format(sqltemp, pwdencoder.encode("123456"), nickname + "的母亲", "3",
								username + "M", StringUtils.toPinyin(nickname) + " mother", "0", "0", username))
						.concat(sqlpostfix);
				writer.write(sql);
				logger.info(sql);
			}

		}
		wb.close();
	}

	void updateStudent(File path, Writer writer)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String sqltemp = "update jq_sysuser set firstName='%s',lastName='%s',surname='%s',givename='%s' where username='%s';\n";
		Workbook wb = WorkbookFactory.create(path);
		for (int s = 0; s < wb.getNumberOfSheets(); s++) {
			Sheet sheet = wb.getSheetAt(s);
			Iterator<Row> rows = sheet.rowIterator();
			if (!rows.hasNext())
				continue;
			rows.next();// ignore head rows
			while (rows.hasNext()) {
				Row row = rows.next();
				if (row == null)
					continue;

				String username = StringUtils.trimAllWhitespace(row.getCell(0).getStringCellValue());
				String surname = StringUtils.trimAllWhitespace(row.getCell(3).getStringCellValue());
				String givename = StringUtils.trimAllWhitespace(row.getCell(4).getStringCellValue());
				String firstName = StringUtils.toPinyin(surname);
				String lastName = StringUtils.toPinyin(givename);

				String sql = String.format(sqltemp, firstName, lastName, surname, givename, username);

				writer.write(sql);
				logger.info(sql);

			}

		}
		wb.close();
	}

}
