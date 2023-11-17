package com.wusp.util;

import com.alibaba.fastjson.JSONObject;

import org.junit.jupiter.api.Test;

public class FastJsonUtilTest {

    private static String expectValue = "<empty>";

    private static void defaultValueToEmpty(JSONObject jsonObject, String key) {
        FastJsonUtil.convertsExpectValueToSpecifiedValue(jsonObject, key, "<empty>", "");
    }

    @Test
    public void testConvertsExpectValueToSpecifiedValue() {
        JSONObject preApproveInfo = new JSONObject();
        //1、婚姻选择“其他”时，码值无法转换，传空值给思享驾
        preApproveInfo.put("marriage", expectValue);
        System.out.println(preApproveInfo.toJSONString());
        FastJsonUtil.convertsExpectValueToSpecifiedValue(preApproveInfo, "marriage", expectValue, "");
        System.out.println(preApproveInfo.toJSONString());
        System.out.println("=============================================================================");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("marriage", "lalala");
        System.out.println(jsonObject2.toJSONString());
        FastJsonUtil.convertsExpectValueToSpecifiedValue(jsonObject2, "marriage", expectValue, "");
        System.out.println(jsonObject2.toJSONString());
        System.out.println("=============================================================================");
        JSONObject jsonObject3 = new JSONObject();
        FastJsonUtil.convertsExpectValueToSpecifiedValue(jsonObject3, "marriage", expectValue, "");
        System.out.println(jsonObject3);
        System.out.println("=============================================================================");
        FastJsonUtil.convertsExpectValueToSpecifiedValue(null, "marriage", expectValue, "");
        System.out.println("=============================================================================");
    }

    @Test
    public void test2() {

        JSONObject params = new JSONObject();

        JSONObject iPreApproveInfo = new JSONObject();
        iPreApproveInfo.put("marriage", expectValue);
        iPreApproveInfo.put("censusProCityDistrict", expectValue);
        params.put("preApproveInfo", iPreApproveInfo);

        JSONObject iPersonalInfo = new JSONObject();
        iPersonalInfo.put("custAddress", expectValue);
        params.put("personalInfo", iPersonalInfo);

        JSONObject iPersonalEmpInfo = new JSONObject();
        iPersonalEmpInfo.put("companyProCityDistrict", expectValue);
        params.put("personalEmpInfo", iPersonalEmpInfo);

        //params.put("financeInfo",new JSONObject());
        System.out.println(params.toJSONString());

        JSONObject preApproveInfo = params.getJSONObject("preApproveInfo");
        defaultValueToEmpty(preApproveInfo, "marriage");
        defaultValueToEmpty(preApproveInfo, "censusProCityDistrict");
        params.put("preApproveInfo", preApproveInfo);

        //3、居住地址无法匹配的情况
        JSONObject personalInfo = params.getJSONObject("personalInfo");
        defaultValueToEmpty(personalInfo, "custAddress");
        params.put("personalInfo", personalInfo);

        //公司地址-省市区
        JSONObject personalEmpInfo = params.getJSONObject("personalEmpInfo");
        defaultValueToEmpty(personalEmpInfo, "companyProCityDistrict");
        params.put("personalEmpInfo", personalEmpInfo);

        JSONObject financeInfo = params.getJSONObject("financeInfo");
        if (financeInfo != null) {
            financeInfo.putIfAbsent("downPaymentAmount", "0");
            financeInfo.putIfAbsent("tailPaymentAmount", "0");
            params.put("financeInfo", financeInfo);
        }
        System.out.println(params.toJSONString());
    }
}
