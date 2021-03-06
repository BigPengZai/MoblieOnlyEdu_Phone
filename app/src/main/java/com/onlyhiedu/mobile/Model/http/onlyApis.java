package com.onlyhiedu.mobile.Model.http;


import com.onlyhiedu.mobile.Model.bean.StudentInfo;
import com.onlyhiedu.mobile.Model.bean.UserDataBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by xuwc on 2016/11/24.
 */
public interface onlyApis {


    String HOST = "http://192.168.1.182:8080/";

    /**
     * 客户端登录
     *
     * @param phone
     * @param password
     * @param deviceType
     * @param userType
     * @return
     */
    @FormUrlEncoded
    @POST("client/user/login")
    Flowable<onlyHttpResponse<UserDataBean>> getUser(@Field("phone") String phone, @Field("password") String password, @Field("timestamp") Long timestamp, @Field("deviceType") String deviceType, @Field("userType") String userType);

    /**
     * 获取学生信息
     *
     * @param token
     * @return
     */
    @POST("client/user/getStudentInfo")
    Flowable<onlyHttpResponse<StudentInfo>> getStudentInfo();

    /**
     * 修改学生性别
     * @param sex
     * @return
     */
    @FormUrlEncoded
    @POST("client/student/updateSex")
    Flowable<onlyHttpResponse> updateSex(@Field("sex") int sex);


    /**
     * 修改学生年级
     * @param sex
     * @return
     */
    @FormUrlEncoded
    @POST("client/student/updateGrade")
    Flowable<onlyHttpResponse> updateGrade(@Field("grade") String grade);


    @FormUrlEncoded
    @POST("client/student/updateExamArea")
    Flowable<onlyHttpResponse> updateExamArea(@Field("examArea") String examArea);
}
