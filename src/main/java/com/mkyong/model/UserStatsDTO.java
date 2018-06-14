package com.mkyong.model;

import java.util.List;
import java.util.Map;

public class UserStatsDTO {
    private Map<String,Integer> stats;

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }
}
