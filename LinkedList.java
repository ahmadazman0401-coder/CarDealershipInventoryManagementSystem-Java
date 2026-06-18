import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LinkedList<E> 
{
    private Node<E> head;
    private Node<E> current;
    private Node<E> tail;

    public LinkedList() 
    {
        head = null;
        tail = null;
        current = null;
    }

    public boolean isEmpty()
    {
        return (head == null);
    }

    public void insertAtFront(E insertItem) 
    {
        Node<E> newNode = new Node<E>(insertItem);
        if (isEmpty()) 
        {
            head = newNode;
            tail = newNode;
        } else 
        {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertAtBack(E elem)
    {
        Node<E> newNode = new Node<E>(elem);
        if (isEmpty())
        {
            head = newNode;
            tail = newNode;
        } 
        else
        {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public E removeFromFront()
    {
        if (isEmpty()) return null;
        E removeItem = head.data;
        if (head == tail) 
        {
            head = null;
            tail = null;
        } 
        else 
        {
            head = head.next;
        }
        return removeItem;
    }

    public E removeFromBack() 
    {
        if (isEmpty()) return null;
        E removeItem = tail.data;
        if (head == tail)
        {
            head = null;
            tail = null;
        } 
        else
        {
            current = head;
            while (current.next != tail)
            {
                current = current.next;
            }
            tail = current;
            tail.next = null;
        }
        return removeItem;
    }

    public E getFirst()
    {
        if (isEmpty()) return null;
        current = head;
        return current.data;
    }

    public E getNext() 
    {
        if (current == null || current == tail) return null;
        current = current.next;
        return current.data;
    }

    // 1st processing = remove 
    // This method is to searches the list for a car with the given plate number and deletes it from inventory.
    public void removeSoldCar(String plateNum)
    {
        if (isEmpty()) 
            return;
        if (((Car) head.data).getPlateNum().equals(plateNum))
        {
            removeFromFront();
            return;
        }
        Node<E> temp = head;
        while (temp.next != null)
        {
            if (((Car) temp.next.data).getPlateNum().equals(plateNum))
            {
                if (temp.next == tail) 
                tail = temp;
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }
    // Add new car to inventory
    public void addCar(Car newCar) 
    {
        insertAtBack((E) newCar);
    }
    
    //2nd processing = update
    // This method is to update car price by its plate number
    public void updateCarPrice(String plateNum, double newPrice) 
    {
        Node<E> temp = head;
        while (temp != null) 
        {
            Car c = (Car) temp.data;
            if (c.getPlateNum().equals(plateNum)) 
            {
                c.setCarPrice(newPrice);
                return;
            }
            temp = temp.next;
        }
    }

    // 3rd processing = Searching
    // This method is to find and display car by brand
    public void displayCarsByBrand(String brandName)
    {
        Node<E> temp = head;
        boolean found = false;
        
        while (temp != null)
        {
            Car c = (Car) temp.data;
            if (c.getModel().equalsIgnoreCase(brandName))
            {
                System.out.println(c.toString());
                found = true;
            }
            temp = temp.next;
        }
        if (!found)
        {
            System.out.println("Car with brand/model '" + brandName + "' is not available.");
        }

    }

    // 4th processing = sorting
    // Sorts the car inventory in ascending order by manufacturing year.
    // This method is to sort inventory by year
    public void sortInventoryByYear()
    {
        if (isEmpty() || head.next == null) 
            return;
        boolean swapped;
        do {
            swapped = false;
            Node<E> temp = head;
            while (temp.next != null) 
            {
                Car c1 = (Car) temp.data;
                Car c2 = (Car) temp.next.data;
                if (c1.getManufacturingYear() > c2.getManufacturingYear()) 
                {
                    E tempData = temp.data;
                    temp.data = temp.next.data;
                    temp.next.data = tempData;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
        
        System.out.println("\n=== Cars Sorted by Year ===");
        displayAllCars();

    }
    
    public void sortInventoryByPlate() 
    {
        if (isEmpty() || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Node<E> temp = head;
            while (temp.next != null) 
            {
                Car c1 = (Car) temp.data;
                Car c2 = (Car) temp.next.data;
                if (c1.getPlateNum().compareTo(c2.getPlateNum()) > 0) 
                {
                    E tempData = temp.data;
                    temp.data = temp.next.data;
                    temp.next.data = tempData;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
        System.out.println("\n=== Cars Sorted by Plate Number ===");
        displayAllCars();
    }
    
    public void displayAllCars() 
    {
        Node<E> temp = head;
        while (temp != null) 
        {
            Car c = (Car) temp.data;
            System.out.println(c.toString());
            temp = temp.next;
        }
    }

    
    // 5th processing = Traversal
    // Split and Displays cars that are priced above or equal to a given threshold.
    // this method is to split by price to filter the price based on the suitable budget
    public void displayCarsUnderBudget(double threshold)
    {
        Node<E> temp = head;
        System.out.println("Cars priced below RM" + threshold + ":");
        while (temp != null) 
        {
            Car c = (Car) temp.data;
            if (c.getCarPrice() < threshold) 
            {
                System.out.println(c.toString());
            }
            temp = temp.next;
        }
    }

    // 6th processing = calculation
    // Calculates monthly loan payment for a car by plate number
    public double calculateLoan(String plateNum, double annualRate, int years) 
    {
        Car c = findCarByPlate(plateNum);
        if (c == null)
        {
            System.out.println("Car not found.");
            return 0;
        }
    
        double P = c.getCarPrice();
        double r = annualRate / 100.0 / 12; 
        int n = years * 12;
    
        double monthlyPayment;
        if (r == 0) 
        {
            monthlyPayment = P / n; 
        }
        else 
        {
            monthlyPayment = (P * r) / (1 - Math.pow(1 + r, -n));
        }
    
        System.out.printf("Loan Calculation for %s (%s):\n", c.getPlateNum(), c.getModel());
        System.out.printf("Price: RM%.2f | Rate: %.2f%% | Term: %d years\n", P, annualRate, years);
        System.out.printf("Monthly Payment: RM%.2f\n", monthlyPayment);
    
        return monthlyPayment;
    }


    // Helper method = find car
    // This method is to find and return a car object by its plate number.
    public Car findCarByPlate(String plateNum) 
    {
        Node<E> temp = head;
        while (temp != null) 
        {
            Car c = (Car) temp.data;
            if (c.getPlateNum().equalsIgnoreCase(plateNum))
            {
                return c;
            }
            temp = temp.next;
        }
        return null;
    }

    // Helper method = save to file
    // This method is to save the entire linked list back into a file after updates or removals.
    public void saveToFile(String filename) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            Node<E> temp = head;
            while (temp != null)
            {
                Car c = (Car) temp.data;
                bw.write(c.getPlateNum() + "," +
                         c.getModel() + "," +
                         c.getVehicleType() + "," +
                         c.getManufacturingYear() + "," +
                         c.getCarColour() + "," +
                         c.getCarPrice());
                bw.newLine();
                temp = temp.next;
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Display total number of cars and summary by category
    public void displayCategorySummary() {
        int sedanCount = 0, hatchbackCount = 0, coupeCount = 0;
        double sedanTotal = 0, hatchbackTotal = 0, coupeTotal = 0;
    
        Node<E> temp = head;
        while (temp != null) {
            Car c = (Car) temp.data;
            switch (c.getVehicleType()) {
                case 'S': sedanCount++; sedanTotal += c.getCarPrice(); break;
                case 'H': hatchbackCount++; hatchbackTotal += c.getCarPrice(); break;
                case 'C': coupeCount++; coupeTotal += c.getCarPrice(); break;
            }
            temp = temp.next;
        }
    
        System.out.println("\n=== Inventory Summary ===");
        System.out.println("Sedans: " + sedanCount + " cars, Total Value RM" + sedanTotal);
        System.out.println("Hatchbacks: " + hatchbackCount + " cars, Total Value RM" + hatchbackTotal);
        System.out.println("Coupes: " + coupeCount + " cars, Total Value RM" + coupeTotal);
    }
}