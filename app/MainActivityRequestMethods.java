package com.example.eventfinder;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.*;






public class MainActivity extends AppCompatActivity {
    String baseURL = "https://wmc.ms.wits.ac.za/students/s2993801/";
    Connection connect = new Connection();
    RecyclerView recyclerView;
    ArrayList<Integer> eventRatings = new ArrayList<>();



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
        //Copied this from one of the xml files, giving me an error for some reason
        recyclerView = findViewById(R.id.recycler_view0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView textView_Login = (TextView) findViewById(R.id.tv_login);
        TextView info_text = (TextView) findViewById(R.id.tv_info);
        TextView forgot_password = (TextView) findViewById(R.id.tv_forgot_pass);
        TextInputLayout name = (TextInputLayout) findViewById(R.id.NameTextField);
        TextInputLayout confirm_password = (TextInputLayout) findViewById(R.id.ConfirmPasswordTextField);
        TextInputLayout username = (TextInputLayout) findViewById(R.id.UsernameTextField);
        Button button_Login = (Button) findViewById(R.id.btn_login);
        //User authentication B
        button_Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                JSONObject object = null;
                try {
                    object = new JSONObject();
                    object.put("email", "example@email.com");
                    object.put("password", "123456");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Callback callback = new Callback() {
                    public void onFailure(Call call, IOException err) {
                        err.printStackTrace();
                    }

                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        JSONObject react = new JSONObject(responseData);
                        String status = react.getString("status");

                        if (status.equals("success")) {
                            int userID = react.getJSONObject("data").getInt("user_id");
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, Homepage.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                };
                connect.postRequest(object, baseURL + "api/register.php", callback);
            };
        };
        //Not sure whether this code should link things to this file or another file, cause there's multiple pages, so I'll link it properly when the whole skeleton is uploaded
//A. REGISTER USER

        register_button.setOnClickListener(new View.OnClickListener(){
                                               public void onClick(View register) {

                                                   JSONObject object = null;
                                                   try {
                                                       object = new JSONObject();
                                                       object.put("email", "user@example.com");
                                                       object.put("password", "plain_text_password");
                                                   } catch (JSONException e) {
                                                       e.printStackTrace();
                                                   }
                                                   Callback callback = new Callback() {
                                                       public void onFailure(Call call, IOException err) {
                                                           err.printStackTrace();
                                                       }

                                                       public void onResponse(Call call, Response response) throws IOException {
                                                           String responseData = response.body().string();
                                                           JSONObject react = new JSONObject(responseData);
                                                           String status = react.getString("status");
                                                           if (status.equals("success")) {
                                                               runOnUiThread(new Runnable() {
                                                                   public void run() {
                                                                       Intent intent = new Intent(this.mainactivity, Homepage.class);//"Homepage" is just whatever you'd use to take the user to homepage
                                                                       startActivity(intent);
                                                                   }
                                                               });
                                                           }

                                                       }
                                                   };
                                                   connect.postRequest(object, baseURL + "/api/register.php", callback);
                                               }
                                           }


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


    //2.Finding nearby events
    //A.Nearby events

 public void GetNearbyEvents(String URL,int userID,double lat,double lng,double rad){

     String url = HttpUrl.parse(URL).newBuilder()
             .addQueryParameter("user_id", String.valueOf(userID))
             .addQueryParameter("lat", String.valueOf(lat))
             .addQueryParameter("lng", String.valueOf(lng))
             .addQueryParameter("radius", String.valueOf(rad))
             .build().toString();
        Callback callEventStuff = new Callback(){
          public void onFailure(Call call, IOException err){
              err.printStackTrace();
          }
          public void onResponse(Call call,Response response)throws IOException{
              String responseData = response.body().string();
              JSONObject jsonConvrt = new JSONObject(responseData);
              JSONArray eventArray = jsonConvrt.getJSONArray("data");
//putting events in an arraylist
              ArrayList<EventCardItem> eventList = new ArrayList<>();
              for(int i=0;i<eventArray.length();++i){
                    JSONObject event = eventArray.getJSONObject(i);
                    EventCardItem item = new EventCardItem();
                    item.setTitle(event.getString("title"));
                    item.setEventId(event.getInt("event_id"));
                    item.setDesrciption(event.getString("description"));
                    item.setlatitude(event.getDouble("latitude"));
                    item.setLongitude(event.getDouble("longitude"));
                    item.setEventDate(event.getString("event_date"));
                    item.setCreatorId(event.getInt("creator_id"));
                    eventList.add(item);
              }
              runOnUiThread(new Runnable(){
                 @Override
                 public void run() {
                     MainPageAdapter adapter = new MainPageAdapter(eventList);
                     recyclerView.setAdapter(adapter);
                 }
              });
          }
      };
        connect.getRequest(url,callEventStuff);
 }
//B. CREATE EVENT
ArrayList<JSONObject> createdEvents = new ArrayList<>();
    public void CreateEvent(){
        JSONObject sendEvent = new JSONObject();
        //I can't find the fields for creating events so these are dummies,I'm not sure if that's my job
        String title = titleInputField.getText().toString();
        String description = descriptionInputField.getText().toString();
        double lat = Double.parseDouble(latitudeInputField.getText().toString());
        double lng = Double.parseDouble(longitudeInputField.getText().toString());
        String eventDate="2026-03-28 07:00:00";//Dummy until I find a way to get this from user
        //Where I'd store the Id's presumably
        ArrayList<Integer> categoryIds = new ArrayList<>();
try{
        sendEvent.put("creator_id", userId);//where do I get userId?
        sendEvent.put("title",title);
        sendEvent.put("description",description);
        sendEvent.put("latitude",lat);
        sendEvent.put("longitude",lng);
        //don't know how to get input here
        sendEvent.put("event_date",eventDate);
        sendEvent.put("category_ids",new JSONArray(categoryIds));
        createdEvents.add(sendEvent);}
catch (JSONException err) {
            err.printStackTrace();
        }
        Callback createCallback = new Callback(){
            public void onFailure(Call call,IOException err){
                err.printStackTrace();
            }
            public void onResponse(Call call,Response response)throws IOException{
                String jsonData = response.body().string();
                JSONObject check = new JSONObject(jsonData);

                if(check.getString("status").equals("success")){
               //show event
                }
            };
        };
        connect.postRequest(sendEvent,baseURL+"api/create_event.php",createCallback);
    }
    //  C. DELETE EVENT
    public void DeleteEvent() {
        //so that the callback is in the same scope as the request
        Callback deleteCallback = null;
        for (int i = 0; i < createdEvents.size(); ++i) {
            if (createdEvents.get(i).getString("title").equals(deletedEventInput)) {
                createdEvents.remove(i);
                break;
            }
        }
        deleteCallback = new Callback() {
            public void onFailure(Call call, IOException err) {
                err.printStackTrace();
            }

            public void onResponse(Call call, Response response) {
                runOnUiThread( new Runnable(){

                    Toast.makeText(getApplicationContext(), "Event deleted successfully", Toast.LENGTH_SHORT).show();
                });
            }
        };
        //until I have a way to get the userId I'll just use 1 till then
        connect.deleteRequest(baseURL + "api/delete_event.php?id=" + "1", deleteCallback);
    }
    //3. EVENT INTERACTIONS
    //A. SAVE or RSVP an event
    public void saveEvent(){
    JSONObject save = new JSONObject();
    //values hardcoded until I know how to retrieve inputs
        try{
    save.put("user_id",1);
    save.put("event_id",101);
    save.put("interaction_type","rsvp");}
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    Callback saveCallback = new Callback(){
        public void onFailure(Call call,IOException err){
            err.printStackTrace();
        }

        public void onResponse(Call call, Response response)throws IOException{
            //nothing to do really, if it's successful just move on
            }
        };
        connect.postRequest(save,baseURL+"api/interact.php",saveCallback);
    }
//B. Rate an event

    public void rateEvent(){
        JSONObject rating = new JSONObject();

        //hardcoded
        try{
        rating.put("user_id",   1);
        rating.put("event_id",101);
        rating.put("rating",5);
        eventRatings.add(5);}
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Callback rateCallback = new Callback(){
            public void onFailure(Call call,IOException err){
                err.printStackTrace();
            }
            public void onResponse(Call call, Response response)throws IOException{
//
            }
        };

        connect.postRequest(rating,baseURL+"api/rate_event.php",rateCallback);
    }

//C. Get User Profile
    public void getUserProfile(){
   Callback getUserCallback = new Callback(){
       public void onFailure(Call call,IOException err){
           err.printStackTrace();
       }
       public void onResponse(Call call,Response response)throws IOException{
           String responseData = response.body().string();
           try {
               JSONObject object = new JSONObject(responseData);
           } catch (JSONException e) {
               throw new RuntimeException(e);
           }
           Double sum = 0.0;
           Double mean;

           for(int i=0;i<eventRatings.size();++i){
               sum=sum+eventRatings.get(i);
           }
           mean=sum/eventRatings.size();
            //whatever mechanism that's used to change the star rating this is the logic, input mean into that textview
       }
        };
   connect.getRequest(baseURL+"api/get_user_score.php?target_user_id=5",getUserCallback);

    }

    //D UNSAVE/CANCEL RSVP
    public void deleteEvent(){
        //however the user picks the event, just get the event Id and just tell the backend to delete the event
        Callback cancelCallback = new Callback(){
            public void onFailure(Call call,IOException err){
                err.printStackTrace();
            }
            public void onResponse(Call call,Response response)throws IOException{

        }

    };
connect.deleteRequest(baseURL+"api/remove_interaction.php?user_id=1&event_id=101",cancelCallback);
    }
    //E. GET MY EVENTS
    public void getMyEvents(){

           Callback getEventsCallback = new Callback(){
             public void onFailure(Call call, IOException err){
                 err.printStackTrace();
             }
             public void onResponse(Call call,Response response)throws IOException{
                 String responseData = response.body().string();
                 JSONObject convert = new JSONObject(responseData);
                 JSONArray eventStore = new JSONArray();
                 eventStore = convert.getJSONArray("data");
                 ArrayList<EventCardItem> events = new ArrayList<>();
                 for(int i=0;i<eventStore.length();++i){
                     JSONObject event = eventStore.getJSONObject(i);
                     EventCardItem item = new EventCardItem();
                     item.setEventId(event.getInt("event_id"));
                     item.setTitle(event.getString("title"));
                     item.setDescription(event.getString("description"));
                     item.setLatitude(event.getDouble("latitude"));
                     item.setLongitude(event.getDouble("longitude"));
                     item.setEventDate(event.getString("event_date"));
                     events.add(item);
                 }
             }
           };
        connect.getRequest(baseURL+"api/get_my_events.php?user_id=1",getEventsCallback);
    }
    //GET SAVED/RSVP'D EVENTS
    public void getSaved(){
        Callback getSavedCallback = new Callback(){
            //userId hardcoded for now
            public void onFailure(Call call, IOException err){
                err.printStackTrace();
            }
            public void onResponse(Call call,Response response)throws IOException{
                //Basically putting the returned events into an list that I assume will be outputed on one of the pages
                String responseData = response.body().string();
                JSONObject convert = new JSONObject(responseData);

                JSONArray eventStore = null;
                try {
                    eventStore = convert.getJSONArray("data");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                ArrayList<EventCardItem> events = new ArrayList<>();
                for(int i=0;i<eventStore.length();++i){
                    JSONObject event = eventStore.getJSONObject(i);
                    EventCardItem item = new EventCardItem();
                    item.setEventId(event.getInt("event_id"));
                    item.setTitle(event.getString("title"));
                    item.setDescription(event.getString("description"));
                    item.setLatitude(event.getDouble("latitude"));
                    item.setLongitude(event.getDouble("longitude"));
                    item.setEventDate(event.getString("event_date"));
                    item.setInteractionType(event.getString("interaction_type"));
                    events.add(item);
                }
            }
        };
        connect.getRequest(baseURL+"api/get_saved_events.php?user_id=1",getSavedCallback);
    }
    //4.Profile and data management
    //A.Update user preferences
    public void userPreference(){
        //Whatever category id's that the user inputs should be put into this ArrayList
        ArrayList<Integer> categoryIDs = new ArrayList<>();
        JSONArray parseIDs = new JSONArray();
        //however you get category IDs, run it through the loop, put in JSONArray then parse through JSONObject
        Integer category_ids;
        for(int i=0;i<categoryIDs.size();++i){
            parseIDs.put(category_ids);
        }
        JSONObject IDs = new JSONObject();
        //dummy value
        try{
        IDs.put("user_id",1);
        IDs.put("category_ids",parseIDs);}
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Callback userPrefCallback = new Callback(){
            public void onFailure(Call call,IOException err){
                err.printStackTrace();
            }
            public void onResponse(Call call,Response response)throws IOException{
                runOnUiThread(new Runnable(){

                    Toast.makeText(getApplicationContext(), "User preferences updated successfully", Toast.LENGTH_SHORT).show();
                });

            }
        };
        connect.postRequest(IDs,baseURL+"api/update_preferences.php",userPrefCallback);
    }
    //B. Get User preferences
    public void getUserPreferences(){
        Callback userPred = new Callback(){
            public void onFailure(Call call,IOException err){
                err.printStackTrace();
            }
            public void onResponse(Call call,Response response)throws IOException{
                //not sure how to store this, implement when page is ready
            }
        };
        connect.getRequest(baseURL+"api/get_preferences.php?user_id=1",userPred);
    }

    //C. Get all Categories
    public void allCategories(){
        Callback allCatCallback = new Callback(){
            public void onFailure(Call call,IOException err){
                err.printStackTrace();
            }
            public void onResponse(Call call,Response response)throws IOException{
//don't have the page rn so this comes after I know how it works
        }
        };
        connect.getRequest(baseURL+"api/get_categories.php",allCatCallback);
    }
    //D. Delete account
    //bit unsure about this cause the API contract never gets me to specify which userid this affects
    public void deleteAccount(){
        Callback deleteCallback = new Callback(){
            public void onFailure(Call call,IOException err){
                err.printStackTrace();
            }
            public void onResponse(Call call, Response response)throws IOException{
                runOnUiThread(new Runnable(){

                    Toast.makeText(getApplicationContext(), "Account and all associated data permanently deleted.", Toast.LENGTH_SHORT).show();
                });
            }
        };
        connect.deleteRequest(baseURL+"api/delete_account.php",deleteCallback);
    }
}

