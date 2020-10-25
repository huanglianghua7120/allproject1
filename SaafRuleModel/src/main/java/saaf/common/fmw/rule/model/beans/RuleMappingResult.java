package saaf.common.fmw.rule.model.beans;

import saaf.common.fmw.rule.model.entities.SaafWebserviceParamInfoEntity_HI;

import java.util.List;

/**
 * Created by Admin on 2017/8/2.
 */
public class RuleMappingResult {
    private boolean success;
    private String message;
    private List<Action> actions;

    public RuleMappingResult() {
    }

    public RuleMappingResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RuleMappingResult(boolean success, String message, List<Action> actions) {
        this.success = success;
        this.message = message;
        this.actions = actions;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public class Action{
        private String dimName;
        private String dimValue;
        private String operator;
        private String busTargetType;
        private String busTargetSource;
        private String webserviceUrl;
        private List<SaafWebserviceParamInfoEntity_HI> params;

        public Action() {
        }

        public String getWebserviceUrl() {
            return webserviceUrl;
        }

        public void setWebserviceUrl(String webserviceUrl) {
            this.webserviceUrl = webserviceUrl;
        }

        public List<SaafWebserviceParamInfoEntity_HI> getParams() {
            return params;
        }

        public void setParams(List<SaafWebserviceParamInfoEntity_HI> params) {
            this.params = params;
        }

        public String getDimName() {
            return dimName;
        }

        public void setDimName(String dimName) {
            this.dimName = dimName;
        }

        public String getDimValue() {
            return dimValue;
        }

        public void setDimValue(String dimValue) {
            this.dimValue = dimValue;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getBusTargetType() {
            return busTargetType;
        }

        public void setBusTargetType(String busTargetType) {
            this.busTargetType = busTargetType;
        }

        public String getBusTargetSource() {
            return busTargetSource;
        }

        public void setBusTargetSource(String busTargetSource) {
            this.busTargetSource = busTargetSource;
        }

    }


}
