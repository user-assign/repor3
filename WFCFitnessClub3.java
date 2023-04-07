import java.util.*;

public class WFCFitnessClub3 {
    static String[] days = {"Saturday", "Sunday"};
    static String[] lessons = {"Spin", "Yoga", "Bodysculpt", "Zumba", "Aquacise", "Box Fit"};
    static int[][] bookings = new int[2][12]; // 2 days, 2 lessons per day, 5 customers per lesson
    static int[][] ratings = new int[6][2]; // 6 lesson types, 2 ratings (sum and count)
    static double[] incomes = new double[6]; // 6 lesson types
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to WFC Fitness Club!");
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. View timetable");
            System.out.println("2. Make a booking");
            System.out.println("3. Cancel a booking");
            System.out.println("4. Write a review");
            System.out.println("5. Generate report");
            System.out.println("6. Exit");
            
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    viewTimetable();
                    break;
                case 2:
                    makeBooking();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    writeReview();
                    break;
                    case 5:
                    generateReport();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public static void viewTimetable() {
        System.out.println("How would you like to view the timetable?");
        System.out.println("1. By day");
        System.out.println("2. By lesson type");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Which day would you like to view (Saturday or Sunday)?");
                String day = input.next();
                int index = Arrays.asList(days).indexOf(day);
                if (index < 0) {
                    System.out.println("Invalid day!");
                    return;
                }
                System.out.println("Timetable for " + day + ":");
                for (int i = 0; i < 2; i++) {
                    System.out.println("Lesson " + (i + 1) + ": " + lessons[i + index * 2] + " (" + bookings[index][i * 2] + "/" + 5 + " booked, " + bookings[index][i * 2 + 1] + "/" + 5 + " booked)");
                }
                break;
            case 2:
                System.out.println("Which lesson type would you like to view?");
                String lesson = input.next();
                int i = Arrays.asList(lessons).indexOf(lesson);
                if (i < 0) {
                    System.out.println("Invalid lesson type!");
                    return;
                }
                System.out.println("Timetable for " + lesson + ":");
                for (int j = 0; j < 2; j++) {
                    System.out.println("Lesson " + (j + 1) + " on " + days[j] + ": " + bookings[j][i * 2] + "/" + 5 + " booked, " + bookings[j][i * 2 + 1] + "/" + 5 + " booked");
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public static void makeBooking() {
        System.out.println("Please enter your name:");
        String name = input.next();
        
        System.out.println("Please enter the lesson type:");
        String lesson = input.next();
        int i = Arrays.asList(lessons).indexOf(lesson);
        if (i < 0) {
        System.out.println("Invalid lesson type!");
        return;
        }
        System.out.println("Please enter the day (Saturday or Sunday):");
        String day = input.next();
        int j = Arrays.asList(days).indexOf(day);
        if (j < 0) {
        System.out.println("Invalid day!");
        return;
        }
        if (bookings[j][i * 2] == 5 && bookings[j][i * 2 + 1] == 5) {
        System.out.println("Sorry, this lesson is fully booked!");
        return;
        }
        if (bookings[j][i * 2] < 5) {
        bookings[j][i * 2]++;
        } else {
        bookings[j][i * 2 + 1]++;
        }
        System.out.println("Booking successful!");
        }
        public static void cancelBooking() {
            System.out.println("Please enter your name:");
            String name = input.next();
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 6; i++) {
                    if (bookings[j][i * 2] > 0 && name.equals(bookings[j][i * 2 + 1])) {
                        bookings[j][i * 2]--;
                        System.out.println("Booking cancelled!");
                        return;
                    }
                    if (bookings[j][i * 2 + 1] > 0 && name.equals(bookings[j][i * 2 + 1])) {
                        bookings[j][i * 2 + 1]--;
                        System.out.println("Booking cancelled!");
                        return;
                    }
                }
            }
            System.out.println("Booking not found!");
        }
        
        
        public static void writeReview() {
            System.out.println("Please enter your name:");
            String name = input.next();
            System.out.println("Please enter the lesson type:");
            String lesson = input.next();
            int i = Arrays.asList(lessons).indexOf(lesson);
            if (i < 0) {
                System.out.println("Invalid lesson type!");
                return;
            }
            System.out.println("Please rate the lesson on a scale of 1 to 5:");
            int rating = input.nextInt();
            ratings[i][0] += rating;
            ratings[i][1]++;
            System.out.println("Thank you for your review!");
        }
        
        public static void generateReport() {
            System.out.println("Number of customers per lesson on each of the 8 days:");
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 6; i++) {
                    int count = bookings[j][i * 2] + bookings[j][i * 2 + 1];
                    System.out.println(days[j] + " " + lessons[i] + ": " + count);
                    double rating = (double) ratings[i][0] / ratings[i][1];
                    System.out.println("Average rating: " + rating);
                }
            }
            System.out.println("Type of fitness lessons which has generated the highest income:");
            double maxIncome = 0;
            int maxIndex = -1;
            for (int i = 0; i < 6; i++) {
            incomes[i] = bookings[0][i * 2] * 10 + bookings[0][i * 2 + 1] * 10;
            incomes[i] += bookings[1][i * 2] * 10 + bookings[1][i * 2 + 1] * 10;
            if (incomes[i] > maxIncome) {
            maxIncome = incomes[i];
            maxIndex = i;
            }
            System.out.println(lessons[i] + ": " + incomes[i]);
            }
            System.out.println("Lesson with the highest income: " + lessons[maxIndex]);
            }
            
        }            