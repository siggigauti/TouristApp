package is.siggigauti.touristapp.ui;

/**
 * Created by Sindri on 15.3.2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.model.CatMatcher;
import is.siggigauti.touristapp.model.Category;

import static is.siggigauti.touristapp.R.id.checkBox0;
import static is.siggigauti.touristapp.R.id.checkBox1;
import static is.siggigauti.touristapp.R.id.checkBox2;
import static is.siggigauti.touristapp.R.id.checkBox3;
import static is.siggigauti.touristapp.R.id.checkBox4;


public class Filter extends AppCompatActivity {

    ArrayList<Category> categoriesArrayList = new ArrayList<Category>();
    ArrayList<CatMatcher> categoriesMatcherList = new ArrayList<CatMatcher>();
    int checkedArray[] = {0, 0, 0, 0, 0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(is.siggigauti.touristapp.R.layout.activity_filter);

        // Go Back to Trips page
        Button button_filterPage_goBackToList = (Button) findViewById(R.id.button_filterPage_goBackToList);
        button_filterPage_goBackToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListOfTrips.idToSearchFor = checkedArray;

                Intent in = new Intent(Filter.this, ListOfTrips.class);
                startActivity(in);
            }
        });


        final CheckBox checkBox_0 = (CheckBox) findViewById(checkBox0);
        checkBox_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox_0.isChecked()){
                    System.out.println("CheckBox 0 checked");
                    checkedArray[0] = 1;
                }else{
                    System.out.println("CheckBox 0 Un-Checked");
                    checkedArray[0] = 0;
                }
            }
        });


        final CheckBox checkBox_1 = (CheckBox) findViewById(checkBox1);
        checkBox_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox_1.isChecked()){
                    System.out.println("CheckBox 1 checked");
                    checkedArray[1] = 1;
                }else{
                    System.out.println("CheckBox 1 Un-Checked");
                    checkedArray[1] = 0;
                }
            }
        });



        final CheckBox checkBox_2 = (CheckBox) findViewById(checkBox2);
        checkBox_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox_2.isChecked()){
                    System.out.println("CheckBox 2 checked");
                    checkedArray[2] = 1;
                }else{
                    System.out.println("CheckBox 2 Un-Checked");
                    checkedArray[2] = 0;
                }
            }
        });


        final CheckBox checkBox_3 = (CheckBox) findViewById(checkBox3);
        checkBox_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox_3.isChecked()){
                    System.out.println("CheckBox 3 checked");
                    checkedArray[3] = 1;
                }else{
                    System.out.println("CheckBox 3 Un-Checked");
                    checkedArray[3] = 0;
                }
            }
        });


        final CheckBox checkBox_4 = (CheckBox) findViewById(checkBox4);
        checkBox_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox_4.isChecked()){
                    System.out.println("CheckBox 4 checked");
                    checkedArray[4] = 1;
                }else{
                    System.out.println("CheckBox 4 Un-Checked");
                    checkedArray[4] = 0;
                }
            }
        });



        /*
        DBHandler dbHandler = new DBHandler(this);
        categoriesArrayList = dbHandler.getAllCategories();
        categoriesMatcherList = dbHandler.getAllCatMatcher();


        for(CatMatcher category : categoriesMatcherList){
            System.out.println("prentum trip id: " + category.getTripId());
            System.out.println("prentum category id: " + category.getCategoryId());
        }
        */
        //System.out.print(categoriesArrayList);
    }

















}