package models.reservation;

import models.Book;
import models.member.Member;
import utils.RandomHandler;

import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private Long id;
    private Book book;
    private Member member;
    private LocalDate reservationDate;
    private ReservationStatus status;

    public Reservation( Book book, Member member, LocalDate reservationDate, ReservationStatus status) {
        this.id = RandomHandler.generateRandomLong();
        this.book = book;
        this.member = member;
        this.reservationDate = reservationDate;
        this.status = status;
    }
    public void showReserve(){
        System.out.println("Id: "+ this.getId() +"\n"+ "Libro: "+ this.getBook().getTitle() + "\n"+ "Miembro: "+
                this.getMember().getId() + " " + this.getMember().getName() + "\n" + "Fecha de reserva: "+ this.reservationDate + "\n" +
                "Estado de reserva: " +this.getStatus()+"\n\n");
    }

    public static void newReserve(Book book, Member member, List<Reservation> reservationList){
        Reservation reserve = new Reservation(book,member, LocalDate.now(), ReservationStatus.ACTIVE);
        reservationList.add(reserve);
        System.out.println("El libro fue reservado con exito");
    }

    public static void listReserves(ReservationStatus status,List<Reservation> reservationList){
        if(reservationList.isEmpty()){
            System.out.println("La lista de reservas esta vacia");
            return;
        }
        System.out.println("La lista de reservas "+ status +" es:"+"\n ");
        for (Reservation reserve : reservationList){
            if(reserve.getStatus() == status) reserve.showReserve();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
