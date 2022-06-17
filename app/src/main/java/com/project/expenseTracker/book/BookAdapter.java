package com.project.expenseTracker.book;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.project.expenseTracker.HomeActivity;
import com.project.expenseTracker.R;
import com.project.expenseTracker.dbhelper.DBHelper;
import com.project.expenseTracker.incomeandexpenses.IncomeAndExpensesActivity;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> implements Filterable {
    private Context context;
    public ArrayList<Book> bookArrayList;
    DBHelper db;
    ArrayList<Book> backUp;
    private static final String TAG = "BookAdapter";



    public BookAdapter(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
        backUp = new ArrayList<>(bookArrayList);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_view_list,null);
        return new MyViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //get the data model based on position
        final Book book= bookArrayList.get(position);

        holder.book_name.setText(bookArrayList.get(position).getName());
         holder.id =book.getId();

        holder.bookMoreVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(holder.context,view);
                popupMenu.getMenuInflater().inflate(R.menu.book_menu, popupMenu.getMenu());
                popupMenu.show();
                Log.d("Bookid",String.valueOf(book.getId()));
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.rename:
                                    update_book_name(holder.context,book.getName(),book.getId());
                                break;
                            case R.id.remove:
                                    remove_book(holder.context,book.getId());
                                break;

                        }
                        return true;
                    }
                });
            }
        });

    }

    private void update_book_name(Context context, String current_name, int book_id) {
        final Context given_context = context;
        final int given_book_id = book_id;

        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View mView = LayoutInflater.from(context).inflate(R.layout.rename_book,null);
        TextInputEditText renameBook = mView.findViewById(R.id.renameBook);
        renameBook.setText(current_name);
        renameBook.setSelectAllOnFocus(true);
        final Button edit_btn = mView.findViewById(R.id.update);
        final Button cancel_btn = mView.findViewById(R.id.cancel);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
//        renameBook.setText();

        // getting id and name for editing bookName
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper database = new DBHelper(context);
                Book book = database.getBookId(given_book_id);
                String given_text = renameBook.getText().toString();
                if (given_text.length() > 0) {
                    book.setName(given_text);
//                    update book's entry in the database
                    database.update_book(book);
                    int index = 0;
                    for (Book book_in_main : BookFragment.bookArrayList) {
                        if (book_in_main.getId() == book.getId())
                            break;
                        index++;
                    }
                    BookFragment.bookArrayList.get(index).setName(book.getName());
                    BookFragment.bookAdapter.notifyItemChanged(index);
                }
                alertDialog.dismiss();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        alertDialog.show();


    }

    private void remove_book(Context context, int target_id){
        final Context given_context = context;
        final int given_target_id = target_id;
        Log.d(TAG, "Deleting target From MainActivity");

        // add an "Are you sure?" popup window
        AlertDialog.Builder builder = new AlertDialog.Builder(given_context);
        builder.setCancelable(true);
        builder.setTitle(context.getResources().getString(R.string.delete)+"?");    // Remove?
        builder.setMessage(context.getResources().getString(R.string.book_deleting_warrning));
        builder.setPositiveButton(context.getResources().getString(R.string.confirm),   //Confirm
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // removes the target from the database
                        DBHelper database = new DBHelper(given_context);
                        database.deleteTargetGivenTarget(database.getBookId(given_target_id));
                        int index = 0;
                        for (Book target_in_list : BookFragment.bookArrayList){
                            if(target_in_list.getId() == given_target_id) {
                                break;
                            }
                            index++;
                        }
                        BookFragment.bookArrayList.remove(index);
                        BookFragment.bookAdapter.notifyItemRemoved(index);
                        BookFragment.bookAdapter.notifyItemRangeChanged(index, BookFragment.bookArrayList.size());
                        BookFragment.recyclerView.scrollToPosition(0);

                        // Show the no subjects to display if the last one has been deleted
                        if (BookFragment.bookArrayList.size() == 0){
//                            HomeActivity.noSubjectsTextView.setVisibility(View.VISIBLE);
                        }
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }


    //---------------------------------- for searchView
    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override // background thread
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Book> filteredData = new ArrayList<>();
            if (keyword.toString().isEmpty()) {
                filteredData.addAll(backUp);
            } else {
                for (Book obj : backUp) {
                    if (obj.getName().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filteredData.add(obj);
                }
            }
            FilterResults results =  new FilterResults();
            results.values = filteredData;
            return results;
        }

        @Override // main UI thread
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            bookArrayList.clear();
            bookArrayList.addAll((ArrayList<Book>)filterResults.values);
            notifyDataSetChanged();
        }
    };

    //    testing searchbar
    public void filterList(ArrayList<Book> filterList) {
        bookArrayList = filterList;
        notifyDataSetChanged();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private Context context;
             TextView book_name,bookMoreVert;
             int id;

        public MyViewHolder(Context context,View itemView) {
            super(itemView);
            book_name = itemView.findViewById(R.id.bookName);
            bookMoreVert = itemView.findViewById(R.id.bookMoreVert);
            this.context = context;

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);

        }



        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d(TAG,"onClick"+position);
            Intent intent = new Intent(context, IncomeAndExpensesActivity.class);
            intent.putExtra("bookName", book_name.getText());
            intent.putExtra("bookId", id);
            Log.d("dipesh",String.valueOf(id));
            context.startActivity(intent);

        }
    }


}
