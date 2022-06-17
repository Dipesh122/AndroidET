package com.project.expenseTracker.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.expenseTracker.book.Book;
import com.project.expenseTracker.incomeandexpenses.IncomeAndExpenses;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper  {
    private Context context;


    public static final String DATABASE_NAME = "BookDB.db";

    // Columns of the Book table
    public static final String BOOKS_TABLE = "books";
    public static final String COLUMN_BOOK_ID = "id";
    public static final String COLUMN_BOOK_NAME = "book_name";

    // Columns of the expenseRecord
    public static final String EXPENSE_TABLE = "expenses";
    public static final String EXPENSE_ID = "id";
    public static final String EXPENSE_AMOUNT = "amount" ;
    public static final String EXPENSE_REMARK = "remark";
    public static final String EXPENSE_CATEGORY = "category";

    private String[] bookAllColumns = { COLUMN_BOOK_ID, COLUMN_BOOK_NAME};


    //FOR BOOK not null means, it's a must
    private static final String SQL_CREATE_TABLE_BOOKS = "CREATE TABLE " + BOOKS_TABLE + " ("
            + COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BOOK_NAME + " TEXT NOT NULL " + ");";

    //FOR EXPENSES not null means, it's a must
    private static final String SQL_CREATE_TABLE_EXPENSE = "CREATE TABLE " + EXPENSE_TABLE + " ("
            + EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EXPENSE_AMOUNT + " INTEGER NOT NULL, " + EXPENSE_REMARK + " TEXT NOT NULL, "+ EXPENSE_CATEGORY + " TEXT NOT NULL "+ ");";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String qry = "create table expenses ( id integer primary key autoincrement, remark text not null)";
        db.execSQL(SQL_CREATE_TABLE_BOOKS);
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+BOOKS_TABLE);
        db.execSQL("drop table if exists expenses");
        onCreate(db);
    }


    //---------------------------------SQLITE CODE FOR books_table
    public Book insertBook(String name) {
        Book book = new Book();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book_name",name);
        int insertId = (int) db.insert("books",null,contentValues);
        Cursor result = db.query("books",bookAllColumns,COLUMN_BOOK_ID + " = "+insertId, null,null,null,null);
        result.moveToFirst();
        Book newBook = cursorToTarget(result);
        result.close();

        return newBook;

    }

    //fetch data
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> book_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " select * from "+BOOKS_TABLE+" order by "+COLUMN_BOOK_ID+" desc ";
        Cursor result = db.rawQuery(query,null);
        if (result != null){
            result.moveToFirst();
            while(!result.isAfterLast()){
                Book target = cursorToTarget(result);
                book_list.add(target);
                result.moveToNext();
            }
        }
        result.close();
        return book_list;
    }

    protected Book cursorToTarget(Cursor cursor){
        Book book = new Book();
        book.setId(cursor.getInt(0));
        book.setName(cursor.getString(1));
        return book;
    }

    //getting id of books_table for edit_book_name
    public Book getBookId(int book_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + BOOKS_TABLE +" WHERE "+COLUMN_BOOK_ID+"="+book_id, null );
        res.moveToFirst();
        Book new_book = cursorToTarget(res);
        res.close();
        return new_book;

    }

    //updating book_name
    public void update_book(Book book) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        // fill contentValues with the data
        contentValues.put(COLUMN_BOOK_ID,book.getId());
        contentValues.put(COLUMN_BOOK_NAME,book.getName());
        db.update(BOOKS_TABLE,contentValues,COLUMN_BOOK_ID+ " = "+book.getId(),null);
    }

    //delete book from recyclerview
    public void deleteTargetGivenTarget (Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Deleting the target
        db.delete(BOOKS_TABLE,
                COLUMN_BOOK_ID + " = "  + book.getId(),
                null);
    }


    //---------------------------------SQLITE CODE FOR Expense_table
    public String insertExpense(String remark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("expense_remark",remark);
        float result = db.insert("expenses",null,contentValues);
        if (result == -1) {
            return "Failed to Add Expense";
        }
        else {
            return "New Expense is Added";
        }
    }

    //fetch data
//    public ArrayList<IncomeAndExpenses> getAllExpenses() {
//        ArrayList<Book> book_list = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = " select * from "+BOOKS_TABLE+" order by "+COLUMN_BOOK_ID+" desc ";
//        Cursor result = db.rawQuery(query,null);
//        if (result != null){
//            result.moveToFirst();
//            while(!result.isAfterLast()){
//                Book target = cursorToTarget(result);
//                book_list.add(target);
//                result.moveToNext();
//            }
//        }
//        result.close();
//        return book_list;
//    }
//
//    protected IncomeAndExpenses cursorToTarget(Cursor cursor){
//        IncomeAndExpenses incomeAndExpenses = new IncomeAndExpenses();
////        book.setId(cursor.getInt(0));
////        book.setName(cursor.getString(1));
//        return incomeAndExpenses;
//    }
//
//    //getting id of books_table for edit_book_name
//    public IncomeAndExpenses getExpenseId(int book_id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res =  db.rawQuery( "SELECT * FROM " + BOOKS_TABLE +" WHERE "+COLUMN_BOOK_ID+"="+book_id, null );
//        res.moveToFirst();
//        Book new_book = cursorToTarget(res);
//        res.close();
//        return new_book;
//
//    }
//
//    //updating book_name
//    public void update_Expense(Book book) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        // fill contentValues with the data
//        contentValues.put(COLUMN_BOOK_ID,book.getId());
//        contentValues.put(COLUMN_BOOK_NAME,book.getName());
//        db.update(BOOKS_TABLE,contentValues,COLUMN_BOOK_ID+ " = "+book.getId(),null);
//    }
//
//    //delete book from recyclerview
//    public void deleteExpense(Book book) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(BOOKS_TABLE,
//                COLUMN_BOOK_ID + " = "  + book.getId(),
//                null);
//    }
}
