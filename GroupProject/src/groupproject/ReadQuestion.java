/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//commas inside  the question description must be handled, no changing the delimiter


/**
 *
 * @author Alexis
 */
public class ReadQuestion {
    public ArrayList<QuestionInfo> readQuestion(String filePath) throws FileNotFoundException, IOException {
        ArrayList<QuestionInfo> questionList = new ArrayList();

        //read from text files
        String pathname = filePath;
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        String[] parts;
        line = br.readLine();

        while (line != null) {
            parts = line.split("，");
            QuestionInfo question=null;
            if(parts[0].equals("MC")||parts[0].equals("MA")){
                question=new QuestionInfo(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],parts[6],parts[7],parts[8],parts[9],parts[10]);
            }
            else if(parts[0].equals("TF")||parts[0].equals("FIB")){
                question=new QuestionInfo(parts[0],parts[1],parts[2],"","","","","","","","");
            }          
            line = br.readLine();
            questionList.add(question);

        }
        return questionList;
    }
}
