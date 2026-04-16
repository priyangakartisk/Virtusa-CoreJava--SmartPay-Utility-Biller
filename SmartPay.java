import java.util.Scanner;

interface Billable {
    double calculateTotal();
}

class CustomerBill implements Billable {

    private static final double TAX_RATE = 0.10;
    private String customerName;
    private int    unitsConsumed;

    CustomerBill(String customerName, int unitsConsumed) {
        this.customerName  = customerName;
        this.unitsConsumed = unitsConsumed;
    }

    private double calculateBaseCharge() {

        double charge = 0.0;

        if (unitsConsumed <= 100) {

            charge = unitsConsumed * 1.00;

        } else if (unitsConsumed <= 300) {

            charge = (100 * 1.00)
                   + ((unitsConsumed - 100) * 2.00);

        } else {

            charge = (100 * 1.00)
                   + (200 * 2.00)
                   + ((unitsConsumed - 300) * 5.00);
        }

        return charge;
    }

    @Override
    public double calculateTotal() {
        double base = calculateBaseCharge();
        double tax  = base * TAX_RATE;
        return base + tax;
    }

    void printReceipt() {

        double baseCharge = calculateBaseCharge();
        double taxAmount  = baseCharge * TAX_RATE;
        double finalTotal = baseCharge + taxAmount;

        String slabInfo;
        if (unitsConsumed <= 100) {
            slabInfo = "Standard  (0-100 units @ $1.00)";
        } else if (unitsConsumed <= 300) {
            slabInfo = "Moderate  (101-300 units @ $2.00)";
        } else {
            slabInfo = "High Usage (300+ units @ $5.00)";
        }

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║        SmartPay  —  Digital Receipt       ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  Customer    : %-27s║%n", customerName);
        System.out.printf( "║  Units Used  : %-27d║%n", unitsConsumed);
        System.out.printf( "║  Slab        : %-27s║%n", slabInfo);
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  Base Charge : $%-26.2f║%n", baseCharge);
        System.out.printf( "║  Tax (10%%)   : $%-26.2f║%n", taxAmount);
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  FINAL TOTAL : $%-26.2f║%n", finalTotal);
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
    }
}


public class SmartPay {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     Welcome to SmartPay Utility Biller   ║");
        System.out.println("║       Type EXIT as name to quit           ║");
        System.out.println("╚══════════════════════════════════════════╝");

        while (true) {

            System.out.print("\nEnter Customer Name (or EXIT): ");
            String name = input.nextLine().trim();

            if (name.equalsIgnoreCase("EXIT")) {
                System.out.println("\nThank you for using SmartPay. Goodbye!\n");
                break;
            }

            if (name.isEmpty()) {
                System.out.println(" Name cannot be blank. Please try again.");
                continue;
            }

            int previousReading = readInt(input, "Enter Previous Meter Reading : ");

            int currentReading  = readInt(input, "Enter Current  Meter Reading : ");

            if (currentReading < previousReading) {
                System.out.println("  ✖  Error: Current reading cannot be less than "
                                 + "the previous reading. Please check the meter.");
                continue;
            }

            int units = currentReading - previousReading;

            if (units == 0) {
                System.out.println("  ℹ  No units consumed this billing cycle. "
                                 + "No charge generated.");
                continue;
            }

            CustomerBill bill = new CustomerBill(name, units);
            bill.printReceipt();
        }

        input.close();
    }


    private static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value < 0) {
                    System.out.println(" Reading must be zero or positive. Try again.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println(" That doesn't look like a number. Try again.");
            }
        }
    }
}