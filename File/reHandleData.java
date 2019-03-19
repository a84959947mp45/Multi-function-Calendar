
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
			System.out.println(ansi().fg(YELLOW).a("	�п�J�Q�n�ק諸�ϥΪ�:").reset());
			System.out.println(ansi().fg(RED).a("(0���})").reset());
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
				System.out.print(ansi().fg(RED).a("	�d�L���ϥΪ�").reset());
			}
		}while(check==1);
		System.out.println(ansi().fg(YELLOW).a(name).reset());
		data.inputFile(name);
		while(true) {
		System.out.println(ansi().fg(YELLOW).a("�п�ܱ��U�ӷQ���檺����").reset());
		System.out.println(ansi().fg(colorOrder[0]).a("(1 �Ƨǥ�����ơA��s�J���").reset());
		System.out.println(ansi().fg(colorOrder[1]).a("(2 �]�w�ϥΪ̱ƧǬO�Ѥj�Τp").reset());
		System.out.println(ansi().fg(colorOrder[2]).a("(3 ��^").reset());
		System.out.println(ansi().fg(YELLOW).a("(4 �h�X�{��").reset());
		String select = input.nextLine();
		//������ܪ��Ҧ�
		if(select.equals("1")) {
				System.out.println(ansi().fg(colorOrder[0]).a("(1 �Ѥj��p").reset());
				System.out.println(ansi().fg(colorOrder[1]).a("(2 �Ѥp��j").reset());
				System.out.println(ansi().fg(YELLOW).a("(3 ��^").reset());

				select = input.nextLine();
				if(select.equals("1")) {
					view.controlls=-1;
					view.SortWhat(select);
					data.OutputFile(name);
					System.out.println(ansi().fg(YELLOW).a("�����Ƨ�").reset());
				}else if(select.equals("2")) {
					view.controlls=1;
					view.SortWhat(select);
					data.OutputFile(name);
				}else if(select.equals("3")) {
					continue;
				}
		}
		else if(select.equals("2")) {
			System.out.println(ansi().fg(colorOrder[0]).a("(1 �Ѥj��p").reset());
			System.out.println(ansi().fg(colorOrder[1]).a("(2 �Ѥp��j").reset());
			System.out.println(ansi().fg(YELLOW).a("(3 ��^").reset());
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
	//�ק�K�X
	public static void updateAccount(String name) {
		AnsiConsole.systemInstall();
		int position=-1;

		position=judgeAccount(name);
		while(true) {
			System.out.print(ansi().fg(YELLOW).a("�п�J�±K�X: ").reset());
			System.out.println(ansi().fg(RED).a("��J0�h�X").reset());
			String select = input.nextLine();
			if(select.equals("0")) {
				break;
			}else if(!select.equals(AccountData.get(position).passwd)) {
				System.out.println(ansi().fg(RED).a("�K�X���~�Э��s��J").reset());
				continue;
			}else {
				System.out.println(ansi().fg(YELLOW).a("��J�s�K�X").reset());
				String newPasswd = input.nextLine();
				System.out.println(ansi().fg(YELLOW).a("�T�{�K�X").reset());
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
	//�R���H��
	public static void deleteAccount(String name) {
			AnsiConsole.systemInstall();
			int position=-1;

			position=judgeAccount(name);

				AccountData.remove(position);
				data.AccountOutputFile();
				System.out.println(ansi().fg(YELLOW).a("�����R��").reset());

	}
	//���]�K�X��00000000
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
		System.out.println(ansi().fg(YELLOW).a("�������]").reset());
		return;

}
	//�R�����
	public static void deleteData() {
		AnsiConsole.systemInstall();
		while(true) {
			int position=-1;
			System.out.print(ansi().fg(YELLOW).a("���U�ӿ�J�A�Q�n�R������Ƥu�@�s��").reset());
			System.out.println(ansi().fg(RED).a("(��J0���})").reset());
			String select = input.nextLine();
			position=judgeUpdate(select);

			if(select.equals("0")) {
				  while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
			}else if(position>=0) {
				  DataHere.remove(position);
				  return;
			}else {
					System.out.println(ansi().fg(RED).a("���~���u�@�s���A�Э��s��J").reset());
				 continue;
			}
		}
	}
	public static void updateData() {
		AnsiConsole.systemInstall();
		while(true) {
			int XXX;
			System.out.println(ansi().fg(YELLOW).a("���U�ӿ�J�A�Q�n�ק諸��Ƥu�@�s��").reset());
			System.out.println(ansi().fg(RED).a("(��J0���})").reset());
			String select = input.nextLine();
			XXX=judgeUpdate(select);

			if(select.equals("0")) {
				  while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
		     }else if(XXX>=0){
		    	  DataStruct re =  DataHere.get(XXX);
				  while(true) {
						System.out.print(ansi().fg(YELLOW).a("�п�ܷQ�n�ק�Χ�s���\��").reset());
						System.out.println(ansi().fg(RED).a("(��J0�H�ɥi�H���s���)").reset());
						System.out.print(ansi().fg(colorOrder[0]).a("(1 �ϥΤu�@�W�l/").reset());
						System.out.print(ansi().fg(colorOrder[1]).a("(2 �ϥζ}�l�ɶ�/").reset());
						System.out.print(ansi().fg(colorOrder[2]).a("(3 �ϥε����ɶ�/").reset());
						System.out.print(ansi().fg(colorOrder[3]).a("(4 �ϥΧ����ʤ���/").reset());
						System.out.println(ansi().fg(colorOrder[4]).a("(5 �ϥΤu�@���A/").reset());
						System.out.print(ansi().fg(colorOrder[3]).a("(6 �ϥΤu�@�s��/").reset());
						System.out.print(ansi().fg(colorOrder[2]).a("(7 �ϥΤu�@����/").reset());
						System.out.print(ansi().fg(colorOrder[1]).a("(8 �ϥΤu�@���e/").reset());
						System.out.print(ansi().fg(colorOrder[0]).a("(9 ���s��J�s��/").reset());
						System.out.println(ansi().fg(YELLOW).a("(10 ��^���/").reset());
						String SelectX = input.nextLine();
						if(SelectX.equals("1")) {
							System.out.println(ansi().fg(YELLOW).a("��J�ק諸�u�@�W�l").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //�P�_��ƥ��T�_
						    }else {
						    	re.workName = select;
						    }
						}else if(SelectX.equals("2")) {
							try{

								System.out.print(ansi().fg(YELLOW).a("��J�ק諸�}�l�ɶ�,").reset());
								System.out.println(ansi().fg(RED).a("EX.2017/06/06").reset());
								select = input.nextLine();


							if(select.equals("0")) {
								   continue;
							   //�P�_��ƥ��T�_
						    }else {
						       Scanner reDate = new Scanner(select).useDelimiter("/");
							   int yy = reDate.nextInt();
							   int mm = reDate.nextInt();
							   int dd = reDate.nextInt();
							   DateX startDate1 = new DateX(mm,dd,yy);
							   if(startDate1.dateOK(mm,dd,yy)) {
							      re.startDate = startDate1;
							   }else {
									System.out.println(ansi().fg(RED).a("�ɶ��榡���~�A�нT�{���X").reset());
									continue;
							   }

								 reDate.close();

						    }
							}catch(Exception e){
								System.out.println(ansi().fg(RED).a("�ɶ��榡���~�A�нT�{���X").reset());
									continue;


							}
						}else if(SelectX.equals("3")) {
							try{
							System.out.print(ansi().fg(YELLOW).a(" ��J�ק諸�����ɶ�,").reset());
							System.out.println(ansi().fg(RED).a("EX.2018/06/06").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //�P�_��ƥ��T�_
						    }else {
						    	Scanner reDate = new Scanner(select).useDelimiter("/");
								   int yy = reDate.nextInt();
								   int mm = reDate.nextInt();
								   int dd = reDate.nextInt();
								   DateX endDate1 = new DateX(mm,dd,yy);
								   if(endDate1.dateOK(mm,dd,yy)) {
								      re.endDate = endDate1;
								   }else {
									System.out.println(ansi().fg(RED).a("�ɶ��榡���~�A�нT�{���X").reset());
										continue;
								   }
								    reDate.close();
							}

							}catch(Exception e){
								System.out.println(ansi().fg(RED).a("�ɶ��榡���~�A�нT�{���X").reset());
									continue;
								}


						}else if(SelectX.equals("4")) {
							System.out.println(ansi().fg(YELLOW).a(" ��J�ק諸�����ʤ���,").reset());
							System.out.print(ansi().fg(RED).a("EX 99%").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //�P�_��ƥ��T�_
						    }else {
						    	if(select.charAt(select.length()-1)=='%') {
									   Scanner rePercent = new Scanner(select).useDelimiter("%");
									   float ss = rePercent.nextFloat();
									   if(0<=ss&&ss<=100) {
										     re.percent = select;
									   }else {
									System.out.println(ansi().fg(YELLOW).a("�п�J0~100��������").reset());
										   continue;
									   }
								   }else {
									System.out.println(ansi().fg(RED).a("�п�J���T����").reset());
									   continue;
								   }
						    }
						}else if(SelectX.equals("5")) {

							System.out.print(ansi().fg(YELLOW).a(" ��J�ק�᪺�u�@���A�A").reset());
							System.out.println(ansi().fg(RED).a("EX �����B���椤�B���}�l").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //�P�_��ƥ��T�_
						    }else {
						    	if(select.equals("����")||select.equals("���椤")||select.equals("���}�l")){
						    		re.status = select;
									   break;
								}else {
									System.out.println(ansi().fg(RED).a("�п�J���T����").reset());
								}
						    }
						}else if(SelectX.equals("6")) {
							System.out.print(ansi().fg(RED).a(" ��J�ק��u�@�s���A�Ʀr���i�H").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //�P�_��ƥ��T�_
						    }else {
						    	if(judgeNumber(select, select.length())==0) {
									   continue;
								   }else {
									   re.Numbering = select;
								   }
						    }
						}else if(SelectX.equals("7")) {
							System.out.print(ansi().fg(RED).a(" ��J�ק�᪺����").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //�P�_��ƥ��T�_
						    }else {
							System.out.println(ansi().fg(RED).a("���U�ӿ�J�u�@����: ").reset());
							System.out.print(ansi().fg(colorOrder[0]).a("(1 �u�@").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.print(ansi().fg(colorOrder[1]).a("(2 �Ҹ�").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.print(ansi().fg(colorOrder[2]).a("(3 �p�H").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.print(ansi().fg(colorOrder[3]).a("(4 ����").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.print(ansi().fg(colorOrder[4]).a("(5 ��L").reset());
							System.out.print(ansi().fg(YELLOW).a("/").reset());
							System.out.println(ansi().fg(colorOrder[3]).a("(6 �ۭq").reset());
								    select = input.nextLine();
								   if(select.equals("0")) {
									   continue;
								   }else if(select.equals("1")) {
									   re.classX  = "�u�@";
									   break;
								   }else if(select.equals("2")) {
									   re.classX  = "�Ҹ�";
									   break;
								   }else if(select.equals("3")) {
									   re.classX  = "�p�H";
									   break;
								   }else if(select.equals("4")) {
									   re.classX  = "����";
									   break;
								   }else if(select.equals("5")) {
									   re.classX  = "��L";
									   break;
								   }else {
										System.out.println(ansi().fg(YELLOW).a("���U�ӿ�J�ۭq����: ").reset());
									   String XX  = input.nextLine();
									   if(XX.equals("0")) {
											continue;
										   //�P�_��ƥ��T�_
									    }else {
											   re.classX=XX;
										}
								   }

						    }
						}else if(SelectX.equals("8")) {
							System.out.print(ansi().fg(YELLOW).a(" ��J�ק��u�@���e").reset());
							select = input.nextLine();
							if(select.equals("0")) {
								   continue;
							   //�P�_��ƥ��T�_
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
				System.out.println(ansi().fg(RED).a("�п�J���T����").reset());
		 }
	  }

    }

	public static void addNewDate() {
		AnsiConsole.systemInstall();
		while(true) {
			System.out.println(ansi().fg(YELLOW).a("���U�ӿ�J�A�Q�n��s����ơA").reset());
			System.out.print(ansi().fg(colorOrder[0]).a("�u�@�W��").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[1]).a("�}�l�ɶ�").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[2]).a("�����ɶ�").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[3]).a("������").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[4]).a("�u�@���A").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[3]).a("�u�@�s��").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.print(ansi().fg(colorOrder[2]).a("�u�@����").reset());
			System.out.print(ansi().fg(YELLOW).a("/").reset());
			System.out.println(ansi().fg(colorOrder[1]).a("�u�@���e").reset());
			System.out.println(ansi().fg(RED).a("��J0�i�H���}��J").reset());
		   DataStruct re = new DataStruct();
		   //��J�u�@�W��
		   while(true) {
				System.out.println(ansi().fg(YELLOW).a("���U�ӿ�J�u�@�W��:").reset());
			   re.workName  = input.nextLine();
			   //���X���}�T�{
			   if(re.workName.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
			   }else {
				   break;
			   }
		   }
		   //��J�}�l�ɶ�
		   while(true) {
			try{
			System.out.print(ansi().fg(YELLOW).a("���U�ӿ�J�}�l�ɶ� ").reset());
			System.out.println(ansi().fg(RED).a("ex.2017/06/01:").reset());
			   String XX = input.nextLine();
			   if(XX.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
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
				System.out.println(ansi().fg(RED).a("��J���~").reset());
			}
		   }
		   //��J�����ɶ�
		   while(true) {
			try{
				System.out.print(ansi().fg(YELLOW).a("���U�ӿ�J�����ɶ�  ").reset());
				System.out.println(ansi().fg(RED).a("ex.2017/06/01:").reset());
			   String XX = input.nextLine();
			   if(XX.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
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
				System.out.println(ansi().fg(RED).a("��J���~").reset());
			}
		   }
		   //��J������
		   while(true) {

				System.out.print(ansi().fg(YELLOW).a("���U�ӿ�J������: ").reset());
				System.out.println(ansi().fg(RED).a("ex.87%").reset());
			   re.percent  = input.nextLine();
			   //���X���}�T�{
			   if(re.percent.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
			   }else {
				   if(re.percent.charAt(re.percent.length()-1)=='%') {
					   Scanner rePercent = new Scanner(re.percent).useDelimiter("%");
					   float ss = rePercent.nextFloat();
					   if(0<=ss&&ss<=100) {
						     break;
					   }else {
							System.out.println(ansi().fg(YELLOW).a("�п�J0~100��������").reset());
						   continue;
					   }
				   }else {
						System.out.println(ansi().fg(RED).a("�п�J���T����").reset());
					   continue;
				   }
			   }
		   }
		   //�P�_���A
		   while(true) {
			System.out.print(ansi().fg(YELLOW).a("���U�ӿ�J�u�@������: ").reset());
			System.out.println(ansi().fg(RED).a("ex.�����A���椤�A���}�l").reset());
			   re.status  = input.nextLine();
			   //���X���}�T�{
			   if(re.status.equals("0")) {
				   while(true) {
						System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
			   }else if(re.status.equals("����")||re.status.equals("���椤")||re.status.equals("���}�l")){
				   break;
			   }else {
				   continue;
			   }
		   }
		   //�u�@�s��
		   while(true) {
			System.out.println(ansi().fg(YELLOW).a("���U�ӿ�J�u�@�s��: �Ʀr���i�H").reset());
			   re.Numbering  = input.nextLine();
			   //���X���}�T�{
			   if(re.Numbering.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
			   }else {

				   if(judgeNumber(re.Numbering, re.Numbering.length())==0) {
					   continue;
				   }else {
					   break;
				   }
			   }
		   }
//		 //�u�@����
		   while(true) {
				System.out.println(ansi().fg(YELLOW).a("���U�ӿ�J�u�@����: ").reset());
				System.out.print(ansi().fg(colorOrder[0]).a("(1 �u�@").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("(2 �Ҹ�").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("(3 �p�H").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("(4 ����").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.print(ansi().fg(colorOrder[4]).a("(5 ��L").reset());
				System.out.print(ansi().fg(YELLOW).a("/").reset());
				System.out.println(ansi().fg(colorOrder[3]).a("(6 �ۭq").reset());

			   String select = input.nextLine();
			   if(select.equals("0")) {
				   while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
				   re.classX  = "�u�@";
				   break;
			   }else if(select.equals("2")) {
				   re.classX  = "�Ҹ�";
				   break;
			   }else if(select.equals("3")) {
				   re.classX  = "�p�H";
				   break;
			   }else if(select.equals("4")) {
				   re.classX  = "����";
				   break;
			   }else if(select.equals("5")) {
				   re.classX  = "��L";
				   break;
			   }else {

				System.out.println(ansi().fg(YELLOW).a("���U�ӿ�J�ۭq����: ").reset());
				   re.classX  = input.nextLine();
			   }
				   //���X���}�T�{
			   if(re.classX.equals("0")) {
				  while(true) {
					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
				   //�P�_��ƥ��T�_
			    }else {
					   break;
				}

		   }
		   //�u�@���e
		   while(true) {
				System.out.println(ansi().fg(YELLOW).a("���U�ӿ�J�u�@���e:").reset());
			   re.work  = input.nextLine();
			   //���X���}�T�{
			   if(re.work.equals("0")) {
				   while(true) {

					System.out.print(ansi().fg(YELLOW).a("�T�{���}? ").reset());
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
			   //�P�_��ƥ��T�_
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
					System.out.println(ansi().fg(RED).a("�A��J�����O�Ʀr�A�нT�{��A��J").reset());

				   return 0;
			   }
		}
		for(int i =0 ; i<DataHere.size();i++) {
			if(DataHere.get(i).Numbering.equals(a)) {
				System.out.println(ansi().fg(RED).a("���঳���ƪ��u�@�s����A�Э��s��J").reset());
				return 0;
			}
		}
		return 1;
	}
}


