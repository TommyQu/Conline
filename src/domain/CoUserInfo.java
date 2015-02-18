//By 陈帆

package domain;

import java.util.Date;

/*
创建用户基本信息表格
表明：CoUserInfo
用户只能禁用，不被删除
*/
public class CoUserInfo
{
	private int id=0;						/*用户ID*/
	private String  name="";			/*用户名*/
	private String pwd="";			/*用户密码，
	  统一使用加盐后的MD5加密算法加密密码*/
	private String sex="";					/*用户性别*/
	private int status=0;				/*用户是否激活,用户是否被禁用
	0 = 表示正常用户
	1 = 表示未激活
	2 = 被禁用*/
	private int authority=10;				/*用户权限
	 [0,32 767]
               普通用户权限>=10
	 管理员用户>=128*/
	private String activeCode="";			/*激活的验证信息*/
	private String email="";				/*用户邮箱地址*/
	/*以下为可选字段，不一定要全部填写*/
	private String qq="";					/*用户QQ*/
	private String phone="";				/*用户电话号码*/	
	private String address="";				/*用户地址*/
	private String zipCode="";				/*用户邮编号码*/
	private String school="";				/*用户学校*/
	Date  birthday=new Date();			/*用户生日*/
	private String major="";				/*用户专业*/
	private String grade="";				/*用户年级*/
	Date creatTime = new Date();		/*创建时间*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	
}
