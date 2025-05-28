package edu.sfsu.authentication.ui.notifications;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sfsu.authentication.DatabaseHelper;
import edu.sfsu.authentication.R;
import edu.sfsu.authentication.adapter.RecyclerViewAdapter;
import edu.sfsu.authentication.databinding.FragmentNotificationsBinding;
import edu.sfsu.authentication.model.home.DatabaseModel;

public class NotificationsFragment extends Fragment {


    private FragmentNotificationsBinding binding;
    public RecyclerView recyclerView;

    @SuppressLint("Range")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView = view.findViewById(R.id.rv_homeFragment);

        // execute the nested class.
        new DatabaseAsyncTask().execute();

        return view ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class DatabaseAsyncTask extends AsyncTask<String, ProgressBar, Boolean> {

        SQLiteDatabase db;
        Cursor cursor;

        SQLiteOpenHelper openHelper = new DatabaseHelper(getActivity());

        protected void onPreExecute() {}

        protected Boolean doInBackground(String... params) {
            Log.i("log", "Do in the Background");
            for(int i = 0; i < 10; i++) {
                publishProgress(i);
            }

            try {
                db = openHelper.getReadableDatabase();
                cursor = db.query(
                        "Car",
                        new String[] { "ID", "COLOR", "MAKE", "MODEL", "PRICE", "DESCRIPTION", "RESOURCE"},
                        null, null, null, null, null);

                return true;
            } catch (SQLException e)  {
                return false;
            }
        }

        private void publishProgress(int i) {
        }

        protected void onProgressUpdate(Integer... values) {
            // onProgressUpdate(values[0]);
        }

        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            ArrayList<DatabaseModel> mod;
            DatabaseRecyclerViewAdapter databaseRecyclerViewAdapter = new DatabaseRecyclerViewAdapter();
            recyclerView.setAdapter(databaseRecyclerViewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            /*
            int record_str;
            String color_str;
            String make_str;
            String model_str;
            String price_str;
            String description_str;
            int resource_str;
            */

            TextView id = (TextView) getView().findViewById(R.id.tv_id);
            TextView color = (TextView) getView().findViewById(R.id.tv_color);
            TextView make = (TextView) getView().findViewById(R.id.tv_make);
            TextView model = (TextView) getView().findViewById(R.id.tv_model);
            TextView price = (TextView) getView().findViewById(R.id.tv_price);
            TextView desc = (TextView) getView().findViewById(R.id.tv_description);
            ImageView resource = (ImageView) getView().findViewById(R.id.iv_resource);

            String res = "";

            /**
             * getColumnIndex(String columnName);
             * Returns the zero-based index for the given column name, or -1 if the column name doesn't exist.
             *
             * int index_id = cursor.getColumnIndex("ID");
             * (index_id == 0)  true
             */

            // uncomment to show database without image.
            /*
            int index_id = cursor.getColumnIndex("ID");
            int index_color = cursor.getColumnIndex("COLOR");
            int index_make = cursor.getColumnIndex("MAKE");
            int index_model = cursor.getColumnIndex("MODEL");
            int index_price = cursor.getColumnIndex("PRICE");
            int index_description = cursor.getColumnIndex("DESCRIPTION");
            int index_resource = cursor.getColumnIndex("RESOURCE");

            for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
                res = res + cursor.getString(index_id) + "\n";
                id.setText(res);

                res = res + cursor.getString(index_color) + "\n";
                color.setText(res);

                res = res + cursor.getString(index_make) + "\n";
                make.setText(res);

                res = res + cursor.getString(index_model) + "\n";
                model.setText(res);

                res = res + cursor.getString(index_price) + "\n";
                price.setText(res);

                res = res + cursor.getString(index_description) + "\n";
                desc.setText(res);

                res = res + cursor.getInt(index_resource) + "\n";

                // resource.setImageResource(resource_str);
                // resource.setImageResource(cursor.getInt(index_resource));

                Log.i("log", "resource_str -> " + resource_str);

                // id.setText(res);
                //color.setText(cursor.getString(index_color));
            }
            */

            // Show last record along with image.
            if(cursor.moveToLast()) {
                int record_str = cursor.getInt(0);
                String color_str = cursor.getString(1);
                String make_str = cursor.getString(2);
                String model_str = cursor.getString(3);
                String price_str = cursor.getString(4);
                String description_str = cursor.getString(5);
                int resource_str = cursor.getInt(6);

                id.setText(Integer.toString(record_str));
                color.setText(color_str);
                make.setText(make_str);
                model.setText(model_str);
                price.setText(price_str);
                desc.setText(description_str);
                resource.setImageResource(resource_str);
            }

            cursor.close();
            db.close();
        }
    }

    public class DatabaseRecyclerViewAdapter extends RecyclerView.Adapter<DatabaseRecyclerViewAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.i("log", "[ onCreateViewHolder loaded ]");
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull DatabaseRecyclerViewAdapter.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}