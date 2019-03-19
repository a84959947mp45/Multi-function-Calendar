
import java.util.*;
import java.io.*;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import static java.lang.System.out;

public class DataHandle {
	public static List<AccountDataStruct> AccountData = new ArrayList<AccountDataStruct>();
	public static List<DataStruct> DataHere = new ArrayList<DataStruct>();
	public static int logicNumber = 0;
	public static DateX lastDate = new DateX("June",7,1998);

	//存取所有帳號資料
	public DataHandle(){
		AnsiConsole.systemInstall();
	}
	public  void createAccount(String Name,String Account,String Password) {
		AccountDataStruct re=new AccountDataStruct();
		re.name= Name;
		re.account=Account;
		re.passwd=Password;
		AccountData.add(re);
		String s = Name +".txt";
		File file=new File(s);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AccountOutputFile();
	}
	 public  void inputAccount() {
		 Scanner  inputStream  = null;
		 //存取所有帳號資料
		 try {
			 inputStream = new Scanner(new FileInputStream("AccountData.txt"));
			 while(inputStream.hasNext()) {
				 AccountDataStruct re= new AccountDataStruct();
				 re.name=inputStream.nextLine();
				 re.account=inputStream.nextLine();
				 re.passwd=inputStream.nextLine();
				 AccountData.add(re);
			 }
			 inputStream.close();
		 }
		 catch(Exception e) {

		 }
	 }
	 public String searchAccount(String account) {
		  for(int i =0;i<AccountData.size();i++) {
			   if(AccountData.get(i).account.equals(account)) {
				   return AccountData.get(i).name;
			   }
		  }
		  return "NO";
	 }
	 //輸入帳號密碼，引入資料到陣列，假如沒有則回傳NO
	 public String searchAccount(String account,String passwd) {
		  for(int i =0;i<AccountData.size();i++) {
			   if(AccountData.get(i).account.equals(account)&&AccountData.get(i).passwd.equals(passwd)) {
				   inputFile(AccountData.get(i).name);
				   return AccountData.get(i).name;
			   }
		  }
		  return "NO";
	 }
	 public String searchName(String name,String account) {
		  for(int i =0;i<AccountData.size();i++) {
			   if(AccountData.get(i).name.equals(name)||AccountData.get(i).account.equals(account)) {
				   inputFile(AccountData.get(i).name);
				   return AccountData.get(i).name;
			   }
		  }
		  return "NO";
	 }
	 //依照使用者找檔案存入DataHere
	 public void inputFile(String name) {
		 DataHere.clear();
		Scanner  inputStream  = null;
		String s = name +".txt";

		try {
			File x =new File(s);
		   inputStream = new Scanner(new FileInputStream(x));
		   String number = inputStream.nextLine();
                logicNumber = Integer.parseInt(number);
                String YY =(String) inputStream.nextLine();
			   Scanner reDateX = new Scanner(YY);
			    String x1 = (String)reDateX.next();
			    int  x2 = (int)reDateX.nextInt(), x3= (int)reDateX.nextInt();
			   lastDate = new DateX(x1,x2,x3);
			   reDateX.close();
		   while(inputStream.hasNext()) {
			   DataStruct re = new DataStruct();
			   re.workName  = (String)inputStream.nextLine();
			   String XX = (String)inputStream.nextLine();
			   Scanner reDate = new Scanner(XX);
			   //月日年
			   String r1 = (String)reDate.next(); int r2 = (int)reDate.nextInt(),r3 =(int) reDate.nextInt();
			   re.startDate = new DateX(r1,r2,r3);
			   reDate.close();
			    XX =(String) inputStream.nextLine();
			   Scanner reDateY = new Scanner(XX);
			    r1 = (String)reDateY.next();  r2 = (int)reDateY.nextInt(); r3= (int)reDateY.nextInt();
			   re.endDate = new DateX(r1,r2,r3);
			   reDateY.close();
			   re.percent   = (String)inputStream.nextLine();
			   re.status    = (String)inputStream.nextLine();
			   re.Numbering = (String)inputStream.nextLine();
			   re.classX    = (String)inputStream.nextLine();
			   re.work      = (String)inputStream.nextLine();
			   DataHere.add(re);
		   }
		}

			 catch(Exception e)
		     {

			 }
			 inputStream.close();

	}
	 //存回檔案
	 public void OutputFile(String name) {
			PrintWriter  outputStream  = null;
			String s = name +".txt";


			try {
				File x =new File(s);
				outputStream = new PrintWriter(new FileOutputStream(x));
				outputStream.println(Integer.toString(logicNumber));
				outputStream.println(lastDate.toString());
				for(int i =0; i <DataHere.size();i++ ) {
					outputStream.println(DataHere.get(i).workName);
					outputStream.println(DataHere.get(i).startDate.toString());
					outputStream.println(DataHere.get(i).endDate.toString());
					outputStream.println(DataHere.get(i).percent);
					outputStream.println(DataHere.get(i).status);
					outputStream.println(DataHere.get(i).Numbering);
					outputStream.println(DataHere.get(i).classX);
					outputStream.println(DataHere.get(i).work);
				}
				outputStream.close();

			 }
			 catch(Exception e)
		     {

			 }
		}
	 	//存回帳號資料檔案
	  public void AccountOutputFile() {
			PrintWriter  outputStream  = null;


			try {
				outputStream = new PrintWriter(new FileOutputStream("AccountData.txt"));
				for(int i =0; i <AccountData.size();i++ ) {
					outputStream.println(AccountData.get(i).name);
					outputStream.println(AccountData.get(i).account);
					outputStream.println(AccountData.get(i).passwd);
				}
				outputStream.close();

			 }
			 catch(Exception e)
		     {

			 }
		}
}
