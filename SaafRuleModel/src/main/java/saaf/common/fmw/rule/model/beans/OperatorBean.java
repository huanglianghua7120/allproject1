package saaf.common.fmw.rule.model.beans;

public class OperatorBean {
    private String operatorCode;
    private String operatorName;

    public OperatorBean() {

    }

    public OperatorBean(String operatorCode, String operatorName) {
        this.operatorCode = operatorCode;
        this.operatorName = operatorName;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorName() {
        return operatorName;
    }
}

