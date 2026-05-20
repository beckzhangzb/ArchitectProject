package com.my.proj.lunar;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.EightChar;
import com.nlf.calendar.util.LunarUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FortuneTellerAli {

    // 十天干生旺死绝表（十二长生表）
    // 行：甲(1)到癸(10)，列：子(1)到亥(12)
    private static final String[][] CHANG_SHENG_TABLE = {
            {}, // 0位占位
            {}, // 甲长生在亥，逆行
            {}, // 乙长生在午，逆行
            {"长生", "病", "死", "墓", "绝", "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺"}, // 丙戊长生在寅 (索引3)
            {"长生", "病", "死", "墓", "绝", "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺"}, // 丁己长生在酉 (索引10)
            {"绝", "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓"}, // 庚长生在巳 (索引6)
            {"绝", "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓"}, // 辛长生在子 (索引1)
            {"死", "墓", "绝", "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病"}, // 壬长生在申 (索引9)
            {"死", "墓", "绝", "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病"}  // 癸长生在卯 (索引4)
    };
    // 阳顺阴逆修正：甲丙戊庚壬为阳顺，乙丁己辛癸为阴逆。
    // 上面表格为了简化，只写了顺行和逆行的基础模版，实际查表时需做偏移处理。
    // 更严谨的做法是直接在代码里写死每个天干对应的长生地支偏移量，这里为了代码简洁，采用偏移计算法。

    // 十天干十二长生地支偏移量（以子为0基准，阳干顺数，阴干逆数）
    private static final int[] CHANG_SHENG_OFFSET = {0, 11, 6, 2, 9, 2, 9, 8, 1, 4, 3}; // 0占位, 甲在亥(11), 乙在午(6)...

    /**
     * 获取天干在地支的十二长生状态
     * @param ganIndex 天干索引 (1-10)
     * @param zhiIndex 地支索引 (1-12)
     */
    private static String getShiErYun(int ganIndex, int zhiIndex) {
        // 阳干（甲丙戊庚壬）：1,3,5,7,9 -> 顺行
        // 阴干（乙丁己辛癸）：2,4,6,8,10 -> 逆行
        int offset = CHANG_SHENG_OFFSET[ganIndex];
        int distance;
        if (ganIndex % 2 == 1) { // 阳干顺行
            distance = (zhiIndex - 1 - offset + 12) % 12;
        } else { // 阴干逆行
            distance = (offset - (zhiIndex - 1) + 12) % 12;
        }
        String[] states = {"长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓", "绝", "胎", "养"};
        return states[distance];
    }

    /**
     * 计算十神（副星）
     * @param targetGanIndex 被计算的天干索引 (1-10)
     * @param dayGanIndex 日主天干索引 (1-10)
     */
    private static String getShiShen(int targetGanIndex, int dayGanIndex) {
        int diff = (targetGanIndex - dayGanIndex + 10) % 10;
        // 同性为偏，异性为正 (阳干为奇数，阴干为偶数)
        boolean samePolarity = (dayGanIndex % 2) == (targetGanIndex % 2);

        switch (diff) {
            case 0: return samePolarity ? "比肩" : "劫财";
            case 1: return samePolarity ? "食神" : "伤官";
            case 2: return samePolarity ? "偏财" : "正财";
            case 3: return samePolarity ? "七杀" : "正官";
            case 4: return samePolarity ? "偏印" : "正印";
            case 6: return samePolarity ? "劫财" : "比肩";
            case 7: return samePolarity ? "伤官" : "食神";
            case 8: return samePolarity ? "正财" : "偏财";
            case 9: return samePolarity ? "正官" : "七杀";
            case 5: return samePolarity ? "正印" : "偏印";
            default: return "";
        }
    }

    private static int getIndex(String[] array, String value) {
        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(value)) return i;
        }
        return 0;
    }

    public static Map<String, Object> generateFortuneData(String name, int year, int month, int day, int hour, int minute, int gender) {
        Solar solar = Solar.fromYmdHms(year, month, day, hour, minute, 0);
        Lunar lunar = solar.getLunar();
        EightChar eightChar = lunar.getEightChar();

        Map<String, Object> result = new HashMap<>();

        // 基础信息
        Map<String, String> basicInfo = new HashMap<>();
        basicInfo.put("公历", solar.toFullString());
        basicInfo.put("农历", lunar.toFullString());
        basicInfo.put("性别", gender == 1 ? "男" : "女");
        result.put("命主信息", basicInfo);

        // 准备天干地支数组和下标
        String[] GAN = LunarUtil.GAN;
        String[] ZHI = LunarUtil.ZHI;

        String yearGanZhi = eightChar.getYear();
        String monthGanZhi = eightChar.getMonth();
        String dayGanZhi = eightChar.getDay();
        String timeGanZhi = eightChar.getTime();

        String[] gans = {yearGanZhi.substring(0,1), monthGanZhi.substring(0,1), dayGanZhi.substring(0,1), timeGanZhi.substring(0,1)};
        String[] zhis = {yearGanZhi.substring(1), monthGanZhi.substring(1), dayGanZhi.substring(1), timeGanZhi.substring(1)};

        int dayGanIndex = getIndex(GAN, gans[2]); // 日主下标

        List<Map<String, String>> pillars = new ArrayList<>();
        String[] labels = {"年柱", "月柱", "日柱", "时柱"};

        for (int i = 0; i < 4; i++) {
            Map<String, String> col = new HashMap<>();
            int gIndex = getIndex(GAN, gans[i]);
            int zIndex = getIndex(ZHI, zhis[i]);

            col.put("柱名", labels[i]);
            col.put("天干", gans[i]);
            col.put("地支", zhis[i]);

            // 1. 主星 (天干十神)
            if (i == 2) col.put("主星", "日主");
            else col.put("主星", getShiShen(gIndex, dayGanIndex));

            // 2. 藏干 (使用你提供的 getYearHideGan 等方法)
            List<String> hideGanList = new ArrayList<>();
            if (i == 0) hideGanList = eightChar.getYearHideGan();
            else if (i == 1) hideGanList = eightChar.getMonthHideGan();
            else if (i == 2) hideGanList = eightChar.getDayHideGan();
            else if (i == 3) hideGanList = eightChar.getTimeHideGan();

            StringBuilder cangGanSb = new StringBuilder();
            StringBuilder fuXingSb = new StringBuilder();
            for (String cangGan : hideGanList) {
                int cangGanIdx = getIndex(GAN, cangGan);
                cangGanSb.append(cangGan).append(" ");
                // 3. 副星 (藏干的十神，手写计算)
                fuXingSb.append(getShiShen(cangGanIdx, dayGanIndex)).append(" ");
            }
            col.put("藏干", cangGanSb.toString().trim());
            col.put("副星", fuXingSb.toString().trim());

            // 4. 星运 (日干对地支的十二长生，手写查表)
            col.put("星运", getShiErYun(dayGanIndex, zIndex));

            // 5. 自坐 (本柱天干对地支的十二长生，手写查表)
            col.put("自坐", getShiErYun(gIndex, zIndex));

            // 6. 空亡
            String kongWang = "";
            String xunKong = "";
            if (i == 0) xunKong = eightChar.getYearXunKong();
            else if (i == 1) xunKong = eightChar.getMonthXunKong();
            else if (i == 2) xunKong = eightChar.getDayXunKong();
            else if (i == 3) xunKong = eightChar.getTimeXunKong();

            if (xunKong.contains(zhis[i])) kongWang = "空";
            col.put("空亡", kongWang);

            // 7. 纳音
            if (i == 0) col.put("纳音", eightChar.getYearNaYin());
            else if (i == 1) col.put("纳音", eightChar.getMonthNaYin());
            else if (i == 2) col.put("纳音", eightChar.getDayNaYin());
            else if (i == 3) col.put("纳音", eightChar.getTimeNaYin());

            // 8. 神煞 (由于库方法不明确，这里仅做占位，实际需遍历 lunar.getYearShenSha() 等)
            col.put("神煞", "略");

            pillars.add(col);
        }
        result.put("四柱排盘", pillars);
        return result;
    }

    public static void main(String[] args) {
        Map<String, Object> data = generateFortuneData("徐泽", 1991, 5, 10, 10, 0, 1);
        List<Map<String, String>> pillars = (List<Map<String, String>>) data.get("四柱排盘");

        System.out.println("柱名\t天干\t地支\t主星\t藏干\t\t副星\t\t星运\t自坐\t空亡\t纳音");
        for (Map<String, String> p : pillars) {
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s%n",
                    p.get("柱名"), p.get("天干"), p.get("地支"), p.get("主星"),
                    p.get("藏干"), p.get("副星"), p.get("星运"), p.get("自坐"),
                    p.get("空亡"), p.get("纳音"));
        }
    }
}