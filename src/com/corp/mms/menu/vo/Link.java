package com.corp.mms.menu.vo;

public class Link {
	private String mHref; //��ַ
	private String alt;  //��ע
	
	public Link(){
		
	}
	public Link(String mHref,String alt){
		this.mHref=mHref;
		this.alt=alt;
	}
	
	public String getmHref() {
		return mHref;
	}
	public void setmHref(String mHref) {
		this.mHref = mHref;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	
	
}
