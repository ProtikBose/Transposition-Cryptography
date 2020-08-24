/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1505044trans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PRANTO
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Integer> result = new ArrayList<>();

    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    private void permute(String str, int l, int r) {
        if (l == r) {

            result.add(Integer.parseInt(str));
        } else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Main jva = new Main();
        String plainText = new String("");

        String finalPlainText = new String("");
        String formation = new String("");
//        plainText = "RMLLGTRIWDATRTROLRBFADIVXEYCERTRVNTTINHSRLTITNIOEXHAIMIDMWDOEUMNAPWTOGRAIMXERLENAOFALGNOGIPIAMAPRSOXTDWONAOOEHHELIEUTSBNEZVNS";
//        String keyword = new String("MORNING");
//        String keyword2 = new String("DIVISION");
//        String keyword3=new String("LENINGRAD");
//        String keyword4=new String("TOMORROW");

        File file = new File("D:\\Academic life\\4-1\\Security Sessional\\Offline1 Solution"
                + "\\1505044Trans\\src\\pkg1505044trans\\transposition-44.txt");

        String newLine = new String("\n");

        String st;
        int numbering = 0;
        String keyword[] = new String[10];
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            plainText = br.readLine();
            br.readLine();
            String t = br.readLine();
            String[] Tempkeyword = t.split(",");

            for (String a : Tempkeyword) {

                keyword[numbering++] = a.replace(" ", "").toUpperCase();
                System.out.println(keyword[numbering - 1]);
            }

            System.out.println(plainText);
            System.out.println(t);
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        File file1 = new File("D:\\Academic life\\4-1\\Security Sessional\\Offline1 Solution"
                + "\\1505044Trans\\src\\pkg1505044trans\\output.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(file1));

        int length = plainText.length();
        int num = 0;

        ArrayList<Integer> array = new ArrayList<Integer>();

        for (int i = 2; i <= length; ++i) {
            if (length % i == 0) {
                array.add(num++, i);
            }
        }
        System.out.println(array);

        for (int i = array.get(0); i < length; i++) {
            int flag = 0;

            if (array.contains(i)) {

                char matrix[][] = new char[i][length / i];
                char transpose[][] = new char[length / i][i];

                int val = 0;

                for (int k = 0; k < i; k++) {

                    for (int j = 0; j < length / i; j++) {
                        matrix[k][j] = plainText.charAt(val++);
                        System.out.print(matrix[k][j]);
                    }
                    System.out.println("");

                }
                //System.out.println("");

                String number = new String("");

                for (int j = 1; j <= i; j++) {
                    number = number + Integer.toString(j);
                }

                try {
                    jva.permute(number, 0, number.length() - 1);
                    System.out.println(result);
                    System.out.println(result.size());

                    for (int a = 0; a < result.size(); a++) {

                        String str = new String("");
                        str = Integer.toString(result.get(a));
                        //System.out.println("ok1");
                        for (int j = 0; j < length / i; j++) {
                            for (int c = 0; c < str.length(); c++) {
                                transpose[j][c]
                                        = matrix[Character.getNumericValue(str.charAt(c)) - 1][j];
                            }
                        }
                        //System.out.println("ok2");
                        String text = new String("");
                        for (int j = 0; j < length / i; j++) {
                            for (int k = 0; k < i; k++) {
                                text += transpose[j][k];
                            }
                        }

                        int checkflag = 0;
                        for (int ks = 0; ks < numbering; ks++) {
                            if (!text.contains(keyword[ks])) {
                                checkflag = 1;
                                //System.out.println("ok");
                                break;
                            }
                        }
                        if (checkflag == 0) {
                            
                            
                            for (int j = 0; j < length / i; j++) {
                                for (int k = 0; k < i; k++) {
                                    System.out.print(transpose[j][k]);
                                }
                                System.out.println("");
                            }
                            System.out.println("");
                            flag = 1;
                            System.out.println(text.toLowerCase());
                            writer.write("Plaintext : " + text.toLowerCase());
                            writer.newLine();
                            writer.newLine();
                            finalPlainText = text;

                            System.out.println(result.get(a));
                            String temp = Integer.toString(result.get(a));
                            formation = temp;
                            int l = temp.length();
                            String finale = new String("");
                            for (int m = 0; m < l; m++) {
                                //int as=l-Character.getNumericValue(temp.charAt(m))+1;
                                finale += temp.charAt(m);
                                if (m < l - 1) {
                                    finale += ",";
                                }
                            }
                            writer.write("Length of key : " + l);
                            writer.newLine();
                            writer.write("The ordering is : " + finale);
                            writer.newLine();
                            break;
                        }

                        //System.out.println("");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Exception Occurs !!");
                }

            }
            if (flag == 1) {
                System.out.println("FOUND !!!");
                break;
            }
        }
        //br.close();
        int len = formation.length();
        int nm = 0;

        char mat[][] = new char[length / len][len];
        for (int i = 0; i < length / len; i++) {
            for (int j = 0; j < len; j++) {
                mat[i][j] = finalPlainText.charAt(nm++);
            }
        }

        String cipher = new String("");
        char ch[][]=new char[len][length/len];

        for(int c=0;c<len;c++){
            for(int i=0;i<length/len;i++){
                ch[Character.getNumericValue(formation.charAt(c))-1][i]
                        =mat[i][c];
                //System.out.println(mat[i][c]);
            }
            //System.out.println("");
        }
        for(int i=0;i<len;i++){
            for(int j=0;j<length/len;j++){
                cipher+=ch[i][j];
            }
        }
        writer.newLine();
        System.out.println(cipher);
        writer.write("Encoded Text: "+cipher);
        writer.newLine();
        int cnt=0;
        for(int i=0;i<length;i++){
            if(cipher.charAt(i)==plainText.charAt(i)){
                cnt++;
            }
        }
        double accuracy=(double)cnt/(double)length*100;
        System.out.println(accuracy);
        writer.newLine();
        writer.write("Accuracy:"+accuracy);
        writer.newLine();
        
        writer.close();
    }

}
