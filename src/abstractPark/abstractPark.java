package abstractPark;

public abstract class abstractPark {
	public abstract void ShowMenu();
	public abstract void ShowState();
	public abstract void InPark(String carno);
	public abstract void OutPark(String carno);
	public abstract int Charge(String carno);
	public abstract void FindMyCar(String carno);
}
