package com.example.eventfinder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventfinder.R;
import com.example.eventfinder.network.ApiClient;
import com.example.eventfinder.utils.SessionManager;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView textView_Login = findViewById(R.id.tv_login);
        TextView info_text = findViewById(R.id.tv_info);
        TextView forgot_password = findViewById(R.id.tv_forgot_pass);
        TextInputLayout name = findViewById(R.id.NameTextField);
        TextInputLayout username = findViewById(R.id.UsernameTextField);
        TextInputLayout confirm_password = findViewById(R.id.ConfirmPasswordTextField);
        Button button_Login = findViewById(R.id.btn_login);
        TextInputLayout emailLayout = findViewById(R.id.EmailTextField);
        TextInputLayout passwordLayout = findViewById(R.id.PasswordTextField);

        //Switching from signup to login
        textView_Login.setOnClickListener(v -> {
            name.setVisibility(View.GONE);
            confirm_password.setVisibility(View.GONE);
            username.setVisibility(View.GONE);
            button_Login.setText("Log In");
            info_text.setText("Sign In");
            textView_Login.setVisibility(View.GONE);
            forgot_password.setVisibility(View.VISIBLE);
        });


        button_Login.setOnClickListener(v -> {
            String emailText = emailLayout.getEditText().getText().toString().trim();
            String passwordText = passwordLayout.getEditText().getText().toString().trim();
            String nameText = name.getEditText().getText().toString().trim();


            //If text is login hit the login endpoint otherwise hit the signup endpoint.
            //Storing that respective endpint
            String currentMode = button_Login.getText().toString();
            String endpoint = currentMode.equals("Log In") ? "login.php" : "register.php";

            //validating input
            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!currentMode.equals("Log In")) {
                String confirmText = confirm_password.getEditText().getText().toString().trim();


                if (nameText.isEmpty()) {
                    Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!passwordText.equals(confirmText)) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
            }


            JSONObject payload = new JSONObject();
            try {
                payload.put("email", emailText);
                payload.put("password", passwordText);
                if (!currentMode.equals("Log In")) {
                    payload.put("name", name.getEditText().getText().toString().trim());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



            ApiClient.getInstance().postRequest(endpoint, payload, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    //OkHTTP runs on background thread. If we don't tell android studio to run on the main ui thread
                    //it'll try to use the background thread too and crash the app (found out the hard way...)
                    runOnUiThread(() -> Toast.makeText(Signup.this, "Network error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful() && response.body() != null) {
                        String responseData = response.body().string();

                        //same thing. have to tell android studio to use the main ui thread
                        runOnUiThread(() -> {
                            try {
                                JSONObject jsonResponse = new JSONObject(responseData);
                                String status = jsonResponse.getString("status");

                                if (status.equals("success")) {
                                    //Getting the "user_id" from the data in the response
                                    JSONObject dataObj = jsonResponse.getJSONObject("data");
                                    int userId = dataObj.getInt("user_id");

                                    Toast.makeText(Signup.this, "Success!", Toast.LENGTH_SHORT).show();

                                    //saving "user_id" globally
                                    SessionManager.saveUserId(Signup.this, userId);

                                    //After the user has successfully logged in go to the main page
                                    Intent intent = new Intent(Signup.this, MainFeed.class);
                                    startActivity(intent);
                                    finish(); //finish is a built in function which just destroys this screen so the user can't come back

                                } else {
                                    //This runs if the server worked fine, but some data in the request was incorrect like
                                    //a wrong passsword or something
                                    String errorMsg = jsonResponse.optString("message", "Authentication failed");
                                    Toast.makeText(Signup.this, errorMsg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();
                                Log.e("API_ERROR", "JSON Error", e);
                            }
                        });
                    } else {
                        runOnUiThread(() -> Toast.makeText(Signup.this, "Server error: " + response.code(), Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });
    }
}