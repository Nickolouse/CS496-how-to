package studentosu.howtoexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        main_text = (TextView) findViewById(R.id.percent_field);
        main_button = (Button) findViewById(R.id.cancel_button);
        main_button.setOnClickListener(new ButtonListener());
        our_async_task = new InitTask();
        our_async_task.execute(this);

    }

    protected class ButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            our_async_task.cancel(true);
        }
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
        }

        /**
         * This is where the meat of your async task will take place in more complex examples
         */
        @Override
        protected String doInBackground(Context... params) {
            int i = 0;
            while (i <= 50) {
                try {
                    Thread.sleep(50);
                    publishProgress(i);
                    i++;
                }
                catch (Exception e) {
                }
            }
            return "COMPLETE!";
        }

        /**
         * This method can be called from doInBackground to do updates to the UI
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            main_text.setText((values[0] * 2) + "%");
            main_text.setTextSize(values[0]);
        }

        /**
         * This method should be used if it makes sense to allow the user to cancel the action.
         */
        @Override
        protected void onCancelled() {
            super.onCancelled();
            main_text.setText("Cancelled!");
            main_text.setTextColor(0xFFFF0000);
        }

        /**
         * This is the last thing that happens after doInBackground exits
         */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            main_text.setText(result);
            main_text.setTextColor(0xFF69adea);
            main_button.setVisibility(View.INVISIBLE);
        }
    }
}