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
		
		System.out.println("<<"+year+"�� "+(month+1)+"�� "+day+" �� ������ ���α׷� ����>>\n");
		
		while(true) {
			park.ShowMenu();
			System.out.print("�޴��� ������ �ּ���. >> ");
			int choice = in.nextInt();
		
			switch(choice) {
			case 1:
				park.ShowState();
				break;
			case 2:
				while(check) {
					System.out.print("���ڸ� �� ��ȣ�� �Է����ּ���. ����)1234 >> ");
					carno = in.next();
					
					if(carno.length() == 4) {
						park.InPark(carno);
						break;
					}
					else
						System.out.println("�� �� �Է��ϼ̽��ϴ�.");
				}
				break;
			case 3:
				while(check) {
					System.out.print("���ڸ� �� ��ȣ�� �Է����ּ���. ����)1234 >> ");
					carno = in.next();
					
					if(carno.length() == 4) {
						park.OutPark(carno);
						break;
					}
					else
						System.out.println("�� �� �Է��ϼ̽��ϴ�.");
				}
				break;
			case 4:
				while(check) {
					System.out.print("���ڸ� �� ��ȣ�� �Է����ּ���. ����)1234 >> ");
					carno = in.next();
					
					if(carno.length() == 4) {
						park.FindMyCar(carno);
						break;
					}
					else
						System.out.println("�� �� �Է��ϼ̽��ϴ�.");
				}
				break;
			case 5:
				System.out.println("\n\n<< �������� ���α׷��� �����մϴ�. >>");
				System.out.println("<<   �̿����ּż�  �����մϴ�.   >>");
				return;
			}
		}
	}
	public static void main(String[] args) {
		Park start = new Park();
		start.run();
	}

}
