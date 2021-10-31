package com.example.ecommerce;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

  private static String DatabaseName="commerceDB";
  SQLiteDatabase commerceDB;

    public DatabaseHelper(Context context) {
        super(context, "commerceDB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table customers(cust_id integer primary key autoincrement,cust_name Text not null ," +
                "username Text not null unique, password Text not null,gender Text not null,birthdate Date not null,job Text not null)");

        sqLiteDatabase.execSQL("create table categories (cat_id integer primary key autoincrement,cat_name Text not null)");

        sqLiteDatabase.execSQL("create table products (product_id integer primary key autoincrement,product_name Text not null," +
                "price integer not null, quantity integer not null,catID integer not null,Foreign key (catID) references categories (cat_id) )");

        sqLiteDatabase.execSQL("create table orders(ord_id integer primary key autoincrement,orderDate Date not null," +
                "address Text not null,custID integer not null,Foreign key (custID) references customers (cust_id))");

       // sqLiteDatabase.execSQL("create table orderdetails (ordID integer primary key not null,proID integer primary key not null" +
         //       " ,Foreign key (ordID) references orders (ord_id),Foreign key (proID) references products (product_id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists customers ");
        sqLiteDatabase.execSQL("drop table if exists categories ");
        sqLiteDatabase.execSQL("drop table if exists products ");
        sqLiteDatabase.execSQL("drop table if exists orders ");
        sqLiteDatabase.execSQL("drop table if exists orderdetails ");
     onCreate(sqLiteDatabase);
    }

     public void addperson(String name,String username,String password,String gender,String bitrhdate,String job){

         ContentValues row=new ContentValues();
         row.put("cust_name",name);
         row.put("username",username);
         row.put("password",password);
         row.put("gender",gender);
         row.put("birthdate",bitrhdate);
         row.put("job",job);
        commerceDB=getWritableDatabase();
         commerceDB.insert("customers",null,row);
         commerceDB.close();
}

    public boolean checkUser(String username1) {
        // array of columns to fetch
        String[] columns = {"username"};
        commerceDB= getReadableDatabase();
        // selection criteria
        String selection = "username" + " = ?" ;
        // selection arguments
        String[] selectionArgs = {username1};
        // query user table with conditions
        Cursor cursor = commerceDB.query("customers", columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        commerceDB.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean updatepassword(String username,String password){

        ContentValues row=new ContentValues();
        row.put("password",password);
        commerceDB=getWritableDatabase();
       long result= commerceDB.update("customers",row,"username=?",new String[]{username});
       if(result==-1)
           return false;
       else
           return true;
    }


        public Cursor fetchdataperson(){
          commerceDB=getReadableDatabase();
          String[] rowdetails={"cust_name","username","password","gender","birthdate","job","id"};
          Cursor cursor=commerceDB.query("customers",rowdetails,null,null,null,null,null);
          if(cursor!=null)
              cursor.moveToFirst();
          commerceDB.close();
          return cursor;


        }

        public void addproduct(String name,int price ,int quantity,int catID)
        {
            ContentValues row=new ContentValues();
            row.put("name",name);
            row.put("price",price);
            row.put("quantity",quantity);
            row.put("catID",catID);
            commerceDB=getWritableDatabase();
            commerceDB.insert("products",null,row);
            commerceDB.close();
        }



      public void deleteproduct(String name)
      {
          commerceDB=getWritableDatabase();
          commerceDB.delete("products","name='" +name +"'",null);
          commerceDB.close();
      }

    public void updateproduct(String oldname,String newquantity)
    {
        ContentValues row=new ContentValues();
        row.put("newquantity",newquantity);
        commerceDB=getWritableDatabase();
        commerceDB.update("products",row,"name like ?",new String[]{oldname});
        commerceDB.close();
    }

    public Cursor searchproduct(String name){
        commerceDB=getReadableDatabase();
        String[] rowDetails1={"name","password"};
        Cursor cursor1=null;
        String sql="select * from "+"Department"+" where "+"name"+" like '%"+name+"%'";
        cursor1=commerceDB.rawQuery(sql,null);
        if(cursor1!=null)
            cursor1.moveToFirst();
        commerceDB.close();
        return cursor1;
    }

    public void addorder(String orddate,String custid ,String address)
    {
        ContentValues row=new ContentValues();
        row.put("orddate",orddate);
        row.put("custid",custid);
        row.put("address",address);
        commerceDB=getWritableDatabase();
        commerceDB.insert("orders",null,row);
        commerceDB.close();
    }


    public Cursor showtotalpriceorder(String name)
    {
        //
        commerceDB=getReadableDatabase();
        String[] rowdetails={"name"};
        Cursor cursor1=null;
        String sql="select price from "+"products"+" where "+"name"+" like '%"+name+"%'";
        cursor1=commerceDB.rawQuery(sql,null);
        if(cursor1!=null)
            cursor1.moveToFirst();
        commerceDB.close();
        return cursor1;
    }

    public void addcategory(String catname,String price ,String quantity,String catid)
    {
        ContentValues row=new ContentValues();
        row.put("catname",catname);
        row.put("price",price);
        row.put("quantity",quantity);
        row.put("catid",catid);
        commerceDB=getWritableDatabase();
        commerceDB.insert("ecommerce",null,row);
        commerceDB.close();
    }


}
