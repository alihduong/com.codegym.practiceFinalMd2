package system;

import manage.PhoneBookManage;
import model.PhoneBook;

import java.util.ArrayList;
import java.util.Scanner;

public class SystemRunTime {
    Scanner scanner = new Scanner(System.in);
    PhoneBookManage phoneBookManage = new PhoneBookManage();
    int choice;

    public void MenuSystem() {
        do {
            System.out.println("+----------------------------------------------------+");
            System.out.println("+----------------------------------------------------+");
            System.out.println("|                1. Xem Danh Sách                    |");
            System.out.println("|                2. Thêm Mới                         |");
            System.out.println("|                3. Cập Nhật                         |");
            System.out.println("|                4. Xóa                              |");
            System.out.println("|                5. Tìm Kiếm                         |");
            System.out.println("|                6. Ghi Vào File                     |");
            System.out.println("|                7. Đọc Từ File                      |");
            System.out.println("|                8. Thoát                            |");
            System.out.println("+----------------------------------------------------+");
            System.out.print("            Mời Nhập Lựa Chọn : ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    phoneBookManage.displayPhoneBook();
                    break;
                case 2:
                    phoneBookManage.addPhoenBook();
                    break;
                case 3:
                    System.out.print("Nhập Vào Số Điện Thoại");
                    String s = scanner.nextLine();
                    phoneBookManage.ediPhoneBook(s);
                    System.out.println(s);
                    break;
                case 4:
                    System.out.print("Nhập Số Điện Thoại : ");
                    String s1 = scanner.nextLine();
                    phoneBookManage.deletePhoneBook(s1);
                    System.out.println(s1);
                    break;
                case 5:
                    System.out.print("Nhập Tên Muốn Tìm : ");
                    String name = scanner.nextLine();
                    ArrayList<PhoneBook> s2 = phoneBookManage.searchContact(name);
                    System.out.println(s2);
                    break;
                case 6:
                    phoneBookManage.writeFile(phoneBookManage.getContactList(), phoneBookManage.PATH_NAME);
                    break;
                case 7:
                    ArrayList<PhoneBook> contacts = PhoneBookManage.readFile(PhoneBookManage.PATH_NAME);
                    contacts.forEach(System.out::println);
                    break;
                case 8:
                    System.exit(0);
            }
        } while (choice != 0);
    }
}
