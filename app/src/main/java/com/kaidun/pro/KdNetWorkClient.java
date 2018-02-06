package com.kaidun.pro;

import android.text.TextUtils;

import com.kaidun.pro.bean.ClassBean;
import com.kaidun.pro.bean.MsgDetailBean;
import com.kaidun.pro.bean.ReadAndUnReadBean;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.ZKBase;

/**
 * Created by lmj on 2018/1/25.
 */

public class KdNetWorkClient {


    /**
     * 留言
     */
    public void leaveMsg(String content,String theme,String classId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("kfmMsgTitle", theme);
            jsonObject.put("kfmMsgText", content);
            jsonObject.put("classId", classId);
            KDConnectionManager.getInstance().getZHApi().leaveMessage(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response != null) {
                                if (response.body().getStatusCode() == 208){
                                    PageCtrl.startLoginActivity(ZKBase.getContext());
                                    return;
                                }
                                  mCallBack.getSuccessDataCallBack(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null) {
                                mCallBack.getFailDataCallBack(-1);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取消息详情
     */
    public void getMsgDetail(String keyId,String kmdcode) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
           /* jsonObject.put("emId", keyId);*/
            jsonObject.put("kfmId", keyId);    //主键id
            // TODO: 2018/2/5 test 需要修改这里
            if (!TextUtils.isEmpty(kmdcode)){
                jsonObject.put("slideStatus", "down");
                jsonObject.put("kmdCode", kmdcode);
            }
//            jsonObject.put("messageContent", "内容");
            KDConnectionManager.getInstance().getZHApi().getMsgDetail(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgDetailBean>() {
                        @Override
                        public void onResponse(Call<MsgDetailBean> call, Response<MsgDetailBean> response) {
                            if (mCallBack != null && response != null && response.isSuccessful()) {
                                if (response.body().getStatusCode() == 208){
                                    PageCtrl.startLoginActivity(ZKBase.getContext());
                                    return;
                                }
                                mCallBack.getSuccessDataCallBack(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgDetailBean> call, Throwable t) {
                            if (mCallBack != null) {
                                mCallBack.getFailDataCallBack(-1);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取未读和已读信息    “001”（已读）“002”（未读）
     */
    public void getReadAndUnReadMsg(String flag,String kfmcode) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("kfmRole", "001");
            if (!TextUtils.isEmpty(kfmcode)){
                jsonObject.put("slideStatus", "down");
                jsonObject.put("kmdCode", kfmcode);
            }
            jsonObject.put("status", flag);
            jsonObject.put("kfmStatus", flag);
            KDConnectionManager.getInstance().getZHApi().getReadAndUnreadMsg(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<ReadAndUnReadBean>() {
                        @Override
                        public void onResponse(Call<ReadAndUnReadBean> call, Response<ReadAndUnReadBean> response) {
                            if (response.isSuccessful()) {
                                ReadAndUnReadBean body = response.body();
                                if (mCallBack != null && body != null) {
                                    if (body.getStatusCode() == 208){
                                        PageCtrl.startLoginActivity(ZKBase.getContext());
                                        return;
                                    }
                                    mCallBack.getSuccessDataCallBack(body);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ReadAndUnReadBean> call, Throwable t) {
                            if (mCallBack != null) {
                                mCallBack.getFailDataCallBack(-1);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回复详情消息
     */
    public void sendMsgDetail(String keyId, String msg) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("kmdMsgText", msg);
            jsonObject.put("kmdKfmId", keyId);
            jsonObject.put("kfmId", keyId);
            KDConnectionManager.getInstance().getZHApi().sendMsgDetail(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response.isSuccessful()) {
                                if (response.body().getStatusCode() == 208){
                                    PageCtrl.startLoginActivity(ZKBase.getContext());
                                    return;
                                }
                                mCallBack.getSuccessDataCallBack(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null) {
                                mCallBack.getFailDataCallBack(-1);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除未读消息
     */
    public void udpateMessage(String keyId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("kfmId", keyId);
            jsonObject.put("kmdKfmId", keyId);
            KDConnectionManager.getInstance().getZHApi().udpateMessage(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response != null) {
                                if (response.body().getStatusCode() == 208){
                                    PageCtrl.startLoginActivity(ZKBase.getContext());
                                    return;
                                }
                                mCallBack.getSuccessDataCallBack(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null) {
                                mCallBack.getFailDataCallBack(-1);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送消息时点击“+” 查找班级
     */
    public void selectClassInfo() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            KDConnectionManager.getInstance().getZHApi().selectClassInfo(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<ClassBean>() {
                        @Override
                        public void onResponse(Call<ClassBean> call, Response<ClassBean> response) {
                            if (mCallBack != null && response != null) {
                                if (response.body().getStatusCode() == 208){
                                    PageCtrl.startLoginActivity(ZKBase.getContext());
                                    return;
                                }
                                    mCallBack.getSuccessDataCallBack(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<ClassBean> call, Throwable t) {
                            if (mCallBack != null) {
                                mCallBack.getFailDataCallBack(-1);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //-----------------------回调接口----------------------------
    private DataCallBack mCallBack;

    public DataCallBack getmCallBack() {
        return mCallBack;
    }

    public void setmCallBack(DataCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public interface DataCallBack<T> {
        void getSuccessDataCallBack(T data);

        void getFailDataCallBack(int failIndex);

    }

}
