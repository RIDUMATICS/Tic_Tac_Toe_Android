package sunkanmi.com.ttt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class threeByThree2Players extends AppCompatActivity implements View.OnClickListener {
    private final Button[][] button = new Button[3][3];
    private int player1Score = 0;
    private int player2Score = 0;
    private int count = 0;
    private boolean isPlayer1Turn = true;
    private String player1card;
    private String player2card;
    private TextView displayForPlayer1;
    private TextView displayForPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_by_three2_players);
        player1card = getIntent().getExtras().getString("playerSymbol");
        switch (player1card) {
            case "X":
                player2card = "O";
                break;
            case "O":
                player2card = "X";
                break;
        }

        displayForPlayer1 = findViewById(R.id.player1View);
        displayForPlayer2 = findViewById(R.id.player2View);
        Button btnReset = findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayForPlayer1.setText("Player 1 : 0");
                player1Score = 0;
                displayForPlayer2.setText("Player 2 : 0");
                player2Score = 0;
                clearBoard();

            }
        });


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_"+i+""+j;
                int resID = getResources().getIdentifier(buttonID, "id" , getPackageName());
                button[i][j] = findViewById(resID);
                button[i][j].setOnClickListener(this);
            }
        }






    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }else{
            if(isPlayer1Turn) {
                ((Button) v).setText(player1card);
                isPlayer1Turn = false;
                count++;
            }else if(!isPlayer1Turn){
                ((Button) v).setText(player2card);
                count++;
                isPlayer1Turn = true;
            }
        }

        if(checkForWin() != ""){
            if(checkForWin() == player1card){
                player1Wins();

            }else{
                player2Wins();
            }
        }else if(count == 9){
            draw();
        }
    }

    public String checkForWin(){
        String field [][] = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = button[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])  && !field[i][0].equals("")){
                return field[i][0];
            }
        }
        for (int i = 0; i < 3; i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])  && !field[0][i].equals("")){
                return field[0][i];
            }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])  && !field[0][0].equals("")){
            return field[0][0];
        }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0])  && !field[0][2].equals("")){
            return field[0][2];
        }

        return "";
    }


    private void player1Wins() {
        player1Score++;
        Toast.makeText(this, "Player 1 wins! ", Toast.LENGTH_SHORT ).show();
        displayForPlayer1.setText("Player 1: " + player1Score);
        clearBoard();
    }

    private void player2Wins() {
        player2Score++;
        Toast.makeText(this, "Player 2 wins! ", Toast.LENGTH_SHORT ).show();
        displayForPlayer2.setText("Player 2: " + player2Score);
        clearBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT ).show();
        clearBoard();
    }

    private void clearBoard() {
        count = 0;
        isPlayer1Turn=true;
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3; j++ ){
                button[i][j].setText("");
            }
        }
    }
}
