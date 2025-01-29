package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CenterDao {
    String name;
    public Map<String, List<SlotsDao>> slotsAvailable;

    public CenterDao(String name) {
        this.name = name;
        this.slotsAvailable = new HashMap<>();
    }

    public void addWorkoutSlot(String workoutType, int startTime, int endTime, int capacity) {
        slotsAvailable.computeIfAbsent(workoutType, k -> new ArrayList<>())
                .add(new SlotsDao(workoutType, startTime, endTime, capacity));
    }

    public List<SlotsDao> getAvailableSlots(String workoutType, String date) {
        return slotsAvailable.getOrDefault(workoutType, new ArrayList<>()).stream()
                .filter(SlotsDao::isAvailable)
                .collect(Collectors.toList());
    }
}
