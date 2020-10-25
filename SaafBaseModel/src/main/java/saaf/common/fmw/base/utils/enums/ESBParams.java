package saaf.common.fmw.base.utils.enums;

/**
 * @author: ZHJ
 * @date:Created in 15:44 2019/10/30
 * @modify By:
 * @description :
 */
public class ESBParams {
    /**
     * 服务接口程序名
     */
    public enum ServiceName{
        /**
         * EBS的服务接口
         */
        callSrmProcedure("callSrmProcedure"),
        /**
         * EKP认证流程单号查询接口(EKP->SRM),带有主题、需求描述
         */
        EKP_MTERIAL_CRTIFICATION("ekpMterialCrtification"),
        /**
         * EKP认证流程单号查询接口(EKP->SRM),只带有主题
         */
        EKP_REVIEW_MAIN("ekpReviewMain"),
        /**
         * EKP 常备材料新增/取消
         **/
        EKP_STANDING_MATERIALS("getEkpStandingMaterials"),
        /**
         * EKP 行政类物料请购
         **/
        EKP_SADMINISTRATIVE_MATERIAL("getEkpAdministrativeMaterial"),
        /**
         * EKP 请购IT用品
         **/
        EKP_IT_SUPPLIES("getITSupplies"),
        /**
         * EKP 消防安全及环保类设施请购
         **/
        EKP_SAFETY_ENVIRONMENTALF("getEkpSafetyEnvironmentalF"),
        /**
         * EKP 生产类物料请购
         **/
        EKP_PRODUCT_MATERIAL_REQ("ekpProductMaterialReq"),
        /**
         * EKP 原材料物料新建询价
         **/
        EKP_EBS_RAW_IMPORT_SERVICE("ekpEbsRawImportService"),
        /**
         * 查询流程附件属性
         */
        EKP_REVIEWFILE_SERVICE("ekpReviewfileService"),
        /**
         * srm金属材料回填接口
         **/
        MATERIALS_INQUIRY_BACK_FILL("materialsInquiryBackFill"),

        /**
         * 来料检验(ESB->SRM)
         */
        GET_IQC_CHECK_RESULT("getIQCCheckResult"),
        /**
         * 制程不良(EKP->srm)
         */
        GET_A8_ONLINE_EXCEPTION_HANDLING("getA8OnLineExceptionHandling"),
        /**
         * PCN变更EKP查询结果
         */
        EKP_SUPPLIER_PCN("ekpSupplierPcn"),
        /**
         * 索赔修改金额和币种,EKP查询
         */
        GET_EKP_AP_SUPPLIER_CLAIM("getEkpApSupplierClaim"),
        /**
         * 单工序计价
         */
        GET_ITEM_ESL("getItemEsl"),
        /**
         * 报废接口
         */
        QUERY_SCRAP("queryScrap"),
        /**
         * 物资编码接口(EKP-->SRM)
         */
        EKP_EBS_RAW("ekpEbsRaw"),
        //流程节点查询
        EKP_REVIEW_NODE("ekpReviewNode"),
        /**
         * 外包送货单
         */
        QUERY_EXT_PACKING("queryExtPacking");



        private String value;
        private ServiceName(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }
    /**
     * 实际调用接口程序名
     */
    public enum BusinessServiceName{
        /**
         * SCC_银行分行信息查询接口——导出(EBS—>SRM)
         */
        REST_SRM_PULLBKBBRHLIST("REST_SRM_PULLBKBBRHLIST"),
        /**
         * SCC_银行信息查询接口——导出(EBS—>SRM)
         */
        REST_SRM_PULLBANKLIST("REST_SRM_PULLBANKLIST"),
        /**
         * SCC_员工信息查询接口——导出(EBS—>SRM)
         */
        REST_SRM_PULLEMPLYLIST("REST_SRM_PULLEMPLYLIST"),
        /**
         * SCC_采购申请查询接口——导出(EBS—>SRM)
         */
        REST_SRM_PUSHPOREQLIST("REST_SRM_PUSHPOREQLIST"),
        /**
         * SCC_采购订单推送EBS接口(SRM->EBS)
         */
        REST_SRM_PUSHPOORDLIST("REST_SRM_PUSHPOORDLIST"),
        REST_SRM_PUSHRCVLIST("REST_SRM_PUSHRCVLIST"),
        /**
         * SCC_币种查询接口——导出(EBS—>SRM)
         */
        REST_SRM_PULLCURRLIST("REST_SRM_PULLCURRLIST"),
        /**
         *一揽子报价单推送接口(SRM->EBS)
         */
        REST_SRM_PUSHPOBLKLIST("REST_SRM_PUSHPOBLKLIST"),
        /**
         *  来源补充规则推送接口(SRM——>EBS)(SRM->EBS)
         */
        REST_SRM_PUSHSRCRULELIST("REST_SRM_PUSHSRCRULELIST"),
        /**
         * 供应商档案信息接口——导入(SRM——>EBS)
         */
        REST_SRM_PUSHPOVENDLIST("REST_SRM_PUSHPOVENDLIST"),
        /**
         * ASL信息接口==批准供应商接口——导入(SRM——>EBS)
         */
        REST_SRM_PUSHASLLIST("REST_SRM_PUSHASLLIST"),
        /**
         * 物料信息查询接口——导出(EBS——>SRM)
         */
        REST_SRM_PULLITEMSLIST("REST_SRM_PULLITEMSLIST"),
        /**
         * 付款条件查询接口——导出(EBS——>SRM)
         */
        REST_SRM_PULLPAYTERMLIST("REST_SRM_PULLPAYTERMLIST"),

        //技改编号/子项目编号接口
        REST_SRM_PULLJGLIST("REST_SRM_PULLJGLIST"),

        /**
         * SCC_每日汇率查询接口——导出(EBS—>SRM)
         */
        REST_SRM_PULLDRATELIST("REST_SRM_PULLDRATELIST");
        private String value;
        private BusinessServiceName(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }
    /**
     * 目标系统/源系统
     */
    public enum SystemName{
        PCB_APS("PCB_APS"),
        PCBA_APS("PCBA_APS"),
        APS("APS"),
        SRM("SRM"),
        E_SOURCE("E-SOURCE"),
        ESB("ESB"),
        EBS("EBS"),
        ERP("ERP"),
        PCBA_E_SOURING("PCBA_E-SOURING"),
        PCB_SRM("PCB_SRM"),
        INPLAN("INPLAN"),
        EKP("EKP"),
        IMES("IMES"),
        A8("A8"),
        SRM_SERVER("SRM_SERVER"),
        SCC_SRM("SCC_SRM");
        private String value;
        private SystemName(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }

    /**
     * 返回的状态码
     */
    public enum StatusCode{
        /**
         * 系统参数字段名
         */
        SYSTEM_PARAMS("systemParams"),
        /**
         * 业务响应内容的字段名
         */
        BUSINESS_DATA("businessData"),
        /**
         * 同步数据返回的状态字段名
         */
        PROCESS_CODE("PROCESS_CODE"),
        /**
         * 同步数据返回的提示信息
         */
        PROCESS_MSG("PROCESS_MSG"),
        /**
         * 返回的状态字段名
         */
        ESB_CODE("esbCode"),
        /**
         * 返回的提示信息
         */
        ESB_DESC("esbDesc"),
        /**
         * 返回成功的状态码
         */
        ESB_CODE_OK("000000");
        private String value;
        private StatusCode(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }
}
