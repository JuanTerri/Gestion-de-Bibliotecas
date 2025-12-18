package models.member;

import models.loan.Loan;
import utils.RandomHandler;

import java.util.List;

public class Member {
    private Long id;
    private String name;
    private String email;
    private MemberType type;
    private Boolean active;
    private Double finesBalance;

    public Member(String name, String email, MemberType type, Boolean active, Double finesBalance) {
        this.id = RandomHandler.generateRandomLong();
        this.name = name;
        this.email = email;
        this.type = type;
        this.active = active;
        this.finesBalance = finesBalance;
    }

    public static void listMembers(List<Member> members){
        System.out.println("La lista de socios es: \n");
        for (Member member : members) member.showMember();
    }

    public void showMember(){
        System.out.println("Id: " + this.getId() + "\n"+ "Nombre: " +this.getName() + "\n" +"Tipo de socio: " +
                this.getType() + "\n"+"Estado: " +this.getActive()+ "\n"+ "Saldo: " + this.getFinesBalance()+ "\n");
    }

    public void subcribe(){
        this.setActive(true);
    }
    public void unsubcribe(){
        this.setActive(false);
    }

    public static void setFine(Double fine, Loan loan){
        loan.getMember().setFinesBalance(loan.getMember().getFinesBalance()+fine);
        System.out.println("Se realizo la multa");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getFinesBalance() {
        return finesBalance;
    }

    public void setFinesBalance(Double finesBalance) {
        this.finesBalance = finesBalance;
    }
}
