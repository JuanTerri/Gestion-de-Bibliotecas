package models.loan;

import models.Book;
import models.member.Member;
import models.member.MemberType;
import utils.RandomHandler;

import java.time.LocalDate;
import java.util.List;

public class Loan {

    private Long id;
    private Book book;
    private Member member;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public Loan(Book book, Member member, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, LoanStatus status) {
        this.id = RandomHandler.generateRandomLong();
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public void showLoan(){
        System.out.println("Id: "+ this.getId() + "\n"+ "Libro:"+ this.getBook().getTitle() + "\n" + "Miembro: "+ this.getMember().getId() +" "+ this.getMember().getName()+   "\n" +
                "Fecha de inicio: "+ this.getLoanDate() +"\n"+ "Fecha de vencimiento: " + this.getDueDate() + "\n" + "Fecha de devolucion: " + this.getReturnDate() +
                "\n" + "Estado: " + this.getStatus()+"\n\n");
    }

    public static void listLoans(LoanStatus status,List<Loan> loanList){
        if(loanList.isEmpty()){
            System.out.println("La lista de prestamos esta vacia");
            return;
        }
        System.out.println("La lista de prestamos "+ status +" es: \n" );
        Boolean loanShown = false;
        for (Loan loan : loanList){
            if(loan.getStatus() == status){
                loan.showLoan();
                loanShown = true;
            }
        }
        if (!loanShown) System.out.println("La lista de prestamos "+ status +" esta vacia");
    }

    public static void evaluateNewLoan(Book book, Member member, List<Loan> loanList){
        if(member.getType()== MemberType.STANDARD){
            Loan loan = new Loan(book, member, LocalDate.now(), LocalDate.now().plusDays(14), null, LoanStatus.ACTIVE);
            book.lessCopies();
            loanList.add(loan);
        } else if(member.getType()==MemberType.PREMIUM){
            Loan loan = new Loan(book, member, LocalDate.now(), LocalDate.now().plusDays(21), null, LoanStatus.ACTIVE);
            book.lessCopies();
            loanList.add(loan);
        }
    }

    public static Loan getLoanByMemberAndBookByStatus(Member member, Book book,List<Loan> loanList){
        for (Loan loan : loanList){
            if (loan.getMember().equals(member) && loan.getBook().equals(book) && loan.getStatus() != LoanStatus.RETURNED) return loan;
        }
        System.out.println("No se encontro el prestamo de: "+ book.getTitle() +" y miembro: " +member.getName());
        return null;
    }

    public static void checkLoanOverdue(Loan loan){
        if(loan.getStatus() == LoanStatus.ACTIVE && loan.getDueDate().isBefore(LocalDate.now())) loan.setStatus(LoanStatus.OVERDUE);
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

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
