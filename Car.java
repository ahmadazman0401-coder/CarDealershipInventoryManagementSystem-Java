public class Car {
    private String plateNum;
    private String model;
    private char vehicleType; // S=Sedan, H=Hatchback, C=Coupe
    private int manufacturingYear;
    private String carColour;
    private double carPrice;

    public Car(String plateNum, String model, char vehicleType, int manufacturingYear, String carColour, double carPrice) {
        this.plateNum = plateNum;
        this.model = model;
        this.vehicleType = vehicleType;
        this.manufacturingYear = manufacturingYear;
        this.carColour = carColour;
        this.carPrice = carPrice;
    }

    // Getters
    public String getPlateNum() { return plateNum; }
    public String getModel() { return model; }
    public char getVehicleType() { return vehicleType; }
    public int getManufacturingYear() { return manufacturingYear; }
    public String getCarColour() { return carColour; }
    public double getCarPrice() { return carPrice; }

    // Setters
    public void setPlateNumber(String plateNum) { this.plateNum = plateNum; }
    public void setModel(String model) { this.model = model; }
    public void setVehicleType(char vehicleType) { this.vehicleType = vehicleType; }
    public void setManufacturingYear(int manufacturingYear) { this.manufacturingYear = manufacturingYear; }
    public void setCarColour(String carColour) { this.carColour = carColour; }
    public void setCarPrice(double carPrice) { this.carPrice = carPrice; }

    @Override
    public String toString() {
        return "Plate Number: " + plateNum + " | " +
               "Model: " + model + " | " +
               "Vehicle Type: " + vehicleType + " | " +
               "Manufacturing Year: " + manufacturingYear + " | " +
               "Car Colour: " + carColour + " | " +
               "Car Price: RM" + carPrice;
    }
}
