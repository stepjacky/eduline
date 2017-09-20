package org.jackysoft.edu.entity;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.google.common.primitives.Ints;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.InputType;
import org.jackysoft.edu.annotation.OptionAttr;
import org.jackysoft.edu.annotation.OptionText;
import org.jackysoft.edu.annotation.OptionValue;
import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.annotation.Transient;
import org.jackysoft.edu.formbean.MediaFile;
import org.jackysoft.utils.SchoolUtils;
import org.jackysoft.utils.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Table(label = "用户")
public class SysUser implements UserDetails, CredentialsContainer,
Authentication ,MediaFile{

		
	@Transient
	private UsernamePasswordAuthenticationToken token;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3702831641023458271L;

	@Column(id = true, label = "用户ID")
	@NotNull
	@Size(min = 6, max = 8)
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	@OptionValue
	private String username;

	@OptionAttr
	@Column(label = "汉语拼音名")
	private String pyname;
	
	@Column(label = "姓名", query = true)
	@OptionText
	private String nickname;
	@Column(label = "密码", list = false, input = false)
	@Size(min = 6, max = 8)
	private String password;	

	@Column(label = "性别", formType = InputType.SELECT, data = { "0", "女", "1",
			"男" }, query = true)
	private int sex;

	@Column(label = "出生日期", formType = InputType.INPUT_DATE)
	private long birthday;

	@Column(label = "孩子账号", formType = InputType.INPUT_HIDDEN, query = false, defaultValue = "")
	private String children;

	@Column(label = "年级")
	private int grade;
	
	@Column()
	private int inyear;
	/**
	 * 0管理员 1学生 2老师 3家长
	 * */
	@Column(label = "用户类型", formType = InputType.SELECT, data = { "1", "学生",
			"2", "老师", "3", "家长" })
	@NotNull
	private int userType = 0;
	
	
	@Column(label="英文姓")
	private String firstName;
	@Column(label="英文名")
	private String lastName;
	
	@Column(label="中文姓")
	private String surname;
	
	@Column(label="中文名")
	private String givename;
	
	@Column(label="课本")
	private String textbook;


	@Column(label = "父亲")
	private String father;

	@Column(label = "父亲电话")
	private String fatherPhone;

	@Column(label = "母亲")
	private String mother;

	@Column(label = "母亲电话")
	private String motherPhone;




	@Transient
	private String passwordn;
	@Transient
	private String passwordr;

	public SysUser() {		
	}
	
	
	
	@Override
	public void eraseCredentials() {
		password = null;

	}

	@Override
	@JsonIgnore
	public Collection<GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> auths = Lists.newArrayList();
		
		switch (getUserType()) {
		case 0:
			auths.add(new Authority("ROLE_ADMIN", "管理员"));
			break;
		case 1:
			auths.add(new Authority("ROLE_STUDENT", "学生"));
			break;
		case 2:
			auths.add(new Authority("ROLE_TEACHER", "老师"));
			break;
		case 3:
			auths.add(new Authority("ROLE_PARENTS", "家长"));
			break;

		}
		auths.add(new Authority("ROLE_USER", "普通用户"));		
		return auths;
	}

	@Override
	@JsonIgnore
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {

		return false;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {

		return false;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {

		return false;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {

		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	@JsonIgnore
	public String getName() {
		if (this.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) this.getPrincipal()).getUsername();
		}

		if (getPrincipal() instanceof Principal) {
			return ((Principal) getPrincipal()).getName();
		}

		return (this.getPrincipal() == null) ? "" : this.getPrincipal()
				.toString();
	}

	@Override
	@JsonIgnore
	public Object getCredentials() {

		return password;
	}

	@Override
	@JsonIgnore
	public Object getDetails() {

		return this;
	}

	@Override
	@JsonIgnore
	public Object getPrincipal() {

		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAuthenticated() {

		return true;
	}

	/**
	 * "1", "学生",
	 * "2", "老师", 
	 * "3", "家长"
	 * **/
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getNickname() {
		return nickname==null?"":nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getPasswordn() {
		return passwordn;
	}

	public void setPasswordn(String passwordn) {
		this.passwordn = passwordn;
	}

	public String getPasswordr() {
		return passwordr;
	}

	public void setPasswordr(String passwordr) {
		this.passwordr = passwordr;
	}

	
	public int getGrade() {
		return this.grade;
	}


	public String getTextbook() {
		return textbook;
	}

	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}

	public int myGrade() {
	    return SchoolUtils.getGrade(this.getUsername());
	}

	public int beginYear() {

	    return SchoolUtils.getInyear(this.getUsername());
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getPyname() {
		return pyname;
	}

	public void setPyname(String pyname) {
		this.pyname = pyname;
	}

	
	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	}

	@Override
	public String getFilename() {
	
		return getName()+"["+getNickname()+"]-在读证明.docx";
	}

	@Override
	public String getRealpath() {
		return "";
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
	
	public int getInyear() {
		return inyear;
	}

	public void setInyear(int inyear) {
		this.inyear = inyear;
	}

	
	
	
	
	
	


	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getGivename() {
		return givename;
	}



	public void setGivename(String givename) {
		this.givename = givename;
	}


	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getFatherPhone() {
		return fatherPhone;
	}

	public void setFatherPhone(String fatherPhone) {
		this.fatherPhone = fatherPhone;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getMotherPhone() {
		return motherPhone;
	}

	public void setMotherPhone(String motherPhone) {
		this.motherPhone = motherPhone;
	}

	public static interface UserType{
		//0管理员 1学生 2老师 3家长
		int ADMIN=0,STUDENT=1,TEACHER=2,PARENTS=3;
		
	}


	public UsernamePasswordAuthenticationToken getToken() {
		return token;
	}



	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
}
