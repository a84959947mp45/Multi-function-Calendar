
import java.text.*;
import java.util.*;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import static java.lang.System.out;

public class view  extends Function{
		private static List<DataStruct> reData = new ArrayList<DataStruct>();
		public static int controlls=1;
		static final Color[] colorOrder={RED,BLUE,MAGENTA,GREEN,CYAN};
		//�ݥ���
		public static  void ViewAll() {
			AnsiConsole.systemInstall();
			int pageMAX = (DataHere.size()-1)/10;
			int page = 0;
		     Calender_yy_mm C_yy_mm ;


			while(true) {
				System.out.print(ansi().fg(CYAN).a(""));
				out.printf("%d��/%d��%n",page+1,pageMAX+1);
				System.out.print(ansi().fg(CYAN).a("").reset());
				out.println("");
				try {
					for(int i=0;i<10;i++) {
							String nouse = DataHere.get(page*10+i).workName;

							System.out.print(ansi().fg(CYAN).a(""));
							out.printf("��%d�����%n",page*10+i+1);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�u�@�W��:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(DataHere.get(page*10+i).workName);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�}�l�ɶ�:");
							System.out.print(ansi().fg(CYAN).a(""));
							C_yy_mm = new Calender_yy_mm(DataHere.get(page*10+i).startDate.getYear()
									,DataHere.get(page*10+i).startDate.getMonth(),DataHere.get(page*10+i).startDate.getDay());
						    C_yy_mm.printCalender_yy_mm();
							System.out.print(ansi().fg(YELLOW).a(""));
						    out.println("�����ɶ�:");
							System.out.print(ansi().fg(CYAN).a(""));
							C_yy_mm = new Calender_yy_mm(DataHere.get(page*10+i).endDate.getYear()
									,DataHere.get(page*10+i).endDate.getMonth(),DataHere.get(page*10+i).endDate.getDay());
						    C_yy_mm.printCalender_yy_mm();
							System.out.print(ansi().fg(YELLOW).a(""));
						    out.println("�u�@�ʤ���:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(DataHere.get(page*10+i).percent);
							String re= DataHere.get(page*10+i).percent;
							Scanner Ninput = new Scanner(re).useDelimiter("%");
							int sizeX = Ninput.nextInt()/10;
							Ninput.close();

							out.print("|");
							for(int j=0;j<sizeX;j++) {
								out.print("*");
							}
							for(int j=0;j<10-sizeX;j++) {
								out.print(" ");
							}
							out.println("|");
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�������A:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(DataHere.get(page*10+i).status);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�u�@�s��:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(DataHere.get(page*10+i).Numbering);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("����:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(DataHere.get(page*10+i).classX);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�u�@���e:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(DataHere.get(page*10+i).work);
							out.println();
							System.out.print(ansi().fg(CYAN).a("").reset());

					}
				}
				catch(IndexOutOfBoundsException e){

				}
				System.out.print(ansi().fg(YELLOW).a("(1 ").reset());
				System.out.print(ansi().fg(colorOrder[4]).a("�W�@�� ").reset());
				System.out.print(ansi().fg(YELLOW).a("(2 ").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("�U�@�� ").reset());
				System.out.print(ansi().fg(YELLOW).a("(3 ").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("��ܱƧǺ��� ").reset());
				System.out.print(ansi().fg(YELLOW).a("(4 ").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("��^ ").reset());
				System.out.print(ansi().fg(YELLOW).a("(5 ").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("�h�X�{��").reset());
				String select = input.nextLine();
				if(select.equals("1")) {
					if(page==0) {
						System.out.println(ansi().fg(YELLOW).a("�w�g�O�̤W�@���F").reset());
						continue;
					}
					page--;
				}else if(select.equals("2")) {
					if(page==pageMAX) {
							System.out.println(ansi().fg(YELLOW).a("�w�g�O�̫�@���F").reset());
							continue;
						}
						page++;
				}else if(select.equals("3")) {
						view.SortWhat();
						page = 0;
						continue;
				}else if(select.equals("4")) {

					return;
				}else if(select.equals("5")) {
//				    data.OutputFile("XXX");
					System.exit(1);
				}else {
					 continue;
				}
			}
		}
		//�ݤ���
		public static void ViewToday(String today,int show) {//1��ܸ��� 2��ܥ���
			reData.clear();
			Scanner re = new Scanner(today).useDelimiter("/");
			int yy,mm,dd;
			yy = re.nextInt();
			mm = re.nextInt();
			dd = re.nextInt();
			for(int i =0 ; i<DataHere.size();i++) {
				String workdate=DataHere.get(i).startDate.toString();
				String workend=DataHere.get(i).endDate.toString();
				Scanner rs = new Scanner(workdate).useDelimiter(" ");
				Scanner rend = new Scanner(workend).useDelimiter(" ");
				int y,m,d,yyy,mmm,ddd;
				String ms = rs.next();
				m=DateX.getMonth(ms);
				d = rs.nextInt();
				y = rs.nextInt();
				String mmms = rend.next();
				mmm=DateX.getMonth(mmms);
				ddd=rend.nextInt();
				yyy=rend.nextInt();
				if((yy>y)||(yy==y&&mm>m)||(yy==y&&mm==m&&dd>=d)) {
					if((yyy>yy)||(yyy==yy&&mmm>mm)||(yyy==yy&&mmm==mm&&ddd>=dd)) {
						reData.add(DataHere.get(i));
					}
				}
			}
			
			if(show==1) {
				out.println("***********�����{**********");
				try {
					for(int i=0;i<reData.size();i++) {
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�u�@�W��:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(reData.get(i).workName);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�������A:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(reData.get(i).status);
							System.out.print(ansi().fg(YELLOW).a("").reset());
							if(i!=reData.size()-1)out.println();
					}
				}
				catch(IndexOutOfBoundsException e){
				}
				out.println("**************************");
			}
			else if(show==2) {
				int pageMAX = (reData.size()-1)/10;
				int page = 0;
				Scanner XINPUT ;
			    Calender_yy_mm C_yy_mm ;


				while(true) {
					System.out.print(ansi().fg(CYAN).a(""));
					out.printf("%d��/%d��%n",page+1,pageMAX+1);
					System.out.print(ansi().fg(CYAN).a("").reset());
					out.println("");
					try {
						for(int i=0;i<10;i++) {
								String nouse=reData.get(page*10+i).workName;


								System.out.print(ansi().fg(CYAN).a(""));
								out.printf("��%d�����%n",page*10+i+1);
								System.out.print(ansi().fg(YELLOW).a(""));
								out.println("�u�@�W��:");
								System.out.print(ansi().fg(CYAN).a(""));
								out.println(reData.get(page*10+i).workName);
								System.out.print(ansi().fg(YELLOW).a(""));
								out.println("�}�l�ɶ�:");
								System.out.print(ansi().fg(CYAN).a(""));
								C_yy_mm = new Calender_yy_mm(reData.get(page*10+i).startDate.getYear()
										,reData.get(page*10+i).startDate.getMonth(),reData.get(page*10+i).startDate.getDay());
							    C_yy_mm.printCalender_yy_mm();
								System.out.print(ansi().fg(YELLOW).a(""));
							    out.println("�����ɶ�:");
								System.out.print(ansi().fg(CYAN).a(""));
								C_yy_mm = new Calender_yy_mm(reData.get(page*10+i).endDate.getYear()
										,reData.get(page*10+i).endDate.getMonth(),reData.get(page*10+i).endDate.getDay());
							    C_yy_mm.printCalender_yy_mm();
								System.out.print(ansi().fg(YELLOW).a(""));
							    out.println("�u�@�ʤ���:");
								System.out.print(ansi().fg(CYAN).a(""));
								out.println(reData.get(page*10+i).percent);
								String re1= reData.get(page*10+i).percent;
								XINPUT = new Scanner(re1).useDelimiter("%");
								int sizeX = XINPUT.nextInt()/10;
								XINPUT.close();
								out.print("|");
								for(int j=0;j<sizeX;j++) {
									out.print("*");
								}
								for(int j=0;j<10-sizeX;j++) {
									out.print(" ");
								}
								out.println("|");
								System.out.print(ansi().fg(YELLOW).a(""));
								out.println("�������A:");
								System.out.print(ansi().fg(CYAN).a(""));
								out.println(reData.get(page*10+i).status);
								System.out.print(ansi().fg(YELLOW).a(""));
								out.println("�u�@�s��:");
								System.out.print(ansi().fg(CYAN).a(""));
								out.println(reData.get(page*10+i).Numbering);
								System.out.print(ansi().fg(YELLOW).a(""));
								out.println("����:");
								System.out.print(ansi().fg(CYAN).a(""));
								out.println(reData.get(page*10+i).classX);
								System.out.print(ansi().fg(YELLOW).a(""));
								out.println("�u�@���e:");
								System.out.print(ansi().fg(CYAN).a(""));
								out.println(reData.get(page*10+i).work);
								System.out.print(ansi().fg(CYAN).a("").reset());
								out.println();

						}
					}
					catch(IndexOutOfBoundsException e){

					}
					System.out.print(ansi().fg(YELLOW).a("(1 ").reset());
					System.out.print(ansi().fg(colorOrder[4]).a("�W�@�� ").reset());
					System.out.print(ansi().fg(YELLOW).a("(2 ").reset());
					System.out.print(ansi().fg(colorOrder[3]).a("�U�@�� ").reset());
					System.out.print(ansi().fg(YELLOW).a("(3 ").reset());
					System.out.print(ansi().fg(colorOrder[2]).a("��ܱƧǺ��� ").reset());
					System.out.print(ansi().fg(YELLOW).a("(4 ").reset());
					System.out.print(ansi().fg(colorOrder[1]).a("��^ ").reset());
					System.out.print(ansi().fg(YELLOW).a("(5 ").reset());
					System.out.println(ansi().fg(colorOrder[0]).a("�h�X�{��").reset());
					String select = input.nextLine();
					out.println(select);
					if(select.equals("1")) {
						if(page==0) {
							System.out.println(ansi().fg(YELLOW).a("�w�g�O�̤W�@���F").reset());
							continue;
						}
						page--;
					}else if(select.equals("2")) {
						if(page==pageMAX) {
								System.out.println(ansi().fg(YELLOW).a("�w�g�O�̫�@���F").reset());
								continue;
							}
							page++;
					}else if(select.equals("3")) {
							view.SortWhat("startDate");
							page = 0;
							continue;
					}else if(select.equals("4")) {

						return;
					}else if(select.equals("5")) {
//					    data.outputFile();
						System.exit(1);
					}else {
						 continue;
					}

				}
			}
		}
		public static void ViewClass(String classSelect,String SelectName) {
			AnsiConsole.systemInstall();
			reData.clear();
			if(classSelect.equals("workName")) {
				for(int i =0 ; i<DataHere.size();i++) {
					if(DataHere.get(i).workName.equals(SelectName)) {
						reData.add(DataHere.get(i));
					}
				}
			}else if(classSelect.equals("startDate")) {
				for(int i =0 ; i<DataHere.size();i++) {
					if(DataHere.get(i).startDate.toString().equals(SelectName)) {
						reData.add(DataHere.get(i));
					}
				}
			}else if(classSelect.equals("endDate")) {
				for(int i =0 ; i<DataHere.size();i++) {
					if(DataHere.get(i).endDate.toString().equals(SelectName)) {
						reData.add(DataHere.get(i));
					}
				}
			}else if(classSelect.equals("percent")) {
				for(int i =0 ; i<DataHere.size();i++) {
					if(DataHere.get(i).percent.equals(SelectName)) {
						reData.add(DataHere.get(i));
					}
				}
			}else if(classSelect.equals("status")) {
				for(int i =0 ; i<DataHere.size();i++) {
					if(DataHere.get(i).status.equals(SelectName)) {
						reData.add(DataHere.get(i));
					}
				}
			}else if(classSelect.equals("Numbering")) {
				for(int i =0 ; i<DataHere.size();i++) {
					if(DataHere.get(i).Numbering.equals(SelectName)) {
						reData.add(DataHere.get(i));
					}
				}
			}else if(classSelect.equals("classX")) {
				for(int i =0 ; i<DataHere.size();i++) {
					if(DataHere.get(i).classX.equals(SelectName)) {
						reData.add(DataHere.get(i));
					}
				}
			}else if(classSelect.equals("work")) {
				for(int i =0 ; i<DataHere.size();i++) {
					if(DataHere.get(i).work.equals(SelectName)) {
						reData.add(DataHere.get(i));
					}
				}
			}else {
				System.out.println(ansi().fg(RED).a("�d�L���").reset());
				return;
			}


			int pageMAX = (reData.size()-1)/10;
			int page = 0;
			Scanner XINPUT ;
		    Calender_yy_mm C_yy_mm ;


			while(true) {
				System.out.print(ansi().fg(CYAN).a(""));
				out.printf("%d��/%d��%n",page+1,pageMAX+1);
				System.out.print(ansi().fg(CYAN).a("").reset());
				out.println("");
				try {
					for(int i=0;i<10;i++) {
							String nouse=reData.get(page*10+i).workName;


							System.out.print(ansi().fg(CYAN).a(""));
							out.printf("��%d�����%n",page*10+i+1);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�u�@�W��:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(reData.get(page*10+i).workName);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�}�l�ɶ�:");
							System.out.print(ansi().fg(CYAN).a(""));
							C_yy_mm = new Calender_yy_mm(reData.get(page*10+i).startDate.getYear()
									,reData.get(page*10+i).startDate.getMonth(),reData.get(page*10+i).startDate.getDay());
						    C_yy_mm.printCalender_yy_mm();
							System.out.print(ansi().fg(YELLOW).a(""));
						    out.println("�����ɶ�:");
							System.out.print(ansi().fg(CYAN).a(""));
							C_yy_mm = new Calender_yy_mm(reData.get(page*10+i).endDate.getYear()
									,reData.get(page*10+i).endDate.getMonth(),reData.get(page*10+i).endDate.getDay());
						    C_yy_mm.printCalender_yy_mm();
							System.out.print(ansi().fg(YELLOW).a(""));
						    out.println("�u�@�ʤ���:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(reData.get(page*10+i).percent);
							String re= reData.get(page*10+i).percent;
							XINPUT = new Scanner(re).useDelimiter("%");
							int sizeX = XINPUT.nextInt()/10;
							XINPUT.close();
							out.print("|");
							for(int j=0;j<sizeX;j++) {
								out.print("*");
							}
							for(int j=0;j<10-sizeX;j++) {
								out.print(" ");
							}
							out.println("|");
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�������A:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(reData.get(page*10+i).status);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�u�@�s��:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(reData.get(page*10+i).Numbering);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("����:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(reData.get(page*10+i).classX);
							System.out.print(ansi().fg(YELLOW).a(""));
							out.println("�u�@���e:");
							System.out.print(ansi().fg(CYAN).a(""));
							out.println(reData.get(page*10+i).work);
							System.out.print(ansi().fg(CYAN).a("").reset());
							out.println();

					}
				}
				catch(IndexOutOfBoundsException e){

				}
				System.out.print(ansi().fg(YELLOW).a("(1 ").reset());
				System.out.print(ansi().fg(colorOrder[4]).a("�W�@�� ").reset());
				System.out.print(ansi().fg(YELLOW).a("(2 ").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("�U�@�� ").reset());
				System.out.print(ansi().fg(YELLOW).a("(3 ").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("��ܱƧǺ��� ").reset());
				System.out.print(ansi().fg(YELLOW).a("(4 ").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("��^ ").reset());
				System.out.print(ansi().fg(YELLOW).a("(5 ").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("�h�X�{��").reset());
				String select = input.nextLine();
				out.println(select);
				if(select.equals("1")) {
					if(page==0) {
						System.out.println(ansi().fg(YELLOW).a("�w�g�O�̤W�@���F").reset());
						continue;
					}
					page--;
				}else if(select.equals("2")) {
					if(page==pageMAX) {
							System.out.println(ansi().fg(YELLOW).a("�w�g�O�̫�@���F").reset());
							continue;
						}
						page++;
				}else if(select.equals("3")) {
						view.SortWhat(classSelect);
						page = 0;
						continue;
				}else if(select.equals("4")) {

					return;
				}else if(select.equals("5")) {
//				    data.outputFile();
					System.exit(1);
				}else {
					 continue;
				}

			}
		}
		//�ݿ��
		void search(String select) {

		}
		public static void SortWhat() {
			AnsiConsole.systemInstall();
				System.out.println(ansi().fg(YELLOW).a("�p�G�A�Q�n�̺����ƧǡA�п�ܺ���").reset());
				System.out.print(ansi().fg(YELLOW).a("(1 ").reset());
				System.out.print(ansi().fg(colorOrder[0]).a("�u�@�W�l/").reset());
				System.out.print(ansi().fg(YELLOW).a("(2 ").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("�}�l�ɶ�/").reset());
				System.out.print(ansi().fg(YELLOW).a("(3 ").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("�����ɶ�/").reset());
				System.out.print(ansi().fg(YELLOW).a("(4 ").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("�����ʤ���/").reset());
				System.out.print(ansi().fg(YELLOW).a("(5 ").reset());
				System.out.println(ansi().fg(colorOrder[4]).a("�u�@���A/").reset());
				System.out.print(ansi().fg(YELLOW).a("(6 ").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("�u�@�s��/").reset());
				System.out.print(ansi().fg(YELLOW).a("(7 ").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("�u�@����/").reset());
				System.out.print(ansi().fg(YELLOW).a("(8 ").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("�u�@���e/").reset());
				System.out.print(ansi().fg(YELLOW).a("(9 ").reset());
				System.out.print(ansi().fg(colorOrder[0]).a("��^���/").reset());
				System.out.print(ansi().fg(YELLOW).a("(10").reset());
				System.out.println(ansi().fg(colorOrder[1]).a("�h�X�{��").reset());
			String select = input.next();
			while(true) {
				if(select.equals("1")) {
					Collections.sort(DataHere, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String name1=  r1.workName;
			                String name2=  r2.workName;
			                Collator instance = Collator.getInstance(Locale.TAIWAN);
			                return controlls*instance.compare(name1, name2);
					    }
					});
					return;
				}else if(select.equals("2")) {
					Collections.sort(DataHere, new Comparator<DataStruct>() {

						public int compare(DataStruct r1, DataStruct r2) {
							DateX time1=  r1.startDate;
			                DateX time2=  r2.startDate;

			                return controlls*time1.precedes(time2);
						}
					});

					return;

				}else if(select.equals("3")) {
					Collections.sort(DataHere, new Comparator<DataStruct>(){
						public int compare(DataStruct r1, DataStruct r2) {
							DateX time1=  r1.endDate;
			                DateX time2=  r2.endDate;
			                return controlls*time1.precedes(time2);
					    }
					} );
					return;
				}else if(select.equals("4")) {
					Collections.sort(DataHere, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String percent1=  r1.percent;
			                String percent2=  r2.percent;
			                return controlls*percent1.compareTo(percent2);
					    }
					});
					return;
				}else if(select.equals("5")) {
					Collections.sort(DataHere, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String name1=  r1.status;
			                String name2=  r2.status;
			                Collator instance = Collator.getInstance(Locale.TAIWAN);
			                return controlls*instance.compare(name1, name2);
					    }
					});
					return;
				}else if(select.equals("6")) {
					Collections.sort(DataHere, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String percent1=  r1.Numbering;
			                String percent2=  r2.Numbering;
			                return controlls*percent1.compareTo(percent2);
					    }
					});
					return;
				}else if(select.equals("7")) {
						Collections.sort(DataHere, new Comparator<DataStruct>(){

							public int compare(DataStruct r1, DataStruct r2) {
								String percent1=  r1.Numbering;
				                String percent2=  r2.Numbering;
				                return controlls*percent1.compareTo(percent2);
						    }
					});
					return;
				}else if(select.equals("8")) {
					Collections.sort(DataHere, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String name1=  r1.work;
			                String name2=  r2.work;
			                Collator instance = Collator.getInstance(Locale.TAIWAN);
			                return controlls*instance.compare(name1, name2);
					    }
					});
					return;
				}else if(select.equals("9")) {
					return;
				}else if(select.equals("10")) {
//				    data.outputFile();
					System.exit(1);
				}else {
					 continue;
				}

			}
		}
		//�����Ƨ�
		public static void SortWhat(String X) {
			AnsiConsole.systemInstall();

			Scanner XINPUT  =  new Scanner(System.in);
				System.out.println(ansi().fg(YELLOW).a("�п�ܱƧǺ���").reset());
				System.out.print(ansi().fg(YELLOW).a("(1 ").reset());
				System.out.print(ansi().fg(colorOrder[0]).a("�u�@�W�l/").reset());
				System.out.print(ansi().fg(YELLOW).a("(2 ").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("�}�l�ɶ�/").reset());
				System.out.print(ansi().fg(YELLOW).a("(3 ").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("�����ɶ�/").reset());
				System.out.print(ansi().fg(YELLOW).a("(4 ").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("�����ʤ���/").reset());
				System.out.println(ansi().fg(YELLOW).a("(5 ").reset());
				System.out.print(ansi().fg(colorOrder[4]).a("�u�@���A/").reset());
				System.out.print(ansi().fg(YELLOW).a("(6 ").reset());
				System.out.print(ansi().fg(colorOrder[3]).a("�u�@�s��/").reset());
				System.out.print(ansi().fg(YELLOW).a("(7 ").reset());
				System.out.print(ansi().fg(colorOrder[2]).a("�u�@���e/").reset());
				System.out.print(ansi().fg(YELLOW).a("(8 ").reset());
				System.out.print(ansi().fg(colorOrder[1]).a("��^���/").reset());
				System.out.print(ansi().fg(YELLOW).a("(9 ").reset());
				System.out.println(ansi().fg(colorOrder[0]).a("�h�X�{��").reset());
			String select = XINPUT.next();
			while(true) {
				if(select.equals("1")) {
					Collections.sort(reData, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String name1=  r1.workName;
			                String name2=  r2.workName;
			                Collator instance = Collator.getInstance(Locale.TAIWAN);
			                return controlls*instance.compare(name1, name2);
					    }
					});
					return;
				}else if(select.equals("2")) {
					Collections.sort(reData, new Comparator<DataStruct>() {

						public int compare(DataStruct r1, DataStruct r2) {
							DateX time1=  r1.startDate;
			                DateX time2=  r2.startDate;

			                return controlls*time1.precedes(time2);
						}
					});

					return;

				}else if(select.equals("3")) {
					Collections.sort(reData, new Comparator<DataStruct>(){
						public int compare(DataStruct r1, DataStruct r2) {
							DateX time1=  r1.endDate;
			                DateX time2=  r2.endDate;
			                return controlls*time1.precedes(time2);
					    }
					} );
					return;
				}else if(select.equals("4")) {
					Collections.sort(reData, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String percent1=  r1.percent;
			                String percent2=  r2.percent;
			                return controlls*percent1.compareTo(percent2);
					    }
					});
					return;
				}else if(select.equals("5")) {
					Collections.sort(reData, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String name1=  r1.status;
			                String name2=  r2.status;
			                Collator instance = Collator.getInstance(Locale.TAIWAN);
			                return controlls*instance.compare(name1, name2);
					    }
					});
					return;
				}else if(select.equals("6")) {
					Collections.sort(reData, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String percent1=  r1.percent;
			                String percent2=  r2.percent;
			                return controlls*percent1.compareTo(percent2);
					    }
					});
					return;
				}else if(select.equals("7")) {
					Collections.sort(reData, new Comparator<DataStruct>(){

						public int compare(DataStruct r1, DataStruct r2) {
							String name1=  r1.work;
			                String name2=  r2.work;
			                Collator instance = Collator.getInstance(Locale.TAIWAN);
			                return controlls*instance.compare(name1, name2);
					    }
					});
					return;
				}else if(select.equals("8")) {
					return;
				}else if(select.equals("9")) {
//					data.outputFile("XXX");
					System.exit(1);
				}else {
					 continue;
				}
				XINPUT.close();
			}
		}




}

