/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findtextinlog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lion
 */
public class Test {
    
    private ArrayList<LineItem> itemArrayList;
    
    public String textURL;
    
    public Test(String url, String regex){
        textURL = url;
        try {
            itemArrayList = new ArrayList();
            
            //这里比较重要，通过这样的转换消除了中文乱码问题
            InputStreamReader myInputStreamReader = new InputStreamReader(new FileInputStream(url),"gb2312");
       
            BufferedReader myBufferedReader = new BufferedReader(myInputStreamReader);
            
            String valueString = null;

            int countLine = 0;
            while((valueString = myBufferedReader.readLine())!=null)
            {
                if(StringUtil.findPattern(valueString, regex))
                    countLine++;
            }
                myBufferedReader.close();
               myInputStreamReader.close();
               System.out.println(countLine);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList getItemArrayList(){
        return this.itemArrayList;
    }
    
    public static void main(String [] args)
    {
        double time1 = System.currentTimeMillis();
        new Test("C:\\test.log","[2]\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d");
        double time2 = System.currentTimeMillis();
        
        System.out.println("The program running: "+(time2-time1)/1000);
    }
    
    
    
}
