
import java.util.*;
import static java.lang.System.out;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
public class reHandleData extends Function{
	static final Color[] colorOrder={RED,BLUE,MAGENTA,GREEN,CYAN};
	public static void SettingUser(){
		AnsiConsole.systemInstall();
		String name="";
		int check=1;
		do{
			System.out.println(ansi().fg(YELLOW).a("	請輸入想要修改的使用者:").reset());
			System.out.println(ansi().fg(RED).a("(0離開)").reset());
			name=input.nextLine();
			if(name.equals("0")){
				return;
			}
			for(int i =0;i<AccountData.size();i++) {
				if(AccountData.get(i).name.equals(name)){
					check=0;
					break;
				}
			}
			if(check==1){
				System.out.print(ansi().fg(RED).a("	查無此使用者").reset());
			}
		}while(check==1);
		System.out.println(ansi().fg(YELLOW).a(name).reset());
		data.inputFile(name);
		while(true) {
		System.out.println(ansi().fg(YELLOW).a("請選擇接下來想執行的項目").reset());
		System.out.println(ansi().fg(colorOrder[0]).a("(1 排序全部資料，後存入資料").reset());
		System.out.println(ansi().fg(colorOrder[1]).a("(2 設定使用者排序是由大或小").reset());
		System.out.println(ansi().fg(colorOrder[2]).a("(3 返回").reset());
		System.out.println(ansi().fg(YELLOW).a("(4 退出程式").reset());
		String select = input.nextLine();
		//選資料顯示的模式
		if(select.equals("1")) {
				System.out.println(ansi().fg(colorOrder[0]).a("(1 由大到小").reset());
				System.out.println(ansi().fg(colorOrder[1]).a("(2 由小到大").reset());
				System.out.println(ansi().fg(YELLOW).a("(3 返回").reset());

				select = input.nextLine();
				if(select.equals("1")) {
					view.controlls=-1;
					view.SortWhat(select);
					data.OutputFile(name);
					System.out.println(ansi().fg(YELLOW).a("完成排序").reset());
				}else if(select.equals("2")) {
					view.controlls=1;
					view.SortWhat(select);
					data.OutputFile(name);
				}else if(select.equals("3")) {
					continue;
				}
		}
		else if(select.equals("2")) {
			System.out.println(ansi().fg(colorOrder[0]).a("(1 由大到小").reset());
			System.out.println(ansi().fg(colorOrder[1]).a("(2 由小到大").reset());
			System.out.println(ansi().fg(YELLOW).a("(3 返回").reset());
			select = input.nextLine();
			if(select.equals("1")) {
				view.controlls=-1;
			}else if(select.equals("2")) {
				view.controlls=1;
			}else if(select.equals("3")) {
				continue;
			}
		}else if(select.equals("3")) {
			return;
		}else if(select.equals("4")) {
			System.exit(1);
		}
		}
	}
	//修改密碼
	public static void updateAccount(String name) {
		AnsiConsole.systemInstall();
		int position=-1;

		position=judgeAccount(name);
		while(true) {
			System.out.print(ansi().fg(YELLOW).a("請輸入舊密碼: ").reset());
			System.out.println(ansi().fg(RED).a("輸入0退出").reset());
			String select = input.nextLine();
			if(select.equals("0")) {
				break;
			}else if(!select.equals(AccountData.get(position).passwd)) {
				System.out.println(ansi().fg(RED).a("密碼錯誤請重新輸入").reset());
				continue;
			}else {
				System.out.println(ansi().fg(YELLOW).a("輸入新密碼").reset());
				String newPasswd = input.nextLine();
				System.out.println(ansi().fg(YELLOW).a("確認密碼").reset());
				String newPasswd2 = input.nextLine();
				if(newPasswd.equals(newPasswd2)) {
					AccountDataStruct re =new AccountDataStruct();
					re.name = AccountData.get(position).name;
					re.account = AccountData.get(position).account;
					re.passwd = newPasswd;
					AccountData.set(position, re);
					data.AccountOutputFile();
					return;
				}
			}
		}

	}
	//刪除人員
	public static void deleteAccount(String name) {
			AnsiConsole.systemInstall();
			int position=-1;

			position=judgeAccount(name);

				AccountData.remove(position);
				data.AccountOutputFile();
				System.out.println(ansi().fg(YELLOW).a("完成刪除").reset());

	}
	//重設密碼為00000000
	public static void restPassword(String name) {
		AnsiConsole.systemInstall();
		int position=-1;

		position=judgeAccount(name);

		AccountDataStruct re =new AccountDataStruct();
		re.name = AccountData.get(position).name;
		re.account = AccountData.get(position).account;
		re.passwd = "00000000";
		AccountData.set(position, re);
		data.AccountOutputFile();
		System.out.println(ansi().fg(YELLOW).a("完成重設").reset());
		return;

}
	//刪除資料
	public static void deleteData() {
		AnsiConsole.systemInstall();
		while(true) {
			int position=-1;
			System.out.print(ansi().fg(YELLOW).a("接下來輸入你想要刪除的資料工作編號").reset());
			System.out.println(ansi().fg(RED).a("(輸入0離開)").reset());
			String select = input.nextLine();
			position=judgeUpdate(select);

			if(select.equals("0")) {
				  while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a("Y/N").reset());
				     String ss = input.nextLine();
				     if(ss.equals("Y")) {
						   return;
				     }else if(ss.equals("N")) {
						   break;
				     }else {
				   continue;
				   }
			    }
				   continue;
			   //判斷資料正確否
			}else if(position>=0) {
				  DataHere.remove(position);
				  return;
			}else {
					System.out.println(ansi().fg(RED).a("錯誤的工作編號，請重新輸入").reset());
				 continue;
			}
		}
	}
	public static void updateData() {
		AnsiConsole.systemInstall();
		while(true) {
			int XXX;
			System.out.println(ansi().fg(YELLOW).a("接下來輸入你想要修改的資料工作編號").reset());
			System.out.println(ansi().fg(RED).a("(輸入0離開)").reset());
			String select = input.nextLine();
			XXX=judgeUpdate(select);

			if(select.equals("0")) {
				  while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a("Y/N").reset());
				     String ss = input.nextLine();
				     if(ss.equals("Y")) {
						   return;
				     }else if(ss.equals("N")) {
						   break;
				     }else {
				   continue;
				   }
			    }
				   continue;
			   //判斷資料正確否
		     }else if(XXX>=0){
		    	  DataStruct re =  DataHere.get(XXX);
				  while(true) {
						System.out.print(ansi().fg(YELLOW).a("請選擇想要修改或更新的功能").reset());
						System.out.println(ansi().fg(RED).a("(輸入0隨時可以重新選擇)").reset());
						System.out.print(ansi().fg(colorOrder[0]).a("(1 使用工作名子/").reset());
						System.out.print(ansi().fg(colorOrder[1]).a("(2 使用開始時間/").reset());
						System.out.print(ansi().fg(colorOrder[2]).a("(3 使用結束時間/").reset());
						System.out.print(ansi().fg(colorOrder[3]).a("(4 使用完成百分比/").reset());
						System.out.println(ansi().fg(colorOrder[4]).a("(5 使用工作狀態/").reset());
						System.out.print(ansi().fg(colorOrder[3]).a("(6 使用工作編號/").reset());
						System.out.print(ansi().fg(colorOrder[2]).a("(7 使用工作分類/").reset());
						System.out.print(ansi().fg(colorOrder[1]).a("(8 使用工作內容/").reset());
						System.out.print(ansi().fg(colorOrder[0]).a("(9 重新輸入編號/").reset());
						System.out.println(ansi().fg(YELLOW).a("(10 返回選單/").reset());
						String SelectX = input.nextLine();
						if(SelectX.equals("1")) {
							System.out.println(ansi().fg(YELLOW).a("輸入修改的工作名子").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //判斷資料正確否
						    }else {
						    	re.workName = select;
						    }
						}else if(SelectX.equals("2")) {
							try{

								System.out.print(ansi().fg(YELLOW).a("輸入修改的開始時間,").reset());
								System.out.println(ansi().fg(RED).a("EX.2017/06/06").reset());
								select = input.nextLine();


							if(select.equals("0")) {
								   continue;
							   //判斷資料正確否
						    }else {
						       Scanner reDate = new Scanner(select).useDelimiter("/");
							   int yy = reDate.nextInt();
							   int mm = reDate.nextInt();
							   int dd = reDate.nextInt();
							   DateX startDate1 = new DateX(mm,dd,yy);
							   if(startDate1.dateOK(mm,dd,yy)) {
							      re.startDate = startDate1;
							   }else {
									System.out.println(ansi().fg(RED).a("時間格式錯誤，請確認後輸出").reset());
									continue;
							   }

								 reDate.close();

						    }
							}catch(Exception e){
								System.out.println(ansi().fg(RED).a("時間格式錯誤，請確認後輸出").reset());
									continue;


							}
						}else if(SelectX.equals("3")) {
							try{
							System.out.print(ansi().fg(YELLOW).a(" 輸入修改的結束時間,").reset());
							System.out.println(ansi().fg(RED).a("EX.2018/06/06").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //判斷資料正確否
						    }else {
						    	Scanner reDate = new Scanner(select).useDelimiter("/");
								   int yy = reDate.nextInt();
								   int mm = reDate.nextInt();
								   int dd = reDate.nextInt();
								   DateX endDate1 = new DateX(mm,dd,yy);
								   if(endDate1.dateOK(mm,dd,yy)) {
								      re.endDate = endDate1;
								   }else {
									System.out.println(ansi().fg(RED).a("時間格式錯誤，請確認後輸出").reset());
										continue;
								   }
								    reDate.close();
							}

							}catch(Exception e){
								System.out.println(ansi().fg(RED).a("時間格式錯誤，請確認後輸出").reset());
									continue;
								}


						}else if(SelectX.equals("4")) {
							System.out.println(ansi().fg(YELLOW).a(" 輸入修改的完成百分比,").reset());
							System.out.print(ansi().fg(RED).a("EX 99%").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //判斷資料正確否
						    }else {
						    	if(select.charAt(select.length()-1)=='%') {
									   Scanner rePercent = new Scanner(select).useDelimiter("%");
									   float ss = rePercent.nextFloat();
									   if(0<=ss&&ss<=100) {
										     re.percent = select;
									   }else {
									System.out.println(ansi().fg(YELLOW).a("請輸入0~100的完成度").reset());
										   continue;
									   }
								   }else {
									System.out.println(ansi().fg(RED).a("請輸入正確的值").reset());
									   continue;
								   }
						    }
						}else if(SelectX.equals("5")) {

							System.out.print(ansi().fg(YELLOW).a(" 輸入修改後的工作狀態，").reset());
							System.out.println(ansi().fg(RED).a("EX 完成、執行中、未開始").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //判斷資料正確否
						    }else {
						    	if(select.equals("完成")||select.equals("執行中")||select.equals("未開始")){
						    		re.status = select;
									   break;
								}else {
									System.out.println(ansi().fg(RED).a("請輸入正確的值").reset());
								}
						    }
						}else if(SelectX.equals("6")) {
							System.out.print(ansi().fg(RED).a(" 輸入修改後工作編號，數字都可以").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //判斷資料正確否
						    }else {
						    	if(judgeNumber(select, select.length())==0) {
									   continue;
								   }else {
									   re.Numbering = select;
								   }
						    }
						}else if(SelectX.equals("7")) {
							System.out.print(ansi().fg(RED).a(" 輸入修改後的分類").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //判斷資料正確否
						    }else {
							System.out.println(ansi().fg(RED).a("接下來輸入工作分類: ").reset());
							System.out.print(ansi().fg(colorOrder[0]).a("(1 工作").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.print(ansi().fg(colorOrder[1]).a("(2 考試").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.print(ansi().fg(colorOrder[2]).a("(3 私人").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.print(ansi().fg(colorOrder[3]).a("(4 活動").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.print(ansi().fg(colorOrder[4]).a("(5 其他").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.println(ansi().fg(colorOrder[3]).a("(6 自訂").reset());
								    select = input.nextLine();
								   if(select.equals("0")) {
									   continue;
								   }else if(select.equals("1")) {
									   re.classX  = "工作";
									   break;
								   }else if(select.equals("2")) {
									   re.classX  = "考試";
									   break;
								   }else if(select.equals("3")) {
									   re.classX  = "私人";
									   break;
								   }else if(select.equals("4")) {
									   re.classX  = "活動";
									   break;
								   }else if(select.equals("5")) {
									   re.classX  = "其他";
									   break;
								   }else {
										System.out.println(ansi().fg(YELLOW).a("接下來輸入自訂分類: ").reset());
									   String XX  = input.nextLine();
									   if(XX.equals("0")) {
											continue;
										   //判斷資料正確否
									    }else {
											   re.classX=XX;
										}
								   }

						    }
						}else if(SelectX.equals("8")) {
							System.out.print(ansi().fg(YELLOW).a(" 輸入修改後工作內容").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //判斷資料正確否
						    }else {
						    	re.work = select;
						    }
						}else if(SelectX.equals("9")) {
							DataHere.set(XXX, re);
							break;
						}else if(SelectX.equals("10")) {
							DataHere.set(XXX, re);
							return;
						}

				  }
				  DataHere.set(XXX, re);
		 }else{
				System.out.println(ansi().fg(RED).a("請輸入正確的值").reset());
		 }
	  }

    }

	public static void addNewDate() {
		AnsiConsole.systemInstall();
		while(true) {
			System.out.println(ansi().fg(YELLOW).a("接下來輸入你想要更新的資料，").reset());
			System.out.print(ansi().fg(colorOrder[0]).a("工作名稱").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[1]).a("開始時間").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[2]).a("結束時間").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[3]).a("完成度").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[4]).a("工作狀態").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[3]).a("工作編號").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[2]).a("工作分類").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.println(ansi().fg(colorOrder[1]).a("工作內容").reset());
			System.out.println(ansi().fg(RED).a("輸入0可以離開輸入").reset());
		   DataStruct re = new DataStruct();
		   //輸入工作名稱
		   while(true) {
				System.out.println(ansi().fg(YELLOW).a("接下來輸入工作名稱:").reset());
			   re.workName  = input.nextLine();
			   //跳出離開確認
			   if(re.workName.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a(" Y/N").reset());
					   String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
					   }else {
						   continue;
					   }
				   }
				   continue;
			   //判斷資料正確否
			   }else {
				   break;
			   }
		   }
		   //輸入開始時間
		   while(true) {
			try{
			System.out.print(ansi().fg(YELLOW).a("接下來輸入開始時間 ").reset());
			System.out.println(ansi().fg(RED).a("ex.2017/06/01:").reset());
			   String XX = input.nextLine();
			   if(XX.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a(" Y/N").reset());
					   String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
					   }else {
						   continue;
					   }
				   }
				   continue;
			   //判斷資料正確否
			   }else {
				   Scanner reDate = new Scanner(XX).useDelimiter("/");
				   int yy = reDate.nextInt();
				   int mm = reDate.nextInt();
				   int dd = reDate.nextInt();
				   DateX startDate  = new DateX(mm,dd,yy);
				   if(startDate.dateOK(mm,dd,yy)) {
					    re.startDate = startDate;
					}else {
						continue;
					}
				   reDate.close();
				   break;
			   }
			}catch(Exception e){
				System.out.println(ansi().fg(RED).a("輸入錯誤").reset());
			}
		   }
		   //輸入結束時間
		   while(true) {
			try{
				System.out.print(ansi().fg(YELLOW).a("接下來輸入結束時間  ").reset());
				System.out.println(ansi().fg(RED).a("ex.2017/06/01:").reset());
			   String XX = input.nextLine();
			   if(XX.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a(" Y/N").reset());
					   String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
					   }else {
						   continue;
					   }
				   }
				   continue;
			   //判斷資料正確否
			   }else {
				   Scanner reDate = new Scanner(XX).useDelimiter("/");
				   int yy = reDate.nextInt();
				   int mm = reDate.nextInt();
				   int dd = reDate.nextInt();
				   DateX endDate1 = new DateX(mm,dd,yy);
				   if(endDate1.dateOK(mm,dd,yy)) {
					    re.endDate = endDate1;
					}else {
						continue;
					}
				   reDate.close();
				   break;
			   }
			}catch(Exception e){
				System.out.println(ansi().fg(RED).a("輸入錯誤").reset());
			}
		   }
		   //輸入完成度
		   while(true) {

				System.out.print(ansi().fg(YELLOW).a("接下來輸入完成度: ").reset());
				System.out.println(ansi().fg(RED).a("ex.87%").reset());
			   re.percent  = input.nextLine();
			   //跳出離開確認
			   if(re.percent.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a(" Y/N").reset());
					   String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
					   }else {
						   continue;
					   }
				   }
				   continue;
			   //判斷資料正確否
			   }else {
				   if(re.percent.charAt(re.percent.length()-1)=='%') {
					   Scanner rePercent = new Scanner(re.percent).useDelimiter("%");
					   float ss = rePercent.nextFloat();
					   if(0<=ss&&ss<=100) {
						     break;
					   }else {
							System.out.println(ansi().fg(YELLOW).a("請輸入0~100的完成度").reset());
						   continue;
					   }
				   }else {
						System.out.println(ansi().fg(RED).a("請輸入正確的值").reset());
					   continue;
				   }
			   }
		   }
		   //判斷狀態
		   while(true) {
			System.out.print(ansi().fg(YELLOW).a("接下來輸入工作完成度: ").reset());
			System.out.println(ansi().fg(RED).a("ex.完成，執行中，未開始").reset());
			   re.status  = input.nextLine();
			   //跳出離開確認
			   if(re.status.equals("0")) {
				   while(true) {
						System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
						System.out.println(ansi().fg(RED).a(" Y/N").reset());
					   String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
					   }else {
						   continue;
					   }
				   }
				   continue;
			   //判斷資料正確否
			   }else if(re.status.equals("完成")||re.status.equals("執行中")||re.status.equals("未開始")){
				   break;
			   }else {
				   continue;
			   }
		   }
		   //工作編號
		   while(true) {
			System.out.println(ansi().fg(YELLOW).a("接下來輸入工作編號: 數字都可以").reset());
			   re.Numbering  = input.nextLine();
			   //跳出離開確認
			   if(re.Numbering.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a(" Y/N").reset());
					   String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
					   }else {
						   continue;
					   }
				   }
				   continue;
			   //判斷資料正確否
			   }else {

				   if(judgeNumber(re.Numbering, re.Numbering.length())==0) {
					   continue;
				   }else {
					   break;
				   }
			   }
		   }
//		 //工作分類
		   while(true) {
				System.out.println(ansi().fg(YELLOW).a("接下來輸入工作分類: ").reset());
				System.out.print(ansi().fg(colorOrder[0]).a("(1 工作").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("(2 考試").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("(3 私人").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("(4 活動").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.print(ansi().fg(colorOrder[4]).a("(5 其他").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.println(ansi().fg(colorOrder[3]).a("(6 自訂").reset());

			   String select = input.nextLine();
			   if(select.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a(" Y/N").reset());
					   String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
					   }else {
						   continue;
					   }
				   }
				   continue;
			   }else if(select.equals("1")) {
				   re.classX  = "工作";
				   break;
			   }else if(select.equals("2")) {
				   re.classX  = "考試";
				   break;
			   }else if(select.equals("3")) {
				   re.classX  = "私人";
				   break;
			   }else if(select.equals("4")) {
				   re.classX  = "活動";
				   break;
			   }else if(select.equals("5")) {
				   re.classX  = "其他";
				   break;
			   }else {

				System.out.println(ansi().fg(YELLOW).a("接下來輸入自訂分類: ").reset());
				   re.classX  = input.nextLine();
			   }
				   //跳出離開確認
			   if(re.classX.equals("0")) {
				  while(true) {
					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a(" Y/N").reset());
				      String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
						}else {
				    	   continue;
					   }
		  	      }
					continue;
				   //判斷資料正確否
			    }else {
					   break;
				}

		   }
		   //工作內容
		   while(true) {
				System.out.println(ansi().fg(YELLOW).a("接下來輸入工作內容:").reset());
			   re.work  = input.nextLine();
			   //跳出離開確認
			   if(re.work.equals("0")) {
				   while(true) {

					System.out.print(ansi().fg(YELLOW).a("確認離開? ").reset());
					System.out.println(ansi().fg(RED).a(" Y/N").reset());
					   String ss = input.nextLine();
					   if(ss.equals("Y")) {
						   return;
					   }else if(ss.equals("N")) {
						   break;
					   }else {
						   continue;
					   }
				   }
				   continue;
			   //判斷資料正確否
			   }else {
				   break;
			   }
		   }
		   DataHere.add(re);
		   break;
		}
	}
	private static  int judgeAccount(String name) {
		for(int i =0 ;i<AccountData.size();i++) {
			 if(AccountData.get(i).name.equals(name)) {
				 return i;
			 }
		}
		return -1;
	}
	private static  int judgeUpdate(String number) {
		for(int i =0 ;i<DataHere.size();i++) {
			 if(DataHere.get(i).Numbering.equals(number)) {
				 return i;
			 }
		}
		return -1;
	}

	private static int judgeNumber(String a , int b) {
		for(int i =0;i<b;i++) {
			   if(!('0'<=a.charAt(i)&&a.charAt(i)<='9')){
					System.out.println(ansi().fg(RED).a("你輸入的不是數字，請確認後再輸入").reset());

				   return 0;
			   }
		}
		for(int i =0 ; i<DataHere.size();i++) {
			if(DataHere.get(i).Numbering.equals(a)) {
				System.out.println(ansi().fg(RED).a("不能有重複的工作編號喔，請重新輸入").reset());
				return 0;
			}
		}
		return 1;
	}
}


