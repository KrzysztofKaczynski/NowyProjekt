package pesel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class PeselValidator {
    private String pesel;
    private Gender gender;
    private boolean valid = false;
    private List<Integer> peselList;
    private int intNumbers[] = {9, 7, 3, 1, 9, 7, 3, 1, 9, 7};
    private int yearOfBirth;
    private int dayOfBirth;
    private String monthOfBirth;
    private static final HashMap<Integer, String> miesiace = new HashMap<>();

    static {
        miesiace.put(1, "Styczeń");
        miesiace.put(2, "Luty");
        miesiace.put(3, "Marzec");
        miesiace.put(4, "Kwiecień");
        miesiace.put(5, "Maj");
        miesiace.put(6, "Czerwiec");
        miesiace.put(7, "Lipiec");
        miesiace.put(8, "Sierpień");
        miesiace.put(9, "Wrzesień");
        miesiace.put(10, "Październik");
        miesiace.put(11, "Listopad");
        miesiace.put(12, "Grudzień");
    }

    public PeselValidator(String pesel) {
        if(pesel.length() == 11) {
            this.pesel = pesel;
            this.peselList =  Arrays.stream(pesel.split("\\B"))
                    .map(s->Integer.valueOf(s))
                    .collect(Collectors.toList());

            checkSum(this.peselList.get(10));
            setGender(this.peselList.get(9));
            setDateOfBirth();
        }
    }

    public boolean isValid() {
        return valid;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void checkSum(int sumaKontrolna) {
        int suma = 0;
        for(int i = 0; i < this.intNumbers.length; i++) {
            suma += this.intNumbers[i] * this.peselList.get(i);
        }

        this.valid = suma % 10 == sumaKontrolna;
    }

    public Gender getGender() {
        return gender;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setGender(int index) {
        this.gender = (index % 2 == 0) ? Gender.FEMALE : Gender.MALE;
    }

    public void setDateOfBirth() {
        int yearDefault = 1900 + this.peselList.get(0) * 10 + this.peselList.get(1);
        int monthDefault = this.peselList.get(2) * 10 + this.peselList.get(3);

        if(this.peselList.get(2) == 2 || this.peselList.get(3) == 3) {
            yearDefault += 100;
            monthDefault -= 20;
        } else if(this.peselList.get(2) == 4 || this.peselList.get(3) == 5) {
            yearDefault += 200;
            monthDefault -= 40;
        } else if(this.peselList.get(2) == 6 || this.peselList.get(3) == 7) {
            yearDefault += 300;
            monthDefault -= 60;
        } else if(this.peselList.get(2) == 8 || this.peselList.get(3) == 9) {
            yearDefault -= 100;
            monthDefault -= 80;
        }

        this.yearOfBirth = yearDefault;
        this.monthOfBirth = miesiace.get(monthDefault);
        this.dayOfBirth = this.peselList.get(4) * 10 + this.peselList.get(5);
    }
}
