package org.jackysoft.edu.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.entity.UserLogger;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.SysUserMapper;
import org.jackysoft.edu.service.base.AbstractSQLService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.utils.DateUtils;
import org.jackysoft.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;


@Service
@Transactional("transactionManager")
public class SysUserService extends AbstractSQLService<String, SysUser> implements
		UserDetailsService, AuthenticationProvider {

	
	static final Logger logger = LoggerFactory.getLogger(SysUserService.class);
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private SysUserMapper mapper;

	@Autowired
	private UserLoggerService loggerService;
	

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void upgradeGrade() {
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("userType`1");
		this.findAll(qc).forEach(s -> {
			
			this.updateGrade(s);
			
		});
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void resetPassword(String username) {
		updatePartial(username, "password:123456");		
	}
	
	public ActionResult importsUsers(InputStream inputs) throws IOException, EncryptedDocumentException, InvalidFormatException{
		
			Workbook wb = WorkbookFactory.create(inputs);				
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
						user.setUserType(1);
						save(user);					
					}

				}
				wb.close();	
				
			
		
		return null;
	}
	

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ActionResult save(SysUser user) {
		if (user == null)
			return null;
		user.setPassword(passwordEncoder.encode("123456"));
		boolean noPinyin = false;
		if (user.getUserType() == 1) {
			// 生成双亲号
			SysUser father = new SysUser();
			father.setUserType(3);
			father.setUsername(user.getName() + "F");
			father.setNickname(user.getNickname() + "的父亲");
			father.setPassword(passwordEncoder.encode("123456"));
			father.setSex(1);
			father.setChildren(user.getName());

			super.save(father);

			SysUser mother = new SysUser();
			mother.setUserType(3);
			mother.setUsername(user.getName() + "M");
			mother.setNickname(user.getNickname() + "的母亲");
			mother.setPassword(passwordEncoder.encode("123456"));
			mother.setSex(0);
			mother.setChildren(user.getName());
			super.save(mother);
			
			String firstName = StringUtils.toPinyin(StringUtils.trimAllWhitespace(user.getSurname()));
        	String lastName = StringUtils.toPinyin(StringUtils.trimAllWhitespace(user.getGivename()));
            if(StringUtils.isOnlyAlapha(user.getSurname()) && StringUtils.isOnlyAlapha(user.getGivename())){
            	noPinyin = true;
            	firstName = user.getSurname();  
    			lastName =  user.getGivename();  
            }
			
			user.setFirstName(firstName);
            user.setLastName(lastName);
		}

		if(noPinyin){
		   user.setPyname(user.getFirstName()+" "+user.getLastName());	
		}else{
		   user.setPyname(StringUtils.toPinyin(user.getNickname()));
		}
		
		return super.save(user);
	}

	@Override	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SysUser user = mapper.findById(username);	
		return user;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String name = authentication.getPrincipal().toString();
		if (Strings.isNullOrEmpty(name))
			throw  new BadCredentialsException("empty username is not permitted");
		SysUser user = (SysUser) this.loadUserByUsername(name);
		if (null == user)
			throw  new BadCredentialsException("username ["+name+"] is not found");
		if (passwordEncoder.matches(authentication.getCredentials().toString(),
				user.getPassword())) {
			UserLogger loginfo = new UserLogger();
		    loginfo.setAction("login");
		    loginfo.setUsername(user.getUsername());
		    loginfo.setNickname(user.getNickname());
		    loginfo.setFiretime(DateUtils.currentMillis());
		    WebAuthenticationDetails wed = (WebAuthenticationDetails) authentication.getDetails();
		    loginfo.setIpaddr(wed.getRemoteAddress());
			loggerService.save(loginfo);			
			return user;
		} else {
			throw  new BadCredentialsException("password is not matched");
		}
	}

	public boolean passwordCorrect(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	public void updateGrade(SysUser user) {
		if (null==user || Strings.isNullOrEmpty(user.getUsername()))
			return;
		getMapper().updatePartial(user.getUsername(), "SET grade=" + user.myGrade());
	}

	@Override
	public void updatePartial(String id, String props) {
		if (props == null || id == null)
			return;		
		Set<String> sets = new HashSet<>();
		String[] paires = props.split(";");
		List<String> plist = Lists.newArrayList(paires);
		if (plist.isEmpty())
			return;
		plist.stream()
				.filter(s -> s != null)
				.forEach(
						s -> {
							String[] ss = s.split(":");
							if (ss != null && ss.length == 2) {
								if ("password".equals(ss[0]))
									sets.add(ss[0] + "='"
											+ passwordEncoder.encode(ss[1])
											+ "'");
								else
									sets.add(ss[0] + "='" + ss[1] + "'");
							}
						});
		if (sets.isEmpty())
			return;		
		String sparam = Joiner.on(',').join(sets);
		if (Strings.isNullOrEmpty(sparam))
			return;
		getMapper().updatePartial(id, "SET " + sparam);

	}

	public long exists(String name) {
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("username`" + name);
		return getMapper().countByQuery(qc);
	}

	@Override
	public List<SysUser> search(String query) {
		if(query==null) return Collections.emptyList();
		query = query.replace("'", "");
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries(String.format("username`LIKE`%s`OR;nickname`LIKE`%s`OR;pyname`LIKE`%s`",query, query, query));		
		return this.getMapper().search(qc);
	}

	public SysUser findByNickname(String nickname) {
		return mapper.findByNickname(nickname);
	}
	
	public Pager<SysUser> findAllByUserType(int page,String type){
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("userType`"+type);
		Pager<SysUser> pager = this.findPager(qc, page, 10);
		return pager;
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void update(QueryBuilder qc) {
		// TODO Auto-generated method stub
		super.update(qc);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void remove(QueryBuilder qc) {
		// TODO Auto-generated method stub
		super.remove(qc);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void removeById(String s) {
		
		super.removeById(s);
	}

	private final LoadingCache<String, String> nickCache = CacheBuilder.newBuilder()
		    .maximumSize(10000)
		    .expireAfterAccess(30, TimeUnit.MINUTES)
		    .build(new CacheLoader<String,String>(){

				@Override
				public String load(String key) throws Exception {
					String paraName = StringUtils.trimAllWhitespace(key);
					SysUser t = mapper.findByNickname(paraName);
					return t==null?"":t.getUsername();
				}
		    	
		    }); 
	
	public String retriveCachedUsername(String nickname){
		
	
		String t = null;
		try {
			t =  nickCache.get(nickname);
		} catch (ExecutionException e) {
			logger.error("{}",e);
			return null;
		}
		return t;
	}
	
	
	@Override
	public boolean supports(Class<?> authentication) {
		 return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	public AbstractMapper<String, SysUser> getMapper() {
		return mapper;
	}

}
