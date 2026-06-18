                                                                                                                                                                                                                                                                                                                                                                                                                import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CarDealershipMain
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        LinkedList<Car> carLL = new LinkedList<Car>();

        // To read cars from file
        try (BufferedReader br = new BufferedReader(new FileReader("cars.txt"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(",");
                Car car = new Car(parts[0], parts[1], parts[2].charAt(0),
                        Integer.parseInt(parts[3]), parts[4],
                        Double.parseDouble(parts[5]));
                carLL.insertAtBack(car);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Menu 
        String choice;
        do {
            System.out.println("\n--- Car Dealership Menu ---");
            System.out.println("1. Update Car Price");
            System.out.println("2. Sort Inventory by Year");
            System.out.println("3. Search Cars by Brand/Model");
            System.out.println("4. Find Cars Under Budget");
            System.out.println("5. Calculate loan for car");
            System.out.println("6. Remove Sold Car");
            System.out.println("7. Display Final Inventory");
            System.out.println("8. Add New Car");
            System.out.println("9. Sort Inventory by Plate Number");
            System.out.println("10.Display Category Summary");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scan.nextLine();

            switch (choice)
            {
                case "1":
                    System.out.println("\n--- Updating Car Price ---");
                    System.out.print("Enter car id to update price: ");
                    String updateID = scan.nextLine();
                    
                    Car carToUpdate = carLL.findCarByPlate(updateID);
                    if (carToUpdate != null) 
                    {
                        System.out.println("Current details: " + carToUpdate.toString());
                        System.out.print("Enter new price: ");
                        double newPrice = scan.nextDouble();
                        scan.nextLine();
                        carLL.updateCarPrice(updateID, newPrice);
                        carLL.saveToFile("cars.txt");
                        System.out.println("Car price updated and saved to file.");
                    } else {
                        System.out.println("Car with plate number " + updateID + " not found.");
                    }
                    break;

                case "2":
                    System.out.println("\n--- Sorting Inventory by Year ---");
                    carLL.sortInventoryByYear();
                    break;

                case "3":
                    System.out.println("\n--- Displaying Cars by Brand ---");
                    System.out.print("Enter brand/model name to search: ");
                    String searchBrand = scan.nextLine();
                    carLL.displayCarsByBrand(searchBrand);
                    break;

                case "4":
                    System.out.println("\n--- Cars Under Budget ---");
                    System.out.print("Enter customer budget: ");
                    double budget = Double.parseDouble(scan.nextLine());
                    carLL.displayCarsUnderBudget(budget);
                    break;


                case "5":
                    System.out.println("\n--- Loan Calculator ---");
                    System.out.print("Enter plate number: ");
                    String loanPlate = scan.nextLine();
                    System.out.print("Enter annual interest rate (%): ");
                    double rate = Double.parseDouble(scan.nextLine());
                    System.out.print("Enter loan term (years): ");
                    int years = Integer.parseInt(scan.nextLine());
                    carLL.calculateLoan(loanPlate, rate, years);
                    break;

                case "6":
                    System.out.println("\n--- Removing a Sold Car ---");
                    System.out.print("Enter plate number of sold car to remove: ");
                    String soldID = scan.nextLine();
                    carLL.removeSoldCar(soldID);
                    carLL.saveToFile("cars.txt");
                    System.out.println("Car removed and inventory saved to file.");
                    break;

                case "7":
                    System.out.println("\n--- Final Inventory List ---");
                    Car currentCar = carLL.getFirst();
                    while (currentCar != null) 
                    {
                        System.out.println(currentCar.toString());
                        currentCar = carLL.getNext();
                    }
                    break;
                    
                case "8":
                    System.out.println("\n--- Adding New Car ---");
                    System.out.print("Enter plate number: ");
                    String plate = scan.nextLine();
                    System.out.print("Enter model: ");
                    String model = scan.nextLine();
                    System.out.print("Enter vehicle type (S=Sedan, H=Hatchback, C=Coupe): ");
                    char typeAdd = scan.nextLine().charAt(0);
                    System.out.print("Enter manufacturing year: ");
                    int year = Integer.parseInt(scan.nextLine());
                    System.out.print("Enter colour: ");
                    String colour = scan.nextLine();
                    System.out.print("Enter price: ");
                    double price = Double.parseDouble(scan.nextLine());               
                    Car newCar = new Car(plate, model, typeAdd, year, colour, price);
                    carLL.addCar(newCar);
                    carLL.saveToFile("cars.txt");
                    System.out.println("New car added and saved to file.");
                    break;
                
                case "9":
                    System.out.println("\n--- Sorting Inventory by Plate Number ---");
                    carLL.sortInventoryByPlate();
                    break;
                
                case "10":
                    System.out.println("\n--- Category Summary ---");
                    carLL.displayCategorySummary();
                    System.out.println("--- End of Summary ---");
                    break;

                case "0":
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } 
        while (!choice.equals("0"));

        scan.close();
        
        
        /**
         * Assalamualaikum kat sini kita nak bagi pesanan untuk banyakkan amalan
         * buat persiapan untuk diri sendiri kerana masa kita sudah suntuk
         * kejap je, macam macam berlaku kat dunia ni, dalam negara kita dan luar negara kita
         * masa kecil kita belajar macam macam pasal ujian akhir zaman, fitnah dajjal dan pasal kiamat
         * tanpa sedar, kitalah yang bakal menempuhi segala ujian tersebut. jadi persoalannya adakah kita bersedia?
         */
        
        
        
        
        
        
    }
}