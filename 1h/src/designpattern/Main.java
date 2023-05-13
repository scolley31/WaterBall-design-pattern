package designpattern;

public class Main {

	public static void main(String[] args) {
		System.out.println("撲克牌比大小即將開始, 請 P1~P4 為自己取名");
        ShowDown showDown = new ShowDown();
        showDown.start(showDown);
	}

}
