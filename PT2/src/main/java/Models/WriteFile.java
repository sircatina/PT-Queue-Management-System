package Models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    File file=new File("text.txt");
    public WriteFile(){
    }
    public void create() throws IOException{
        this.file.createNewFile();
    }
    public void writeInFile(String name) throws IOException{
        try{
            FileWriter writer=new FileWriter("C:\\PT2023_30423_Sirca_FlorentinaRaluca_Assignment_2\\PT2\\text.txt",true);
            BufferedWriter BuffWr= new BufferedWriter(writer);
            BuffWr.append(name);
            BuffWr.newLine();
            BuffWr.close();
            writer.close();
        }catch (IOException EX){
            EX.printStackTrace();
        }
    }
}
