package ilya.classutils;

import java.awt.*;
import java.awt.datatransfer.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Utils {

	private Utils() {
		throw new AssertionError("这个程序不是给你这么用的！");
	}
	
	public final static String[] LIST_ALL = new String[] {
            "01♀宋奕妍", "02♂黄文林", "03♂李楚誉", "04♂苏浦", "05♂梁键", "06♂刘浩然", "07♂王张向阳", "08♀周淑艺", "09♂陶云良", "10♂陈陆", "11♂吴金桐", "12♂周谢予", "13♂莫之瑜", "14♂黎泳麟", "15♀麦珊", "16♀陈杨慧", "17♀林世凤", "18♀郑好", "19♂刘朝奕", "20♂陈禹州", "21♂黄天银", "22♀钟丹彤", "23♂杨振梁", "24♂梁钦瑜", "25♂田槟榕", "26♂朱伟余", "27♀周花吉", "28♀陈昱彤", "29♀潘思宇"
    };
	public final static String[] LIST_BOY = new String[] {
            "02♂黄文林", "03♂李楚誉", "04♂苏浦", "05♂梁键", "06♂刘浩然", "07♂王张向阳", "09♂陶云良", "10♂陈陆", "11♂吴金桐", "12♂周谢予", "13♂莫之瑜", "14♂黎泳麟", "19♂刘朝奕", "20♂陈禹州", "21♂黄天银", "23♂杨振梁", "24♂梁钦瑜", "25♂田槟榕", "26♂朱伟余"
    };
	public final static String[] LIST_GIRL = new String[] {
            "01♀宋奕妍", "08♀周淑艺", "15♀麦珊", "16♀陈杨慧", "17♀林世凤", "18♀郑好", "22♀钟丹彤", "27♀周花吉", "28♀陈昱彤", "29♀潘思宇"
    };
	
	public static String getTime() {
		ZonedDateTime time = ZonedDateTime.now(ZoneId.of("Asia/Irkutsk"));
		String dayOfWeek = time.getDayOfWeek()
				.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                .toUpperCase();
		return String.format("%d-%02d-%02d (%s) %02d:%02d:%02d",
				time.getYear(),
				time.getMonthValue(),
				time.getDayOfMonth(),
                dayOfWeek,
                time.getHour(),
                time.getMinute(),
                time.getSecond());
    }
	
	public static void copy(String text) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }


}
