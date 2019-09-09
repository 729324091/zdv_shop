package com.zdv.shop.common;

/**
 * 常量类
 * @author administrator
 */
public class Constant {
	public static final String ucustomername = "港货灵";
	/**验证码*/
	public static final String VERIFY_CODE = "VERIFY_CODE";
	/**是否贷超 0不是，1 是*/
	public static final String IF_DAICHAO = "1";
	
	/**登录失败次数*/
	public static final String LOGIN_ERROR_TIMES = "10";
	/**缓存的KEY*/
	public static final String CACHE_KEY = "CACHE_KEY";
	/**session过期时间(秒)*/
	public static final int SESSION_EXPIRE_SECONDS = 180;
	/**缓存权限信息*/
	public static final String SESSION_OPERATIONS = "SESSION_OPERATIONS";
	/**用户登录信息*/
	public static final String SESSION_IDENTITY_KEY = "SESSION_IDENTITY_KEY";
	/**匿名群组*/
	public static final String ROLE_ANONYMOUS = "anonymous";
	/**上次请求地址*/
	public static final String PRE_REQUEST_PATH = "PRE_REQUEST_PATH";
	/**上次请求时间*/
	public static final String PRE_REQUEST_TIME = "PRE_REQUEST_TIME";
	/**非法请求次数*/
	public static final String MAL_REQUEST_TIMES = "MAL_REQUEST_TIMES";
	/**附件上传位置**/
	public static final String ANNEX_UPLOAD_PATH = "/upload/uploadAnnex";
	/**H5用户登录信息**/
	public static final String SESSION_H5USER = "H5_USER";


	public static final String UCOMPID = "45387020012630016";

    //APP错误管理
	public static String key000 = "成功";
	public static String key001 = "未被邀请";
	public static String key005 = "还未申请过";
	public static String key006 = "审核中";
	public static String key007 = "未审核通过";
	public static String key008 = "已签到过";
	public static String key009 = "参数json格式有问题或者为空了，请检查";
	public static String key010 = "NFC验证失败，该作品还未录入到【区块链书画圈】系统！请请慎重选择！";
	public static String key011 = "NFC唯一码未获得，请重试！";
	public static String key012 = "该NFC已经录入到【区块链书画圈】系统，不能重复录入！";
	public static String key013 = "今天您还未捐缘，行善每日都行！";
	public static String key014 = "缘支付类型不能为空";
	public static String key015 = "手机号服务密码错误";
	public static String key016 = "手机号服务验证码错误";
	public static String key017 = "信用认证还未通过";
	public static String key018 = "请先录入银行卡号，方便放款，在【个人中心】";
	
	public static String key99 = "ID不能为空";
	public static String key100 = "参数不能存在特别字符";
	public static String key101 = "用户名已存在";
	public static String key102 = "用户名不能为空";
	public static String key103 = "用户名或密码错误";
	public static String key104 = "用户名被停用";
	public static String key105 = "密码已经连续5次错误，明天再试！";
	public static String key106 = "地区ID不能为空";
	public static String key107 = "参数格式错误";
	public static String key108 = "经纬度没有获得，不能获得对应数据";
	public static String key109 = "数据ID不能为空";
	public static String key110 = "用户类型不能为空";
	public static String key111 = "内容主体ID不能为空";
	public static String key112 = "用户名格式不对";
	public static String key113 = "验证码不能为空!";
	public static String key114 = "验证码错误";
	public static String key115 = "验证码失效";
	public static String key116 = "手机号不能为空";
	public static String key117 = "密码为空了";
	public static String key118 = "注册失败，可能用户已经存在！";
	public static String key119 = "商户号不能为空";
	public static String key120 = "密码不能为空";
	public static String key121 = "原来密码不能为空";
	public static String key122 = "原密码错误";
	public static String key123 = "未找到对应数据详情";
	public static String key124 = "时间太短，失败坐禅 练功失败";
	public static String key125 = "不能被删除，已有人参与了";
	public static String key126 = "经纬度不能为空";
	public static String key127 = "地址不能空";
	public static String key128 = "用户ID不能为空";
	public static String key129 = "好友ID不能为空";
	public static String key130 = "已添加好友或者对方拒绝好友";
	public static String key131 = "企业ID编码不能为空";
	public static String key132 = "商户号不能为空";
	public static String key133 = "没有任何可以提交的数据";
	public static String key134 = "数据已存在或不存在或者可能已经被删除";
	public static String key135 = "起床也太早了，不正常";
	public static String key136 = "这个时候起床还有意义吗？";
	public static String key137 = "睡觉也太早了，不正常";
	public static String key138 = "这个时候睡觉，身体不要了吗？！";
	public static String key139 = "操作失败，未登录！";
	public static String key140 = "手机号码有误，请认真核对！";
	public static String key141 = "手机号码已经存在，请直接登录就可以了！";
	public static String key142 = "参数不全，注册失败";
	public static String key143 = "地址不能为空";
	public static String key144 = "好友ID不能为空";
	public static String key145 = "改用户已经是好友了";
	public static String key146 = "分页参数不能为空";
	public static String key147 = "没有对应的足球联赛采购";
	public static String key148 = "订单状态不能为空";
	public static String key149 = "订单状态长度不对";
	public static String key150 = "数据不全，操作失败";
	public static String key151 = "比赛ID不能为空";
	public static String key152 = "请先选择图片，再提交";
	public static String key153 = "类型不能为空";
	public static String key154 = "无数据，可能权限不够";
	public static String key155 = "密钥不能为空";
	public static String key156 = "密钥错误";
	public static String key157 = "您改变了IP地址，需要重新登录";
	public static String key158 = "获得第三方信息为空了";
	public static String key159 = "第三方登录类型为空了";
	public static String key160 = "用户被停用";
	public static String key161 = "用户名不能为空";
	public static String key162 = "用户密码保护基本信息不能为空";
	public static String key163 = "密码保护答案有不正确的";
	public static String key164 = "根据您的角色，您没有操作此功能权限";
	public static String key165 = "您还未注册，请输入手机号等信息";
	public static String key166 = "开始时间未传";
	public static String key167 = "结束时间未传";
	public static String key168 = "城市没有选择";
	public static String key169 = "上传文件不能为空";
	public static String key170 = "角色类型不能为空";
	public static String key171 = "已经被收藏，不能重复收藏";
	public static String key172 = "商城分类不能为空";
	public static String key173 = "评论类型不能为空";
	public static String key175 = "收藏类型不能为空";
	public static String key176 = "不能被删除，因为状态已入库";
	public static String key177 = "名称不能为空";
	public static String key178 = "企业名称不能为空";
	public static String key179 = "联系人不能为空";
	public static String key180 = "联系电话不能为空";
	public static String key181 = "推送UKEY丢失";
	public static String key182 = "不能加自己为好友";
	public static String key183 = "请注册或者选择用户名";
	public static String key184 = "第三方授权ID不能为空";
	public static String key185 = "订购数量不能为空";
	public static String key186 = "已经放入购物车";
	public static String key187 = "";
	public static String key188 = "更新时长不能为空";
	public static String key189 = "关注类型不能为空";
	public static String key190 = "选择的用户组不能为空";
	public static String key191 = "标题不能为空";
	public static String key192 = "验证码还在有效期内";
	public static String key193 = "英雄币不能为空";
	public static String key194 = "请传入日期";
	public static String key195 = "已经点赞过来，不能给一个主体多次点赞";
	public static String key196 = "对一个用户进行多次操作，已经操作过一次了";
	public static String key197 = "您离您该签到的位置有点远或者未获得你的位置，签到失败";
	public static String key198 = "该申请审核人已经被选择了，请重新选择";
	public static String key199 = "请选择状态";
	public static String key200 = "审核人顺序数不能为空";
	public static String key201 = "数据已经存在，不能重复提交";
	public static String key202 = "是否为自己的不能为空";
	public static String key203 = "您的积分不足够";//"已经竞猜过，不能重复对一场比赛重复竞猜";
	public static String key204 = "您已经审核过了";
	public static String key205 = "简报内容不能为空";
	public static String key206 = "签到失败，今天已经签到了";
	public static String key207 = "提现金额不能大于您的余额，申请失败";
	public static String key208 = "金额不能为空或者为0";
	public static String key209 = "您的余额不足，请充值";
	public static String key210 = "您无权操作，权限不足";
	public static String key211 = "已支付";
	public static String key212 = "已经绑定过了，不能重复绑定";
	public static String key213 = "挑战赛需要选择自己的一个球队进行挑战";
	public static String key214 = "该轮该组的分组已经存在，请不能重复建组";
	public static String key215 = "比赛或者训练日期不能等于小于当前日期";
	public static String key216 = "比赛已经结束过了，不能重复操作！";
	public static String key217 = "还有比赛未结束，等结束后再结束整个比赛吧";
	public static String key218 = "活动报名已经下架了，不能支付了";
	public static String key219 = "已经被邀请成功过了";
	public static String key220 = "必须是家长才能绑定小孩账号";
	public static String key221 = "您给要绑定的小孩不在一个俱乐部或者学校里，不能绑定！";
	public static String key222 = "非小孩的账号";
	public static String key223 = "不在规定对应区域内，不能操作。";
	public static String key224 = "主体已经被删除了，不能评论";
	public static String key225 = "邀请码不存在！";
	public static String key226 = "不能邀请的对象";
	public static String key227 = "本轮比赛还未结束不能进行下一轮";
	public static String key228 = "昵称不能为空";
	public static String key229 = "没有需要评论的内容";
	public static String key230 = "退款失败，可能状态问题";
	public static String key231 = "不能进行赛事设置";
	public static String key232 = "用户ID没有传，所以不是现有用户注册俱乐部";
	public static String key233 = "微信openid不能为空";
	public static String key234 = "支付失败，请检查是否数据不全！";
	public static String key235 = "还未支付，不能退款";
	public static String key236 = "库存不足";
	public static String key237 = "已被禁止登录";
	public static String key238 = "上传文件不能为空，或格式不对";
	public static String key239 = "用户不存在";
	public static String key240 = "参数不能为空";
	public static String key241 = "产品数量有误";
	public static String key242 = "经销商ID不能为空";
	public static String key243 = "经销商不存在";
	public static String key244 = "销售商不存在";
	public static String key245 = "用户已经是该销售商下的雇员";
	public static String key246 = "用户已经是该经销商下的人员";
	public static String key247 = "父级经销商不存在";
	public static String key248 = "产品不存在";
	public static String key249 = "产品属性不能为空";
	public static String key250 = "订单不存在";
	public static String key251 = "订单已无法取消";
	
	public static String key900 = "恭喜您注册成功，请耐心等待手机接收的初始密码,将在3天内有效！（5-60秒即可送达）";
	public static String key901 = "数据不全，操作失败";
	public static String key902= "已经超时，请重新登录";
	public static String key903 = "登录用户ID丢失";
	public static String key996= "第三方登录失败";
	public static String key997 = "无任何数据";
	public static String key998 = "数据库错误";
	public static String key999 = "未知错误";
	
	//2019.3.28新增
	public static String Ubrowsepoint = "0";
	public static String Usalesnum = "1";
//	public static Integer goodGrade = 1;
//	public static Integer middieGrade = 0;
//	public static Integer badGrade = -1;
	public static Integer slideshowHome = 1;
	public static Integer productHome = 2;
	public static Integer categoryHome = 3;
	public static Integer consultation = 4;
}
