package services;

import java.time.Duration;
import java.time.LocalDateTime;

import entities.Invoice;
import entities.Rental;

public class RentalService {
  private Double pricePerDay;
  private Double pricePerHour;
  private TaxService taxService;

  public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
    this.pricePerDay = pricePerDay;
    this.pricePerHour = pricePerHour;
    this.taxService = taxService;
  }

  private double calcBasicPayment(LocalDateTime start, LocalDateTime finish) {
    double durationInMinutes = Duration.between(start, finish).toMinutes();
    double durationInHours = durationInMinutes / 60;

    double basicPayment;

    if (durationInHours <= 12.0) {
      basicPayment = pricePerHour * Math.ceil(durationInHours);
    } else {
      basicPayment = pricePerDay * Math.ceil(durationInHours / 24.0);
    }

    return basicPayment;
  }

  public void processInvoice(Rental carRental) {
    double basicPayment = calcBasicPayment(carRental.getStart(), carRental.getFinish());
    double tax = taxService.tax(basicPayment);
    carRental.setInvoice(new Invoice(basicPayment, tax));
  }
}
