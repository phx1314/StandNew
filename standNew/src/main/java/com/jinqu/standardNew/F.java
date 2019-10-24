package com.jinqu.standardNew;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.framewidget.ModelUsreInfo;
import com.google.gson.Gson;
import com.jinqu.standardNew.frg.FrgHome;
import com.jinqu.standardNew.model.ModelQunZiLiao;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.widget.MImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.rong.app.App;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

import static com.framewidget.F.cookie;
import static com.framewidget.F.toPinYin;


public class F {
    public static Typeface font = Typeface.createFromAsset(Frame.CONTEXT.getAssets(), "fontawesome-webfont.ttf");
    public static String COLOR = "#0277bd";
    public static double latitude = 0;
    public static double longitude = 0;
    public static String locationAddress = "";
    public static String DOWNLOAD_URL = "https://www.pgyer.com/8CwE";//https://www.pgyer.com/8CwE
    public static String city = "";
    public static String filePath = Environment.getExternalStorageDirectory() + "/" + Frame.CONTEXT.getResources().getString(R.string.app_name) + "文件夹";
    public static ModelUsreLogin mModelUsreLogin;
    public static ModelUsreInfo mModelUsreInfo;
    public static String POST = "POST";
    public static String GET = "GET";
    public static String METHOD_LOGIN = "core/main/checkuserlogin1";//登录
    public static String METHOD_ALLLIST = "core/user/json";//获取所有人员列表
    public static String METHOD_GETTOKEN = "Core/RongYun/GetToken";//获取token
    public static String METHOD_GetAmount = "core/PubFlow/GetAmount";
    public static String METHOD_UpdateEmpMEID = "Core/user/UpdateEmpMEID";//获取token
    public static String METHOD_UPDATE = "http://123.57.166.61/GoldPM9_jqpm/core/AppVersions/GetAppVersionsInfoByNew?appName=" + Frame.CONTEXT.getResources().getString(R.string.app_name) + "&deviceModel=Android";//更新app
    //    http://192.168.0.192/jqwebmvc/core/main/GetAppFile?DeviceModel=Android
    public static String METHOD_UPDATE_USERINFO = "core/User/SaveProfileSetting";//更新用户信息
    public static String METHOD_EDITINFO = "core/user/editInfo";//获取用户信息
    public static String METHOD_EMPLOYEELISTNODELETE = "api/v1/sys/employeeListNoDelete";//获取用户信息
    public static String METHOD_GETLIST = "oa/Message/GetList";//系统消息列表
    public static String METHOD_OANOTICE = "oa/OaNotice/json";//通知列表
    public static String METHOD_UpdateReadByIds = "/Oa/OaMail/UpdateReadByIds?ids=";//通知列表
    public static String METHOD_GetImageNews = "core/layout/GetImageNews";//通知列表
    public static String METHOD_APPROVEREMINDS = "api/v1/sys/approveReminds/";//获取表单数量提醒
    public static String METHOD_PERSONALREMINDS = "api/v1/sys/personalReminds/";//获取表单数量提醒
    public static String METHOD_GROUP = "Core/RongYun/CreateGroup";//创建群
    public static String METHOD_UPDATEGROUP = "Core/RongYun/UpdateGroup";//修改群
    public static String METHOD_UPLOADFILE = "core/ProcessFile/UploadForRYGroup";//上传文件新
    public static String METHOD_CHANGEAVATAR1 = "core/user/ChangeAvatar1";//上传头像到服务器
    public static String METHOD_MYRELATIVEGROUPLIST = "api/v1/Chat/myRelativeGroupList";//获取我的群组
    public static String METHOD_GROUPINFO = "Core/RongYun/editInfo?id=";//根据id获取群组信息
    public static String METHOD_DELETEGROUP = "Core/RongYun/DeleteGroup?GroupID=";//删除群
    public static String METHOD_QUITGROUP = "Core/RongYun/QuitGroup?id=";//退出群
    //    public static String METHOD_EMPLOYEELISTNODELETE = "api/v1/sys/employeeListNoDelete";// 获取所有人员列表
    public static String METHOD_BASEGROUPPAGELIST = "Core/RongYun/GetGroupList";//获取所有群组列表
    public static String METHOD_GetList = "oa/Scheduler/GetList";//获取所有群组列表
    public static String METHOD_GetRemindSchedulers = "OA/Scheduler/GetRemindSchedulers";//获取所有群组列表
    public static String METHOD_CATEGORY = "project/Project/jsonNew?category=0";//获取项目信息列表
    public static String METHOD_BussContractOther = "bussiness/BussContractOther/json?ConTypeID=1";//获取项目信息列表
    public static String METHOD_BussContractOther_sk = "bussiness/BussContractOther/json";//获取项目信息列表
    public static String METHOD_GETDESRECEIVEJSON = "design/desreceive/GetDesReceiveJson";//获取项目信息列表
    public static String METHOD_PROJECTEDITINFO = "project/Project/editInfo?Id=";//获取项目信息
    public static String METHOD_PROJCHILDINFO = "Project/Project/projChildinfo?Id=";//获取项目信息
    public static String METHOD_COMBOGRIDJSON = "project/Project/combogridJson";//获取通用项目选择列表
    public static String METHOD_CATEGORY1 = "project/Project/jsonNew?category=1";//获取项目所含工程列表
    public static String METHOD_JSONPHASE = "project/Project/jsonPhase";//获取项目所含工程列表
    public static String METHOD_GETPROJLIST = "bussiness/BussContract/GetProjList";//获取收款合同所含工程列表
    public static String METHOD_GETPROJSUBLIST = "bussiness/BussContractSub/GetProjSubList";//获取付款合同所含工程列表
    public static String METHOD_RELATIVEGROUPLIST = "api/v1/Chat/relativeGroupList?IsPublic=0&RefID=";// 获取项目工程讨论组
    public static String METHOD_PROJECTPLANLISTJSON = "design/DesTask/ProjectPlanListJson?FormMenu=ProjDiscuss";//获取项目讨论列表
    public static String METHOD_PLANTABLELIST = "design/DesTask/ProjectPlanListJson";//获取工程信息列表
    public static String METHOD_DesTaskCheck = "design/DesTaskCheck/json";//获取工程信息列表
    public static String METHOD_DesTaskCheck_del = "design/DesTaskCheck/del";//获取工程信息列表
    public static String METHOD_ENGINEERING = "api/v1/busi/engineering/";//获取工程信息
    public static String METHOD_PROJSUB_INFO = "project/ProjSub/editInfo?id=";//获取外包工程信息
    public static String METHOD_ENGINEERINGBYATTACH = "api/v1/design/engineeringByAttach/";//获取图纸信息
    public static String METHOD_OAEQUIPMENT = "oa/OaEquipment/json";//设备列表
    public static String METHOD_ENGINEERINGNODEHTMLHTTP = "api/v1/busi/engineeringNodeHtmlHttp?EngID=";//获取工程进度
    public static String METHOD_DESEVENT = "design/DesEvent/json";//获取工程记事列表
    public static String METHOD_DESEVENTEDITINFO = "design/desevent/editInfo?id=";//获取工程记事信息
    public static String METHOD_ENGINERRINGLOG = "api/v1/busi/enginerringLog/";//获取记事信息接受人员列表
    public static String METHOD_DESEVENT_ADD = "design/DesEvent/save";//新增工程记事
    public static String METHOD_ENGINEERINGFILE_ADD = "api/v1/busi/engineeringFile/";//新增工程文件
    public static String METHOD_ENGINEERINGFILEPAGE = "design/desreceive/GetDesReceiveJson";//获取工程文件列表
    public static String METHOD_ENGINERRINGFILE = "api/v1/busi/enginerringFile/";//获取工程文件信息
    public static String METHOD_PROJSUB = "project/ProjSub/json";//获取外包工程信息列表editInfo?id=
    public static String METHOD_BUSSCONTRACT = "bussiness/BussContract/json";//获取收款合同信息列表editInfo?Id=
    public static String METHOD_BUSSCONTRACT_INFO = "bussiness/busscontract/editInfo?Id=";//获取收款合同信息列表editInfo?Id=
    public static String METHOD_BUSSFEEPLAN = "bussiness/BussFeePlan/json";//获取计划收费列表
    public static String METHOD_BUSSFEEFACT = "bussiness/BussFeeFact/json";//获取实际收费列表
    public static String METHOD_BUSSFEEINVOICE = "bussiness/BussFeeInvoice/json";//获取合同开票列表
    public static String METHOD_TASKWORKLISTJSON = "design/DesTask/TaskWorkListJson";//获取工作列表
    public static String METHOD_BUSSCONTRACTSUB = "bussiness/BussContractSub/json";//获取付款合同信息列表
    public static String METHOD_BUSSCONTRACTSUB_INFO = "bussiness/busscontractsub/editInfo?Id=";//获取付款合同信息
    public static String METHOD_BUSSCUSTOMER_INFO = "bussiness/busscustomer/editInfo?id=";//获取顾客单位信息
    public static String METHOD_CONTRACTSUBFEEFACTPAGE = "api/v1/busi/contractSubFeeFactPage?ConSubID=";//获取实际付费列表
    public static String GETDISCUSSINFO = "design/DesDiscuss/GetDiscussInfo";//获取实际付费列表
    public static String METHOD_DELREPLY = "design/DesDiscuss/delReply";
    public static String METHOD_DEL = "design/DesDiscuss/del";
    public static String METHOD_CONTRACTSUBFEEINVOICEPAGE = "api/v1/busi/contractSubFeeInvoicePage?ConSubID=";//获取合同收票列表
    public static String METHOD_BUSSCUSTOMER = "bussiness/BussCustomer/json?TypeID=0";//获取顾客单位列表editInfo?id=
    public static String METHOD_BUSSCUSTOMER_OTHER = "bussiness/BussCustomer/json?TypeID=3";//获取顾客单位列表editInfo?id=
    public static String BUSSCUSTOMERLINKMANALL = "bussiness/BussCustomerLinkMan/json?CustID=&&CustLinkManType=";//获取顾客联系人列表editInfo?id=
    public static String METHOD_BUSSCUSTOMERLINKMAN = "bussiness/BussCustomerLinkMan/json?CustID=";//获取某一顾客所含联系人列表
    public static String METHOD_SUBLISTJSON = "bussiness/BussCustomer/sublistJson";//获取外委顾客单位列表
    public static String METHOD_ENGINEERINGPERSONAL = "api/v1/design/engineeringPersonal/";//获取待办工作信息
    public static String METHOD_BUSSCUSTOMERLINKMAN_SAVE = "bussiness/BussCustomerLinkMan/save";//客户联系人
    public static String METHOD_TASKREMINDLISTJSON = "design/DesTask/TaskRemindListJson";//获取待办工作列表
    public static String METHOD_BussCustomer = "bussiness/BussCustomer/save";//获取待办工作列表
    public static String METHOD_OACARUSE = "oa/OaCarUse/jsoncar_ref";//获取车辆列表
    public static String METHOD_BUSSCUSTOMERLINKMAN_INFO = "bussiness/BussCustomerLinkMan/editInfo?id=";//获取联系人信息
    public static String METHOD_TASKINFOAPPROVEINFO = "design/DesTask/TaskInfoApproveInfo?from=Remind&Id=";//获取待办工作信息 设计校审
    public static String METHOD_GETDIRVERLIST = "oa/OaCarUse/GetDirverList";//获取待办工作信息 设计校审
    public static String METHOD_TASKINFOAPPROVEGETDESTASKAPPROVEALLATTACHDATAJSON = "design/DesTask/TaskInfoApproveGetDesTaskApproveAllAttachDataJson";//获取待办工作 设计文件
    public static String METHOD_DESTASKCHECK = "design/DesTaskCheck/json";//获取待办工作审批意见列表
    public static String METHOD_ENGINEERINGPERSONALEXCHANGEPAGE = "api/v1/design/engineeringPersonalExchangePage?EngineeringID=";//获取待办工作提资列表
    public static String METHOD_SUGGESTION = "api/v1/design/suggestion/";//获取待办工作审批意见信息
    public static String METHOD_IgnoreRemind = "OA/Scheduler/IgnoreRemind";//获取待办工作审批意见信息
    public static String METHOD_UPLOADCHECKIMAGE = "api/v1/design/UploadCheckimage?CheckDesignID=";//上传校审意见图片
    public static String METHOD_ENGINEERINGSTOPPAGE = "api/v1/busi/engineeringStopPage?PageSize=";//获取停止工程列表
    public static String METHOD_ENGINEERINGHISTORYPAGE = "api/v1/busi/engineeringHistoryPage";//获取历史工程列表
    public static String METHOD_DESDISCUSS = "design/DesDiscuss/json";//获取工作动态列表
    public static String METHOD_TaskInfoChangeTaskFinish = "design/DesTask/TaskInfoChangeTaskFinish";//获取工作动态列表
    public static String METHOD_TaskInfoChangeTaskStart = "design/DesTask/TaskInfoChangeTaskStart";//获取工作动态列表
    public static String METHOD_TaskInfoReSign = "design/DesTask/TaskInfoReSign";//获取工作动态列表
    public static String METHOD_save = "design/DesTaskCheck/save";//获取工作动态列表
    public static String METHOD_PROJECT = "Project/Project/EngInfo?id=";//工程详情
    public static String METHOD_TaskInfoGetPreTaskListJson = "Design/DesTask/TaskInfoGetPreTaskListJson";
    public static String METHOD_mobilemenujson = "Core/menu/mobilemenujson?level=1&isIcon=true";
    public static String METHOD_GetTaskInfo = "Design/DesTask/GetTaskInfoViewData";
    public static String METHOD_DYNAMIC = "api/v1/sys/Dynamic/";//获取工作动态内容
    public static String METHOD_SchedulerSave = "oa/Scheduler/Save";//获取工作动态内容
    public static String METHOD_GETREPLYDATA = "design/DesDiscuss/GetReplyData";//获取项目讨论回复列表
    public static String METHOD_DESDISCUSSSAVE = "design/DesDiscuss/save";//新增工作动态
    public static String METHOD_DESDISCUSSSAVEREPLY = "design/DesDiscuss/saveReply";//回复工作动态
    public static String METHOD_MAILRECEIVEJSON = "oa/OaMail/mailreceivejson?Read=0";//获取内部邮件-收件箱
    public static String METHOD_MAILSENDJSON = "oa/OaMail/mailsendjson";//获取内部邮件-发件箱
    public static String METHOD_CAOGAO = "oa/OaMail/mailsendjson?Flag=1";//获取内部邮件-草稿箱
    public static String METHOD_MAILJUNKJSON = "oa/OaMail/mailjunkjson";//获取内部邮件-垃圾箱
    public static String METHOD_OACARUSE_INFO = "OA/OaCarUse/editPhone";//获取用车详情
    public static String METHOD_OAEQUIPGET_INFO = "Oa/OaEquipGet/infoPhone";//获取采购
    public static String METHOD_OAMAIL = "oa/oamail/save";//发送邮件
    public static String METHOD_DeleteRead = "oa/Message/DeleteRead";//发送邮件
    public static String METHOD_SetReadStatus = "oa/Message/SetReadStatus";//发送邮件
    public static String METHOD_OAMAILDETAIL = "oa/OaMail/editInfo";// 获取内部邮件内容
    public static String METHOD_OAMAILDEL = "oa/OaMailRead/delByIds";
    public static String METHOD_OAWORKINGDEL = "oa/OaWorking/del";
    public static String METHOD_OAGOINGDEL = "oa/OaGoing/del";
    public static String METHOD_OALEAVEDEL = "oa/OaLeave/del";
    public static String METHOD_OAMAILDELCG = "oa/OaMail/delByIds";
    public static String METHOD_OAHUIFU = "oa/OaMail/del";//恢复邮件
    public static String METHOD_OAHUIFU_READ = "oa/OaMailRead/del";//恢复邮件
    public static String METHOD_GetData = "oa/Scheduler/GetData";//恢复邮件
    public static String METHOD_OANEW = "oa/OaNew/json";//获取新闻列表
    public static String METHOD_OANEWSREADALL = "api/v1/oa/OaNewsReadAll";//通知公告全部设为已读
    public static String METHOD_OaNotice = "oa/OaNotice/editInfo?Id=";// 获取通知内容
    public static String METHOD_OANEWS = "oa/OaNew/editInfo?Id=";//获取新闻内容
    public static String METHOD_FLOWWIDGET = "core/PubFlow/FlowWidget";//获取表单流程信息
    public static String METHOD_TaskInfoSavePostApprove = "design/DesTask/TaskInfoSavePostApprove";//获取表单流程信息
    public static String METHOD_Update = "design/DesTaskCheck/Update";//获取表单流程信息
    public static String METHOD_APPROVEWHENSTART = "api/v1/sys/approveWhenStart?RefTable=";//发起表单时  获取表单流程信息
    public static String METHOD_APPROVEWHENNOW = "api/v1/sys/approveWhenNow?ApproveNodeID=";//审批表单时 获取表单流程信息
    public static String METHOD_APPROVENEXT = "api/v1/sys/approveNext/";//新增节点
    public static String METHOD_APPROVEDIRECTLYFINISH = "api/v1/sys/approveDirectlyFinish/";//新增直接完成
    public static String METHOD_APPROVEHANDOVER = "api/v1/sys/approveHandover/";//工作移交
    public static String METHOD_APPROVEBACK = "api/v1/sys/approveBack/";//节点回退
    public static String METHOD_APPROVEDISAGREE = "api/v1/sys/approveDisagree/";//节点否决
    public static String METHOD_APPROVEPROGRESS = "api/v1/sys/approveProgress/";//获取审批进度列表
    public static String METHOD_GETFLOWEXE = "core/PubFlow/GetFlowExe";// 获取审批进度列表
    public static String METHOD_GETTODOLISTBYNAME = "core/PubFlow/GetToDoListByName";// 获取表单申请---审核
    public static String METHOD_GETTODOLIST = "core/PubFlow/GetToDoList";// 获取表单申请---审核
    public static String METHOD_BASELOG = "core/BaseLog/json";// 获取表单申请---审核
    public static String METHOD_OAEQUIPGET = "oa/OaEquipGet/json";
    public static String METHOD_GetMails = "Core/Layout/GetMails";
    public static String METHOD_OAWORKING = "oa/OaWorking/json";
    public static String METHOD_OALEAVE = "oa/OaLeave/json";
    public static String METHOD_OAGOING = "oa/OaGoing/json";
    public static String METHOD_OACARUSEJSON = "oa/OaCarUse/json";
    public static String METHOD_CreateWord = "design/DesTaskCheck/CreateWord";
    public static String METHOD_jsonWord = "design/DesTaskCheck/jsonWord";
    public static String METHOD_ISOFORMDESCHANGE = "iso/IsoFormDesChange/json";//获取设计变更申请---我的
    public static String METHOD_TaskInfoApproveSavePostBack = "design/DesTask/TaskInfoApproveSavePostBack";
    public static String METHOD_TaskInfoApproveSavePostPass = "design/DesTask/TaskInfoApproveSavePostPass";
    public static String METHOD_OASTAMPUSE = "oa/OaStampUse/json";
    //    public static String METHOD_OANOTICE = "core/PubFlow/FlowWidget";//新增设计变更申请
    public static String METHOD_ISOFORMDESINPUTREVIEW = "iso/IsoFormDesInputReview/json";//获取设计输入资料评审表---我的
    //    public static String METHOD_OANOTICE = "core/PubFlow/FlowWidget";//新增设计输入资料评审申请
//    public static String METHOD_OANOTICE = "iso/IsoFormDesInputReview/editInfo?id=";//获取设计输入资料评审信息
    public static String METHOD_ISOFORMDESOUTPUTREVIEW = "iso/IsoFormDesOutputReview/json";// 获取设计输出资料评审表---我的
    //    public static String METHOD_OANOTICE = "core/PubFlow/FlowWidget";//新增设计输出资料评审申请
//    public static String METHOD_IsoFormDesOutputReview = "iso/IsoFormDesOutputReview/editInfo?id=";//获取设计输出资料评审信息
    public static String METHOD_BUSSPROJINFORECORDS = "bussiness/BussProjInfoRecords/jsonMy";//获取项目信息备案---我的
    //    public static String METHOD_OANOTICE = "core/PubFlow/FlowWidget";//新增项目信息备案
//    public static String METHOD_BUSSPROJINFORECORDS = "bussiness/bussprojinforecords/editInfo?id=";//获取项目信息备案
    public static String METHOD_GETCUSTOMERLISTCOMBOBOXWITHCOUNT = "bussiness/BussCustomer/GetCustomerListComboboxWithCount?TopCount=10";//获取客户单位列表  项目信息备案用
    public static String METHOD_PROJDISCUSSLISTVIEW = "design/DesDiscuss/ProjDiscussListView";
    public static String METHOD_MESSAGE_INFO = "oa/Message/EditInfo";
    public static String METHOD_LEAVEAPPROVEPAGELIST = "api/v1/oa/leaveApprovePageList?ApproveStatus=";//获取请假申请---审核
    public static String METHOD_LEAVEPAGELIST = "api/v1/oa/leavePageList?IsPublic=1&PageSize=";//获取请假申请---我的
    public static String METHOD_LEAVE = "api/v1/oa/Leave/";//获取请假申请信息
    public static String METHOD_GOINGAPPROVEPAGELIST = "api/v1/oa/goingApprovePageList?ApproveStatus=";//获取外出申请---审核
    public static String METHOD_GOINGPAGELIST = "api/v1/oa/goingPageList?IsPublic=1&PageSize=";//获取外出申请---我的
    //    public static String METHOD_OANOTICE = "api/v1/oa/going/";//获取外出申请信息
    public static String METHOD_Going = "api/v1/oa/Going/";//新增外出申请
    public static String METHOD_WORKOVERAPPROVEPAGELIST = "api/v1/oa/workOverApprovePageList?ApproveStatus=";//获取加班申请---审核
    public static String METHOD_WORKOVERPAGELIST = "api/v1/oa/workOverPageList?IsPublic=1&PageSize=";//获取加班申请---我的
    public static String METHOD_WORKOVER = "api/v1/oa/workOver/";//获取加班申请信息
    //    public static String METHOD_OANOTICE = "api/v1/oa/JQGoingPageList?ApproveStatus=";//获取金曲出差申请---审核
    public static String METHOD_JQGOINGPAGELIST = "api/v1/oa/JQGoingPageList?IsPublic=1&PageSize=";//获取金曲出差申请---我的
    //    public static String METHOD_OANOTICE = "api/v1/oa/JQGoing/";// 获取金曲出差申请信息
    public static String METHOD_JQGOING = "api/v1/oa/JQGoing/";//编辑外出申请
    public static String METHOD_GETATTACHFILES = "core/ProcessFile/GetAttachFiles";//获取附件列表
    public static String METHOD_GETATTACHFILES_WW = "bussiness/BussCustomer/sublistJson";//获取附件列表
    public static String METHOD_GETMAILS = "core/Layout/GetMails";//未读邮件列表
    public static String METHOD_GETATTCHLISTFORMAILFORWARD = "oa/OaMail/GetAttachListForMailForward";//获取附件列表
    public static String METHOD_CLEARTEMP = "api/v1/attach/clearTemp?ctrlID=";//清理临时上传文件夹
    public static String METHOD_TaskInfoGetDesTaskAttachDataJson = "design/DesTask/TaskInfoGetDesTaskAttachDataJson";//清理临时上传文件夹
    public static String METHOD_UPLOAD = "core/ProcessFile/Upload";//上传文件
    public static String METHOD_DELETE = "core/ProcessFile/Delete";//删除文件
    public static String METHOD_OACHECKIN = "oa/OaCheckIn/json";
    public static String METHOD_CHECKINGIN = "oa/OaCheckIn/CheckingIn";//签到
    public static String METHOD_TASKCREATEFOLDER = "core/ProcessFile/TaskCreateFolder";//新建文件夹
    public static String METHOD_GetTaskInfoApprovePostPassViewData = "design/destask/GetTaskInfoApprovePostPassViewData";//新建文件夹
    public static String METHOD_GetTaskInfoApprovePostBackViewData = "design/destask/GetTaskInfoApprovePostBackViewData";//新建文件夹
    public static String METHOD_GetTaskInfoViewData = "design/destask/GetTaskInfoViewData";//新建文件夹
    public static String METHOD_GetTaskInfoPostApproveViewData = "design/destask/GetTaskInfoPostApproveViewData";//新建文件夹
    public static String METHOD_GetTaskInfoApproveViewData = "design/destask/GetTaskInfoApproveViewData";//新建文件夹
    public static String METHOD_OaMeetingUsesave = "oa/OaMeetingUse/save";//获取表单流程信息
    public static String METHOD_GetMessages = "oa/Message/GetMessages";//获取表单流程信息


    public static String refTable_OaMail = "OaMail";
    public static String refTable_Project = "Project";
    public static String refTable_DesEvent = "DesEvent";
    public static String refTable_BussContractSub = "BussContractSub";
    public static String refTable_LinkMan = "LinkMan";
    public static String refTable_Customer = "Customer";
    public static String refTable_SubCustomer = "SubCustomer";
    public static String refTable_ProjSub = "ProjSub";
    public static String refTable_BussContract = "BussContract";
    public static String refTable_Engineering = "Engineering";
    public static String refTable_OaNotice = "OaNotice";
    public static String refTable_OaNew = "OaNew";
    public static String refTable_EngineeringFile = "EngineeringFile";
    public static String refTable_DesReceive = "DesReceive";
    public static String refTable_CarUse = "CarUse";
    public static String refTable_OaMeetingUse = "OaMeetingUse";
    public static String refTable_OaLeave = "OaLeave";
    public static String refTable_OaWorking = "OaWorking";
    public static String refTable_OaGoing = "OaGoing";
    public static String refTable_OaEquipGetFlow = "OaEquipGetFlow";
    public static String refTable_OaStampUse = "OaStampUse";
    public static String refTable_BussContractOther = "BussContractOther";
    public static String refTable_BussFeeInvoiceISO = "BussFeeInvoiceISO";
    public static String refTable_SubFeeFact = "SubFeeFact";

    public static HashMap<String, String> mHashMap = new HashMap();

    public static void init() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        if (!TextUtils.isEmpty(getJson("IP"))) {
            try {
                BaseConfig.setUri("http://" + getJson("IP"));
                ParamsManager.put("image_star", "http://" + getJson("IP").substring(0, getJson("IP").lastIndexOf("/")) + "/GoldFile/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cookie = "";
        mModelUsreLogin = null;
        if (!TextUtils.isEmpty(F.getJson("mModelUsreLogin"))) {
            mModelUsreLogin = new Gson().fromJson(F.getJson("mModelUsreLogin"), ModelUsreLogin.class);
            cookie = "UID=" + mModelUsreLogin.UserInfo.EmpID + "; " + "ASP.NET_SessionId=" + mModelUsreLogin.SessionID + "; " + "AgentID=; expires=Fri, 20-May-1983 16:00:00 GMT; path=/";
        }
        if (!TextUtils.isEmpty(F.getJson("mModelUsreInfo"))) {
            mModelUsreInfo = new Gson().fromJson(F.getJson("mModelUsreInfo"), ModelUsreInfo.class);
        }
        if (!TextUtils.isEmpty(F.getJson("COLOR"))) {
            com.framewidget.F.COLOR = COLOR = F.getJson("COLOR");
        } else {
            com.framewidget.F.COLOR = COLOR = "#2b9efe";
        }
        mHashMap.put("a", "#7986cb");
        mHashMap.put("b", "#ba68c8");
        mHashMap.put("c", "#4dd0e1");
        mHashMap.put("d", "#4fc3f7");
        mHashMap.put("e", "#aed581");
        mHashMap.put("f", "#e57373");
        mHashMap.put("g", "#90a4ae");

        mHashMap.put("h", "#4db6ac");
        mHashMap.put("i", "#7e57c2");
        mHashMap.put("j", "#ffb74d");
        mHashMap.put("k", "#ef5350");
        mHashMap.put("l", "#81c784");
        mHashMap.put("m", "#ff8a65");
        mHashMap.put("n", "#26a69a");

        mHashMap.put("o", "#ab47bc");
        mHashMap.put("p", "#42a5f5");
        mHashMap.put("q", "#64b5f6");
        mHashMap.put("r", "#ff9800");
        mHashMap.put("s", "#78909c");
        mHashMap.put("t", "#5c6bc0");

        mHashMap.put("u", "#9575cd");
        mHashMap.put("v", "#26c6da");
        mHashMap.put("w", "#ec407a");
        mHashMap.put("x", "#f06292");
        mHashMap.put("y", "#67b72e");
        mHashMap.put("z", "#f1ac18");
        mHashMap.put("1", "#686868");
        mHashMap.put("", "#cccccc");


    }

    /**
     * 获取url对应的域名
     *
     * @param url
     * @return
     */
    public static String getDomain(String url) {
        String result = "";
        int j = 0, startIndex = 0, endIndex = 0;
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '/') {
                j++;
                if (j == 2)
                    startIndex = i;
                else if (j == 3)
                    endIndex = i;
            }

        }
        result = url.substring(startIndex + 1, endIndex);
        return result;
    }


    public static void reFreashQiTaQun(ModelQunZiLiao mModelQunZiLiao) {
        try {
            if (mModelQunZiLiao.stateValue != null)
                RongIM.getInstance().refreshGroupInfoCache(
                        new Group(ParamsManager.get("ChatStr") + mModelQunZiLiao.stateValue.Model.GroupID, mModelQunZiLiao.stateValue.Model.GroupName, Uri.parse(ParamsManager.get("PortraitUrlGroupNew") + (TextUtils.isEmpty(mModelQunZiLiao.stateValue.Model.PortraitUri) ? "0" : mModelQunZiLiao.stateValue.Model.PortraitUri))));
        } catch (Exception e) {
        }
    }

    public static Object json2Model(String json, Class<?> mclass) {
        return new Gson().fromJson(json, mclass);

    }

    public static int copy(String fromFile, String toFile) {
        //要复制的文件目录
        File[] currentFiles;
        File root = new File(fromFile);
        //如同判断SD卡是否存在或者文件是否存在
        //如果不存在则 return出去
        if (!root.exists()) {
            return -1;
        }
        //如果存在则获取当前目录下的全部文件 填充数组
        currentFiles = root.listFiles();
        //目标目录
        File targetDir = new File(toFile);
        //创建目录
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        //遍历要复制该目录下的全部文件
        for (int i = 0; i < currentFiles.length; i++) {
            if (currentFiles[i].isDirectory())//如果当前项为子目录 进行递归
            {
                copy(currentFiles[i].getPath() + "/", toFile + currentFiles[i].getName() + "/");
            } else//如果当前项为文件则进行文件拷贝
            {
                CopySdcardFile(currentFiles[i].getPath(), toFile + currentFiles[i].getName());
            }
        }
        return 0;
    }


    public static Bitmap createTextImage(String innerTxt) {
        //若使背景为透明，必须设置为Bitmap.Config.ARGB_4444
        Bitmap bm = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bm);
        Paint paint1 = new Paint();
        String bgColor = mHashMap.get(TextUtils.isEmpty(innerTxt) ? "" : toPinYin(innerTxt.charAt(0)).charAt(0) + "");
        paint1.setColor(Color.parseColor(TextUtils.isEmpty(bgColor) ? "#686868" : bgColor));
        canvas.drawCircle(200 / 2, 200 / 2, 200 / 2, paint1);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (200 / 2 - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        canvas.drawText(innerTxt, 200 / 2, baseLineY, paint);
        return bm;
    }

    //文件拷贝
    //要复制的目录下的所有非子目录(文件夹)文件拷贝
    public static int CopySdcardFile(String fromFile, String toFile) {
        try {
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;
        } catch (Exception ex) {
            return -1;
        }
    }

    public static String stringToBitmap(String string) {
        try {
            Bitmap bitmap = null;
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);

            String name = System.currentTimeMillis() + ".png";
            saveFile(bitmap, filePath, name);
            return "file:" + filePath + "/" + name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void setImage(MImageView mMImageView, String str) {
        if (!(new File(filePath + "/" + str + ".png")).exists()) {
            saveFile(createTextImage(str), filePath, str + ".png");
        }
        mMImageView.setObj("file:" + filePath + "/" + str + ".png");
    }


    /**
     * 将Bitmap转换成文件
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static File saveFile(Bitmap bm, String path, String fileName) {
        try {
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File ignoreFile = new File(filePath + "/.nomedia");
            if (ignoreFile.exists() && ignoreFile.isFile()) {
            } else {
                try {
                    ignoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            File myCaptureFile = new File(path, fileName);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            return myCaptureFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void reFreashQiTa(ModelUsreInfo mModelUsreInfo) {
        try {
            UserInfo userInfo;
            userInfo = new UserInfo(ParamsManager.get("ChatUserStr") + mModelUsreInfo.model.EmpID,
                    mModelUsreInfo.model.EmpName, Uri.parse(BaseConfig.getUri() + ParamsManager.get("PortraitUrlNew") + (TextUtils.isEmpty(mModelUsreInfo.model.EmpHead) ? "0" : mModelUsreInfo.model.EmpHead)));
            RongIM.getInstance().refreshUserInfoCache(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前时间
     *
     * @param format
     * @return
     */
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    /**
     * 判断一个json是否为数组
     *
     * @return
     */
    public static boolean jsonIsArray(String str) {
        boolean bArray = false;
        try {
            JSONArray jsonArray = new JSONArray(str);
            if (jsonArray != null) {
                bArray = true;
            } else {
                bArray = false;
            }
        } catch (JSONException e) {

            e.printStackTrace();
            return bArray;
        }

        return bArray;
    }

    public static void showNormal(Context context, String content) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, IndexAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.putExtra("className", FrgHome.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon_blue).setContentTitle("金曲标准版")
                .setContentText(content).setContentIntent(contentIntent)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE;
        if (F.mModelUsreLogin != null) {
            manager.notify((int) System.currentTimeMillis(), notification);
        }
    }

    public static String getRightdata(String data, Object obj) {
        return getData(data, new Gson().toJson(obj));
    }

    public static String getData(String data, String json) {
        try {
            JSONObject mJSONObject = new JSONObject(json);
            String REGEX = "#\\{([^}]*)\\}";
            Pattern pattern = Pattern.compile(REGEX);
            Matcher m = pattern.matcher(data);
            while (m.find()) {
//                System.out.println(m.group());
//                System.out.println(m.start() + "...." + m.end());//找到每个字符的角标
//                data = data.replace(m.group(), mJSONObject.optInt(m.group().substring(2, m.group().length() - 1)) + "");
                data = data.replace(m.group(), mJSONObject.optString(m.group().substring(2, m.group().length() - 1)).endsWith(".0") ? Math.round(Double.valueOf(mJSONObject.optString(m.group().substring(2, m.group().length() - 1)))) + "" : mJSONObject.optString(m.group().substring(2, m.group().length() - 1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String replaceData(String data, String rightdata) {
        try {
            String REGEX = "#\\{([^}]*)\\}";
            Pattern pattern = Pattern.compile(REGEX);
            Matcher m = pattern.matcher(data);
            while (m.find()) {
//                System.out.println(m.group());
//                System.out.println(m.start() + "...." + m.end());//找到每个字符的角标
//                data = data.replace(m.group(), mJSONObject.optInt(m.group().substring(2, m.group().length() - 1)) + "");
                data = data.replace(m.group(), rightdata);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void changeColor(Drawable backgroundDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable.ConstantState cs = backgroundDrawable.getConstantState();
            try {
                Method method = cs.getClass().getMethod("getChildren", null);// 通过反射调用getChildren方法获取xml文件中写的drawable数组
                method.setAccessible(true);
                Object obj = method.invoke(cs, null);
                Drawable[] drawables = (Drawable[]) obj;

                for (int J = 0; J < drawables.length; J++) {
                    if (drawables[J] instanceof BitmapDrawable && J == 1) {
                        BitmapDrawable gd = (BitmapDrawable) drawables[J];
                        gd.setTint(Color.parseColor(COLOR));
                    }
                    if (drawables[J] instanceof GradientDrawable && J == 1) {
                        GradientDrawable gd = (GradientDrawable) drawables[J];
                        gd.setColor(Color.parseColor(F.COLOR));
                    }
                    if (drawables[J] instanceof ColorDrawable && J == 1) {
                        ColorDrawable gd = (ColorDrawable) drawables[J];
                        gd.setColor(Color.parseColor(F.COLOR));
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void clearJson() {
        com.framewidget.F.cookie = "";
        F.mModelUsreLogin = null;
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString("mModelUsreLogin", "").commit();
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    public static void connect(String token, Context context,
                               RongIMClient.ConnectCallback mConnectCallback) {
        if (context.getApplicationInfo().packageName.equals(App
                .getCurProcessName(context))) {
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, mConnectCallback);
        }
    }


    public static String getJson(String key) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        return sp.getString(key, "");
    }

    public static void saveJson(String key, String json) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString(key, json).commit();

    }


    public static void showToast2Login(Context context) {
        Frame.HANDLES.close("FraLogin");
    }

    public static boolean isFirstInstall() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        return sp.getBoolean("isFirstInstall", true);
    }

    public static void saveFirstInstall() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putBoolean("isFirstInstall", false).commit();
    }

    public static int getversioncode(Context mContext) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
            int versionCode = packInfo.versionCode;
            return versionCode;
        } catch (Exception e) {

        }
        return 0;
    }

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            //通过反射得到tablayout的下划线的Field
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            //得到承载下划线的LinearLayout   //源码可以看到SlidingTabStrip继承得到承载下划线的LinearLayout
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
        //循环设置下划线的左边距和右边距
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
