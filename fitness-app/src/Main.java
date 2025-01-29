import enums.Workouts;
import service.FitnessApplicationServiceImpl;

public class Main {

        public static void main(String[] args) {

            FitnessApplicationServiceImpl fitnessApplicationService = new FitnessApplicationServiceImpl();

            fitnessApplicationService.addCenter("Koramangala");
            fitnessApplicationService.addCenter("Bellandur");

            fitnessApplicationService.registerUser("shivam", "shivam@gmail.com", "Koramangala");

            fitnessApplicationService.addWorkout("Koramangala", Workouts.Weights.toString(), 6, 7, 10, "01-09-24", "30-09-24");
            fitnessApplicationService.addWorkout("Bellandur", Workouts.Weights.toString(), 6, 7, 10, "01-09-24", "30-09-24");

            fitnessApplicationService.addWorkout("Bellandur", Workouts.Cardio.toString(), 18, 19, 5, "01-09-24", "30-09-24");

            System.out.println("Available Slots for Weights: ");
            fitnessApplicationService.availableWorkouts(Workouts.Weights.toString(), "20-09-24").forEach(System.out::println);

            boolean booked = fitnessApplicationService.bookSession("shivam@gmail.com", "Koramangala", Workouts.Weights.toString(), 6, 7, "20-09-24");
            if(booked)
            System.out.println("Booking successfull!" );

            System.out.println("Slots Available: ");
            fitnessApplicationService.viewSchedule("shivam@gmail.com", "20-09-24").forEach(System.out::println);
        }
    }
