package com.example.eventfinder.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventfinder.R;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView_Login = (TextView) findViewById(R.id.tv_login);
        TextView info_text = (TextView) findViewById(R.id.tv_info);
        TextView forgot_password = (TextView) findViewById(R.id.tv_forgot_pass);
        TextInputLayout name = (TextInputLayout) findViewById(R.id.NameTextField);
        TextInputLayout confirm_password = (TextInputLayout) findViewById(R.id.ConfirmPasswordTextField);
        TextInputLayout username = (TextInputLayout) findViewById(R.id.UsernameTextField);
        Button button_Login = (Button) findViewById(R.id.btn_login);


        textView_Login.setOnClickListener(v -> {
            name.setVisibility(View.GONE);
            confirm_password.setVisibility(View.GONE);
            username.setVisibility(View.GONE);
            button_Login.setText("Log In");
            info_text.setText("Sign In");
            textView_Login.setVisibility(View.GONE);
            forgot_password.setVisibility(View.VISIBLE);

        });
    }
}