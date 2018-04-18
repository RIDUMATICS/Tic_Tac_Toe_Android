package sunkanmi.com.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
     private Button onePlayerBtn, twoPlayerBtn;

    public void init() {
        onePlayerBtn =  findViewById(R.id.onePlayerBtn);
        onePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, OptionMenu.class);
                nextPage.putExtra("twoPlayers", false );
                startActivity(nextPage);
            }
        });

        twoPlayerBtn =  findViewById(R.id.twoPlayerBtn);
        twoPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, OptionMenu.class);
                nextPage.putExtra("twoPlayers", true );
                startActivity(nextPage);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
