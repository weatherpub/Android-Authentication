package edu.sfsu.authentication.ui.notifications;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Get a reference to the database.
        SQLiteOpenHelper openHelper = new DatabaseHelper(getActivity());

        try {
            Log.i("log", "NotificationFragment 0");

            SQLiteDatabase db = openHelper.getReadableDatabase();

            // Create a Cursor and get a reference to it. Return all records from a table.
            // String[] COLUMNS = { "ID", "COLOR", "MAKE", "MODEL", "PRICE", "DESCRIPTION", "RESOURCE" };

            Log.i("log", "NotificationFragment 1");

            Cursor cursor = db.query(
                    false,
                    "Car",
                    new String[] { "COLOR", "MAKE", "MODEL", "PRICE", "DESCRIPTION"},
                    "COLOR = ?",
                    new String[] {"Red"}, null, null, null, null, null);

            Log.i("log", "NotificationFragment 2");

            TextView color = (TextView) view.findViewById(R.id.tv_color);
            TextView make = (TextView) view.findViewById(R.id.tv_make);
            TextView model = (TextView) view.findViewById(R.id.tv_model);
            TextView price = (TextView) view.findViewById(R.id.tv_price);
            TextView desc = (TextView) view.findViewById(R.id.tv_description);

            if(cursor.moveToFirst()) {
                Log.i("log", "NotificationFragment 3");
                //int record_txt = cursor.getInt(0);
                String color_str = cursor.getString(0);
                String make_str = cursor.getString(1);
                String model_str = cursor.getString(2);
                String price_str = cursor.getString(3);
                String description_str = cursor.getString(4);
                // int resource_txt = cursor.getInt(5);

                color.setText(color_str);
                make.setText(make_str);
                model.setText(model_str);
                price.setText(price_str);
                desc.setText(description_str);

                // use picasso to output the image
                /*
                ImageView resource = (ImageView) view.findViewById(R.id.iv_resource);
                resource.setImageResource(resource_txt);
                */
            }
            cursor.close();
            db.close();
        } catch (SQLException e)  {
            Toast toast = Toast.makeText(getContext(), "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        return view ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}