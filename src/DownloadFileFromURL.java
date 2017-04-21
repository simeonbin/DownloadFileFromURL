
import org.apache.commons.io.FilenameUtils;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simeon
 */
public class DownloadFileFromURL    {
        static URL url, url2, url3;
        static     URLConnection con, con2, con3;
         static   DataInputStream dis; 
         static   FileOutputStream fos; 
         static    byte[] fileData;  
         static ArrayList<String> urlList = new ArrayList<>(25);
            
       public static void main(String[] args) throws Exception {
           
           urlList.add("http://downloads.solarwinds.com/solarwinds/Release/Management/pg11.TXT");
           urlList.add("http://downloads.solarwinds.com/solarwinds/Release/Management/lorem.TXT");
           urlList.add("http://downloads.solarwinds.com/solarwinds/Release/Management/pg1661.TXT");
           urlList.add("http://downloads.solarwinds.com/solarwinds/Release/Management/cacerts");     
           urlList.add("http://downloads.solarwinds.com/solarwinds/Release/Management/eula.rtf"); 
           urlList.add("http://downloads.solarwinds.com/solarwinds/Release/Management/Story.JPG");
           urlList.add("http://downloads.solarwinds.com/solarwinds/Release/Management/solarwinds.png");
           
           
           for (int iUrl=0; iUrl < urlList.size(); iUrl++) {
               
               try {
                   String urlString = urlList.get(iUrl);
                    //All File Locations go here
                     url = new URL(urlString);
                     
                 } catch (MalformedURLException ex) {
                     Logger.getLogger(DownloadFileFromURL.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 try {
                     // open all the url connections here.
                     con = url.openConnection();                   
                     
                 } catch (IOException ex) {
                     Logger.getLogger(DownloadFileFromURL.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                dis = new DataInputStream(con.getInputStream());
                fileData = new byte[con.getContentLength()]; 
                
                for (int q = 0; q < fileData.length; q++) { 
                    fileData[q] = dis.readByte();
                }
                dis.close(); // close the data input stream               
                
                String fName = FilenameUtils.getName(url.getPath());
                
                fos = new FileOutputStream(new File(fName)); //FILE Save Location goes here
                fos.write(fileData);  // write out the file we want to save.
                fos.close(); // close the output stream writer       
                
                              
               } // end of for
    
       }
    
}
