package com.study.member.vo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.ScriptAssert;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.study.common.vaild.JoinStep2;
import com.study.common.vaild.JoinStep3;
import com.study.common.vaild.RegistType;

@SuppressWarnings("serial")
public class MemberVO implements Serializable {
	//비밀번호는 등록할때만검사, 수정할때는 제외
	
	@NotBlank(message = "아이디는 필수입니다.", groups = { Default.class, JoinStep2.class})
	@Pattern(regexp = "\\w{4,12}" , message = "알파벳, 숫자, 언더바로 이루어진 4글자 이상 12글자 이하로 작성하여 주세요.", groups = { Default.class, JoinStep2.class})
	private String memId;          /* 회원 아이디 */
	
	@NotBlank(message = "비밀번호는 필수입니다.", groups = {RegistType.class,JoinStep2.class})
	@Pattern(regexp = "[\\w!-/]{4,12}" , message = "알파벳, 숫자, 특수문자로 이루어진 4글자 이상 12글자 이하로 작성하여 주세요.", groups = { RegistType.class , JoinStep2.class})
	private String memPass;        /* 회원 비밀번호 */
	
	@NotBlank(message = "이름은 필수입니다.", groups = { Default.class, JoinStep2.class})
	@Pattern(regexp = "[가-힣]{2,6}", message = "한글 두글자 이상 6글자 이하로 입력하여 주세요.", groups = { Default.class, JoinStep2.class})
	private String memName;        /* 회원 이름 */
	
	@NotBlank(message = "생일은 필수입니다", groups = { Default.class, JoinStep3.class})
	@Pattern(regexp = "\\d{4}[-/.]\\d{2}[-/.]\\d{2}", message = "YYYY-MM-DD 형식에 맞게 입력하여 주세요", groups = { Default.class, JoinStep3.class})
	private String memBir;         /* 회원 생일 */
	
	@NotBlank(message = "우편번호는 필수입니다.")
	@Pattern(regexp = "\\d{5}", message = "우편번호가 잘못 되었습니다.", groups = { Default.class, JoinStep3.class})
	private String memZip;         /* 우편번호 */
	
	@NotBlank(message = "기본주소는 필수입니다.", groups = { Default.class, JoinStep3.class})
	private String memAdd1;        /* 주소 */
	
	@NotBlank(message = "상세주소는 필수입니다.", groups = { Default.class, JoinStep3.class})
	private String memAdd2;        /* 상세주소 */
	
	private String memHp;          /* 연락처 */
	
	@NotBlank(message = "이메일은 필수입니다.", groups = { Default.class, JoinStep2.class})
	@Email(message = "이메일 형식이 아닙니다.", groups = { Default.class, JoinStep2.class})
	private String memMail;        /* 이메일 */
	
	@NotBlank(message = "직업은 필수입니다.", groups = { Default.class, JoinStep3.class})
	private String memJob;         /* 직업 코드 */
	
	@NotBlank(message = "취미는 필수입니다.", groups = { Default.class, JoinStep3.class})
	private String memLike;        /* 취미 코드 */
	
	private int memMileage;        /* 마일리지 */
	private String memDelete;      /* 탈퇴여부 */
	//추가된 필드
	private String memJob_nm;         /* 직업 코드 명*/
	private String memLike_nm;        /* 취미 코드 명*/
	
	// alt + shift + s
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public String getMemZip() {
		return memZip;
	}
	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}
	public String getMemAdd1() {
		return memAdd1;
	}
	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}
	public String getMemAdd2() {
		return memAdd2;
	}
	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}
	public String getMemHp() {
		return memHp;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemJob() {
		return memJob;
	}
	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}
	public String getMemLike() {
		return memLike;
	}
	public void setMemLike(String memLike) {
		this.memLike = memLike;
	}
	public int getMemMileage() {
		return memMileage;
	}
	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}
	public String getMemDelete() {
		return memDelete;
	}
	public void setMemDelete(String memDelete) {
		this.memDelete = memDelete;
	}
	public String getMemJobNm() {
		return memJob_nm;
	}
	public void setMemJobNm(String memJob_nm) {
		this.memJob_nm = memJob_nm;
	}
	public String getMemLikeNm() {
		return memLike_nm;
	}
	public void setMemLikeNm(String memLike_nm) {
		this.memLike_nm = memLike_nm;
	}
	
	/*
	 * @Override public String toString() { return
	 * ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); }
	 */
}