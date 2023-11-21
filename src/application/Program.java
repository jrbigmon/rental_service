package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Car;
import entities.Rental;
import entities.User;
import services.BrazilRentalTax;
import services.RentalService;

public class Program {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    System.out.println("Entre com os dados do aluguel");

    System.out.println("Nome do proprieatio");
    String userName = sc.nextLine();
    User user = new User(userName);

    System.out.println("Modelo do veiculo: ");
    String model = sc.nextLine();
    Car car = new Car(model);

    System.out.println("Retirada (dd/MM/yyyy): ");
    LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);

    System.out.println("Entrega (dd/MM/yyyy): ");
    LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

    Rental carRental = new Rental(car, user, start, finish);

    System.out.println("Entre com o preço por dia: ");
    Double pricePerDay = sc.nextDouble();

    System.out.println("Entre com o preço por hora: ");
    Double pricePerHour = sc.nextDouble();

    RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilRentalTax());

    rentalService.processInvoice(carRental);

    System.out.println("Fatura");
    System.out.println("Pagamento basico:" + carRental.getInvoice().getBasicPayment());
    System.out.println("Imposto: " + carRental.getInvoice().getTax());
    System.out.println("Pagamento total: " + carRental.getInvoice().getTotalPayment());

    sc.close();
  }
}
