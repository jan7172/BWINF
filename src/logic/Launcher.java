package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Launcher {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("res/Alice_im_Wunderland.txt");
            BufferedReader br = new  BufferedReader(fileReader);
            String text = "";
            while(br.ready())
                text += br.readLine() + " ";
            br.close();

            text = text.replace(",","");
            text = text.replace("'","");
            text = text.replace(".","");
            text = text.replace("«","");
            text = text.replace("»","");
            text = text.replace("-","");
            text = text.replace(":","");
            text = text.replace("!","");
            text = text.replace("?","");
            text = text.replace(";","");
            text = text.replace("*","");
            text = text.replace("_","");
            text = text.trim().replaceAll(" +", " ");

            searchResult(text.split(" "));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchResult(String[] words) {
        for (int stoerung = 0; stoerung <= 5; stoerung++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("res/stoerung" + stoerung + ".txt"));
                String[] content = reader.readLine().split(" ");
                reader.close();

                int contentIndex = 0;
                boolean found = false;
                //System.out.println(Arrays.toString(content));
                for (int i = 0; i < words.length; i++) {
                    if (content[contentIndex].equals("_") || words[i].equalsIgnoreCase(content[contentIndex])) {
                        //System.out.printf("S%d: %d\n", stoerung,contentIndex);
                        contentIndex++;
                        if (contentIndex == content.length)
                            found = true;
                    } else {
                        contentIndex = 0;
                    }

                    if (found) {
                        System.out.printf("Störung (%s) %d gefunden bei: %d bis %d.\n", Arrays.toString(content),stoerung,i-content.length,i);
                        for (int j = i-content.length+1; j <= i; j++) {
                            System.out.print(words[j] + " ");
                        }
                        System.out.println();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}