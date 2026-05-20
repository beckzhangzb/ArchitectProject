package com.my.proj.lunar;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FortuneTeller {

    /**
     * 计算姓名五格数理（三才五格基础）
     * 注意：严格的姓名学需要计算繁体字（康熙字典）笔画。
     * 这里为了演示方便，暂用字符串长度代替笔画数。
     * 实际生产环境中，建议引入一个 [汉字:笔画数] 的字典 Map 来替换 name.length()。
     */
    private static Map<String, Object> calculateNameNumerology(String name) {
        Map<String, Object> nameData = new HashMap<>();

        // 模拟笔画数（实际开发请替换为真实的康熙字典笔画计算）
        int surnameLen = 1; // 假设单姓
        int firstNameLen = name.length() - 1;

        // 五格计算公式
        int tianGe = surnameLen + 1; // 天格（单姓+1）
        int renGe = surnameLen + (firstNameLen > 0 ? firstNameLen : 1); // 人格（姓+名第一字）
        int diGe = (firstNameLen > 1 ? firstNameLen : 1) + 1; // 地格（名两字相加+1，单名+1）
        int waiGe = tianGe + diGe - renGe; // 外格
        int zongGe = tianGe + diGe; // 总格

        nameData.put("姓名", name);
        nameData.put("模拟笔画(姓/名)", surnameLen + "/" + firstNameLen);
        nameData.put("天格(祖运)", tianGe);
        nameData.put("人格(主运/性格核心)", renGe);
        nameData.put("地格(前运/配偶子女)", diGe);
        nameData.put("外格(副运/社交)", waiGe);
        nameData.put("总格(后运/晚年)", zongGe);

        // 简单的数理吉凶判定（示例：尾数为奇数偏阳刚，偶数偏阴柔）
        nameData.put("核心人格吉凶推断", (renGe % 2 == 0) ? "数理偏阴柔，主内敛稳重" : "数理偏阳刚，主外向进取");

        return nameData;
    }

    /**
     * 生成命理分析报告数据
     * @param name 姓名
     * @param year 出生年
     * @param month 出生月
     * @param day 出生日
     * @param hour 出生时
     * @param minute 出生分
     * @param gender 性别 (1: 男, 0: 女)
     */
    public static Map<String, Object> generateFortuneData(String name, int year, int month, int day, int hour, int minute, int gender) {
        Solar solar = Solar.fromYmdHms(year, month, day, hour, minute, 0);
        Lunar lunar = solar.getLunar();
        com.nlf.calendar.EightChar eightChar = lunar.getEightChar();

        Map<String, Object> result = new HashMap<>();

        // --- 1. 姓名命理推测 (新增部分) ---
        result.put("姓名五格分析", calculateNameNumerology(name));

        // --- 2. 命主基础信息 ---
        Map<String, String> basicInfo = new HashMap<>();
        basicInfo.put("公历生日", solar.toFullString());
        basicInfo.put("农历生日", lunar.toFullString());
        basicInfo.put("生肖", lunar.getYearShengXiao());
        basicInfo.put("星座", solar.getXingZuo());
        basicInfo.put("性别", gender == 1 ? "男" : "女");
        result.put("命主信息", basicInfo);

        // --- 3. 八字排盘 ---
        Map<String, String> baZi = new HashMap<>();
        baZi.put("年柱", eightChar.getYear());
        baZi.put("月柱", eightChar.getMonth());
        baZi.put("日柱", eightChar.getDay());
        baZi.put("时柱", eightChar.getTime());
        result.put("八字排盘", baZi);

        // --- 4. 五行与纳音 ---
        Map<String, String> wuXing = new HashMap<>();
        wuXing.put("年柱五行", eightChar.getYearWuXing());
        wuXing.put("月柱五行", eightChar.getMonthWuXing());
        wuXing.put("日柱五行", eightChar.getDayWuXing());
        wuXing.put("时柱五行", eightChar.getTimeWuXing());
        result.put("五行分布", wuXing);

        // --- 5. 十神 ---
        Map<String, String> shiShen = new HashMap<>();
        shiShen.put("年干十神", eightChar.getYearShiShenGan());
        shiShen.put("月干十神", eightChar.getMonthShiShenGan());
        shiShen.put("时干十神", eightChar.getTimeShiShenGan());
        shiShen.put("日支十神", String.valueOf(eightChar.getDayShiShenZhi()));
        result.put("八字十神", shiShen);

        // --- 6. 命理参考 ---
        Map<String, String> coreInfo = new HashMap<>();
        coreInfo.put("日主(命主五行)", eightChar.getDayGan() + " (" + eightChar.getDayWuXing() + ")");
        coreInfo.put("日柱纳音", eightChar.getDayNaYin());
        result.put("命理参考", coreInfo);

        // --- 7. 当前流年 ---
        Solar today = Solar.fromDate(new Date());
        Lunar lunarToday = today.getLunar();
        result.put("当前分析时间", today.toYmdHms());
        result.put("当前流年干支", lunarToday.getYearInGanZhi());

        return result;
    }

    /**
     * 生成发送给 AI 大模型的提示词
     */
    public static String generateAiPrompt(Map<String, Object> fortuneData) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("你是一位拥有 30 年经验的资深命理学大师。请根据以下用户提供的精准八字数据以及姓名五格，进行深度的命理分析。\n\n");
        prompt.append("请严格遵循以下要求生成报告：\n");
        prompt.append("1. 语气要权威、专业且充满智慧。\n");
        prompt.append("2. 报告必须包含以下章节：【命主画像】、【姓名与性格分析】、【五行能量与喜用神】、【事业机遇】、【财富状况】、【感情姻缘】、【2026年全年运势】。\n");
        prompt.append("3. 在【姓名与性格分析】章节：请结合“姓名五格分析”中的人格、总格数理，分析名字对命主性格的潜在诱导，以及名字五行是否与八字喜用神互补。\n");
        prompt.append("4. 在【五行能量】部分：结合八字五行分布，判断用户的'喜用神'。\n");
        prompt.append("5. 在【2026年全年运势】部分：结合流年干支与用户八字、姓名的互动进行分析。\n\n");

        prompt.append("--- 用户八字与姓名原始数据 ---\n");
        prompt.append("姓名五格分析: ").append(fortuneData.get("姓名五格分析")).append("\n");
        prompt.append("基础信息: ").append(fortuneData.get("命主信息")).append("\n");
        prompt.append("八字排盘: ").append(fortuneData.get("八字排盘")).append("\n");
        prompt.append("五行分布: ").append(fortuneData.get("五行分布")).append("\n");
        prompt.append("十神配置: ").append(fortuneData.get("八字十神")).append("\n");
        prompt.append("命理参考: ").append(fortuneData.get("命理参考")).append("\n");
        prompt.append("当前时间: 2026-05-18 (丙午年)\n");
        prompt.append("--- 请开始你的分析 ---");

        return prompt.toString();
    }

    // --- 测试主函数 ---
    public static void main(String[] args) {
        // 模拟用户输入：姓名 徐泽，1990年5月20日 10点 出生 (男)
        Map<String, Object> data = generateFortuneData("徐泽", 1991, 5, 10, 10, 0, 1);

        System.out.println("【结构化命理数据】");
        printMap(data);

        String aiPrompt = generateAiPrompt(data);
        System.out.println("\n\n【发送给 AI 的提示词】");
        System.out.println(aiPrompt);
    }

    // 辅助方法：递归打印 Map
    private static void printMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                System.out.println(entry.getKey() + ": ");
                printMap((Map<String, Object>) entry.getValue());
            } else {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}