package perissonslist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ApkToolDecom {

    public void ApkToolDecom(){
        //empty constructor
    }


    //method to run apktool
    public void apkToolDecom() {

        String data = null;

        try {

            Process pros = Runtime.getRuntime().exec("apktool d /home/j0nee/Acadamics/Trojan/malware_collection/code/method1/apks/krep.itmtd.ywtjexf-1.apk -o /home/j0nee/Acadamics/Trojan/malware_collection/code/method1/apks/krep.itmtd.ywtjexf-1");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(pros.getInputStream()));

            while ((data = stdInput.readLine()) != null) {
                System.out.println(data);
            }
        }
        catch (IOException e) {
            System.out.println("Exception....");
        }
    }

}
