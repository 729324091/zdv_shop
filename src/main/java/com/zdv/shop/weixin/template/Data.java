package com.zdv.shop.weixin.template;

public class Data {
	private Data_first first;
    
    private Data_keyword1 keyword1; //日期
    
    private Data_keyword2 keyword2; //服务内容
    
    private Data_keyword3 keyword3; //时间
    
    private Data_remark remark;

    public Data_first getFirst() {
        return first;
    }


	public Data_remark getRemark() {
		return remark;
	}

	public void setRemark(Data_remark remark) {
		this.remark = remark;
	}

	public void setFirst(Data_first first) {
		this.first = first;
	}




	public Data_keyword1 getKeyword1() {
		return keyword1;
	}




	public void setKeyword1(Data_keyword1 keyword1) {
		this.keyword1 = keyword1;
	}


	public Data_keyword2 getKeyword2() {
		return keyword2;
	}


	public Data_keyword3 getKeyword3() {
		return keyword3;
	}


	public void setKeyword2(Data_keyword2 keyword2) {
		this.keyword2 = keyword2;
	}


	public void setKeyword3(Data_keyword3 keyword3) {
		this.keyword3 = keyword3;
	}

}
