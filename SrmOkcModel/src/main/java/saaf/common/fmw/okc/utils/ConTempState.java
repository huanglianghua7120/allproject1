package saaf.common.fmw.okc.utils;

/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：ConTempState.java
 * Description：合同模版状态
 * <p>
 * Update History
 * ==============================================================================
 * Version    Date     @Author(Updated By)     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019/5/31      欧阳岛          创建
 * <p>
 * ==============================================================================
 */
public enum ConTempState {
    DRAFT("DRAFT", "拟定状态", "保存"), SUBMITTED("SUBMITTED", "审批中状态", "提交"),
    APPROVED("APPROVED", "批准状态", "审批"), REJECTED("REJECTED", "驳回状态", "驳回"), DISABLED("DISABLED", "失效状态", "失效");
    private final String value;
    private final String name;
    private final String opt;

    private ConTempState(String value, String name, String opt) {
        this.value = value;
        this.name = name;
        this.opt = opt;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getOpt() {
        return opt;
    }

}
