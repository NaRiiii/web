package com.study.questionboard.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vaild.ModifyType;

@SuppressWarnings("serial")
public class QuestionBoardVO implements Serializable {
	// Serializable toString get/set
							//만약 두개가 필요하다면 배열로 표기 group = { ModifyType.class, RegistType.class} 
	@NotNull(message = "글 번호가 비어있습니다.", groups = ModifyType.class)
	@Positive(message = "글 번호가 비어있습니다(양수).", groups = ModifyType.class)
	private int boNo; /* 글 번호 */
	
	@NotBlank(message = "제목은 필수입니다.")
	@Size(min = 1, message = "최소 한글자 이상 작성하여 주세요.")
	private String boTitle; /* 글 제목 */
	
	@NotBlank(message = "분류는 필수입니다.")
	private String boCategory; /* 글 분류 코드 */
	
	@NotBlank(message = "작성자는 필수입니다.")
	@Pattern(regexp = "[가-힣]{2,}", message = "한글 두글자 이상으로 작성하여 주세요.")
	private String boWriter; /* 작성자명 */
		
	@NotBlank(message = "패스워드는 필수입니다.")
	@Pattern(regexp = "\\w{4,12}", message = "알파벳, 숫자, 언더바(_)로 이루어진 4글자 이상 12글자 이하로 작성하여 주세요.")
	private String boPass; /* 비밀번호 */
	private String boContent; /* 글 내용 */
	private String boIp; /* 등록자 IP */
	private int boHit; /* 조회수 */
	private String boRegDate; /* 등록 일자 */
	private String boModDate; /* 수정 일자 */
	private String boDelYn; /* 삭제 여부 */

	// 추가 필드
	private String boCategoryNm; /* 글 분류 명 */

	public QuestionBoardVO() {
	}

	public QuestionBoardVO(int boNo, String boTitle, String boCategory, String boWriter, String boPass, String boContent,
			String boIp, int boHit, String boRegDate, String boModDate, String boDelYn) {
		super();
		this.boNo = boNo;
		this.boTitle = boTitle;
		this.boCategory = boCategory;
		this.boWriter = boWriter;
		this.boPass = boPass;
		this.boContent = boContent;
		this.boIp = boIp;
		this.boHit = boHit;
		this.boRegDate = boRegDate;
		this.boModDate = boModDate;
		this.boDelYn = boDelYn;
	}

	public int getBoNo() {
		return boNo;
	}

	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}

	public String getBoTitle() {
		return boTitle;
	}

	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}

	public String getBoCategory() {
		return boCategory;
	}

	public void setBoCategory(String boCategory) {
		this.boCategory = boCategory;
	}

	public String getBoWriter() {
		return boWriter;
	}

	public void setBoWriter(String boWriter) {
		this.boWriter = boWriter;
	}

	public String getBoPass() {
		return boPass;
	}

	public void setBoPass(String boPass) {
		this.boPass = boPass;
	}

	public String getBoContent() {
		return boContent;
	}

	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}

	public String getBoIp() {
		return boIp;
	}

	public void setBoIp(String boIp) {
		this.boIp = boIp;
	}

	public int getBoHit() {
		return boHit;
	}

	public void setBoHit(int boHit) {
		this.boHit = boHit;
	}

	public String getBoRegDate() {
		return boRegDate;
	}

	public void setBoRegDate(String boRegDate) {
		this.boRegDate = boRegDate;
	}

	public String getBoModDate() {
		return boModDate;
	}

	public void setBoModDate(String boModDate) {
		this.boModDate = boModDate;
	}

	public String getBoDelYn() {
		return boDelYn;
	}

	public void setBoDelYn(String boDelYn) {
		this.boDelYn = boDelYn;
	}

	public String getBoCategoryNm() {
		return boCategoryNm;
	}

	public void setBoCategoryNm(String boCategoryNm) {
		this.boCategoryNm = boCategoryNm;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);

	}
}
