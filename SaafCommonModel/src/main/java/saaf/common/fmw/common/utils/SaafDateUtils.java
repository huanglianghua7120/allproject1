package saaf.common.fmw.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 比较两个日期区间工具类
 * 是否相同checkSameEdge，
 * 重叠contain，
 * @author luofenwu
 *
 */
public class SaafDateUtils {

    /**
     * 字符串转日期类型
     * @param str
     * @return
     */
    public static Date string2UtilDate(String dateStr, String formatStr) {
        if (null == formatStr) {
            formatStr = "yyyy-MM-dd";
        }
        DateFormat format = new SimpleDateFormat(formatStr);
        java.util.Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return date;
    }

    /**
     * 比较两个日期是不是在同一个自然天内
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return true/false
     */
    public static boolean checkSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyyMMdd");
        return formatTime.format(date1).equals(formatTime.format(date2));
    }

    // 比较两个日期相差的天数 相对日期来查

    public static long getDistDatesExt(Date startDate, Date endDate) {
        long totalDate = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStartString = format.format(startDate);
        String dateEndString = format.format(endDate);
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        sb1.append(dateStartString).append(" 00:00:00");
        sb2.append(dateEndString).append(" 00:00:00");
        try {
            startDate = sdf.parse(sb1.toString());
            endDate = sdf.parse(sb2.toString());
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        long timestart = startDate.getTime();
        long timeend = endDate.getTime();
        totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
        System.out.println(startDate + " 与 " + endDate + "相差天数================" + totalDate);
        return totalDate;
    }

    public static class DateInterval {
        private Date startDate;
        private Date endDate;
        private List<Date> intervalDateList = new ArrayList<Date>(); //时间区间内的日期list

        private DateInterval(Date startDate, Date endDate) {
            super();
            this.startDate = startDate;
            this.endDate = endDate;
            this.init();
        }

        private void init() {
            if (startDate == null || endDate == null) {
                throw new IllegalArgumentException("params error:startDate and endDate can not be null!    startDate和endDate参数不能为空");
            }
            if (startDate.after(endDate)) {
                throw new IllegalArgumentException("params error：startDate can not afeter endDate!please check your params!  startDate不能在endDate 之后");
            }
            intervalDateList.add(startDate);
            long distDatesExt = getDistDatesExt(startDate, endDate);
            Calendar c = Calendar.getInstance();
            for (int i = 1; i < distDatesExt; i++) {
                c.setTime(startDate);
                c.add(Calendar.DAY_OF_MONTH, i);
                intervalDateList.add(c.getTime());
            }
            intervalDateList.add(endDate);
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public List<Date> getIntervalDateList() {
            return intervalDateList;
        }

        /**
         * this区间是否与参数区间重叠
         * @param dateInterval
         * @return
         */
        public boolean contain(DateInterval dateInterval) {
            List<Date> thisIntervalDate = this.getIntervalDateList();
            List<Date> otherIntervalDate = dateInterval.getIntervalDateList();
            for (Date thisD : thisIntervalDate) {
                for (Date otherD : otherIntervalDate) {
                    if (checkSameDay(thisD, otherD)) {
                        return true;
                    }
                }
            }

            return false;
        }

        /**
         * this区间与参数区间是否相同
         * @param dateInterval
         * @return
         */
        public boolean checkSameEdge(DateInterval dateInterval) {
            return (checkSameDay(this.getStartDate(), dateInterval.getStartDate()) && checkSameDay(this.getEndDate(), dateInterval.getEndDate()));
        }

        /**
         * 判断两个DateInterval单元的首尾端点是否相隔指定时间
         * @param dateInterval
         * @param intervalDay 两个日期相隔几天
         * @return
         */
        public boolean edgeInvCheck(DateInterval dateInterval, int intervalDay) {
            if (getDistDatesExt(this.getEndDate(), dateInterval.getStartDate()) < intervalDay) {
                return false;
            }

            if (getDistDatesExt(this.getStartDate(), dateInterval.getEndDate()) < intervalDay) {
                return false;
            }
            return true;
        }
    }

    public static void print(List<DateInterval> list) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("print begin=======================================");
        for (DateInterval di : list) {
            for (Date d : di.getIntervalDateList()) {
                System.out.println("di:" + df.format(d));
            }
        }
        System.out.println("print end========================================");
    }

    /***
     * 判断时间区间是否重叠
     * @param listTocheck 源有时间区间list
     * @param diToCheck 需要检验的时间区间
     * @return
     */
    public static boolean checkListContain(List<DateInterval> listTocheck, DateInterval diToCheck) {
        boolean checkFlag = false;
        for (DateInterval di : listTocheck) {
            System.out.println("是否同一区间checkSameEdge:" + diToCheck.checkSameEdge(di));
            System.out.println("是否包含 contain:" + diToCheck.contain(di));
            System.out.println("首尾端点是否相隔指定n天 edgeInvCheck:" + diToCheck.edgeInvCheck(di, 1));
            System.out.println("unit check ret:" + (diToCheck.checkSameEdge(di) || (!diToCheck.contain(di) && diToCheck.edgeInvCheck(di, 1))));
            if (diToCheck.contain(di)) {
                checkFlag = true;
            }
        }
        return checkFlag;
    }

    /**
     * 判断两个时间区间是否重叠
     * @param startDate1 区间1起始日期
     * @param endDate1 区间1终止日期
     * @param startDate2 区间2起始日期
     * @param endDate2 区间2终止日期
     * @return true重叠，false不重叠
     */
    public static boolean checkContain(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
        if (endDate1 == null) {
            endDate1 = string2UtilDate("2099-12-31", null);
        }
        if (endDate2 == null) {
            endDate2 = string2UtilDate("2099-12-31", null);
        }
        DateInterval di = new DateInterval(startDate1, endDate1);
        DateInterval di2 = new DateInterval(startDate2, endDate2);
        System.out.println(startDate1 + " 到 " + endDate1 + " 与 " + startDate2 + " 到 " + endDate2 + " 是否重叠======= " + di2.contain(di));
        return di2.contain(di);
    }

    /**
     * 获取两个月份区间中的中间月份
     * @param oldMonth 201602
     * @param newMonth 201605
     * @return {201603,201604}
     * @throws ParseException
     */
    public static List getFrom2Month(String oldMonth, String newMonth) throws ParseException {
        List list = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        Date oldDate = df.parse(oldMonth);
        Date newDate = df.parse(newMonth);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.MONTH, 1);
        oldDate = calendar.getTime();
        while (oldDate.compareTo(newDate) < 0) {

            String s = df.format(oldDate);
            list.add(s);
            calendar.add(Calendar.MONTH, 1);
            oldDate = calendar.getTime();
        }

        return list;
    }

    public static void main(String[] args) {
        DateInterval di = new DateInterval(string2UtilDate("2016-11-2", null), string2UtilDate("2016-11-8", null));
        DateInterval di2 = new DateInterval(string2UtilDate("2016-10-2", null), string2UtilDate("2016-11-1", null));
        List<DateInterval> list = new ArrayList<DateInterval>();
        list.add(di2);
        print(list);
        System.out.println("list内的n个时间区间与di是否有重叠？  checkListContain=" + checkListContain(list, di));
        System.out.println("di2区间包含di？-------->contain=" + di2.contain(di));


    }

}
