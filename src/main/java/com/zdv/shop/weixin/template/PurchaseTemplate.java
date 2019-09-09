package com.zdv.shop.weixin.template;

public class PurchaseTemplate {
	//{"data":{"customerName":{"color":"#173177","value":"陈丑丑"},"customerPhone":{"color":"#173177","value":"13222222222"},"Day":{"color":"#173177","value":"15时06分"},"first":{"color":"#173177","value":"收到一个新的订单"},"orderId":{"color":"#173177","value":"1002"},"orderType":{"color":"#173177","value":"订位"},"remark":{"color":"#173177","value":"请及时处理您的订单"}},"template_id":"YtO2XATY0VtRbgE4jWWNl-Zdc992FDdguhMUbomNkA0","topcolor":"#173177","touser":"orsrOt9qBgO6dC-F3IL_MF52eplI","url":"http://weixin.qq.com/download"}
private String touser; //用户OpenID
    
    private String template_id; //模板消息ID
    
    private String url; //URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）。
    
    private String topcolor; //标题颜色
    
    private Data data; //详细内容

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String templateId) {
        template_id = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
