public class Person {
    private String nameFirst;
    private String nameLast;
    public Person(String firstName, String lastName) {
        this.nameFirst = firstName;
        this.nameLast = lastName;
    }
    Person(String firstName) {
        this.nameFirst = firstName;
        this.nameLast = "";
    }
    public String getNameFirst() {
        return nameFirst;
    }
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }
    public String getNameLast() {
        return nameLast;
    }
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }
}