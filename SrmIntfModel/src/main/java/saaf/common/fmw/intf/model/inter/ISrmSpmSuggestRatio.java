package saaf.common.fmw.intf.model.inter;

public interface ISrmSpmSuggestRatio {

    /**
     * 绩效结果应用供货比例建议
     *
     * @param schemeId 绩效方案ID
     * @return
     * @throws Exception
     */
    String saveSuggestRatio(Integer schemeId) throws Exception;

}
