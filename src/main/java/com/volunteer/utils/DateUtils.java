package com.volunteer.utils;
import java.util.*;
import java.text.*;
//日期时间工具类 
public class DateUtils {
	
	public static int daysBetween(Date smdate,Date bdate) throws Exception {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
       return Integer.parseInt(String.valueOf(between_days));           
    } 
	
	
	//设置日期和时间的格式  
	//注意：用户自定义的日期时间格式必须与下面定义的日期时间格式的大小写完全一致。如：yyyy年mm月dd日
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");   
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");   
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss"); 
        
    //获得当前时间的对象      
    public static Date now() 
    {   
        return new Date();   
    } 
    
    //获得当前日期时间,日期时间格式yyyy-MM-dd HH:mm:ss
    public static String currentDatetime()
    {   
        return datetimeFormat.format(now());   
    }   
        
    //获得当前日期，日期格式yyyy-MM-dd     
    public static String currentDate() 
    {   
        return dateFormat.format(now());   
    }   
   
    //获得当前时间,时间格式HH:mm:ss    
    public static String currentTime()
    {   
        return timeFormat.format(now());   
    }   
        
    //格式化日期时间,日期时间格式yyyy-MM-dd HH:mm:ss     
    public static String formatDatetime(Date date)
    {   
        return datetimeFormat.format(date);   
    } 
    
  //自定义日期时间格式
    public static String formatDatetime(Date date, String pattern) 
    {   
        SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat.clone();   
        customFormat.applyPattern(pattern);   
        return customFormat.format(date);   
    }   
    
    //格式化日期,日期格式yyyy-MM-dd    
    public static String formatDate(Date date)
    {   
        return dateFormat.format(date);   
    } 
    
    //自定义日期格式
    public static String formatDate(Date date, String pattern) 
    {   
        SimpleDateFormat customFormat = (SimpleDateFormat) dateFormat.clone();   
        customFormat.applyPattern(pattern);   
        return customFormat.format(date);   
    }   
        
    //格式化时间,时间格式hh:mm:ss    
    public static String formatTime(Date date)
    {   
        return timeFormat.format(date);   
    }  
    
    //自定义时间格式
    public static String formatTime(Date date, String pattern) 
    {   
        SimpleDateFormat customFormat = (SimpleDateFormat) timeFormat.clone();   
        customFormat.applyPattern(pattern);   
        return customFormat.format(date);   
    }   
    
    //将字符串日期时间转换成java.util.Date类型 ,日期时间格式yyyy-MM-dd HH:mm:ss     
    public static Date parseDatetime(String datetime) throws ParseException 
    {   
        return datetimeFormat.parse(datetime);   
    }   
    
    //将字符串日期转换成java.util.Date类型,日期时间格式yyyy-MM-dd  
    public static Date parseDate(String date) throws ParseException 
    {   
        return dateFormat.parse(date);   
    }   
  
    //将字符串日期转换成java.util.Date类型,时间格式 HH:mm:ss     
    public static Date parseTime(String time) throws ParseException
    {   
        return timeFormat.parse(time);   
    }   
    
    //本地化时区和语言环境
    public static Calendar calendar()
    {   
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);   
        cal.setFirstDayOfWeek(Calendar.MONDAY);   
        return cal;   
    }
    
    //获得当前时间的毫秒数      
    public static long millis() 
    {   
        return System.currentTimeMillis();   
    }
    
    //获得当前Chinese月份
    public static int month()
    {   
        return calendar().get(Calendar.MONTH) + 1;   
    }
    
    //获得月份中的第几天   
    public static int dayOfMonth()
    {   
        return calendar().get(Calendar.DAY_OF_MONTH);   
    }   
    
    //今天是Chinese星期的第几天       
    public static int dayOfWeek() 
    {   
        return calendar().get(Calendar.DAY_OF_WEEK)-1;   
    }   
    
    //今天是年中的第几天     
    public static int dayOfYear()
    {   
        return calendar().get(Calendar.DAY_OF_YEAR);   
    }
    
    //判断原日期是否在目标日期之前     
    public static boolean isBefore(Date src, Date dst)
    {   
        return src.before(dst);   
    }   
    
    //判断原日期是否在目标日期之后
    public static boolean isAfter(Date src, Date dst)
    {   
        return src.after(dst);   
    }
    
    //判断两日期是否相同     
    public static boolean isEqual(Date date1, Date date2)
    {   
        return date1.compareTo(date2) == 0;   
    }   
    
    //判断某个日期是否在某个日期范围     
    public static boolean between(Date beginDate, Date endDate, Date src)
    {   
        return beginDate.before(src) && endDate.after(src);   
    }
    
    //获得当前月的最后一天        
    public static Date lastDayOfMonth()
    {   
        Calendar cal = calendar();   
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零   
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零   
        cal.set(Calendar.MINUTE, 0);// m置零   
        cal.set(Calendar.SECOND, 0);// s置零   
        cal.set(Calendar.MILLISECOND, 0);// S置零   
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1   
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1   
        return cal.getTime();   
    }
    
    //获得当前月的第一天     
    public static Date firstDayOfMonth() 
    {   
        Calendar cal = calendar();   
        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1   
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零   
        cal.set(Calendar.MINUTE, 0);// m置零   
        cal.set(Calendar.SECOND, 0);// s置零   
        cal.set(Calendar.MILLISECOND, 0);// S置零   
        return cal.getTime();   
    }
    
    private static Date weekDay(int week)
    {   
        Calendar cal = calendar();   
        cal.set(Calendar.DAY_OF_WEEK, week);   
        return cal.getTime();   
    }   
  
    /**  
     * 获得周五日期  
     * <p>  
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday  
     *   
     * @return  
     */  
    public static Date friday()
    {   
        return weekDay(Calendar.FRIDAY);   
    }   
  
    /**  
     * 获得周六日期  
     * <p>  
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday  
     *   
     * @return  
     */  
    public static Date saturday() 
    {   
        return weekDay(Calendar.SATURDAY);   
    }  
    
   
    /**  
     * 获得周日日期  
     * <p>  
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday  
     *   
     * @return  
     */  
    public static Date sunday()
    {   
        return weekDay(Calendar.SUNDAY);   
    }  
    
    //根据自定义pattern将字符串日期转换成java.util.Date类型      
    public static Date parseDatetime(String datetime, String pattern)throws ParseException
    {   
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();   
        format.applyPattern(pattern);   
        return format.parse(datetime);   
    }   
}
