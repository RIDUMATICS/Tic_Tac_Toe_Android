package sunkanmi.com.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionMenu extends AppCompatActivity {
    private RadioGroup radioStyleGroup, radioSymbolGroup;
    private RadioButton radioStyleButton, radioSymbolButton;
    private Button btnStartGame;
    private boolean isTwoPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
        isTwoPlayers = getIntent().getExtras().getBoolean("twoPlayers");
        if(!isTwoPlayers) {
            RadioButton fiveByFive = findViewById(R.id.fiveByFiveRdBtn);
            fiveByFive.setVisibility(View.INVISIBLE);
        }

        addListenerOnTicStyle();
    }

    public void addListenerOnTicStyle() {
        radioStyleGroup = findViewById(R.id.radioStyleGroup);
        radioSymbolGroup = findViewById(R.id.radioSymbolGroup);
        btnStartGame = findViewById(R.id.btnStartGame);

        btnStartGame.setOnClickListener(
                new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                // get selected radio button from radioGroup
                                                int selectedId = radioStyleGroup.getCheckedRadioButtonId();
                                                int selectedId2 = radioSymbolGroup.getCheckedRadioButtonId();
                                                // find the radiobutton by returned id

                                                radioSymbolButton = findViewById(selectedId2);

                                                if( selectedId ==  R.id.threeByThreeRdBtn){
                                                    if(isTwoPlayers){
                                                        Intent nextPage = new Intent(OptionMenu.this, threeByThree2Players.class);
                                                        nextPage.putExtra("playerSymbol", radioSymbolButton.getText().toString() );
                                                        startActivity(nextPage);
                                                    }else {
                                                        Intent nextPage = new Intent(OptionMenu.this, threeByThree.class);
                                                        nextPage.putExtra("playerSymbol", radioSymbolButton.getText().toString());
                                                        startActivity(nextPage);
                                                    }
                                                }else if(selectedId == R.id.fiveByFiveRdBtn){
                                                    if(isTwoPlayers){
                                                        Intent nextPage = new Intent(OptionMenu.this, fiveByFive2Players.class);
                                                        nextPage.putExtra("playerSymbol", radioSymbolButton.getText().toString() );
                                                        startActivity(nextPage);
                                                    }else{
                                                        return;
                                                    }
                                                }


                                            }
                                        }

        );
    }
}
