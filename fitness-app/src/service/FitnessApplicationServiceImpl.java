package service;

import dao.CenterDao;
import dao.SlotsDao;
import dao.UserDao;

import java.util.*;

public class FitnessApplicationServiceImpl {
    Map<String, UserDao> users;
    Map<String, CenterDao> centers;

    public FitnessApplicationServiceImpl() {
        this.users = new HashMap<>();
        this.centers = new HashMap<>();
    }

    public boolean registerUser(String name, String email, String location) {
        if (!users.containsKey(email)) {
            users.put(email, new UserDao(name, email, location));
            return true;
        }
        return false;
    }

    public List<SlotsDao> availableWorkouts(String workoutType, String date) {
        List<SlotsDao> availableSlots = new ArrayList<>();
        for (CenterDao center : centers.values()) {
            availableSlots.addAll(center.getAvailableSlots(workoutType, date));
        }
        return availableSlots;
    }

    public boolean bookSession(String email, String workoutLocation, String workoutType, int startTime, int endTime, String date) {
        UserDao user = users.get(email);
        if (user == null) {
            return false;
        }

        CenterDao center = centers.get(workoutLocation);
        if (center == null) {
            return false;
        }

        List<SlotsDao> slots = center.getAvailableSlots(workoutType, date);
        for (SlotsDao slot : slots) {
            if (slot.startTime == startTime && slot.endTime == endTime && slot.isAvailable()) {
                slot.bookSeat();
                return true;
            }
        }
        return false;
    }

    public void addCenter(String centerName) {
        centers.put(centerName, new CenterDao(centerName));
    }

    public void addWorkout(String centerName, String workoutType, int startTime, int endTime, int capacity, String startDate, String endDate) {
        CenterDao center = centers.get(centerName);
        if (center != null) {
            center.addWorkoutSlot(workoutType, startTime, endTime, capacity);
        }
    }

    public List<SlotsDao> viewSchedule(String email, String date) {
        UserDao user = users.get(email);
        if (user == null) {
            return Collections.emptyList();
        }

        List<SlotsDao> schedule = new ArrayList<>();
        for (CenterDao center : centers.values()) {
            for (List<SlotsDao> slots : center.slotsAvailable.values()) {
                schedule.addAll(slots);
            }
        }
        return schedule;
    }
}
