package com.study.common.util;

import java.text.DecimalFormat;

public class StudyFileUtil {

//	사이즈 값을 입력받아서 팬시사이즈를 구해서 리턴
	public static String fancySize(long size) {
		DecimalFormat df = new DecimalFormat("#,###.##");
		if(size<1024.0) {
			return df.format(size)+"bytes";
		}else if(size<(1024*1024)) {
			return df.format(size/1024.0) + "Kb"; //정수나누기 정수로 하게되면 정수이기때문에 하나를 실수로 만들어주고 계산해야함
		}else if(size < (1024*1024*1024)) {
			return df.format(size/(1024.0*1024)) + "Mb";			
		}else
		return df.format(size/(1024.0*1024*1024)) + "Gb";			
		
	}
	
}
