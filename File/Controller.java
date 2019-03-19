
import static java.lang.System.out;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;




public class Controller extends Function {
	private String name;
	private String account;
	private String password;
	static final Color[] colorOrder={RED,BLUE,MAGENTA,GREEN,CYAN};
	Calender_yy_mm Calender=new Calender_yy_mm(0,0,0);
	public  void Start() {
		data.inputAccount();
		EnterInformation();
	}

	public void EnterInformation() {
		AudioPlayer audio = new AudioPlayer();
		audio.loadAudio("楓之谷.wav");
		audio.setPlayCount(0);  //無限播放
		audio.play();
		AnsiConsole.systemInstall();
		Random ran = new Random();
		// TODO Auto-generated method stub

		while(true){
			System.out.print(ansi().fg(YELLOW).a(""));
			System.out.println("");
			System.out.println("		歡迎使用好行事曆 -");
			System.out.println("			你最喜歡的行事曆 (≧v≦)");
			System.out.print(ansi().fg(YELLOW).a("").reset());
			while(true){

				System.out.println(ansi().fg(CYAN).a("  1 登入帳號 / 2 註冊帳號  / 0 離開:").reset());
				String select = input.nextLine();
				if(select.equals("1")) {
					System.out.println(ansi().fg(YELLOW).a("帳號:").reset());
					account = input.nextLine();
					System.out.println(ansi().fg(YELLOW).a("密碼:").reset());
					password = input.nextLine();
					int c1=ran.nextInt(9)+1;
					int c2=ran.nextInt(9)+1;
					int c3=ran.nextInt(9)+1;
					System.out.println(ansi().fg(YELLOW).a("請輸入驗證碼:"+c1+"*"+c2+"+"+c3+"=").reset());
					String check = input.nextLine();
					if(!check.equals(Integer.toString(c1*c2+c3))) {
						System.out.println(ansi().fg(RED).a("驗證碼錯誤!").reset());
						continue;
					}
					else if(account.equals("cis")&&password.equals("1234") ) {
						audio.pause();
						Manager();
						audio.resume();
						continue;
					}
					else if(AccountJudge(account, password)==0) {
						name=data.searchAccount(account, password);
						audio.pause();
						Menu(name,1);
						audio.resume();
						continue;
					}else {
						System.out.println(data.searchAccount(account, password));
						System.out.println(ansi().fg(RED).a("帳號密碼錯誤").reset());
						continue;
					}
				}else if(select.equals("2")) {
					System.out.println(ansi().fg(YELLOW).a("姓名:").reset());
					name = input.nextLine();
					System.out.println(ansi().fg(YELLOW).a("帳號:").reset());
					account = input.nextLine();
					System.out.println(ansi().fg(YELLOW).a("密碼:").reset());
					password = input.nextLine();
					if(account.equals("0")||name.equals("0")){
						System.out.println(ansi().fg(CYAN).a("帳號或姓名不可為0").reset());
					}
					else if(data.searchName(name,account).equals("NO")){
						data.createAccount(name,account,password);
						System.out.println(ansi().fg(CYAN).a(" 帳號註冊完成").reset());
					}
					else {
						System.out.println(ansi().fg(CYAN).a(" 以有相同帳號，註冊失敗").reset());
					}
					continue;
				}else if(select.equals("0")) {
					input.close();
					System.exit(1);
				}
				else{
					System.out.println(ansi().fg(RED).a(" 輸入錯誤 請重新輸入").reset());
					continue;
				}
			}
		}
	}
	public int AccountJudge(String name,String account,String password) {
		if(name.length() == 0 || account.length() == 0 ||password.length() == 0){
			return 1;
		}
		return 0;
	}

	public int AccountJudge(String account,String password) {
		if(account.length() == 0 ||password.length() == 0){
			return 1;
		}
		for(int i =0;i<AccountData.size();i++) {
			   if(AccountData.get(i).account.equals(account)&&AccountData.get(i).passwd.equals(password)) {
				   return 0;
			   }
		  }
		return 1;
	}

	public int NameJudge(String name,String account) {
		for(int i =0;i<AccountData.size();i++) {
			   if(AccountData.get(i).name.equals(name)||AccountData.get(i).account.equals(account)) {
				   return 1;
			   }
		  }
		return 0;
	}
	public void Menu(String name,int permission) {
		float volume=(float) 0.5;
		AudioPlayer audio = new AudioPlayer();
		audio.loadAudio("楓之谷.wav");
		audio.setPlayCount(0);  //無限播放
		audio.play();
		String select;
		if(permission==1) {
			logicNumber++;
		}
		while(true) {
			if(permission==2)System.out.println("  System 正在使用 "+name+" 的行事曆 ");
			System.out.print(ansi().fg(YELLOW).a("").reset());
			//測試日期
			if(permission==1) {
				System.out.printf("************************** %n");
				System.out.print(ansi().fg(CYAN).a("  歡迎 ").reset());
				System.out.print(ansi().fg(GREEN).a(name).reset());
				System.out.println(ansi().fg(CYAN).a(" 使用行事曆!").reset());
				System.out.print(ansi().fg(CYAN).a("已經登入次數: ").reset());
				System.out.print(ansi().fg(GREEN).a(""));
				System.out.printf("%d %n",logicNumber);
				System.out.print(ansi().fg(CYAN).a("上次登入時間: ").reset());
				System.out.print(ansi().fg(GREEN).a(""));
				System.out.printf("%s %n",lastDate.toString());
				System.out.print(ansi().fg(MAGENTA).a("").reset());
				System.out.printf("************************** %n");
			}
			Calender.printCalender_yy_mm();

			if(permission==1) {
				System.out.println(ansi().fg(YELLOW).a("  請選擇接下來想執行的項目").reset());
				System.out.print(ansi().fg(colorOrder[0]).a("(0 查詢月曆-       	").reset());System.out.println(ansi().fg(colorOrder[0]).a("(7 修改、更新行程-").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("(1 顯示今天日期-	").reset());System.out.println(ansi().fg(colorOrder[1]).a("(8 刪除行程-").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("(2 顯示上一個月月曆-	").reset());System.out.println(ansi().fg(colorOrder[2]).a("(9 修改密碼-").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("(3 顯示下一個月月曆-	").reset());System.out.println(ansi().fg(colorOrder[3]).a("(10 音樂設定-").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("(4 顯示特定日期行程-	").reset());System.out.println(ansi().fg(colorOrder[2]).a("(11 存檔並返回登錄畫面-").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("(5 搜尋行程-		").reset());System.out.println(ansi().fg(colorOrder[1]).a("(12 存檔並結束程式-").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("(6 新增行程-	").reset());
				Date dNow = new Date( );
			    SimpleDateFormat ft =
			    new SimpleDateFormat ("yyyy/MM/dd");
			    String today=ft.format(dNow).toString();
			    view.ViewToday(today,1);
			}
			if(permission==2) {
				System.out.println(ansi().fg(YELLOW).a("  請選擇接下來想執行的項目").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("(1 查詢月曆-").reset());
				System.out.println(ansi().fg(colorOrder[1]).a("(2 顯示特定日期行程-").reset());
				System.out.println(ansi().fg(colorOrder[2]).a("(3 搜尋行程-").reset());
				System.out.println(ansi().fg(colorOrder[2]).a("(4 音樂設定-").reset());
				System.out.println(ansi().fg(colorOrder[1]).a("(5 存檔並返回-").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("(6 存檔並結束程式-").reset());
			}
			select=input.nextLine();
			if(permission==2) {
				if(select.equals("1")) select="0";
				else if(select.equals("2")) select="4";
				else if(select.equals("3")) select="5";
				else if(select.equals("4")) select="10";
				else if(select.equals("5")) select="11";
				else if(select.equals("6")) select="12";
				else select="gg";
			}
			if(select.equals("0")) {
				int year=0;
				int month=0;
				while(true){
					int check=0;
					try{
						System.out.println(ansi().fg(YELLOW).a("請輸入年:").reset());
						year=Integer.parseInt(input.next());
						System.out.println(ansi().fg(YELLOW).a("請輸入月份:").reset());
						month=Integer.parseInt(input.next());
						check=1;
					}
					catch(Exception e)
				    {
					System.out.println(ansi().fg(RED).a("只能輸入數字").reset());
					}
					if(month<=0||month>12){
						check=0;
					}
					if(year<=0){
						check=0;
					}
					if(check==1)break;
				}
				Calender.set_day(year, month, 1);
				continue;
			}
			if(select.equals("1")) {
				Calender.set_day(0, 0, 0);
				continue;
			}else if(select.equals("2")) {
				Calender.current_month();
				continue;
			}else if(select.equals("3")) {
				Calender.next_month();
				continue;
			}else if(select.equals("4")) {
				while(true) {
					System.out.println(ansi().fg(YELLOW).a("  請選擇如何顯示資料").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("(1 顯示所有日期行程").reset());
					System.out.println(ansi().fg(colorOrder[1]).a("(2 顯示特定日期行程").reset());
					System.out.println(ansi().fg(colorOrder[2]).a("(3 顯示今天行程").reset());
					System.out.println(ansi().fg(colorOrder[3]).a("(4 返回").reset());
					select = input.nextLine();
					//顯示全部
					if(select.equals("1")) {
						view.ViewAll();
						break;
					//顯示特定日期
					}else if(select.equals("2")) {
						System.out.print(ansi().fg(YELLOW).a("請輸入要搜尋的時間: ").reset());
						System.out.println(ansi().fg(RED).a("ex,2017/06/06").reset());
						select = input.nextLine();
						Scanner re = new Scanner(select).useDelimiter("/");
						int yy,mm,dd;
						yy = re.nextInt();
						mm = re.nextInt();
						dd = re.nextInt();
						DateX reDate = new DateX(mm,dd,yy);
						//判斷日期是否正確
						if(reDate.dateOK(mm,dd,yy)) {
							view.ViewToday(select,2);
						}else {
							continue;
						}
						continue;
					}else if(select.equals("3")){
						Date dNow = new Date( );
					    SimpleDateFormat ft =new SimpleDateFormat ("yyyy/MM/dd");
					    String today=ft.format(dNow).toString();
					    view.ViewToday(today,2);
						continue;
					}else if(select.equals("4")){
						break;
					}else {
						continue;
					}
				}
			}else if(select.equals("5")) {
				while(true) {
					System.out.println(ansi().fg(YELLOW).a("請選擇如何搜尋行程").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("(1 使用行程名子/").reset());
					System.out.println(ansi().fg(colorOrder[1]).a("(2 使用開始時間/").reset());
					System.out.println(ansi().fg(colorOrder[2]).a("(3 使用結束時間/").reset());
					System.out.println(ansi().fg(colorOrder[3]).a("(4 使用完成百分比/").reset());
					System.out.println(ansi().fg(colorOrder[4]).a("(5 使用行程狀態/").reset());
					System.out.println(ansi().fg(colorOrder[4]).a("(6 使用行程編號/").reset());
					System.out.println(ansi().fg(colorOrder[3]).a("(7 使用行程分類/").reset());
					System.out.println(ansi().fg(colorOrder[2]).a("(8 使用行程內容/").reset());
					System.out.println(ansi().fg(colorOrder[1]).a("(9 返回選單/").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("(10 退出程式").reset());
					select = input.nextLine();
					//顯示全部
					if(select.equals("1")) {
						System.out.println(ansi().fg(YELLOW).a("請輸入要搜尋的行程名子").reset());

						select = input.nextLine();
						view.ViewClass("workName",select);
						break;
					}else if(select.equals("2")) {
						System.out.print(ansi().fg(YELLOW).a("請輸入要搜尋的開始時間: ").reset());
						System.out.println(ansi().fg(RED).a("ex,2017/06/06").reset());
						select = input.nextLine();
						Scanner re = new Scanner(select).useDelimiter("/");
						int yy,mm,dd;
						yy = re.nextInt();
						mm = re.nextInt();
						dd = re.nextInt();
						DateX reDate = new DateX(mm,dd,yy);
						//判斷日期是否正確
						if(reDate.dateOK(mm,dd,yy)) {
							view.ViewClass("startDate",reDate.toString());
						}else {
							continue;
						}
						break;
					}else if(select.equals("3")) {
						System.out.print(ansi().fg(YELLOW).a("請輸入要搜尋的結束時間: ").reset());
						System.out.println(ansi().fg(RED).a("ex,2017/06/06").reset());
						select = input.nextLine();
						Scanner re = new Scanner(select).useDelimiter("/");
						int yy,mm,dd;
						yy = re.nextInt();
						mm = re.nextInt();
						dd = re.nextInt();
						DateX reDate = new DateX(mm,dd,yy);
						//判斷日期是否正確
						if(reDate.dateOK(mm,dd,yy)) {
							view.ViewClass("endDate",reDate.toString());
						}else {
							continue;
						}

						break;
					}else if(select.equals("4")) {
						System.out.println(ansi().fg(YELLOW).a("請輸入要搜尋的完成百分比").reset());
						select = input.nextLine();
						view.ViewClass("percent",select);
						break;
					}else if(select.equals("5")) {
						System.out.println(ansi().fg(YELLOW).a("請輸入要搜尋的完成狀態").reset());
						select = input.nextLine();
						view.ViewClass("status",select);
						break;
					}else if(select.equals("6")) {
						System.out.println(ansi().fg(YELLOW).a("請輸入要搜尋的行程編號").reset());
						select = input.nextLine();
						view.ViewClass("Numbering",select);
						break;
					}else if(select.equals("7")) {
						System.out.println(ansi().fg(YELLOW).a("請輸入要搜尋的行程分類").reset());
						select = input.nextLine();
						view.ViewClass("classX",select);
						break;
					}else if(select.equals("8")) {
						System.out.println(ansi().fg(YELLOW).a("請輸入要搜尋的行程內容").reset());
						select = input.nextLine();
						view.ViewClass("work",select);
						break;
					}else if(select.equals("9")) {
						break;
					}else if(select.equals("10")) {
							System.exit(1);
					}else {
						System.out.println(ansi().fg(RED).a("請重新輸入").reset());
						 continue;
					}
				}
				continue;
			}else if(select.equals("6")) {
				reHandleData.addNewDate();
				continue;
			}else if(select.equals("7")) {
				reHandleData.updateData();
				continue;
			}else if(select.equals("8")) {
				reHandleData.deleteData();
				System.out.println(ansi().fg(RED).a("刪除完成").reset());
				continue;
			}else if(select.equals("9")) {
				if(permission==2) {
					continue;
				}
				reHandleData.updateAccount(name);
				continue;
			}else if(select.equals("10")) {
				while(true) {
					System.out.println(ansi().fg(YELLOW).a("  請選擇接下來想執行的項目").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("(1 暫停播放音樂-").reset());
					System.out.println(ansi().fg(colorOrder[1]).a("(2 恢復播放音樂-").reset());
					System.out.println(ansi().fg(colorOrder[2]).a("(3 音量增加-").reset());
					System.out.println(ansi().fg(colorOrder[3]).a("(4 音量減少-").reset());
					System.out.println(ansi().fg(colorOrder[4]).a("(5 返回主畫面-").reset());
					String select1=input.nextLine();
					if(select1.equals("1")) {
						audio.pause();
						continue;
					}else if(select1.equals("2")) {
						audio.resume();
						continue;
					}else if(select1.equals("3")) {
						volume+=0.1;
						if(volume>1)volume=1;
						audio.setVolume(volume);
						System.out.print(ansi().fg(YELLOW).a("目前音量 : ").reset());
						System.out.println(ansi().fg(BLUE).a((int)(volume*10)+"/10").reset());						continue;
					}else if(select1.equals("4")) {
						volume-=0.1;
						if(volume<0)volume=0;
						audio.setVolume(volume);
						System.out.print(ansi().fg(YELLOW).a("目前音量 : ").reset());
						System.out.println(ansi().fg(BLUE).a((int)(volume*10)+"/10").reset());
						continue;
					}else if(select1.equals("5")) {
						break;
					}
				}
				continue;
			}else if(select.equals("11")) {
				Date dNow = new Date( );
			    SimpleDateFormat ft =
			    new SimpleDateFormat ("yyyy/MM/dd");
			    String today=ft.format(dNow).toString();
			    Scanner re = new Scanner(today).useDelimiter("/");
				int yy,mm,dd;
				yy = re.nextInt();
				mm = re.nextInt();
				dd = re.nextInt();
				DateX last = new DateX(mm,dd,yy);
			    lastDate=last;
				audio.close();
				data.OutputFile(name);
				return;
			}else if(select.equals("12")) {
				Date dNow = new Date( );
			    SimpleDateFormat ft =
			    new SimpleDateFormat ("yyyy/MM/dd");
			    String today=ft.format(dNow).toString();
			    Scanner re = new Scanner(today).useDelimiter("/");
				int yy,mm,dd;
				yy = re.nextInt();
				mm = re.nextInt();
				dd = re.nextInt();
				DateX last = new DateX(mm,dd,yy);
			    lastDate=last;
				audio.close();
				data.OutputFile(name);
				System.exit(1);
			}

		}
	}
///
	public void Manager() {
		while(true) {
			System.out.println(ansi().fg(YELLOW).a("請選擇接下來想執行的項目").reset());
			System.out.println(ansi().fg(colorOrder[0]).a("(1 顯示所有使用者").reset());
			System.out.println(ansi().fg(colorOrder[1]).a("(2 新增使用者").reset());
			System.out.println(ansi().fg(colorOrder[2]).a("(3 查詢使用者行程").reset());
			System.out.println(ansi().fg(colorOrder[3]).a("(4 設定使用者").reset());
			System.out.println(ansi().fg(colorOrder[4]).a("(5 使用者密碼重設").reset());
			System.out.println(ansi().fg(colorOrder[1]).a("(6 刪除使用者").reset());
			System.out.println(ansi().fg(colorOrder[0]).a("(7 退出程式").reset());
			String select = input.nextLine();
			if(select.equals("1")) {
				System.out.print(ansi().fg(CYAN).a("姓名  ").reset());
				System.out.println(ansi().fg(BLUE).a("帳號").reset());
				for(int i =0;i<AccountData.size();i++) {
				System.out.print(ansi().fg(CYAN).a(AccountData.get(i).name).reset());
				System.out.print(" ");
				System.out.println(ansi().fg(BLUE).a(AccountData.get(i).account).reset());
				}
			}else if(select.equals("2")) {
				System.out.println(ansi().fg(YELLOW).a("姓名:").reset());
				name = input.nextLine();
				System.out.println(ansi().fg(YELLOW).a("帳號:").reset());
				account = input.nextLine();
				password = "0000";
				if(name.equals("0")||account.equals("0")){
					System.out.println(ansi().fg(CYAN).a("帳號或姓名不可為0").reset());
				}
				else if(NameJudge(name,account)==0){
					data.createAccount(name,account,password);
					System.out.println(ansi().fg(CYAN).a("密碼預設為0000").reset());
					System.out.println(ansi().fg(CYAN).a("帳號註冊完成").reset());
				}
				else{
					System.out.println(ansi().fg(RED).a("帳號已存在").reset());
				}
			}else if(select.equals("3")) {
				int check=0;
				while(true){
					System.out.println(ansi().fg(YELLOW).a("	請輸入想要查看行程的使用者:").reset());
					System.out.println(ansi().fg(RED).a("(0離開)").reset());
					name=input.nextLine();
					if(name.equals("0")){
						break;
					}
					for(int i =0;i<AccountData.size();i++) {
						if(AccountData.get(i).name.equals(name)){
							check=0;
							break;
						}
						else check=1;
					}
					if(check==1){
						System.out.print(ansi().fg(RED).a("	查無此使用者").reset());
					}
					else {
						data.inputFile(name);
						Menu(name,2);
					}
				}
			}else if(select.equals("4")) {
				reHandleData.SettingUser();
			}else if(select.equals("5")) {
				int check=1;
				while(true){
					System.out.println(ansi().fg(YELLOW).a("	請輸入想要重設密碼的使用者:").reset());
					System.out.println(ansi().fg(RED).a("(0離開)").reset());
					name=input.nextLine();
					if(name.equals("0")){
						break;
					}
					for(int i =0;i<AccountData.size();i++) {
						if(AccountData.get(i).name.equals(name)){
							check=0;
							reHandleData.restPassword(name);
							break;
						}
					}
					if(check==0){
						break;
					}
					if(check==1){
						System.out.print(ansi().fg(RED).a("	查無此使用者").reset());
					}
				}
			}else if(select.equals("6")) {
				int check=1;
				out.print("	請輸入想要刪除的使用者:");
				while(true){
					System.out.println(ansi().fg(YELLOW).a("	請輸入想要刪除的使用者:").reset());
					System.out.println(ansi().fg(RED).a("(0離開)").reset());
					name=input.nextLine();
					if(name.equals("0")){
						break;
					}
					for(int i =0;i<AccountData.size();i++) {
						if(AccountData.get(i).name.equals(name)){
							check=0;
							reHandleData.deleteAccount(name);
							break;
						}
					}
					if(check==0){
						break;
					}
					if(check==1){
						System.out.print(ansi().fg(RED).a("	查無此使用者").reset());
					}
				}
			}else if(select.equals("7")) {
				System.exit(1);
			}
		}
	}
}
