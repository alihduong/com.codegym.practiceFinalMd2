package manage;

import common.Validate;
import model.PhoneBook;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBookManage {
    public static final String PATH_NAME = "contact.csv";
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<PhoneBook> phoneBooks;
    private final Validate validate = new Validate();

    public PhoneBookManage() {
        this.phoneBooks = new ArrayList<>();
    }

    public ArrayList<PhoneBook> getContactList() {
        return phoneBooks;
    }

    public void writeFile(ArrayList<PhoneBook> contacts, String path) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (PhoneBook contact : contacts) {
                bufferedWriter.write(contact.getPhoneNumber() + "," +
                        contact.getGroup() + "," + contact.getName() + "," + contact.getGender() + "," +
                        contact.getAddress() + "," + contact.getBirthday() + "," + contact.getEmail() + "\n");
            }
            bufferedWriter.close();
            System.out.println("Ghi File Thành Công !!!");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<PhoneBook> readFile(String path) {
        ArrayList<PhoneBook> contacts = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                contacts.add(new PhoneBook(strings[0], strings[1], strings[2], strings[3], strings[4], LocalDate.parse(strings[5], DateTimeFormatter.ISO_LOCAL_DATE), strings[6]));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return contacts;
    }

    public void addPhoenBook() {
        String phone = getPhoneNumber();
        System.out.print("Nhập Nhóm Danh Bạ : ");
        String group = scanner.nextLine();
        System.out.print("Nhập Họ Và Tên : ");
        String name = scanner.nextLine();
        System.out.print("Nhập Giới Tính : ");
        String gender = scanner.nextLine();
        System.out.print("Nhập Địa Chỉ : ");
        String address = scanner.nextLine();
        System.out.print("Nhập Ngày Sinh(dd-mm-yyyy):");
        String date = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        String email = getEmail();
        phoneBooks.add(new PhoneBook(phone, group, name, gender, address, dateOfBirth, email));
        writeFile(phoneBooks, PATH_NAME);
    }
    private String getEmail() {
        String email;
        while (true) {
            System.out.print("Nhập Email : ");
            String inputEmail = scanner.nextLine();
            if (!validate.validateEmail(inputEmail)) {
                System.out.println("Email không hợp lệ !!!");
            } else {
                email = inputEmail;
                break;
            }
        }
        return email;
    }
    public String getPhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.print("Nhập Số Điện Thoại : ");
            String phone = scanner.nextLine();
            if (!validate.validatePhone(phone)) {
                System.out.println("Số Điện Thoại Không Hợp Lệ !!!");
            } else {
                phoneNumber = phone;
                break;
            }
        }
        return phoneNumber;
    }
    public void displayPhoneBook() {
        for (PhoneBook phoneBook : phoneBooks) {
            System.out.println(phoneBook);
        }
    }

    public void ediPhoneBook(String phone) {
        PhoneBook phoneBook = null;
        for (PhoneBook pb : phoneBooks) {
            if (pb.getPhoneNumber().equals(phone)) {
                phoneBook = pb;
            }
        }
        if (phoneBook != null) {
            int index = phoneBooks.indexOf(phoneBook);
            System.out.print("Nhập Số Điện Thoại : ");
            String phone1 = scanner.nextLine();
            phoneBook.setPhoneNumber(phone1);
            System.out.print("Nhập Nhóm Danh Bạ: ");
            String group = scanner.nextLine();
            phoneBook.setGroup(group);
            System.out.print("Nhập Họ Và Tên : ");
            String name = scanner.nextLine();
            phoneBook.setName(name);
            System.out.print("Nhập Giới Tính : ");
            String gender = scanner.nextLine();
            phoneBook.setGender(gender);
            System.out.print("Nhập Địa Chỉ : ");
            String address = scanner.nextLine();
            phoneBook.setAddress(address);
            System.out.print("Nhập Ngày Sinh (dd-mm-yyyy) : ");
            String date = scanner.nextLine();
            LocalDate dateOfBirth = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
            phoneBook.setBirthday(dateOfBirth);
            System.out.print("Nhập Email: ");
            String email = scanner.nextLine();
            phoneBook.setEmail(email);
            phoneBooks.set(index, phoneBook);
            System.out.println("Sửa Thành Công !!!");
            writeFile(phoneBooks, PATH_NAME);
        }
    }

    public void deletePhoneBook(String phone) {
        PhoneBook contacts = null;
        for (PhoneBook pb : phoneBooks) {
            if (pb.getPhoneNumber().equals(phone)) {
                contacts = pb;
            }
        }
        phoneBooks.remove(contacts);
        System.out.println("Xóa Thành Công !!!");
    }

    public ArrayList<PhoneBook> searchContact(String name) {
        ArrayList<PhoneBook> contacts1 = new ArrayList<>();
        for (PhoneBook pb : phoneBooks) {
            if (pb.getName().equals(name) || pb.getPhoneNumber().equals(name)) {
                contacts1.add(pb);
            }
        }
        return contacts1;
    }
}
