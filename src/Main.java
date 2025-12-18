import models.*;
import models.loan.Loan;
import models.loan.LoanStatus;
import models.member.Member;
import models.reservation.Reservation;
import utils.BookInitializer;
import utils.MemberInitializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> bookList = BookInitializer.makeBookList();
        List<Member> memberList = MemberInitializer.makeMemberList();
        List<Loan> loanList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();

        //checkeo para un prestamo vencido
        Loan loanOverdue = new Loan(bookList.get(0),memberList.get(2), LocalDate.now().minusDays(30),
                LocalDate.now().minusDays(5),null, LoanStatus.ACTIVE);
        loanList.add(loanOverdue);

        Menu menu = new Menu(bookList, memberList,loanList,reservationList);
        menu.start();
    }
}