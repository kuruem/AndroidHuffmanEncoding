package com.example.emre.hcoder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.*;
import java.lang.Character;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Button button;
    TextView output;

    Map<Character, String> encodedData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        output = (TextView)findViewById(R.id.textView);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = "";
                findCodes(Huffman(findFrequency()), code);
                writeEncodedText();

            }

        });

    }







    //************
    //Mesajdaki harflerin frekanslarını bulup, bunları tuttuğu vector'u return ediyor
    //************
    Vector<Characters> findFrequency(){

        Map<Character, Integer> harfler = new HashMap<Character, Integer>();
        int i = 0;
        String mesaj = input.getText().toString();


        while(i < mesaj.length()) {

            if(harfler.get(mesaj.charAt(i)) != null){
                Integer freq = Integer.parseInt(harfler.get(mesaj.charAt(i)).toString());
                harfler.put(mesaj.charAt(i), freq + 1);
            }

            else
                harfler.put(mesaj.charAt(i), 1);

            i++;
        }


        Vector<Characters> deneme = new Vector<>();
        Set keys = harfler.keySet();

        for(Iterator j = keys.iterator();j.hasNext();){
            Character harf = (Character) j.next();
            deneme.add(new Characters(harf, harfler.get(harf)));
        }

        return deneme;

    }

    public static Characters Huffman(Vector<Characters> harfler){
        Characters left, right, top, minNode1, minNode2;

        top = new Characters('*',0);
        minNode1 = new Characters('*', 0);
        minNode1.left = minNode1.right = null;
        minNode2 = new Characters('*', 0);
        minNode2.left = minNode2.right = null;


        while (harfler.size() > 1) {

            int min = 999999, index = 0;

            for (int i = 0; i < harfler.size(); i++) {
                if (harfler.get(i).getFrequency() < min) {
                    min = harfler.get(i).getFrequency();
                    index = i;
                    minNode1 = harfler.get(i);
                }
            }

            harfler.remove(index);

            min = 999999;

            for (int i = 0; i < harfler.size(); i++) {
                if (harfler.get(i).getFrequency() < min) {
                    min = harfler.get(i).getFrequency();
                    index = i;
                    minNode2 = harfler.get(i);
                }
            }

            harfler.remove(index);

            top = new Characters('*', minNode1.getFrequency() + minNode2.getFrequency());
            top.left = minNode1;
            top.right = minNode2;

            harfler.add(top);

        }

        return top;


    }

    void findCodes(Characters root, String code){

        Characters node = root;


        if(node.character != '*') {
            output.setText(output.getText().toString() + "\n" + node.getCharacter() + ":" + code);
            encodedData.put(node.getCharacter(), code);
        }

        if(node.left !=null)
            findCodes(node.left, code + "0");
        if(node.right != null)
            findCodes(node.right, code + "1");


    }

    void writeEncodedText(){

        String text = input.getText().toString();
        String outputText = "";
        for(int i = 0; i < text.length(); i++){

            outputText = outputText + encodedData.get(text.charAt(i)) + " ";

        }

            output.setText(output.getText().toString() + "\n" + outputText);
    }




}
