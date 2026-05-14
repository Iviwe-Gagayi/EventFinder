//package com.example.eventfinder.ui;
//
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.PickVisualMediaRequest;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.bumptech.glide.Glide;
//import com.example.eventfinder.R;
//
//
//public class Account_activity extends AppCompatActivity {
//
//    EditText nameField, surnameField, usernameField, emailField, passwordField;
//    Button save_btn, deleteAccount_btn;
//    LinearLayout logout_btn;
//    TextView forgotPasswordLink;
//    ImageView profileImage;
//
//
//    ActivityResultLauncher<PickVisualMediaRequest> launcher = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
//        @Override
//        public void onActivityResult(Uri o) {
//            if (o == null) {
//                Toast.makeText(Account_activity.this, "No image Selected", Toast.LENGTH_SHORT).show();
//            } else {
//                Glide.with(getApplicationContext()).load(o).into(profileImage);
//            }
//        }
//    });
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.account_page);
//        nameField=findViewById(R.id.nameField);
//        surnameField= findViewById(R.id.surnameField);
//        usernameField= findViewById(R.id.usernameField);
//        emailField= findViewById(R.id.emailField);
//        passwordField = findViewById(R.id.passwordField);
//        save_btn = findViewById(R.id.btn_save);
//        logout_btn= findViewById(R.id.btn_logout);
//        deleteAccount_btn= findViewById(R.id.btn_delete_account);
//        forgotPasswordLink= findViewById(R.id.forgotPasswordLink);
//
//
//        profileImage = findViewById(R.id.profileImage);
//        profileImage.setOnClickListener(v -> launcher.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build()));
//
//        // Back button
//        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
//        // When user clicks on password field, tap opens a dialog asking for current password
//        passwordField.setOnClickListener(v -> showChangePasswordDialog());
//        // Forgot password link
//        forgotPasswordLink.setOnClickListener(v -> {
//          //do the php send email stuff here
//            Toast.makeText(this,
//                    "Reset link has been sent to your email", Toast.LENGTH_SHORT).show();
//        });
//
//
//
//        // Save changes to database here as well
//        save_btn.setOnClickListener(v -> {
//            String name= nameField.getText().toString();
//            String surname= surnameField.getText().toString();
//            String username= usernameField.getText().toString();
//            String email= emailField.getText().toString();
//
//            if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || email.isEmpty()) {
//                Toast.makeText(this,
//                        "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                return;}
//            //you should verify that the current password is coreect then update to db
//            Toast.makeText(this,
//                    "Changes saved!", Toast.LENGTH_SHORT).show();
//        });
//
//
//
//
//        // Logout styff
//        logout_btn.setOnClickListener(v -> {
//            // Clear SharedPreferences
//            getSharedPreferences("EventApp", MODE_PRIVATE)
//                    .edit()
//                    .clear()
//                    .apply();
//
//            // Goes back to register/login screen
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        });
//
//        // Delete account
//        deleteAccount_btn.setOnClickListener(v -> showDeleteAccountDialog());
//    }
//
//
//
//
//
//
//    //verification of password dialog
//    private void showChangePasswordDialog() {
//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setPadding(50, 40, 50, 10);
//
//        EditText currentPassword = new EditText(this);
//        currentPassword.setHint("Current password");
//        currentPassword.setInputType(
//                android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        layout.addView(currentPassword);
//
//        EditText newPassword = new EditText(this);
//        newPassword.setHint("New password");
//        newPassword.setInputType(
//                android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        layout.addView(newPassword);
//
//        new AlertDialog.Builder(this)
//                .setTitle("Change Password")
//                .setView(layout)
//                .setPositiveButton("Update", (dialog, which) -> {
//                    String current = currentPassword.getText().toString().trim();
//                    String newPass = newPassword.getText().toString().trim();
//
//                    if (current.isEmpty() || newPass.isEmpty()) {
//                        Toast.makeText(this,
//                                "Please fill in both fields",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//                    //verfy passwordh ere
//                    Toast.makeText(this,
//                            "Password updated!", Toast.LENGTH_SHORT).show();
//                })
//                .setNegativeButton("Cancel", null)
//                .show();
//    }
//
//    private void showDeleteAccountDialog() {
//        new AlertDialog.Builder(this)
//                .setTitle("Delete Account")
//                .setMessage("Are you sure? This cannot be undone. " +
//                        "All your saved events and RSVPs will be lost.")
//                .setPositiveButton("Yes, delete", (dialog, which) -> {
//
//                    //delete acc from db here
//                    //then should go back to login/resister page
//                    getSharedPreferences("EventApp", MODE_PRIVATE)
//                            .edit()
//                            .clear()
//                            .apply();
//
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//
//                    Toast.makeText(this,
//                            "Account deleted", Toast.LENGTH_SHORT).show();
//                })
//                .setNegativeButton("Cancel", null)
//                .show();
//    }
//}