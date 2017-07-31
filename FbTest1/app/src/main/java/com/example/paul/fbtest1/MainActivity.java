package com.example.paul.fbtest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    private CallbackManager callbackManager;
    ProfilePictureView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();

        info = (TextView)findViewById(R.id.info);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        userImage = (ProfilePictureView) findViewById(R.id.userImage);

        if (loginButton != null) {
            loginButton.setReadPermissions(Arrays.asList(
                    "public_profile", "email", "user_birthday", "user_friends"));


            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    final String userId = loginResult.getAccessToken().getUserId();

                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    try {
                                        userImage.setProfileId(userId);
                                        String name = object.getString("name");
                                        String gender = object.getString("gender");
                                        String email = object.getString("email");
                                        String birthday = object.getString("birthday");
                                        info.setText("Name: " + name
                                                + "\nGender: " + gender
                                                + "\nEmail: " + email
                                                + "\nBirthday: " + birthday);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender,birthday");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {
                    info.setText("Login attempt canceled.");
                }

                @Override
                public void onError(FacebookException e) {
                    info.setText("Login attempt failed.");
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
