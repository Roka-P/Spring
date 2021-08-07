package java0715;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class _1204CustomerManager {

	static ArrayList<_1204Customer> custList = new ArrayList<_1204Customer>();
	
	static int idx = -1;
	static int cnt = 0;
	
	static Scanner scan = new Scanner(System.in);
	
	private static void loadCustomerData() {
		String fileName = "customer.data";
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			custList = (ArrayList<_1204Customer>) ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
		} catch (IOException e){
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if(ois != null)
				try {ois.close();} catch (IOException e) {}
		}
	}
	
	private static void saveCustomerData() {
		String fileName = "customer.data";
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(custList);
			System.out.println("고객 데이터가 저장됐습니다.");
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
		} catch (IOException e){
			System.out.println(e.getMessage());
		} finally {
			if(oos != null)
				try {oos.close();} catch (IOException e) {}
		}
	}
	
	public static void insertCustomerData() {
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("성별(M/F) : ");
		char gender = scan.next().charAt(0);
		System.out.print("이메일 : ");
		String email = scan.next();
		System.out.print("출생년도 : ");
		int birthYear = scan.nextInt();
		
		_1204Customer cust = new _1204Customer(name, gender, email, birthYear);
		
		custList.add(cust);
	}

	public static void printCustomerData() {
		System.out.println("=-=-=-=-=-=-=CUSTOMER INFO=-=-=-=-=-=-=");
		_1204Customer cust = custList.get(idx);
		System.out.println("이름 : " + cust.getName());
		System.out.println("성별 : " + cust.getGender());
		System.out.println("이메일 : " + cust.getEmail());
		System.out.println("출생년도 : " + cust.getBirthYear());
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	
	public static void updateCustomerData() {
		System.out.println("=-=-=-=-=UPDATE CUSTOMER INFO=-=-=-=-=");
		_1204Customer cust = custList.get(idx);
		System.out.println("이름 (" + cust.getName() + ") : ");
		cust.setName(scan.next());
		System.out.println("성별 (" + cust.getGender() + ") : ");
		cust.setGender(scan.next().charAt(0));
		System.out.println("이메일 (" + cust.getEmail() + ") : ");
		cust.setEmail(scan.next());
		System.out.println("출생년도 (" + cust.getBirthYear() + ") : ");
		cust.setBirthYear(scan.nextInt());
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	
	public static void deleteCustomerData() {
		custList.remove(idx);
	}
	
	public static void main(String[] args) {
		
		 loadCustomerData();
		
		while(true) {
			System.out.printf("\n[INFO]고객 수: %d, 인덱스: %d\n", custList.size(), idx);
			System.out.println("메뉴를 입력하세요.");
			System.out.println("(S)ave, (I)nsert, (P)revious, (N)ext, " + "(C)urrent, (U)pdate, (D)elete, (Q)uit");
			System.out.print("메뉴 입력: ");
			String menu = scan.next();
			menu = menu.toLowerCase();
			switch(menu.charAt(0)) {
			case 's':
				System.out.println("고객정보를 저장합니다.");
				saveCustomerData();
				break;
			case 'i':
				System.out.println("고객정보 입력을 시작합니다.");
				insertCustomerData();
				System.out.println("고객정보를 입력했습니다.");
				break;

			case 'p':
				System.out.println("이전 데이터를 출력합니다.");
				if (idx <=0) {
					System.out.println("이전 데이터가 존재하지 않습니다.");
				} else {
					idx--;
					printCustomerData();
				}
				break;

			case 'n':
				System.out.println("다음 데이터를 출력합니다.");
				if (idx >= custList.size()-1) {
					System.out.println("다음 데이터가 존재하지 않습니다.");
				} else {
					idx++;
					printCustomerData();
				}
				break;

			case 'c':
				System.out.println("현재 데이터를 출력합니다.");
				if (idx>=0 && idx < custList.size()) {
					printCustomerData();
				} else {
					System.out.println("선택된 데이터가 존재하지 않습니다.");
				}
				break;

			case 'u':
				System.out.println("데이터를 수정합니다.");
				if (idx>=0 && idx <custList.size()) {
					System.out.println(idx + " 번째 데이터를 수정합니다.");
					updateCustomerData();
				} else {
					System.out.println("선택된 데이터가 존재하지 않습니다.");
				}
				break;

			case 'd':
				System.out.println("데이터를 삭제합니다.");
				if ((idx>=0) && (idx < custList.size())) {
						System.out.println(idx + "번째 데이터를 삭제합니다.");
						deleteCustomerData();
				}else {
					System.out.println("선택된 데이터가 존재하지 않습니다.");
				}
				break;
				
			case 'q':
				System.out.println("프로그램을 종료합니다.");
				scan.close();
				System.exit(0);
				break;
				
			default :
				System.out.println("메뉴를 잘 못 입력했습니다.");
			} //end switch
		}//end while		
	}//end main

}//end class
