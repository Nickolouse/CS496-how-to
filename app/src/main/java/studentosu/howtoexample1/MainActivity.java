package studentosu.howtoexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;

/**
 * The code here may is originally from an answer in stack overflow that is not my own. I am taking
 * this as a base and modifying it to create my how to. There will likely be little left of the original
 * codet hat I am basing it.
 * http://stackoverflow.com/questions/6450275/android-how-to-work-with-asynctasks-progressdialog
 * the post that I used was by: Sunil Kumar Sahoo
 */

public class MainActivity extends AppCompatActivity {

    protected TextView main_text;
    protected Button main_button;
    protected InitTask our_async_task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_text = (TextView) findViewById(R.id.main_text);
        main_text.setText("Hello world!");
        main_text.setTextColor(Color.BLACK);
        our_async_task = new InitTask();
        our_async_task.execute(this);
    }

    /**
     * sub-class of the activity that inherits from AsyncTask
     */
    protected class InitTask extends AsyncTask<Context, Integer, String> {
        /**
         * This method is called before any of the others
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * initialize variables and other actions that must happen first.
             */
        }

        /**
         * This is where the meat of your async task will take place in more complex examples
         */
        @Override
        protected String doInBackground(Context... params) {
            for(int i = 0; i < 10; i++){
                try {
                    Thread.sleep(300);
                }
                catch (Exception e) {
                    Log.i("async_tutorial", e.getMessage());
                }
                //This is a trivial calculation... and will not take a noticable amount of time
                //but it servs as a place holder to show that the work is done here.
            }
            return "I am done!";
        }


        /**
         * This is the last thing that happens after doInBackground exits
         */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            main_text.setText(result);
            main_text.setTextColor(Color.RED);
        }
    }
}