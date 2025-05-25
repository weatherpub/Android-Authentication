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
import kotlinx.serialization.StringFormat;

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
            SQLiteDatabase db = openHelper.getReadableDatabase();

            /**
             * Return All Records from a table.
             * (Page 664 Head First Android Development)
             */

            /**
            Cursor cursor = db.query(
                    false,
                    "Car",
                    new String[] { "COLOR", "MAKE", "MODEL", "PRICE", "DESCRIPTION", "RESOURCE"},
                    "COLOR = ?",
                    new String[] {"Blue"}, null, null, null, null, null);
             */

            // all values
             Cursor cursor = db.query(
                     "Car",
                     new String[] { "ID", "COLOR", "MAKE", "MODEL", "PRICE", "DESCRIPTION", "RESOURCE"},
                     null, null, null, null, null);
            /*
            Cursor cursor = db.query(
                    false,
                    "Car",
                    new String[] { "COLOR", "MAKE", "MODEL", "PRICE", "DESCRIPTION", "RESOURCE"},
                    "RESOURCE = ?",
                    new String[] {Integer.toString(R.drawable.mustang)},
                    null, null, null, null, null);
            */

            Log.i("log", "NotificationFragment 2");

            TextView id = (TextView) view.findViewById(R.id.tv_id);
            TextView color = (TextView) view.findViewById(R.id.tv_color);
            TextView make = (TextView) view.findViewById(R.id.tv_make);
            TextView model = (TextView) view.findViewById(R.id.tv_model);
            TextView price = (TextView) view.findViewById(R.id.tv_price);
            ImageView resource = (ImageView) view.findViewById(R.id.iv_resource);
            TextView desc = (TextView) view.findViewById(R.id.tv_description);

            if(cursor.moveToFirst()) {
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