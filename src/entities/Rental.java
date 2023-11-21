package entities;

import java.time.LocalDateTime;

public class Rental {
  private Car car;
  private User user;
  private Invoice invoice;

  private LocalDateTime start;
  private LocalDateTime finish;

  public Rental(Car car, User user, LocalDateTime start, LocalDateTime finish) {
    this.car = car;
    this.user = user;
    this.start = start;
    this.finish = finish;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public LocalDateTime getFinish() {
    return finish;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }
}
