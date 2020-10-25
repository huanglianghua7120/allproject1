package saaf.common.fmw.base.model.inter;

import saaf.common.fmw.base.model.entities.SaafBaseOperlogEntity_HI;

public interface ISaafBaseOperlog {
    void saveOperLog(SaafBaseOperlogEntity_HI operlog) throws Exception;

}
