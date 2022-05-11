package abstractPark;
import java.sql.*;
import java.util.Calendar;
import java.util.StringTokenizer;

public class ImplementPark extends abstractPark{
	String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	String DB_USER = "test";
	String DB_PASSWORD = "database";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	public void ShowMenu() {
		System.out.println("**************************");
		System.out.println("     1. ���� ������ ���� ����");
		System.out.println("     2. ����");
		System.out.println("     3. ����");
		System.out.println("     4. ���� ���� ���� ��ġ ã��");
		System.out.println("     5. ������ ���α׷� ����");
		System.out.println("**************************");
	};
	public void ShowState() {
		int count = 0;
		int countLine = 0;
		char [] Line = {'A', 'B', 'C', 'D', 'E', 'F'};
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sql = "select empty from park";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("    1   2   3   4   5   6   7   8   9");
			while(rs.next()) {
				String empty = rs.getString(1);
		
				if(count == 0)
					System.out.print(Line[countLine]);
				
				System.out.print("   "+empty);
				count++;
				
				if(count == 9)
				{
					System.out.println();
					count = 0;
					countLine++;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	};
	public void InPark(String carno) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			String sql = "select carno from park";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String serch = rs.getString(1);
				if(carno.equals(serch))
				{
					System.out.println("\n<< �� ��ȣ : "+carno+" �̹� �����Ǿ� �ֽ��ϴ�. >>\n");
					return;
				}
			}
			
			sql = "select position from park where empty = 'X'";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH);
				int day = now.get(Calendar.DAY_OF_MONTH);
				
				String position = rs.getString(1);
				sql = "update park set empty='O', time = to_date('"+year+"/"+(month+1)+"/"+day+"', 'YYYY/MM/DD'), carno = '"+carno+"' where position='"+position+"'";
				stmt.executeUpdate(sql);
				break;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n<< �� ��ȣ : "+carno+" ���� �Ϸ�Ǿ����ϴ�. >>\n");
	};
	public void OutPark(String carno) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			String sql = "select carno from park";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String serch = rs.getString(1);
				if(carno.equals(serch))
				{
					int charge = Charge(carno);
					
					if(charge != -1)
						System.out.println("\n<< �� ��ȣ : "+carno+" ��������� "+charge+"���� �Դϴ�. >>\n");
					else
						System.out.println("\n<< �� ��ȣ : "+carno+" �������� �� ���� �Ѿ����ϴ�. �����������Ϳ� �������ּ���.>>");
					
					sql = "update park set empty='X', carno = null, time = null where carno = '"+carno+"'";
					stmt.executeUpdate(sql);
					
					System.out.println("\n<< �� ��ȣ : "+carno+" ���� �Ϸ�Ǿ����ϴ�. >>\n");
					return;
				}
			}
			System.out.println("\n<< �� ��ȣ : "+carno+" �����Ǿ� ���� �ʽ��ϴ�. >>\n");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	};
	public int Charge(String carno) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			String sql = "select to_char(time) from park where carno = '"+carno+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String time = rs.getString(1);
				StringTokenizer st = new StringTokenizer(time, "/");
				int pyear = Integer.parseInt(st.nextToken());
				int pmonth = Integer.parseInt(st.nextToken());
				int pday = Integer.parseInt(st.nextToken());
				
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH);
				int day = now.get(Calendar.DAY_OF_MONTH);
				
				pyear = pyear + 2000;
				month = month+1;
				
				if(pyear != year)
					return -1;
				else	{	
					if(pmonth != month)
						if(day - pday < 0) {
							if(month == 1 && pmonth == 12) {
								return (31-pday + day+1);
							}
							else if(month - pmonth > 1) {
								System.out.println("month");
								return -1;
							}
							else { 
								if(pmonth == 1 || pmonth == 3 || pmonth == 5 || pmonth == 7 || pmonth == 8 || pmonth == 9 || pmonth == 10)
									return (31-pday + day+1);
								else
									return (30-pday + day+1);
							}
						}
						else
							return (day - pday+1);
					else
						return (day - pday+1);
				}
					
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("������");
		return -1;
	};
	public void FindMyCar(String carno) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			String sql = "select position, carno from park";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String position = rs.getString(1);
				String serch = rs.getString(2);
				if(carno.equals(serch))
				{
					System.out.println("\n<< �� ��ȣ : "+carno+" "+position+"�� �����Ǿ� �ֽ��ϴ�. >>\n");
					return;
				}
			}
			System.out.println("\n<< �� ��ȣ : "+carno+" �����Ǿ� ���� �ʽ��ϴ�. >>\n");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	};
}
