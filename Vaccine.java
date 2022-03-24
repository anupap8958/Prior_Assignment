
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Vaccine {

    public static void main(String[] args) {
        Vaccine v = new Vaccine();
        v.run();
    }

    void run() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("| โปรแกรมเข้ารับบริการฉีดวัคซีน ในวันที่ 1 มิถุนายน พ.ศ.2564 – 31 สิงหาคม พ.ศ.2564 |");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("*** เงื่อนไขการเข้ารับบริการฉีดวัคซีน ***\n1. ผู้สูงอายุ 65 ปีขึ้นไป (ชาย,หญิง)\n2. เด็กที่มีอายุระหว่าง 6 เดือน ถึง 2 ปี (ชาย,หญิง)");
        System.out.print("*** รูปแบบตัวอย่างข้อมูลการป้อนข้อมูล ***\nชาย เกิดวันเสาร์ที่ 10 มีนาคม พ.ศ.2499\nหญิง เกิดวันศุกร์ที่ 1 ธันวาคม พ.ศ.2498\n: ");
        String dataInputUser = keyboard.nextLine();

        String output = receiveVaccinationService(convertStringToDate(dataInputUser));
        
        System.out.println(output);
    }

    String convertStringToMonth(String m) {
        String index = "";
        String months[] = {
            "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
            "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
            "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
        for (int i = 0; i < months.length; i++) {
            if (months[i].equals(m)) {
                index = (i + 1) + "";
            }
        }
        return index;
    }

    String convertIntToMonth(int m) {
        String months[] = {
            "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
            "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
            "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
        return months[m];
    }

    Calendar convertStringToDate(String target) {
        try {
            String[] arrData = target.split(" ");//0:ชาย 1:เกิดวันเสาร์ที่ 2:10 3:มีนาคม 4:พ.ศ.2499
            int date = Integer.parseInt(arrData[2]);
            if (date < 1 || date > 31) {
                System.err.println("ท่านกรอกวันที่ไม่ถูกต้อง กรุณาทำรายการใหม่อีกครั้ง");
                System.exit(0);
            }
            String gender = arrData[0];
            String stringDateOfBirthUser = arrData[4].replaceAll("[^0-9]+", "") + "-" + convertStringToMonth(arrData[3]) + "-" + date;
            Date dateUser = new SimpleDateFormat("yyyy-MM-dd", new Locale("th", "TH")).parse(stringDateOfBirthUser);
            Calendar calenderUser = Calendar.getInstance();
            calenderUser.setTime(dateUser);
            return calenderUser;
        } catch (ParseException ex) {
            System.err.println("ท่านกรอกข้อมูลผิด กรุณาทำรายการใหม่อีกครั้ง");
        }
        return null;
    }

    String receiveVaccinationService(Calendar calenderUser) {
        // ผู้รับบริการสามารถเข้ารับบริการฉีดวัคซีน ในวันที่ 1 มิถุนายน พ.ศ.2564 – 31 สิงหาคม พ.ศ.2564
        Calendar calenderReceiveVaccine_Start = Calendar.getInstance();
        calenderReceiveVaccine_Start.set(2021, 5, 1); //1 มิถุนายน พ.ศ.2564 start service
        Calendar calenderReceiveVaccine_End = Calendar.getInstance();
        calenderReceiveVaccine_End.set(2021, 7, 31); //31 สิงหาคม พ.ศ.2564 end service

        String eligible_Flag = null;
        String start_Date = null;
        String end_Date = null;

        int date_Start = calenderReceiveVaccine_Start.get(Calendar.DATE);
        int month_Start_int = calenderReceiveVaccine_Start.get(Calendar.MONTH);
        String month_Start_str = convertIntToMonth(month_Start_int);
        int year_Start = calenderReceiveVaccine_Start.get(Calendar.YEAR) + 543;
        int dayOfYear_Start = calenderReceiveVaccine_Start.get(Calendar.DAY_OF_YEAR);

        int date_End = calenderReceiveVaccine_End.get(Calendar.DATE);
        int month_End_int = calenderReceiveVaccine_End.get(Calendar.MONTH);
        String month_End_str = convertIntToMonth(month_End_int);
        int dayOfYear_End = calenderReceiveVaccine_End.get(Calendar.DAY_OF_YEAR);
        int year_End = calenderReceiveVaccine_End.get(Calendar.YEAR) + 543;

        int date_User = calenderUser.get(Calendar.DATE);
        int month_User_int = calenderUser.get(Calendar.MONTH);
        String month_User_str = convertIntToMonth(month_User_int);
        int dayOfYear_User = calenderUser.get(Calendar.DAY_OF_YEAR);
        int year_User = calenderUser.get(Calendar.YEAR) + 543;

        int year = year_End - year_User;
        if (year >= 65) {
            eligible_Flag = "Y";if (dayOfYear_User >= dayOfYear_Start && dayOfYear_User <= dayOfYear_End) {
                start_Date = date_User + " " + month_User_str + " พ.ศ. " + (year_Start);
                end_Date = date_End + " " + month_End_str + " พ.ศ. " + (year_End);
            } else {
                start_Date = date_Start + " " + month_Start_str + " พ.ศ. " + (year_Start);
                end_Date = date_End + " " + month_End_str + " พ.ศ. " + (year_End);
            }
        } else if (year >= 0 && year <= 2){
            eligible_Flag = "Y";
            if(year == 2 && dayOfYear_User > dayOfYear_End) {
                eligible_Flag = "N";
            } else if (year == 2 && dayOfYear_User >= dayOfYear_Start && dayOfYear_User <= dayOfYear_End) {
                start_Date = date_Start + " " + month_Start_str + " พ.ศ. " + (year_Start);
                end_Date = date_User + " " + month_User_str + " พ.ศ. " + (year_End);
            } else if ((year == 0 && dayOfYear_User < dayOfYear_Start) || (year == 1 && month_User_int == 11)) {
                month_User_str = year == 0 ? convertIntToMonth(month_User_int + 6) : convertIntToMonth(5);
                start_Date = date_User + " " + month_User_str + " พ.ศ. " + (year_Start);
                end_Date = date_End + " " + month_End_str + " พ.ศ. " + (year_End);
            } else {
                start_Date = date_Start + " " + month_Start_str + " พ.ศ. " + (year_Start);
                end_Date = date_End + " " + month_End_str + " พ.ศ. " + (year_End);
            }
        } else {
            eligible_Flag = "N";
        }

        if (eligible_Flag.equals("N")) {
            return "ไม่สามารถเข้ารับบริการได้ เนื่องจากอายุจะครบ 65 ปี วันที่ " + date_User + " " + month_User_str + " พ.ศ." + (year_User + 65);
        }

        return "เข้ารับบริการได้ตั้งแต่วันที่ " + start_Date + " - " + end_Date;
    }
}
