import java.util.*;

public class HotelReservationSystem {

    static class Room {
        int roomNumber;
        String category;
        boolean isAvailable;
        double price;

        public Room(int roomNumber, String category, boolean isAvailable, double price) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.isAvailable = isAvailable;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "roomNumber=" + roomNumber +
                    ", category='" + category + '\'' +
                    ", isAvailable=" + isAvailable +
                    ", price=" + price +
                    '}';
        }
    }

    static class Reservation {
        int reservationId;
        String customerName;
        Room room;
        String checkInDate;
        String checkOutDate;
        boolean isPaid;

        public Reservation(int reservationId, String customerName, Room room, String checkInDate, String checkOutDate, boolean isPaid) {
            this.reservationId = reservationId;
            this.customerName = customerName;
            this.room = room;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.isPaid = isPaid;
        }

        @Override
        public String toString() {
            return "Reservation{" +
                    "reservationId=" + reservationId +
                    ", customerName='" + customerName + '\'' +
                    ", room=" + room +
                    ", checkInDate='" + checkInDate + '\'' +
                    ", checkOutDate='" + checkOutDate + '\'' +
                    ", isPaid=" + isPaid +
                    '}';
        }
    }

    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int reservationIdCounter = 1;

    public static void main(String[] args) {
        // Initialize rooms
        rooms.add(new Room(101, "Single", true, 1000));
        rooms.add(new Room(102, "Double", true, 2000));
        rooms.add(new Room(103, "Luxary", true, 5000));
        // Simulate user interaction
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Process payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    processPayment(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void searchAvailableRooms() {
        System.out.println("Available rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        Room room = findRoomByNumber(roomNumber);
        if (room != null && room.isAvailable) {
            Reservation reservation = new Reservation(reservationIdCounter++, name, room, checkInDate, checkOutDate, false);
            reservations.add(reservation);
            room.isAvailable = false;
            System.out.println("Reservation made successfully. Your reservation ID is " + reservation.reservationId);
        } else {
            System.out.println("Room not available.");
        }
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();

        for (Reservation reservation : reservations) {
            if (reservation.reservationId == reservationId) {
                System.out.println(reservation);
                return;
            }
        }
        System.out.println("Reservation not found.");
    }

    private static void processPayment(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();

        for (Reservation reservation : reservations) {
            if (reservation.reservationId == reservationId) {
                if (!reservation.isPaid) {
                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();

                    if (amount >= reservation.room.price) {
                        reservation.isPaid = true;
                        System.out.println("Payment successful.");
                    } else {
                        System.out.println("Insufficient amount. Payment failed.");
                    }
                } else {
                    System.out.println("Reservation already paid.");
                }
                return;
            }
        }
        System.out.println("Reservation not found.");
    }

    private static Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                return room;
            }
        }
        return null;
    }
}
