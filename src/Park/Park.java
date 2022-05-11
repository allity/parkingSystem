package Park;
import abstractPark.abstractPark;
import abstractPark.ImplementPark;
import java.util.*;

public class Park extends ImplementPark {
	Scanner in = new Scanner(System.in);
	abstractPark park = new ImplementPark();
	public void run() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DAY_OF_MONTH);
		String carno;
		boolean check = true;
		
		System.out.println("<<"+year+"년 "+(month+1)+"월 "+day+" 일 주차장 프로그램 시작>>\n");
		
		while(true) {
			park.ShowMenu();
			System.out.print("메뉴를 선택해 주세요. >> ");
			int choice = in.nextInt();
		
			switch(choice) {
			case 1:
				park.ShowState();
				break;
			case 2:
				while(check) {
					System.out.print("네자리 차 번호를 입력해주세요. 예시)1234 >> ");
					carno = in.next();
					
					if(carno.length() == 4) {
						park.InPark(carno);
						break;
					}
					else
						System.out.println("잘 못 입력하셨습니다.");
				}
				break;
			case 3:
				while(check) {
					System.out.print("네자리 차 번호를 입력해주세요. 예시)1234 >> ");
					carno = in.next();
					
					if(carno.length() == 4) {
						park.OutPark(carno);
						break;
					}
					else
						System.out.println("잘 못 입력하셨습니다.");
				}
				break;
			case 4:
				while(check) {
					System.out.print("네자리 차 번호를 입력해주세요. 예시)1234 >> ");
					carno = in.next();
					
					if(carno.length() == 4) {
						park.FindMyCar(carno);
						break;
					}
					else
						System.out.println("잘 못 입력하셨습니다.");
				}
				break;
			case 5:
				System.out.println("\n\n<< 주차관리 프로그램을 종료합니다. >>");
				System.out.println("<<   이용해주셔서  감사합니다.   >>");
				return;
			}
		}
	}
	public static void main(String[] args) {
		Park start = new Park();
		start.run();
	}

}
