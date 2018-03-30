package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivityPracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    private TextView result_user_text_view;
    private TextView result_grupa_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_practical_test01_var03_secondary);

        result_user_text_view = (TextView) findViewById(R.id.result_user_text_view);
        result_grupa_text_view = (TextView) findViewById(R.id.result_grupa_text_view);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("user")) {
            String user = intentThatStartedThisActivity.getStringExtra("user");
            result_user_text_view.setText(user);
        }

        if (intentThatStartedThisActivity.hasExtra("grupa")) {
            String grupa = intentThatStartedThisActivity.getStringExtra("grupa");
            result_grupa_text_view.setText(grupa);
        }
    }

    public void Go_Back_ok(View view) {
        setResult(200);
        finish();
    }

    public void Go_Back_cancel(View view) {
        setResult(300);
        finish();
    }
}
