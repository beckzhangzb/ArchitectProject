package com.my.proj.lunar;

import com.nlf.calendar.EightChar;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import java.util.*;

public class FortuneTellerDou {

    //===================== 基础工具 =====================
    private static final Map<String, List<String>> ZANG_GAN = new HashMap<String, List<String>>() {{
        put("子", Arrays.asList("癸"));
        put("丑", Arrays.asList("己", "癸", "辛"));
        put("寅", Arrays.asList("甲", "丙", "戊"));
        put("卯", Arrays.asList("乙"));
        put("辰", Arrays.asList("戊", "乙", "癸"));
        put("巳", Arrays.asList("丙", "庚", "戊"));
        put("午", Arrays.asList("丁", "己"));
        put("未", Arrays.asList("己", "丁", "乙"));
        put("申", Arrays.asList("庚", "壬", "戊"));
        put("酉", Arrays.asList("辛"));
        put("戌", Arrays.asList("戊", "辛", "丁"));
        put("亥", Arrays.asList("壬", "甲"));
    }};

    private static final Map<String, String> NA_YIN = new HashMap<String, String>() {{
        put("甲子", "海中金");put("乙丑", "海中金");put("丙寅", "炉中火");put("丁卯", "炉中火");
        put("戊辰", "大林木");put("己巳", "大林木");put("庚午", "路旁土");put("辛未", "路旁土");
        put("壬申", "剑锋金");put("癸酉", "剑锋金");put("甲戌", "山头火");put("乙亥", "山头火");
        put("丙子", "涧下水");put("丁丑", "涧下水");put("戊寅", "城头土");put("己卯", "城头土");
        put("庚辰", "白蜡金");put("辛巳", "白蜡金");put("壬午", "杨柳木");put("癸未", "杨柳木");
        put("甲申", "泉中水");put("乙酉", "泉中水");put("丙戌", "屋上土");put("丁亥", "屋上土");
        put("戊子", "霹雳火");put("己丑", "霹雳火");put("庚寅", "松柏木");put("辛卯", "松柏木");
        put("壬辰", "长流水");put("癸巳", "长流水");put("甲午", "沙中金");put("乙未", "沙中金");
        put("丙申", "山下火");put("丁酉", "山下火");put("戊戌", "平地木");put("己亥", "平地木");
        put("庚子", "壁上土");put("辛丑", "壁上土");put("壬寅", "金箔金");put("癸卯", "金箔金");
        put("甲辰", "覆灯火");put("乙巳", "覆灯火");put("丙午", "天河水");put("丁未", "天河水");
        put("戊申", "大驿土");put("己酉", "大驿土");put("庚戌", "钗钏金");put("辛亥", "钗钏金");
        put("壬子", "桑柘木");put("癸丑", "桑柘木");put("甲寅", "大溪水");put("乙卯", "大溪水");
        put("丙辰", "沙中土");put("丁巳", "沙中土");put("戊午", "天上火");put("己未", "天上火");
        put("庚申", "石榴木");put("辛酉", "石榴木");put("壬戌", "大海水");put("癸亥", "大海水");
    }};

    private static final Map<String, Map<String, String>> CHANG_SHENG = new HashMap<String, Map<String, String>>() {{
        put("甲", of("寅长生","卯帝旺","辰衰","巳病","午死","未墓","申绝","酉胎","戌养","亥临官","子沐浴","丑冠带"));
        put("乙", of("午长生","巳帝旺","辰衰","卯病","寅死","丑墓","子绝","亥胎","戌养","酉临官","申沐浴","未冠带"));
        put("丙", of("寅长生","卯沐浴","辰冠带","巳临官","午帝旺","未衰","申病","酉死","戌墓","亥绝","子胎","丑养"));
        put("丁", of("酉长生","申沐浴","未冠带","午临官","巳帝旺","辰衰","卯病","寅死","丑墓","子绝","亥胎","戌养"));
        put("戊", of("寅长生","卯沐浴","辰冠带","巳临官","午帝旺","未衰","申病","酉死","戌墓","亥绝","子胎","丑养"));
        put("己", of("酉长生","申沐浴","未冠带","午临官","巳帝旺","辰衰","卯病","寅死","丑墓","子绝","亥胎","戌养"));
        put("庚", of("巳长生","午沐浴","未冠带","申临官","酉帝旺","戌衰","亥病","子死","丑墓","寅绝","卯胎","辰养"));
        put("辛", of("子长生","亥沐浴","戌冠带","酉临官","申帝旺","未衰","午病","巳死","辰墓","卯绝","寅胎","丑养"));
        put("壬", of("申长生","酉沐浴","戌冠带","亥临官","子帝旺","丑衰","寅病","卯死","辰墓","巳绝","午胎","未养"));
        put("癸", of("卯长生","寅沐浴","丑冠带","子临官","亥帝旺","戌衰","酉病","申死","未墓","午绝","巳胎","辰养"));
    }};

    private static final Map<String, String> KONG_WANG = new HashMap<String, String>() {{
        put("甲子", "戌亥");put("乙丑", "戌亥");put("丙寅", "子丑");put("丁卯", "子丑");
        put("戊辰", "寅卯");put("己巳", "寅卯");put("庚午", "辰巳");put("辛未", "辰巳");
        put("壬申", "午未");put("癸酉", "午未");put("甲戌", "申酉");put("乙亥", "申酉");
        put("丙子", "戌亥");put("丁丑", "戌亥");put("戊寅", "子丑");put("己卯", "子丑");
        put("庚辰", "寅卯");put("辛巳", "寅卯");put("壬午", "辰巳");put("癸未", "辰巳");
        put("甲申", "午未");put("乙酉", "午未");put("丙戌", "申酉");put("丁亥", "申酉");
        put("戊子", "戌亥");put("己丑", "戌亥");put("庚寅", "子丑");put("辛卯", "子丑");
        put("壬辰", "寅卯");put("癸巳", "寅卯");put("甲午", "辰巳");put("乙未", "辰巳");
        put("丙申", "午未");put("丁酉", "午未");put("戊戌", "申酉");put("己亥", "申酉");
        put("庚子", "戌亥");put("辛丑", "戌亥");put("壬寅", "子丑");put("癸卯", "子丑");
        put("甲辰", "寅卯");put("乙巳", "寅卯");put("丙午", "辰巳");put("丁未", "辰巳");
        put("戊申", "午未");put("己酉", "午未");put("庚戌", "申酉");put("辛亥", "申酉");
        put("壬子", "戌亥");put("癸丑", "戌亥");put("甲寅", "子丑");put("乙卯", "子丑");
        put("丙辰", "寅卯");put("丁巳", "寅卯");put("戊午", "辰巳");put("己未", "辰巳");
        put("庚申", "午未");put("辛酉", "午未");put("壬戌", "申酉");put("癸亥", "申酉");
    }};

    private static Map<String, String> of(String... arr) {
        Map<String, String> m = new HashMap<>();
        for (String s : arr) {
            m.put(s.substring(0, 1), s.substring(1));
        }
        return m;
    }

    //===================== 动态神煞计算 =====================
    private static List<String> calcShenSha(String ganZhi, String dayGan, String yearGan, String monthZhi, String pillarType) {
        List<String> shaList = new ArrayList<>();
        String gan = ganZhi.substring(0, 1);
        String zhi = ganZhi.substring(1);

        // 天乙贵人
        Map<String, List<String>> tianYi = new HashMap<String, List<String>>() {{
            put("甲", Arrays.asList("未","丑")); put("乙", Arrays.asList("申","子"));
            put("丙", Arrays.asList("酉","亥")); put("丁", Arrays.asList("酉","亥"));
            put("戊", Arrays.asList("未","丑")); put("己", Arrays.asList("未","丑"));
            put("庚", Arrays.asList("寅","午")); put("辛", Arrays.asList("寅","午"));
            put("壬", Arrays.asList("卯","巳")); put("癸", Arrays.asList("卯","巳"));
        }};
        if (tianYi.get(dayGan).contains(zhi)) {
            shaList.add("天乙贵人");
        }

        // 天德贵人
        Map<String, String> tianDe = new HashMap<String, String>() {{
            put("甲","丁");put("乙","申");put("丙","壬");put("丁","辛");
            put("戊","亥");put("己","寅");put("庚","丙");put("辛","癸");
            put("壬","乙");put("癸","甲");
        }};
        if (gan.equals(tianDe.get(yearGan))) {
            shaList.add("天德贵人");
        }

        // 月德贵人
        Map<String, List<String>> yueDe = new HashMap<String, List<String>>() {{
            put("寅",Arrays.asList("丙"));put("午",Arrays.asList("丙"));put("戌",Arrays.asList("丙"));
            put("申",Arrays.asList("壬"));put("子",Arrays.asList("壬"));put("辰",Arrays.asList("壬"));
            put("亥",Arrays.asList("甲"));put("卯",Arrays.asList("甲"));put("未",Arrays.asList("甲"));
            put("巳",Arrays.asList("庚"));put("酉",Arrays.asList("庚"));put("丑",Arrays.asList("庚"));
        }};
        if (yueDe.get(monthZhi).contains(gan)) {
            shaList.add("月德贵人");
        }

        // 福星贵人
        Map<String, List<String>> fuXing = new HashMap<String, List<String>>() {{
            put("甲",Arrays.asList("子"));put("乙",Arrays.asList("丑"));put("丙",Arrays.asList("寅"));put("丁",Arrays.asList("卯"));
            put("戊",Arrays.asList("辰"));put("己",Arrays.asList("巳"));put("庚",Arrays.asList("午"));put("辛",Arrays.asList("未"));
            put("壬",Arrays.asList("申"));put("癸",Arrays.asList("酉"));
        }};
        if (fuXing.get(dayGan).contains(zhi)) {
            shaList.add("福星贵人");
        }

        // 国印贵人
        Map<String, List<String>> guoYin = new HashMap<String, List<String>>() {{
            put("甲",Arrays.asList("戌"));put("乙",Arrays.asList("亥"));put("丙",Arrays.asList("丑"));put("丁",Arrays.asList("寅"));
            put("戊",Arrays.asList("辰"));put("己",Arrays.asList("巳"));put("庚",Arrays.asList("未"));put("辛",Arrays.asList("申"));
            put("壬",Arrays.asList("戌"));put("癸",Arrays.asList("亥"));
        }};
        if (guoYin.get(dayGan).contains(zhi)) {
            shaList.add("国印贵人");
        }

        // 德秀贵人
        List<String> deXiu = Arrays.asList("丁","酉","申","子","辰");
        if (deXiu.contains(gan) || deXiu.contains(zhi)) {
            shaList.add("德秀贵人");
        }

        // 驿马
        Map<String, List<String>> yiMa = new HashMap<String, List<String>>() {{
            put("寅",Arrays.asList("申"));put("申",Arrays.asList("寅"));
            put("巳",Arrays.asList("亥"));put("亥",Arrays.asList("巳"));
        }};
        if (yiMa.containsKey(monthZhi) && yiMa.get(monthZhi).contains(zhi)) {
            shaList.add("驿马");
        }

        // 劫煞
        Map<String, List<String>> jieSha = new HashMap<String, List<String>>() {{
            put("寅",Arrays.asList("巳"));put("申",Arrays.asList("亥"));
            put("巳",Arrays.asList("寅"));put("亥",Arrays.asList("申"));
        }};
        if (jieSha.containsKey(monthZhi) && jieSha.get(monthZhi).contains(zhi)) {
            shaList.add("劫煞");
        }

        // 魁罡
        List<String> kuiGang = Arrays.asList("壬辰","庚戌","庚辰","戊戌");
        if ("day".equals(pillarType) && kuiGang.contains(ganZhi)) {
            shaList.add("魁罡日");
        }

        // 十恶大败
        List<String> shiE = Arrays.asList("甲辰","乙巳","丙申","丁亥","戊戌","己丑","庚辰","辛巳","壬申","癸亥");
        if ("day".equals(pillarType) && shiE.contains(ganZhi)) {
            shaList.add("十恶大败");
        }

        // 天罗地网
        if (Arrays.asList("戌","亥").contains(zhi)) shaList.add("天罗");
        if (Arrays.asList("辰","巳").contains(zhi)) shaList.add("地网");

        return shaList;
    }

    //===================== 姓名五格 =====================
    private static Map<String, Object> calculateNameNumerology(String name) {
        Map<String, Object> nameData = new HashMap<>();
        int surnameLen = 1;
        int firstNameLen = name.length() - 1;
        int tianGe = surnameLen + 1;
        int renGe = surnameLen + (firstNameLen > 0 ? firstNameLen : 1);
        int diGe = (firstNameLen > 1 ? firstNameLen : 1) + 1;
        int waiGe = tianGe + diGe - renGe;
        int zongGe = tianGe + diGe;

        nameData.put("姓名", name);
        nameData.put("模拟笔画(姓/名)", surnameLen + "/" + firstNameLen);
        nameData.put("天格(祖运)", tianGe);
        nameData.put("人格(主运/性格核心)", renGe);
        nameData.put("地格(前运/配偶子女)", diGe);
        nameData.put("外格(副运/社交)", waiGe);
        nameData.put("总格(后运/晚年)", zongGe);
        nameData.put("核心人格吉凶推断", (renGe % 2 == 0) ? "数理偏阴柔，主内敛稳重" : "数理偏阳刚，主外向进取");
        return nameData;
    }

    //===================== 构建完整四柱（JDK 1.8 纯净版） =====================
    private static List<Map<String, Object>> buildFullBaZi(EightChar eightChar) {
        List<Map<String, Object>> list = new ArrayList<>();
        String[] names = {"年柱", "月柱", "日柱", "时柱"};
        String[] pillarTypes = {"year","month","day","time"};
        String[] gzArr = {eightChar.getYear(), eightChar.getMonth(), eightChar.getDay(), eightChar.getTime()};
        String[] mainStars = {
                eightChar.getYearShiShenGan(),
                eightChar.getMonthShiShenGan(),
                "元男",
                eightChar.getTimeShiShenGan()
        };

        String dayGan = eightChar.getDayGan();
        String yearGan = eightChar.getYearGan();
        String monthZhi = eightChar.getMonthZhi();

        for (int i = 0; i < 4; i++) {
            Map<String, Object> item = new HashMap<>();
            String gz = gzArr[i];
            String gan = gz.substring(0, 1);
            String zhi = gz.substring(1);

            item.put("柱", names[i]);
            item.put("主星", mainStars[i]);
            item.put("天干", gan);
            item.put("地支", zhi);
            item.put("藏干", ZANG_GAN.get(zhi));

            // ===================== 这里是 JDK1.8 兼容写法 =====================
            List<String> fuXing = new ArrayList<>();
            if ("year".equals(pillarTypes[i])) {
                fuXing = eightChar.getYearShiShenZhi();
            } else if ("month".equals(pillarTypes[i])) {
                fuXing = eightChar.getMonthShiShenZhi();
            } else if ("day".equals(pillarTypes[i])) {
                fuXing = eightChar.getDayShiShenZhi();
            } else if ("time".equals(pillarTypes[i])) {
                fuXing = eightChar.getTimeShiShenZhi();
            }
            item.put("副星", fuXing);
            // =================================================================

            item.put("星运", CHANG_SHENG.get(dayGan).get(zhi));
            item.put("自坐", CHANG_SHENG.get(gan).get(zhi));
            item.put("空亡", KONG_WANG.get(gz));
            item.put("纳音", NA_YIN.get(gz));
            item.put("神煞", calcShenSha(gz, dayGan, yearGan, monthZhi, pillarTypes[i]));

            list.add(item);
        }
        return list;
    }

    //===================== 主逻辑 =====================
    public static Map<String, Object> generateFortuneData(String name, int year, int month, int day, int hour, int minute, int gender) {
        Solar solar = Solar.fromYmdHms(year, month, day, hour, minute, 0);
        Lunar lunar = solar.getLunar();
        EightChar eightChar = lunar.getEightChar();

        Map<String, Object> result = new HashMap<>();
        result.put("姓名五格分析", calculateNameNumerology(name));

        Map<String, String> basicInfo = new HashMap<>();
        basicInfo.put("公历生日", solar.toFullString());
        basicInfo.put("农历生日", lunar.toFullString());
        basicInfo.put("生肖", lunar.getYearShengXiao());
        basicInfo.put("星座", solar.getXingZuo());
        basicInfo.put("性别", gender == 1 ? "男" : "女");
        result.put("命主信息", basicInfo);

        result.put("完整四柱排盘", buildFullBaZi(eightChar));

        Map<String, String> baZi = new HashMap<>();
        baZi.put("年柱", eightChar.getYear());
        baZi.put("月柱", eightChar.getMonth());
        baZi.put("日柱", eightChar.getDay());
        baZi.put("时柱", eightChar.getTime());
        result.put("八字排盘", baZi);

        Map<String, String> wuXing = new HashMap<>();
        wuXing.put("年柱五行", eightChar.getYearWuXing());
        wuXing.put("月柱五行", eightChar.getMonthWuXing());
        wuXing.put("日柱五行", eightChar.getDayWuXing());
        wuXing.put("时柱五行", eightChar.getTimeWuXing());
        result.put("五行分布", wuXing);

        Map<String, String> shiShen = new HashMap<>();
        shiShen.put("年干十神", eightChar.getYearShiShenGan());
        shiShen.put("月干十神", eightChar.getMonthShiShenGan());
        shiShen.put("时干十神", eightChar.getTimeShiShenGan());
        shiShen.put("日支十神", String.valueOf(eightChar.getDayShiShenZhi()));
        result.put("八字十神", shiShen);

        Map<String, String> coreInfo = new HashMap<>();
        coreInfo.put("日主(命主五行)", eightChar.getDayGan() + " (" + eightChar.getDayWuXing() + ")");
        coreInfo.put("日柱纳音", eightChar.getDayNaYin());
        result.put("命理参考", coreInfo);

        Solar today = Solar.fromDate(new Date());
        Lunar lunarToday = today.getLunar();
        result.put("当前分析时间", today.toYmdHms());
        result.put("当前流年干支", lunarToday.getYearInGanZhi());

        return result;
    }

    //===================== 输出 =====================
    public static void main(String[] args) {
        Map<String, Object> data = generateFortuneData("徐泽", 1991, 5, 10, 10, 0, 1);
        System.out.println("【结构化命理数据】");
        printMap(data);
    }

    private static void printMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                System.out.println(entry.getKey() + ": ");
                printMap((Map<String, Object>) entry.getValue());
            } else if (entry.getValue() instanceof List) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            } else {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}