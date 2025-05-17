package edu.sfsu.authentication.ui.notifications;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import edu.sfsu.authentication.DatabaseHelper;
import edu.sfsu.authentication.R;
import edu.sfsu.authentication.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Get a reference to the database.
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(getContext());
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            // Create a Cursor and get a reference to it. Return all records from a table.
            Cursor cursor = db.query("Car", new String[] {"_id", "make", "description"}, null, null, null, null, null);

            if(cursor.moveToFirst()) {
                String make_txt = cursor.getString(0);
                String description_txt = cursor.getString(1);
                String id_txt = cursor.getString(2);

                TextView make = (TextView)getActivity().findViewById(R.id.tv_make);
                make.setText(make_txt);

                TextView description = (TextView)getActivity().findViewById(R.id.tv_description);
                description.setText(description_txt);

                TextView id = (TextView)getActivity().findViewById(R.id.tv_record);
                id.setText(id_txt);
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(getContext(), "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        /* *
        * Final TextView textView = binding.textNotifications;
        * notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        */

        return view ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}