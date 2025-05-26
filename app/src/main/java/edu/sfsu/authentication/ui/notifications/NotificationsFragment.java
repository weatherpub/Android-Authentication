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

import edu.sfsu.authentication.DatabaseHelper;
import edu.sfsu.authentication.R;
import edu.sfsu.authentication.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    @SuppressLint("Range")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // execute the nested class.
        new DatabaseAsyncTask().execute();

        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

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

            TextView id = (TextView) getView().findViewById(R.id.tv_id);
            TextView color = (TextView) getView().findViewById(R.id.tv_color);
            TextView make = (TextView) getView().findViewById(R.id.tv_make);
            TextView model = (TextView) getView().findViewById(R.id.tv_model);
            TextView price = (TextView) getView().findViewById(R.id.tv_price);
            ImageView resource = (ImageView) getView().findViewById(R.id.iv_resource);
            TextView desc = (TextView) getView().findViewById(R.id.tv_description);

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
}