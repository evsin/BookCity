package com.lansun.mobile.assistant.constant;

/**
 * <pre>
 * 业务名:
 * 功能说明: 
 * 编写日期:	2013-6-3
 * 作者:	 张云飞
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class MyConstant {
    /**
     * 退出程序ACTION
     */
    public static final String EXIT_ACTION = "com.asos.zyf.exit";
    /**
     * 推送开关标识
     */
    public static final String PUSH_SWITCH_FLAG = "pushSwitchFlag";
    /**
     * 推送开关
     */
    public static final String PUSH_SWITCH = "on";
    /**
     * 群组-精品推荐
     */
    public static final String GROUP_ONE = "1003";
    /**
     * 群组-本地热销
     */
    public static final String GROUP_TWO = "1002";

    /**
     * 群组-全省热销
     */
    public static final String GROUP_TWO_TWO = "1000";
    /**
     * 群组-最新产品
     */
    public static final String GROUP_THREE = "1001";
    /**
     * 群组-飙升
     */
    public static final String GROUP_FOUR = "1004";
    /**
     * 采购入口
     */
    public static final String PURCHASE_ENTRANCE = "http://b2b.ec189.com/profile.php?a=login&UserName=o2o0123&ProductID=pid";
    /**
     * 接机平台/换机平台（售后）
     */
    public static final String SALE_ENTRANCE = "http://rs.tellingtech.com/Users/LogOn?ReturnUrl=%2f&UserName=o2o0123";

    /**
     * 采购入口
     */
    public static String getPurchaseEntrance(String userName, String pid) {
        return "http://b2b.ec189.com/profile.php?a=login&UserName=" + userName + "&ProductID=" + pid;
    }

    /**
     * 接机平台/换机平台（售后）
     */
    public static String getSaleEntrance(String userName) {
        return "http://rs.tellingtech.com/Users/LogOn?ReturnUrl=%2f&UserName=" + userName;
    }
}
