package lab7.user1;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MyService extends IntentService {

    public MyService() {
        super("ok");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this, "Service Started", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            synchronized (this) {
                Firebase fb = new Firebase("https://kevin-110.firebaseio.com/second");
                String[] words = {"hello", "from", "user", "1"};
                for (String s : words) {
                    fb.setValue(s);
                    try {
                        wait(3000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(MyService.this, "Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
