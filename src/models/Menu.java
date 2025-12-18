package models;

import models.loan.Loan;
import models.loan.LoanStatus;
import models.member.Member;
import models.member.MemberType;
import models.reservation.Reservation;
import models.reservation.ReservationStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private List<Book> bookList;
    private List<Member> memberList;
    private List<Loan> loanList;
    private List<Reservation> reservationList;

    public Menu(List<Book> books, List<Member> members, List<Loan> loanList, List<Reservation> reservationList){
        this.bookList = books;
        this.memberList = members;
        this.loanList = loanList;
        this.reservationList = reservationList;
    }

    public void start() {
        boolean active = true;
        while (active) {
            this.checkAllLoanOverdue();
            Menu.menu();
            try {
                String number = scanner.nextLine().trim(); // hay muchos usuarios que les gusta escribir el numero seguido de espacio ejemplo "5 "
                switch (number) {
                    case "1":
                        Book.listBooks(bookList);
                        returnMenu();
                        break;
                    case "2":
                        searchBook();
                        returnMenu();
                        break;
                    case "3":
                        Member.listMembers(memberList);
                        returnMenu();
                        break;
                    case "4":
                        manageMemberStatus();
                        returnMenu();
                        break;
                    case "5":
                        loanRegister();
                        returnMenu();
                        break;
                    case "6":
                        registerBookReturn();
                        returnMenu();
                        break;
                    case "7":
                        Loan.listLoans(LoanStatus.ACTIVE,loanList);
                        returnMenu();
                        break;
                    case "8":
                        Loan.listLoans(LoanStatus.OVERDUE,loanList);
                        returnMenu();
                        break;
                    case "9":
                        Member member = selectMember();
                        Book book = selectBook();
                        Reservation.newReserve(book,member,reservationList);
                        returnMenu();
                        break;
                    case "10":
                        Reservation.listReserves(ReservationStatus.ACTIVE, reservationList);
                        returnMenu();
                        break;
                    case "11":
                        payFine();
                        returnMenu();
                        break;
                    case "0":
                        active = false;
                        break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Error: ingrese un valor valido");
                scanner.nextLine();
            }
        }
    }

    private void returnMenu(){
        System.out.println("Pulse enter para volver al menu ...");
        scanner.nextLine();
    }
    public static void menu(){
        System.out.println("""
                Bienvenido al menu de la biblioteca, escriba el numero para realizar la accion
                1) Listar libros
                2) Buscar libros
                3) Listar socios
                4) Alta / Baja de socio
                5) Registrar préstamo
                6) Registrar devolución
                7) Listar préstamos activos
                8) Listar préstamos vencidos
                9) Reservar libro
                10) Listar reservas activas
                11) Pagar multa
                0) Salir
        """);
    }

    public void searchBook() {
        System.out.println("""
                1) Titulo
                2) Autor
                0) Volver
                """);
        try {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                     Book book = Book.getBookByTitle(scanner,bookList);
                     if (Objects.nonNull(book)){
                         book.showBook();
                     }
                    break;
                case "2":
                    List<Book> books = Book.getBooksByAuthor(scanner, bookList);
                    Book.showBooks(books);
                    break;
                case "0":
            }
        } catch (InputMismatchException exception) {
            System.out.println("Error: ingrese un valor valido");
            scanner.nextLine();
        }
    }

    public void manageMemberStatus(){
        System.out.println("""
                1) Alta
                2) Baja
                0) Volver
                """);
        try {
            String choice = scanner.nextLine().trim();
            switch (choice){
                case "1":
                    subcribeMember();
                    break;
                case "2":
                    unsubcribeMember();
                    break;
                case "0":
                    return;
            }
        } catch (InputMismatchException exception) {
            System.out.println("Error: ingrese un valor valido");
            scanner.nextLine();
        }
    }

    public void subcribeMember() {
        Member member = selectMember();
        if (member.getActive().equals(Boolean.FALSE)) {
            member.subcribe();
            System.out.println("Se dio de alta con exito \n");
        }
        else System.out.println("El usuario ya se encuentra activo en el sistema");
    }

    public void unsubcribeMember(){
        Member member = selectMember();
        if (member.getActive().equals(Boolean.TRUE)) {
            member.unsubcribe();
            System.out.println("Se dio de baja con exito \n");
        }
        else System.out.println("El usuario ya fue dado de baja previamente");
    }

    public void loanRegister(){
        Member member = selectMember();

        if(!member.getActive()){
            System.out.println("El miembro esta inactivo no puede pedir prestamos");
            return;
        }
        if (memberExpiredLoan(member)){
            System.out.println("El miembro tiene contratos vencidos no puede pedir prestamos");
            return;
        }

        Book book = selectBook();

        if (book.getAvalaibleCopies().equals(0)){
            boolean reserveConfirmation = false;
            while(!reserveConfirmation){
                System.out.println("""
                    No hay ejemplares disponibles, desea reservarlo?
                    ¿Confirma la reserva?
                    1) Si
                    2) No
                    """);
                try {
                    String answer = scanner.nextLine().trim();
                    switch (answer) {
                        case "1":
                            Reservation.newReserve(book, member,reservationList);
                            reserveConfirmation = true;
                            break;
                        case "2":
                            System.out.println("Reserva cancelada");
                            reserveConfirmation = true;
                            break;
                    }
                }   catch (InputMismatchException exception) {
                    System.out.println("Error: ingrese un valor valido");
                    scanner.nextLine();
                }
            }
        } else {
            if(this.evaluateLoanRequest(member)){
                Loan.evaluateNewLoan(book,member,loanList);
                System.out.println("Se realizo el prestamo con exito");
            } else System.out.println("Tu membresia no cumple con los requisitos para el prestamo");
        }
    }

    public Book selectBook(){
        int i = 1;
        for (Book book : bookList){
            System.out.println("Opcion " +i);
            book.showBook();
            i++;
        }
        Book book = null;
        boolean optionIsValid = false;
        while(!optionIsValid){
            System.out.println("Ingrese la opcion deseada");
            try{
                String option = scanner.nextLine().trim();
                book = bookList.get(Integer.parseInt(option)-1);
                optionIsValid = true;

            } catch (Exception exception){
                System.out.println("Error opcion incorrecta");
            }
        }
        return book;
    }

    public Member selectMember(){
        int i = 1;
        for (Member member : memberList){
            System.out.println("Opcion " +i);
            member.showMember();
            i++;
        }
        Member member = null;
        boolean optionIsValid = false;
        while(!optionIsValid){
            System.out.println("Ingrese la opcion deseada");
            try{
                String option = scanner.nextLine().trim();
                member = memberList.get(Integer.parseInt(option)-1);
                optionIsValid = true;

            } catch (Exception exception){
                System.out.println("Error opcion incorrecta");
            }
        }
        return member;
    }

    public boolean evaluateLoanRequest(Member member){
        if (member.getType() == MemberType.STANDARD && countActiveLoansByMember(member) < 2) {
            return true;
        } else return member.getType() == MemberType.PREMIUM && countActiveLoansByMember(member) < 5;
    }

    public Member searchMember(){
        System.out.println("Ingresa el numero de socio");
        String memberNumber = scanner.nextLine().trim();
        try {
            Long id = Long.parseLong(memberNumber);
            for (Member member : memberList) {
                if (member.getId().equals(id)) return member;
            }
            System.out.println("No se encontraron miembros con ese id");
            return null;
        } catch (NumberFormatException e){
            System.out.println("Debe ingresar un numero valido");
            return null;
        }
    }

    public boolean memberExpiredLoan(Member member){
        for (Loan loan : loanList){
            if(loan.getMember().equals(member)){
                if(loan.getStatus()== LoanStatus.OVERDUE) return true;
            }
        }
        return false;
    }

    public int countActiveLoansByMember(Member member) {
        int count = 0;
        for (Loan loan : loanList) {
            if (loan.getMember().equals(member)  && loan.getStatus() == LoanStatus.ACTIVE) count++;
        }
        return count;
    }

    public void registerBookReturn(){
        Book book = selectBook();
        Member member = selectMember();

        Loan loan = Loan.getLoanByMemberAndBookByStatus(member,book,loanList);
        if (Objects.isNull(loan)) return;

        loan.setReturnDate(LocalDate.now());
        book.moreCopies();
        Loan.checkLoanOverdue(loan);

        if (loan.getStatus()== LoanStatus.OVERDUE){
            Double fine = calculateFine(loan);
            Member.setFine(fine, loan);
        }

        loan.setStatus(LoanStatus.RETURNED);
        System.out.println("Se realizo con exito la devolucion");

        Reservation reserve = searchFirstReserve(book);
        if(Objects.nonNull(reserve)) instantLoanReservationFulfilled(reserve);
    }

    public double calculateFine(Loan loan){
            long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
            return daysLate * 100;
    }

    public void payFine(){
        Member member = selectMember();
        member.setFinesBalance(0D);
        System.out.println("La multa fue pagada");
    }

    public Reservation searchFirstReserve(Book book){
        for (Reservation reserve : reservationList){
                if(reserve.getBook().equals(book) && reserve.getStatus() == ReservationStatus.ACTIVE) return reserve;
        }return null;
    }

    public void instantLoanReservationFulfilled (Reservation reservation){
        if(reservation.getMember().getActive().equals(false)){
            System.out.println("El miembro que tenia la reserva asignada esta inactivo no se pudo realizar el prestamos instantaneo");
            return;
        }
        if(evaluateLoanRequest(reservation.getMember())){
            Loan.evaluateNewLoan(reservation.getBook(),reservation.getMember(),loanList);
            System.out.println("Se realizo el prestamo instantaneo con exito");
            reservation.setStatus(ReservationStatus.FULFILLED);
        } else System.out.println("No se pudo realizar el prestamo instananeo");
    }

    public void checkAllLoanOverdue(){
        for(Loan loan : loanList) Loan.checkLoanOverdue(loan);
    }

}
