package utils;

import models.member.Member;
import models.member.MemberType;

import java.util.ArrayList;
import java.util.List;

public class MemberInitializer {
    public static List<Member> makeMemberList(){
    List<Member> members = new ArrayList<>();

    members.add(new Member("Juan","juan@gmail.com", MemberType.STANDARD, true ,1000D ));
    members.add(new Member("Ramiro","ramiro@gmail.com",MemberType.PREMIUM, true ,1750D ));
    members.add(new Member("Pablo","pablo@gmail.com",MemberType.PREMIUM, false ,4500D ));

    return  members;
}
}
