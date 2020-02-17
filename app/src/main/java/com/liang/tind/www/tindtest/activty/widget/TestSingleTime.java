package com.liang.tind.www.tindtest.activty.widget;

import com.maxwell.imkid.library.tool.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * @author 梁天德
 * @date 2020/01/19 16:28
 * desc
 */
public class TestSingleTime {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入你的名字:");
        String name = scanner.next();
        System.out.println("输入你的出生日期：");
        String birthday = scanner.next();
        System.out.println("你好，" + name);
        System.out.println("你出生于:" + birthday);
        Date date = DateUtils.parseDate(birthday, DateUtils.yyyyMMDD);
        Calendar instance = Calendar.getInstance();
        long time = instance.getTimeInMillis() - date.getTime();

        long singleDay = time / (1000 * 3600 * 24);
        System.out.println("挑战365天不恋爱");
        System.out.println("今天是第:" + singleDay + "天");
    }
}
