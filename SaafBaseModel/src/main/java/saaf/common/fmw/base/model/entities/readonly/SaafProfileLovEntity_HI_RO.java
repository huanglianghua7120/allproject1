package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;

public class SaafProfileLovEntity_HI_RO implements Serializable {

    public static String QUERY_SQL = " SELECT *\r\n" +
        "  FROM (SELECT sr.responsibility_id \"levelId\"\r\n" +
        "               ,sr.responsibility_name \"levelName\"\r\n" +
        "               ,sr.responsibility_key \"levelDes\"\r\n" +
        "               ,'R' \"leve\"\r\n" +
        "          FROM saaf_responsibilitys sr\r\n" +
        "        UNION ALL\r\n" +
        "        SELECT sf.menu_id levelid\r\n" +
        "              ,sf.menu_tittle levelname\r\n" +
        "              ,sf.prompt leveldes\r\n" +
        "              ,'F' leve\r\n" +
        "          FROM saaf_menu_functions sf\r\n" +
        "        UNION ALL\r\n" +
        "        SELECT sou.inst_id levelid\r\n" +
        "              ,sou.inst_name levelname\r\n" +
        "              ,sou.inst_code leveldes\r\n" +
        "              ,'O' leve\r\n" +
        "          FROM saaf_institution sou\r\n" +
        "        UNION ALL\r\n" +
        "        SELECT su.user_id levelid\r\n" +
        "              ,su.user_name levelname\r\n" +
        "              ,su.user_full_name leveldes\r\n" +
        "              ,'U' leve\r\n" +
        "          FROM saaf_users su) t\r\n";

    private int levelId;
    private String levelName;
    private String levelDes;
    private String leve;

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelDes(String levelDes) {
        this.levelDes = levelDes;
    }

    public String getLevelDes() {
        return levelDes;
    }

    public void setLeve(String leve) {
        this.leve = leve;
    }

    public String getLeve() {
        return leve;
    }
}
