package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;

// class responsible for saving contract data to a csv file
public class ContractDataManager {

    public static void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter("contracts.csv", true)) {

            // initialize the string that will hold all contract details
            String contractData = "";

            // get the vehicle associated with the contract
            Vehicle v = contract.getVehicleSold();

            if (contract instanceof SalesContract) {
                SalesContract sale = (SalesContract) contract;

                // format all relevant sales contract and vehicle info into a string
                contractData = String.format( "SALE|%s|%s|%s|%d|\n%s|%s|%s|%s|%s|%.2f|%.2f|%d|%d|%.2f|%s|%.2f\n",

                        sale.getCustomerName(),  // customer name
                        sale.getCustomerEmail(), // customer email
                        v.getVin(),              // vehicle VIN
                        v.getYear(),             // vehicle year
                        v.getMake(),             // vehicle make
                        v.getModel(),            // vehicle model
                        v.getVehicleType(),      // vehicle type
                        v.getColor(),            // vehicle color
                        v.getOdometer(),         // vehicle odometer reading
                        v.getPrice(),            // vehicle price
                        sale.getSalesTax(),      // calculated sales tax
                        sale.getRecordingFee(),  // recording fee
                        sale.getProcessingFee(), // processing fee
                        sale.getTotalPrice(),    // total price of sale
                        sale.isFinance() ? "YES" : "NO",  // is it financed prompt?
                        sale.getMonthlyPayment()  // monthly payment amount
                );

            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;

                // format the lease contract
                contractData = String.format("LEASE|%s|%s|%s|%d|\n%s|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f|%.2f\n",
                        lease.getCustomerName(),  // customer name
                        lease.getCustomerEmail(), // customer email
                        v.getVin(),               // vehicle VIN
                        v.getYear(),              // vehicle year
                        v.getMake(),              // vehicle make
                        v.getModel(),             // vehicle model
                        v.getVehicleType(),       // vehicle type
                        v.getColor(),             // vehicle color
                        v.getOdometer(),          // vehicle odometer reading
                        v.getPrice(),             // vehicle price
                        lease.getExpectedEndingValue(),  // expected value at lease end
                        lease.getLeaseFee(),      // lease fee
                        lease.getTotalPrice(),    // total lease cost
                        lease.getMonthlyPayment() // monthly payment for lease
                );


            }
            // write the final contract data to the CSV file
            writer.write(contractData);

        } catch (IOException e) {
            // handle any errors that occur while writing to the file
            System.out.println("FAILED TO WRITE THE CONTRACT: " + e.getMessage());
        }
    }
}
