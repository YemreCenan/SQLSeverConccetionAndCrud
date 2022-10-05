package com.example.sqldataadd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView id = findViewById(R.id.edittextid);
        TextView name = findViewById(R.id.edittextname);
        TextView address = findViewById(R.id.edittextaddres);
        Button btnInsert=findViewById(R.id.btnadd);
        Button btnUpdate=findViewById(R.id.btnUpd);
        Button btnDelete=findViewById(R.id.btnDlt);
        TextView textViewResult= findViewById(R.id.textViewresult);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection=connectionclass();
                try {

                    if (connection !=null){

                        String sqlinsert="Insert into UserInfo_Tab (ID,İsim,Adres) "+
                                "values('"+id.getText().toString()+"','"
                                +name.getText().toString()+"','"
                                +address.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs=st.executeQuery(sqlinsert);



                    }

                }
                catch (Exception exception){

                    Log.e("Error",exception.getMessage());
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection =connectionclass();
                try {
                    if (connection!=null){

                         String sqlupdate ="UPDATE UserInfo_Tab SET İsim='"+name.getText().toString()+"'" +
                                 ",Adres='"+address.getText().toString()+"' WHERE ID ='"+id.getText().toString()+"'";
                         Statement st =connection.createStatement();
                         ResultSet rs =st.executeQuery(sqlupdate);


                    }


                }
                catch (Exception exception){

                    Log.e("Error",exception.getMessage());

                }
            }
        });
    }



    @SuppressLint("NewApi")

    public Connection connectionclass(){

        Connection con=null;
        String ip="jdbc:jtds:sqlserver://192.168.81.213/CRUDAndroidDB;integratedSecurity=true";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con= DriverManager.getConnection(ip,"user","1111111");

        }
        catch (Exception exception){

            Log.e("Error",exception. getMessage());
        }
        return con;

    }
}