package com.project.covid.model;

import lombok.Getter;

@Getter
public class Template {
	String object_type;
	String text;
	
	public Template() {
		super();
		this.object_type = "text";
		this.text = "코로나 확진자와 접촉 의심 됩니다. 가까운 보건소나 병원에서 코로나 검사 받길 권장드립니다.";
	}
	
	
}
