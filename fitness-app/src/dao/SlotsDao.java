package dao;

public class SlotsDao {
    String workoutType;
    public int startTime;
    public int endTime;
    int capacity;
    int bookedSeats;

    public SlotsDao(String workoutType, int startTime, int endTime, int capacity) {
        this.workoutType = workoutType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.bookedSeats = 0;
    }

    public boolean isAvailable() {
        return bookedSeats < capacity;
    }

    public void bookSeat() {
        if (isAvailable()) {
            bookedSeats++;
        }
    }

    @Override
    public String toString() {
        return workoutType + " [" + startTime + " - " + endTime + "] - Seats Available: " + (capacity - bookedSeats) ;
    }
}
