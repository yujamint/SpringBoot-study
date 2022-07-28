package hello.hellospring.controller;

public class MemberForm {
    private String name; // templates/members/createMemberForm의 name이랑 매칭 될 것임

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
