package com.kaidun.pro;

import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lmj on 2018/1/25.
 */

public class KdNetWorkClient {


    /**
     * 留言
     */
    public void leaveMsg(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("kfmRole", "001");
            jsonObject.put("unitCode", "1");
            jsonObject.put("kfmMsgTitle", "考试");
            jsonObject.put("kfmMsgText", "下周考试");
            jsonObject.put("kfmSender", "07147e6f-9315-4cc8-99da-613423288aa8");
            jsonObject.put("classId", "49b2259f-d73f-4046-9a60-4e9c446bb47a");
            KDConnectionManager.getInstance().getZHApi().leaveMessage(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response != null){
                              //  mCallBack.getSuccessDataCallBack(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null){
                                mCallBack.getFailDataCallBack(-1);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 消息详情
     */
    public void getMsgDetail(String keyId){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("emId", keyId);
            jsonObject.put("kfmId", keyId);    //主键id
            jsonObject.put("slideStatus", "down");
            jsonObject.put("kmdCode", "10000048");
            jsonObject.put("messageContent", "内容");
            KDConnectionManager.getInstance().getZHApi().getMsgDetail(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response != null){
                              //  mCallBack.getSuccessDataCallBack(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null){
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
    public void getReadMsg(String flag){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("kfmRole", "001");
            jsonObject.put("slideStatus", "down");
            jsonObject.put("status", flag);
            jsonObject.put("kfmStatus", flag);
            KDConnectionManager.getInstance().getZHApi().getReadAndUnreadMsg(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response != null){
                            //    mCallBack.getSuccessDataCallBack(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null){
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
    public void sendMsgDetail(String keyId){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("kmdMsgText", "正文");
            jsonObject.put("kmdKfmId", keyId);
            KDConnectionManager.getInstance().getZHApi().sendMsgDetail(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response != null){
                                //    mCallBack.getSuccessDataCallBack(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null){
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
    public void udpateMessage(String keyId){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("kfmId", keyId);
            KDConnectionManager.getInstance().getZHApi().udpateMessage(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response != null){
                                //    mCallBack.getSuccessDataCallBack(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null){
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
    public void selectClassInfo(String keyId){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            KDConnectionManager.getInstance().getZHApi().selectClassInfo(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            if (mCallBack != null && response != null){
                                //    mCallBack.getSuccessDataCallBack(response);
                            }
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {
                            if (mCallBack != null){
                                mCallBack.getFailDataCallBack(-1);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //---------------------------------------------------
    private DataCallBack mCallBack;

    public DataCallBack getmCallBack() {
        return mCallBack;
    }

    public void setmCallBack(DataCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public interface DataCallBack<T>{
        void getSuccessDataCallBack(T data);
        void getFailDataCallBack(int failIndex);

    }

}
