

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date; 
import java.text.SimpleDateFormat;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Calender_yy_mm {


        private int yy;
	private int mm,dd;

        Calender_yy_mm(int year,int mon,int day){
	AnsiConsole.systemInstall();
        	if(year==0&&mon==0&&day==0) {
        		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        		Date date = new Date();
        		String strDate = sdFormat.format(date);
        		
        	this.yy= Integer.valueOf(strDate.substring(0,4));
            	this.mm=Integer.valueOf(strDate.substring(5,7));
            	this.dd=Integer.valueOf(strDate.substring(8,10));
        		
        	}else {
        	
        	
        	this.yy=year;
        	this.mm=mon;
        	this.dd=day;
        	}
        }//end_建構子
	void set_day(int year,int mon,int day){
      	 	if(year==0&&mon==0&&day==0) {
      		  		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
      		  		Date date = new Date();
       		 		String strDate = sdFormat.format(date);
        		
        			this.yy= Integer.valueOf(strDate.substring(0,4));
        	    		this.mm=Integer.valueOf(strDate.substring(5,7));
        	    		this.dd=Integer.valueOf(strDate.substring(8,10));
				return;
		}
		else {
			this.yy=year;
			this.mm=mon;
			this.dd=day;
			return;

		}
	}
	void current_month(){
		if(this.mm==1){
			this.yy-=1;
			this.mm=12;
			this.dd=1;
			return;
		}else{
			this.mm-=1;
			this.dd=1;
			return;
		}
	}
	void next_month(){
		if(this.mm==12){
			this.yy+=1;
			this.mm=1;
			this.dd=1;
			return;
		}else{
			this.mm+=1;
			this.dd=1;
			return;
		}
	}
	private Color color1=GREEN;
	private Color color2=YELLOW;
	private Color color3=BLUE;
	private Color color4=RED;
	private Color color5=CYAN;


        void printCalender_yy_mm(){
//ansi().fg(WHITE).a("日"+" 一"+" 二"+" 三"+" 四"+" 五"+" 六").reset()
	//color1
        System.out.print( ansi().fg(this.color1).a(Integer.toString(this.yy)));
  	System.out.print(ansi().fg(this.color1 ).a(" 年  "));
 	System.out.print(ansi().fg(this.color1).a(Integer.toString(this.mm)));
 	System.out.print(ansi().fg(this.color1).a(" 月  "));
	System.out.print(ansi().fg(this.color1).a(Integer.toString(this.dd)));
	System.out.println(ansi().fg(this.color1).a(" 日"));

	//color2
        System.out.print( ansi().fg(this.color2).a("日"));
	System.out.print(ansi().fg(this.color2).a(" 一"));
	System.out.print(ansi().fg(this.color2).a(" 二"));
	System.out.print(ansi().fg(this.color2).a(" 三"));
	System.out.print(ansi().fg(this.color2).a(" 四"));
	System.out.print(ansi().fg(this.color2).a(" 五"));
	System.out.println(ansi().fg(this.color2).a(" 六").reset() );

	//color3
        System.out.println(ansi().fg(this.color3).a("---------------------").reset());

        Calendar cal = new GregorianCalendar(this.yy,this.mm-1,1);
        int day = cal. getActualMaximum(Calendar.DATE); //取得當月最大天數
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);//星期幾?
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//當月的第一天

        for(int k = 1 ; k < day_of_week ; k++){
          System.out.print("   ");
        } //先把空格空出來


        for(day_of_month = 1 ; day_of_month <= day ; day_of_month++){//此FOR迴圈是要將日期印出來
        	if(day_of_month==this.dd) {
        		if(day_of_month<10){  //若那一天是個位數的印法
		//color4
                    System.out.print(" "+(ansi().fg(this.color4).a(Integer.toString(this.dd)).reset()+" "));
                    }else{  //十位數的印法
                    System.out.print(ansi().fg(this.color4).a(Integer.toString(this.dd)).reset()+" ");
                    }
        	}else {
        		if(day_of_month<10){  //若那一天是個位數的印法
        			System.out.print(" ");
			//color5
				System.out.print(ansi().fg(this.color5).a(Integer.toString(day_of_month)).reset());
				System.out.print(" ");
        		}else{  //十位數的印法
				System.out.print(ansi().fg(this.color5).a(Integer.toString(day_of_month)).reset());
				System.out.print(" ");
        		} 
        	}//整個IF迴圈是為了要設定格式

           if (cal.get(Calendar.DAY_OF_WEEK) == 7&&day_of_month!=day) {  //每到星期六則跳行
                      System.out.println();
                  }
           cal.add(Calendar.DAY_OF_MONTH, 1);
         }
        System.out.println("");
      } //end_printCalender_yy_mm()
      

}