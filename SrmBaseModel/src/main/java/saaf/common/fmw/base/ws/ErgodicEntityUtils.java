package saaf.common.fmw.base.ws;

import java.lang.reflect.Field;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ErgodicEntityUtils {

    public static void main(String[] args) {
    }

    public static <T> T ergodicEntity(T row,T paramRow) {
        T obj=null;
        Class cls = row.getClass();
        Class paramCls = paramRow.getClass();
        Field[] fields = cls.getDeclaredFields();
        Field[] paramFields = paramCls.getDeclaredFields();
        try {
            for(int j=1; j<fields.length; j++){
                Field f = fields[j];
                Field p = paramFields[j];
                f.setAccessible(true);
                p.setAccessible(true);
                String type = p.getGenericType().toString();
                String view = String.valueOf(p.get(paramRow));
                if("null".equals(view)||"".equals(view.trim())){
                    view = null;
                }
                if ("class java.lang.String".equals(type)){
                    f.set(row,view);
                } else if(("class java.lang.Integer").equals(type)){
                    if(view != null){
                        f.set(row,Integer.parseInt(view));
                    } else {
                        f.set(row,null);
                    }
                } else if(("class java.util.Date").equals(type)){
                    if(view != null){
                        SimpleDateFormat formatter = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
                        ParsePosition pos = new ParsePosition(0);
                        Date strtodate = formatter.parse(view, pos);
                        f.set(row,strtodate);
                    } else {
                        f.set(row,null);
                    }
                }
            }
        } catch (Exception e) {
            obj=null;
        }
        return row;
    }

}
