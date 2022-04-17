package com.project.expenseTracker.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Expense.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table expense_tbl(amount integer primary key autoincrement, add_mark text not null, category text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists expense_tbl");
    }

    public Boolean insertData(String amount, String add_remark, String category) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("add_remark",add_remark);
        contentValues.put("category",category);
        long result = DB.insert("expense_tbl",null, contentValues);
        if (result == -1) {
            return false;
        }else {
            return true;
        }

    }
//
//    public Boolean updateData(String amount, String remark, String category) {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("remark",remark);
//        contentValues.put("category",category);
//        Cursor cursor = DB.rawQuery("select * from expenseDetail where name = ?",new String[]{amount});
//        if (cursor.getCount() > 0) {
//            long result = DB.update("expenseDetail",contentValues,"amount=?",new String[]{amount});
//            if (result == -1) {
//                return false;
//            }else {
//                return true;
//            }
//        }   else {
//            return false;
//        }
//
//
//    }
//
//    public Boolean deleteData(String amount) {
//        SQLiteDatabase DB = this.getReadableDatabase();
//
//        Cursor cursor = DB.rawQuery("select * from expenseDetail where name = ?",new String[]{amount});
//        if (cursor.getCount() > 0) {
//            long result = DB.delete("expenseDetail","amount=?",new String[]{amount});
//            if (result == -1) {
//                return false;
//            }else {
//                return true;
//            }
//        }   else {
//            return false;
//        }
//
//
//    }
//
//    public Cursor getData() {
//        SQLiteDatabase DB = this.getReadableDatabase();
//
//        Cursor cursor = DB.rawQuery("select * from expenseDetail",null);
//        return cursor;
//    }
}
