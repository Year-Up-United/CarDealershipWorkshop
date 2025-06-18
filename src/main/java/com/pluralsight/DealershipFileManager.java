package com.pluralsight;

import java.io.*;
import java.util.regex.Pattern;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader reader = new BufferedReader(fileReader);

            String dataString;

            // Read dealership info from the first line
            if ((dataString = reader.readLine()) != null) {
                // Parse the dealership line (pipe-delimited)
                String[] data = dataString.split(Pattern.quote("|"));

                String name = data[0].trim();
                String address = data[1].trim();
                String phone = data[2].trim();

                dealership = new Dealership(name, address, phone);
            }

            // Read and add vehicles
            while ((dataString = reader.readLine()) != null) {
                if (dataString.trim().isEmpty()) continue;

                // Split by pipe delimiter (matching the original format)
                String[] parts = dataString.split(Pattern.quote("|"));


                // String[] parts = dataString.split(",");
                //int vin = Integer.parseInt(parts[0].split(":")[1].trim());

                if (parts.length >= 8) {
                    try {
                        int vin = Integer.parseInt(parts[0].trim());
                        int year = Integer.parseInt(parts[1].trim());
                        String make = parts[2].trim();
                        String model = parts[3].trim();
                        String type = parts[4].trim();
                        String color = parts[5].trim();
                        int odometer = Integer.parseInt(parts[6].trim());
                        double price = Double.parseDouble(parts[7].trim());

                        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                        dealership.addVehicle(vehicle);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing vehicle data: " + dataString);
                    }
                }
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
            // Create a default dealership if file doesn't exist
            dealership = new Dealership("Default Dealership", "123 Main St", "555-0000");
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            // Write dealership info (pipe-delimited)
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            // Write each vehicle (pipe-delimited to match reading format)
            for (Vehicle v : dealership.getAllVehicles()) {
                String vehicleLine = v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        v.getPrice();
                writer.write(vehicleLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving dealership inventory: " + e.getMessage());
        }
    }

}