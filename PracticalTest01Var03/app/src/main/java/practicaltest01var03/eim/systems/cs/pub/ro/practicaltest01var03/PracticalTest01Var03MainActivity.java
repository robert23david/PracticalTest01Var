package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText grupa;
    private Button navigate_to_sec_activ;
    private Button display_info;
    private TextView result_text_view;
    private CheckBox checkbox_user;
    private CheckBox checkbox_grupa;

    private Integer limit = 5;
    public boolean serviceStatus = false;
    private IntentFilter startedServiceIntentFilter;
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String result = "";
            int ok = 0;
            String err_msg = "";
            switch (view.getId()) {
                case R.id.display_info:
                    if (checkbox_user.isChecked()) {
                        if (user.getText().toString().equals("")) {
                            ok = 1;
                            err_msg = "Eroare: campul user este gol";
                        } else {
                            result = result + user.getText().toString() + " ";
                        }

                    }
                    if (checkbox_grupa.isChecked()) {
                        if (grupa.getText().toString().equals("")) {
                            ok = 1;
                            err_msg = "Eroare: campul grupa este gol";
                        } else {
                            result = result + grupa.getText().toString();
                        }
                    }

                    if (ok == 0) {
                        result_text_view.setText(result);
                    } else {
                        Toast.makeText(PracticalTest01Var03MainActivity.this, err_msg, Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if (!serviceStatus) {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(getApplicationContext(), PracticalTest01Var03Service.class));
                        intent.putExtra("user",  user.getText().toString());
                        intent.putExtra("grupa",  grupa.getText().toString());
                        startService(intent);
                        serviceStatus = true;
                    }

                    break;

                case R.id.navigate_to_sec_activ:
                    Context context = PracticalTest01Var03MainActivity.this;
                    Class cls = MainActivityPracticalTest01Var03SecondaryActivity.class;
                    Intent intent = new Intent(context, cls);
                    intent.putExtra("user", user.getText().toString());
                    intent.putExtra("grupa", grupa.getText().toString());
                    startActivityForResult(intent, 111);
                    break;
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getApplicationContext(), PracticalTest01Var03Service.class));
        stopService(intent);

        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        user = (EditText) findViewById(R.id.user_edit_text);
        grupa = (EditText) findViewById(R.id.grupa_edit_text);

        savedInstanceState.putString("USER_EDIT_TEXT", user.getText().toString());
        savedInstanceState.putString("GRUPA_EDIT_TEXT", grupa.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("USER_EDIT_TEXT")) {
            user = (EditText) findViewById(R.id.user_edit_text);
            user.setText(savedInstanceState.getString("USER_EDIT_TEXT"));
        }
        if (savedInstanceState.containsKey("GRUPA_EDIT_TEXT")) {
            grupa = (EditText) findViewById(R.id.grupa_edit_text);
            grupa.setText(savedInstanceState.getString("GRUPA_EDIT_TEXT"));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        result_text_view = (TextView) findViewById(R.id.result_text_view);
        navigate_to_sec_activ = (Button) findViewById(R.id.navigate_to_sec_activ);
        display_info = (Button) findViewById(R.id.display_info);

        user = (EditText) findViewById(R.id.user_edit_text);
        grupa = (EditText) findViewById(R.id.grupa_edit_text);

        checkbox_user = (CheckBox) findViewById(R.id.checkbox_user);
        checkbox_grupa = (CheckBox) findViewById(R.id.checkbox_grupa);

        navigate_to_sec_activ.setOnClickListener(buttonClickListener);
        display_info.setOnClickListener(buttonClickListener);

        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver(result_text_view);
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction("0");
        startedServiceIntentFilter.addAction("1");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 111:
                Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
