package com.corp.mms.page.vo;

public class Page
{
  private int lineCount = 0;
  private int pageSize = 15;
  private int pageCount;
  private int pageNo = 1;

  public Page() {
  }

  public Page(int pageSize) {
    this.pageSize = pageSize;
  }
  public void setLineCount(int lineCount) {
    this.lineCount = lineCount;
    if (this.lineCount % this.pageSize == 0)
      this.pageCount = (this.lineCount / this.pageSize);
    else
      this.pageCount = (this.lineCount / this.pageSize + 1);
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public int getPageCount() {
    return this.pageCount;
  }

  public int getPageNo() {
    return this.pageNo;
  }
  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }
}