
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
		audio.loadAudio("������.wav");
		audio.setPlayCount(0);  //�L������
		audio.play();
		AnsiConsole.systemInstall();
		Random ran = new Random();
		// TODO Auto-generated method stub

		while(true){
			System.out.print(ansi().fg(YELLOW).a(""));
			System.out.println("");
			System.out.println("		�w��ϥΦn��ƾ� -");
			System.out.println("			�A�̳��w����ƾ� (��v��)");
			System.out.print(ansi().fg(YELLOW).a("").reset());
			while(true){

				System.out.println(ansi().fg(CYAN).a("  1 �n�J�b�� / 2 ���U�b��  / 0 ���}:").reset());
				String select = input.nextLine();
				if(select.equals("1")) {
					System.out.println(ansi().fg(YELLOW).a("�b��:").reset());
					account = input.nextLine();
					System.out.println(ansi().fg(YELLOW).a("�K�X:").reset());
					password = input.nextLine();
					int c1=ran.nextInt(9)+1;
					int c2=ran.nextInt(9)+1;
					int c3=ran.nextInt(9)+1;
					System.out.println(ansi().fg(YELLOW).a("�п�J���ҽX:"+c1+"*"+c2+"+"+c3+"=").reset());
					String check = input.nextLine();
					if(!check.equals(Integer.toString(c1*c2+c3))) {
						System.out.println(ansi().fg(RED).a("���ҽX���~!").reset());
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
						System.out.println(ansi().fg(RED).a("�b���K�X���~").reset());
						continue;
					}
				}else if(select.equals("2")) {
					System.out.println(ansi().fg(YELLOW).a("�m�W:").reset());
					name = input.nextLine();
					System.out.println(ansi().fg(YELLOW).a("�b��:").reset());
					account = input.nextLine();
					System.out.println(ansi().fg(YELLOW).a("�K�X:").reset());
					password = input.nextLine();
					if(account.equals("0")||name.equals("0")){
						System.out.println(ansi().fg(CYAN).a("�b���Ωm�W���i��0").reset());
					}
					else if(data.searchName(name,account).equals("NO")){
						data.createAccount(name,account,password);
						System.out.println(ansi().fg(CYAN).a(" �b�����U����").reset());
					}
					else {
						System.out.println(ansi().fg(CYAN).a(" �H���ۦP�b���A���U����").reset());
					}
					continue;
				}else if(select.equals("0")) {
					input.close();
					System.exit(1);
				}
				else{
					System.out.println(ansi().fg(RED).a(" ��J���~ �Э��s��J").reset());
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
		audio.loadAudio("������.wav");
		audio.setPlayCount(0);  //�L������
		audio.play();
		String select;
		if(permission==1) {
			logicNumber++;
		}
		while(true) {
			if(permission==2)System.out.println("  System ���b�ϥ� "+name+" ����ƾ� ");
			System.out.print(ansi().fg(YELLOW).a("").reset());
			//���դ��
			if(permission==1) {
				System.out.printf("************************** %n");
				System.out.print(ansi().fg(CYAN).a("  �w�� ").reset());
				System.out.print(ansi().fg(GREEN).a(name).reset());
				System.out.println(ansi().fg(CYAN).a(" �ϥΦ�ƾ�!").reset());
				System.out.print(ansi().fg(CYAN).a("�w�g�n�J����: ").reset());
				System.out.print(ansi().fg(GREEN).a(""));
				System.out.printf("%d %n",logicNumber);
				System.out.print(ansi().fg(CYAN).a("�W���n�J�ɶ�: ").reset());
				System.out.print(ansi().fg(GREEN).a(""));
				System.out.printf("%s %n",lastDate.toString());
				System.out.print(ansi().fg(MAGENTA).a("").reset());
				System.out.printf("************************** %n");
			}
			Calender.printCalender_yy_mm();

			if(permission==1) {
				System.out.println(ansi().fg(YELLOW).a("  �п�ܱ��U�ӷQ���檺����").reset());
				System.out.print(ansi().fg(colorOrder[0]).a("(0 �d�ߤ��-       	").reset());System.out.println(ansi().fg(colorOrder[0]).a("(7 �ק�B��s��{-").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("(1 ��ܤ��Ѥ��-	").reset());System.out.println(ansi().fg(colorOrder[1]).a("(8 �R����{-").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("(2 ��ܤW�@�Ӥ���-	").reset());System.out.println(ansi().fg(colorOrder[2]).a("(9 �ק�K�X-").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("(3 ��ܤU�@�Ӥ���-	").reset());System.out.println(ansi().fg(colorOrder[3]).a("(10 ���ֳ]�w-").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("(4 ��ܯS�w�����{-	").reset());System.out.println(ansi().fg(colorOrder[2]).a("(11 �s�ɨê�^�n���e��-").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("(5 �j�M��{-		").reset());System.out.println(ansi().fg(colorOrder[1]).a("(12 �s�ɨõ����{��-").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("(6 �s�W��{-	").reset());
				Date dNow = new Date( );
			    SimpleDateFormat ft =
			    new SimpleDateFormat ("yyyy/MM/dd");
			    String today=ft.format(dNow).toString();
			    view.ViewToday(today,1);
			}
			if(permission==2) {
				System.out.println(ansi().fg(YELLOW).a("  �п�ܱ��U�ӷQ���檺����").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("(1 �d�ߤ��-").reset());
				System.out.println(ansi().fg(colorOrder[1]).a("(2 ��ܯS�w�����{-").reset());
				System.out.println(ansi().fg(colorOrder[2]).a("(3 �j�M��{-").reset());
				System.out.println(ansi().fg(colorOrder[2]).a("(4 ���ֳ]�w-").reset());
				System.out.println(ansi().fg(colorOrder[1]).a("(5 �s�ɨê�^-").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("(6 �s�ɨõ����{��-").reset());
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
						System.out.println(ansi().fg(YELLOW).a("�п�J�~:").reset());
						year=Integer.parseInt(input.next());
						System.out.println(ansi().fg(YELLOW).a("�п�J���:").reset());
						month=Integer.parseInt(input.next());
						check=1;
					}
					catch(Exception e)
				    {
					System.out.println(ansi().fg(RED).a("�u���J�Ʀr").reset());
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
					System.out.println(ansi().fg(YELLOW).a("  �п�ܦp����ܸ��").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("(1 ��ܩҦ������{").reset());
					System.out.println(ansi().fg(colorOrder[1]).a("(2 ��ܯS�w�����{").reset());
					System.out.println(ansi().fg(colorOrder[2]).a("(3 ��ܤ��Ѧ�{").reset());
					System.out.println(ansi().fg(colorOrder[3]).a("(4 ��^").reset());
					select = input.nextLine();
					//��ܥ���
					if(select.equals("1")) {
						view.ViewAll();
						break;
					//��ܯS�w���
					}else if(select.equals("2")) {
						System.out.print(ansi().fg(YELLOW).a("�п�J�n�j�M���ɶ�: ").reset());
						System.out.println(ansi().fg(RED).a("ex,2017/06/06").reset());
						select = input.nextLine();
						Scanner re = new Scanner(select).useDelimiter("/");
						int yy,mm,dd;
						yy = re.nextInt();
						mm = re.nextInt();
						dd = re.nextInt();
						DateX reDate = new DateX(mm,dd,yy);
						//�P�_����O�_���T
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
					System.out.println(ansi().fg(YELLOW).a("�п�ܦp��j�M��{").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("(1 �ϥΦ�{�W�l/").reset());
					System.out.println(ansi().fg(colorOrder[1]).a("(2 �ϥζ}�l�ɶ�/").reset());
					System.out.println(ansi().fg(colorOrder[2]).a("(3 �ϥε����ɶ�/").reset());
					System.out.println(ansi().fg(colorOrder[3]).a("(4 �ϥΧ����ʤ���/").reset());
					System.out.println(ansi().fg(colorOrder[4]).a("(5 �ϥΦ�{���A/").reset());
					System.out.println(ansi().fg(colorOrder[4]).a("(6 �ϥΦ�{�s��/").reset());
					System.out.println(ansi().fg(colorOrder[3]).a("(7 �ϥΦ�{����/").reset());
					System.out.println(ansi().fg(colorOrder[2]).a("(8 �ϥΦ�{���e/").reset());
					System.out.println(ansi().fg(colorOrder[1]).a("(9 ��^���/").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("(10 �h�X�{��").reset());
					select = input.nextLine();
					//��ܥ���
					if(select.equals("1")) {
						System.out.println(ansi().fg(YELLOW).a("�п�J�n�j�M����{�W�l").reset());

						select = input.nextLine();
						view.ViewClass("workName",select);
						break;
					}else if(select.equals("2")) {
						System.out.print(ansi().fg(YELLOW).a("�п�J�n�j�M���}�l�ɶ�: ").reset());
						System.out.println(ansi().fg(RED).a("ex,2017/06/06").reset());
						select = input.nextLine();
						Scanner re = new Scanner(select).useDelimiter("/");
						int yy,mm,dd;
						yy = re.nextInt();
						mm = re.nextInt();
						dd = re.nextInt();
						DateX reDate = new DateX(mm,dd,yy);
						//�P�_����O�_���T
						if(reDate.dateOK(mm,dd,yy)) {
							view.ViewClass("startDate",reDate.toString());
						}else {
							continue;
						}
						break;
					}else if(select.equals("3")) {
						System.out.print(ansi().fg(YELLOW).a("�п�J�n�j�M�������ɶ�: ").reset());
						System.out.println(ansi().fg(RED).a("ex,2017/06/06").reset());
						select = input.nextLine();
						Scanner re = new Scanner(select).useDelimiter("/");
						int yy,mm,dd;
						yy = re.nextInt();
						mm = re.nextInt();
						dd = re.nextInt();
						DateX reDate = new DateX(mm,dd,yy);
						//�P�_����O�_���T
						if(reDate.dateOK(mm,dd,yy)) {
							view.ViewClass("endDate",reDate.toString());
						}else {
							continue;
						}

						break;
					}else if(select.equals("4")) {
						System.out.println(ansi().fg(YELLOW).a("�п�J�n�j�M�������ʤ���").reset());
						select = input.nextLine();
						view.ViewClass("percent",select);
						break;
					}else if(select.equals("5")) {
						System.out.println(ansi().fg(YELLOW).a("�п�J�n�j�M���������A").reset());
						select = input.nextLine();
						view.ViewClass("status",select);
						break;
					}else if(select.equals("6")) {
						System.out.println(ansi().fg(YELLOW).a("�п�J�n�j�M����{�s��").reset());
						select = input.nextLine();
						view.ViewClass("Numbering",select);
						break;
					}else if(select.equals("7")) {
						System.out.println(ansi().fg(YELLOW).a("�п�J�n�j�M����{����").reset());
						select = input.nextLine();
						view.ViewClass("classX",select);
						break;
					}else if(select.equals("8")) {
						System.out.println(ansi().fg(YELLOW).a("�п�J�n�j�M����{���e").reset());
						select = input.nextLine();
						view.ViewClass("work",select);
						break;
					}else if(select.equals("9")) {
						break;
					}else if(select.equals("10")) {
							System.exit(1);
					}else {
						System.out.println(ansi().fg(RED).a("�Э��s��J").reset());
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
				System.out.println(ansi().fg(RED).a("�R������").reset());
				continue;
			}else if(select.equals("9")) {
				if(permission==2) {
					continue;
				}
				reHandleData.updateAccount(name);
				continue;
			}else if(select.equals("10")) {
				while(true) {
					System.out.println(ansi().fg(YELLOW).a("  �п�ܱ��U�ӷQ���檺����").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("(1 �Ȱ����񭵼�-").reset());
					System.out.println(ansi().fg(colorOrder[1]).a("(2 ��_���񭵼�-").reset());
					System.out.println(ansi().fg(colorOrder[2]).a("(3 ���q�W�[-").reset());
					System.out.println(ansi().fg(colorOrder[3]).a("(4 ���q���-").reset());
					System.out.println(ansi().fg(colorOrder[4]).a("(5 ��^�D�e��-").reset());
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
						System.out.print(ansi().fg(YELLOW).a("�ثe���q : ").reset());
						System.out.println(ansi().fg(BLUE).a((int)(volume*10)+"/10").reset());						continue;
					}else if(select1.equals("4")) {
						volume-=0.1;
						if(volume<0)volume=0;
						audio.setVolume(volume);
						System.out.print(ansi().fg(YELLOW).a("�ثe���q : ").reset());
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
			System.out.println(ansi().fg(YELLOW).a("�п�ܱ��U�ӷQ���檺����").reset());
			System.out.println(ansi().fg(colorOrder[0]).a("(1 ��ܩҦ��ϥΪ�").reset());
			System.out.println(ansi().fg(colorOrder[1]).a("(2 �s�W�ϥΪ�").reset());
			System.out.println(ansi().fg(colorOrder[2]).a("(3 �d�ߨϥΪ̦�{").reset());
			System.out.println(ansi().fg(colorOrder[3]).a("(4 �]�w�ϥΪ�").reset());
			System.out.println(ansi().fg(colorOrder[4]).a("(5 �ϥΪ̱K�X���]").reset());
			System.out.println(ansi().fg(colorOrder[1]).a("(6 �R���ϥΪ�").reset());
			System.out.println(ansi().fg(colorOrder[0]).a("(7 �h�X�{��").reset());
			String select = input.nextLine();
			if(select.equals("1")) {
				System.out.print(ansi().fg(CYAN).a("�m�W  ").reset());
				System.out.println(ansi().fg(BLUE).a("�b��").reset());
				for(int i =0;i<AccountData.size();i++) {
				System.out.print(ansi().fg(CYAN).a(AccountData.get(i).name).reset());
				System.out.print(" ");
				System.out.println(ansi().fg(BLUE).a(AccountData.get(i).account).reset());
				}
			}else if(select.equals("2")) {
				System.out.println(ansi().fg(YELLOW).a("�m�W:").reset());
				name = input.nextLine();
				System.out.println(ansi().fg(YELLOW).a("�b��:").reset());
				account = input.nextLine();
				password = "0000";
				if(name.equals("0")||account.equals("0")){
					System.out.println(ansi().fg(CYAN).a("�b���Ωm�W���i��0").reset());
				}
				else if(NameJudge(name,account)==0){
					data.createAccount(name,account,password);
					System.out.println(ansi().fg(CYAN).a("�K�X�w�]��0000").reset());
					System.out.println(ansi().fg(CYAN).a("�b�����U����").reset());
				}
				else{
					System.out.println(ansi().fg(RED).a("�b���w�s�b").reset());
				}
			}else if(select.equals("3")) {
				int check=0;
				while(true){
					System.out.println(ansi().fg(YELLOW).a("	�п�J�Q�n�d�ݦ�{���ϥΪ�:").reset());
					System.out.println(ansi().fg(RED).a("(0���})").reset());
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
						System.out.print(ansi().fg(RED).a("	�d�L���ϥΪ�").reset());
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
					System.out.println(ansi().fg(YELLOW).a("	�п�J�Q�n���]�K�X���ϥΪ�:").reset());
					System.out.println(ansi().fg(RED).a("(0���})").reset());
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
						System.out.print(ansi().fg(RED).a("	�d�L���ϥΪ�").reset());
					}
				}
			}else if(select.equals("6")) {
				int check=1;
				out.print("	�п�J�Q�n�R�����ϥΪ�:");
				while(true){
					System.out.println(ansi().fg(YELLOW).a("	�п�J�Q�n�R�����ϥΪ�:").reset());
					System.out.println(ansi().fg(RED).a("(0���})").reset());
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
						System.out.print(ansi().fg(RED).a("	�d�L���ϥΪ�").reset());
					}
				}
			}else if(select.equals("7")) {
				System.exit(1);
			}
		}
	}
}
