package com.my.proj.controller;

import com.my.proj.lunar.FortuneTellerDou;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fortune")
public class FortuneController {

    private static final Logger logger = LoggerFactory.getLogger(FortuneController.class);

    @PostMapping("/calculate")
    public Map<String, Object> calculateFortune(@RequestBody Map<String, Object> request) {
        logger.info("Received fortune calculation request: {}", request);

        try {
            String name = (String) request.getOrDefault("name", "");
            int year = ((Number) request.get("year")).intValue();
            int month = ((Number) request.get("month")).intValue();
            int day = ((Number) request.get("day")).intValue();
            int hour = ((Number) request.get("hour")).intValue();
            int minute = ((Number) request.get("minute")).intValue();
            int gender = ((Number) request.get("gender")).intValue();
            String region = (String) request.getOrDefault("region", "");

            Map<String, Object> result = FortuneTellerDou.generateFortuneData(name, year, month, day, hour, minute, gender);
            result.put("success", true);
            result.put("region", region);

            logger.info("Fortune calculation completed for: {}", name);
            return result;
        } catch (Exception e) {
            logger.error("Error calculating fortune", e);
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "计算失败: " + e.getMessage());
            return error;
        }
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "ok");
        result.put("service", "fortune-teller");
        return result;
    }
}