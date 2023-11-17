package fastjson.jsonpath;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;

public class Usage {

    private static String jsonStr = "{\"_id\":\"6518f1fbf8e7bf7c32854e9e\",\"affiliatedUnit\":{},"
        + "\"applyNo\":\"NC-A0000017\",\"capitalCode\":\"ZF-000011\",\"capitalNo\":\"\","
        + "\"carInfo\":{\"applyNo\":\"NC-A0000017\",\"brandId\":\"0105\",\"brandName\":\"沃尔沃\","
        + "\"evaluatingPrice\":\"0\",\"guidePrice\":\"396900\",\"isGreen\":\"0\",\"isInternet\":\"0\","
        + "\"mileage\":\"0\",\"modelId\":\"246\",\"modelName\":\"XC60\",\"optionalPrice\":0,"
        + "\"purchaseCity\":\"320600\",\"purchaseProvince\":\"32\",\"salePrice\":\"276000\","
        + "\"secondSalePrice\":\"0\",\"specialBusiness\":\"no\",\"styleId\":\"246L1670C161\",\"styleName\":\"B5 四驱 "
        + "智逸豪华版\",\"transNums\":0,\"vehicleId\":\"7729674495405088916\",\"yearStyle\":\"2024\",\"yearTransNum\":0},"
        + "\"certNo\":\"320623199101182361\",\"channelInfo\":{\"applyNo\":\"NC-A0000017\","
        + "\"approveRecordUserName\":\"NTB-NTBQT\",\"approveReportUserName\":\"NTB-NTBQT\",\"channelBelong\":\"03\","
        + "\"channelCity\":\"320600\",\"channelCode\":\"NTB\",\"channelId\":196,"
        + "\"channelName\":\"南通文峰恒信行汽车销售服务有限公司\",\"channelProvince\":\"32\"},"
        + "\"comAttachementFile\":[{\"applyNo\":\"NC-A0000017\",\"createTime\":\"1696133627000\","
        + "\"fileMd5\":\"2f24c06caaa843a7b448be254459f22e\",\"fileName\":\"申请人身份证人像面.jpg\",\"fileType\":\"jpg\","
        + "\"isElectronic\":\"0\",\"uniqueType\":\"mainBorrowerIdCardFront\"},{\"applyNo\":\"NC-A0000017\","
        + "\"createTime\":\"1696133627000\",\"fileMd5\":\"07ae9d98a4e24afea710f4ff2c53c324\",\"fileName\":\"申请人身份证国徽面"
        + ".jpg\",\"fileType\":\"jpg\",\"isElectronic\":\"0\",\"uniqueType\":\"mainBorrowerIdCardBack\"},"
        + "{\"applyNo\":\"NC-A0000017\",\"createTime\":\"1696133627000\","
        + "\"fileMd5\":\"0bf02c6e15c4490d9bf7fd73505154ef\",\"fileName\":\"沃尔沃汽车金融服务隐私政策_CG-A0000096.PDF\","
        + "\"fileType\":\"PDF\",\"isElectronic\":\"1\",\"uniqueType\":\"chuliankejifuwuxieyi\"},"
        + "{\"applyNo\":\"NC-A0000017\",\"createTime\":\"1696133627000\","
        + "\"fileMd5\":\"18b9fe51b4ff409fb4d89bb2bd69cee5\",\"fileName\":\"客户同意声明函_CG-A0000096.PDF\","
        + "\"fileType\":\"PDF\",\"isElectronic\":\"1\"}],\"costDetails\":[{\"algorithmType\":\"cardloan\","
        + "\"annualInterestRate\":\"23.0\",\"applyNo\":\"NC-A0000017\",\"basicPoint\":0,"
        + "\"carId\":\"7917451427658703109\",\"contractAmt\":\"276000.00\",\"costType\":\"01\","
        + "\"customerCardRate\":\"23.0\",\"custRate\":\"23.00\",\"discountAmt\":\"0.00\",\"discountType\":\"0\","
        + "\"disRateCal\":\"0.00\",\"downPayAmt\":\"56000.00\",\"downPayScale\":\"20.28\",\"iSFarmerLoan\":\"0\","
        + "\"isTail\":\"0\",\"loanAmt\":\"220000.00\",\"loanTerm\":60,\"monthPayAmt\":\"4510.00\","
        + "\"productId\":\"7917451152781254756\",\"productName\":\"标贷产品\",\"productNumber\":\"PD20230097\","
        + "\"rateType\":\"0\",\"repaymentMethod\":\"7\",\"sameProduct\":false,\"settleRate\":\"23.00\","
        + "\"tailPayAmt\":\"0.00\",\"tailPayScale\":\"0.00\",\"totalInterest\":\"50600.00\",\"totalRent\":\"270600"
        + ".00\"},{\"algorithmType\":\"cardloan\",\"annualInterestRate\":\"0\",\"applyNo\":\"NC-A0000017\","
        + "\"basicPoint\":0,\"contractAmt\":\"0\",\"costType\":\"02\",\"customerCardRate\":\"0\",\"custRate\":\"0\","
        + "\"discountAmt\":\"0\",\"discountRate\":\"0\",\"discountType\":\"0\",\"disRateCal\":\"0\","
        + "\"downPayAmt\":\"0\",\"downPayScale\":\"0\",\"isTail\":\"0\",\"loanAmt\":\"0\",\"loanTerm\":0,"
        + "\"maxDiscountAmt\":\"0\",\"monthPayAmt\":\"0\",\"productId\":\"7917451152781254756\","
        + "\"productName\":\"标贷产品\",\"repaymentMethod\":\"7\",\"sameProduct\":false,\"settleRate\":\"0\","
        + "\"tailPayAmt\":\"0\",\"tailPayScale\":\"0\",\"totalInterest\":\"0\",\"totalRent\":\"0\"},"
        + "{\"applyNo\":\"NC-A0000017\",\"carId\":\"7917451427658703109\",\"contractAmt\":\"276000.00\","
        + "\"costType\":\"03\",\"discountAmt\":\"0.00\",\"downPayAmt\":\"56000.00\",\"loanAmt\":\"220000.00\","
        + "\"maxDiscountAmt\":\"0\",\"monthPayAmt\":\"4510.00\",\"sameProduct\":false,\"tailPayAmt\":\"0.00\","
        + "\"tailPayScale\":\"0.00\",\"totalInterest\":\"50600.00\",\"totalRent\":\"270600.00\"}],"
        + "\"custAddressDetail\":[{\"addressType\":\"1\",\"applyNo\":\"NC-A0000017\",\"city\":\"320600\","
        + "\"county\":\"320623\",\"custRole\":\"01\",\"detailAddress\":\"江苏省如东县长沙镇北坎村十六组335号\","
        + "\"isDefault\":\"null\",\"province\":\"32\"},{\"addressType\":\"2\",\"applyNo\":\"NC-A0000017\","
        + "\"city\":\"320600\",\"county\":\"320682\",\"custRole\":\"01\",\"detailAddress\":\"如皋市江中世纪城\","
        + "\"houseType\":\"01\",\"isDefault\":\"true\",\"postalCode\":\"226500\",\"province\":\"32\","
        + "\"street\":\"320682001\"},{\"addressType\":\"3\",\"applyNo\":\"NC-A0000017\",\"city\":\"320600\","
        + "\"county\":\"320682\",\"custRole\":\"01\",\"detailAddress\":\"如皋市如城镇\",\"isDefault\":\"false\","
        + "\"postalCode\":\"226500\",\"province\":\"32\"}],\"custBaseInfo\":[{\"applyNo\":\"NC-A0000017\","
        + "\"certNo\":\"320623199101182361\",\"certType\":\"00001\",\"custRole\":\"01\",\"custType\":\"0\","
        + "\"fullName\":\"夏丽婷\",\"isLongTerm\":false,\"telPhone\":\"18795744118\"}],"
        + "\"custContacts\":[{\"applyNo\":\"NC-A0000017\",\"custName\":\"陈晨\",\"custRelation\":\"00006\","
        + "\"telPhone\":\"15295648194\"},{\"applyNo\":\"NC-A0000017\",\"custName\":\"夏建兵\","
        + "\"custRelation\":\"00104\",\"telPhone\":\"15262742585\"}],\"custPersonalDetail\":[{\"age\":\"32.70\","
        + "\"applyNo\":\"NC-A0000017\",\"birthday\":\"1991-01-18\",\"custLastname\":\"夏\",\"custName\":\"丽婷\","
        + "\"custRole\":\"01\",\"familyNumbers\":0,\"highestEducation\":\"20\",\"industryType\":\"00003\","
        + "\"maritalStatus\":\"00001\",\"monthlyIncome\":\"15000.00\",\"monthlyPayment\":\"0.00\","
        + "\"monthlySpend\":\"0.00\",\"nationality\":\"00001\",\"position\":\"00005\",\"sex\":\"F\","
        + "\"spellLastname\":\"Xia\",\"spellName\":\"LiTing\",\"supplementHouseStartDate\":\"2023-10-01 12:13:46\","
        + "\"unitName\":\"江苏九鼎模具新材料有限公司\",\"unitNature\":\"40\",\"workAge\":\"7\"}],\"DELFLAG\":\"0\","
        + "\"discountDetails\":[{\"applyNo\":\"NC-A0000017\",\"costId\":\"7917451427658703120\",\"costType\":\"01\","
        + "\"discountId\":\"0\"},{\"applyNo\":\"NC-A0000017\",\"costId\":\"7917451427658703120\",\"costType\":\"01\","
        + "\"discountId\":\"1\"}],\"id\":\"6518f1fbf8e7bf7c32854e9e\",\"ISODATE\":\"2023-10-01 12:13:46\","
        + "\"orderInfo\":{\"affiliatedWay\":\"0\",\"agencyMethod\":\"1\",\"applyNo\":\"NC-A0000017\","
        + "\"applyReporter\":\"高燕\",\"businessType\":\"01\",\"capitalName\":\"招商银行\",\"carNature\":\"01\","
        + "\"carPurpose\":\"00\",\"carType\":\"00\",\"custType\":\"0\",\"dealerName\":\"南通文峰恒信行汽车销售服务有限公司\","
        + "\"incomingChannel\":\"oneNetwork\",\"intoFirstDate\":\"2023-10-01 12:13:47\",\"isMortgage\":\"N\","
        + "\"locationInfo\":[{\"lat\":0,\"lng\":0,\"type\":\"106\"},{\"lat\":0,\"lng\":0,\"type\":\"107\"},"
        + "{\"lat\":0,\"lng\":0,\"type\":\"108\"}],\"operateWay\":\"00\",\"platType\":\"01\","
        + "\"productId\":\"7917451152781254756\",\"productName\":\"标贷产品\",\"salesMethod\":\"retail\","
        + "\"temporaryNumber\":\"CG-A0000096\"}}";

    public static void main(String[] args) {
        JSONObject input = JSON.parseObject(jsonStr);
        JSONObject output = new JSONObject();
        //System.out.println(JSONPath.eval(jsonStr, "$.applyNo"));
        JSONPath.set(output,"$.businessNo",JSONPath.eval(jsonStr,"$.applyNo"));
        JSONPath.set(output,"$.preApproveInfo.agentCode",JSONPath.eval(jsonStr,"$.channelInfo.channelCode"));

        System.out.println(JSONPath.eval(jsonStr,"$.custAddressDetail[custRole='01'][addressType='1'].province[0]"));
        //JSONPath.set(output,"$.preApproveInfo.censusProCityDistrict",JSONPath.eval(jsonStr,"$.custAddressDetail[addressType='1'].province"));

        System.out.println(output);
    }
}
