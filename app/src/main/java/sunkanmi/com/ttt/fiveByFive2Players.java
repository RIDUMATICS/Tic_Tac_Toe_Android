package sunkanmi.com.ttt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fiveByFive2Players extends AppCompatActivity implements View.OnClickListener {
    private final Button[][] buttons = new Button[5][5];
    private int player1Score = 0;
    private int player2Score = 0;
    private int count = 0;
    private boolean isPlayer1Turn = true;
    private TextView displayForPlayer1;
    private TextView displayForPlayer2;
    private String player2card;
    private String player1card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_by_five2_players);
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

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonID = "button_"+i+""+j;
                int resID = getResources().getIdentifier(buttonID, "id" , getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }



    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
            if(isPlayer1Turn) {
                ((Button) v).setText(player1card);
                isPlayer1Turn = false;
                count++;
            }else if(!isPlayer1Turn){
                ((Button) v).setText(player2card);
                count++;
                isPlayer1Turn = true;
            }
            if(checkForWin()){
                if(count % 2 != 0){
                    player1Wins();
                }else{
                    player2Wins();
                }
            }else if(count == 25){
                draw();
            }
        }




    private boolean checkForWin() {
        /// Checking Vertical
        String field [][] = new String[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && field[0][i].equals(field[3][i]) && !(field[0][i].equals("")) ) {
                return true;
            }
        }
        for (int i = 0; i < 5; i++) {
            if(field[1][i].equals(field[2][i]) && field[1][i].equals(field[3][i]) && field[1][i].equals(field[4][i]) && !(field[1][i].equals("")) ){
                return true;
            }
        }
        /// Checking Horizontal
        for (int i = 0; i < 5; i++) {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && field[i][0].equals(field[i][3]) && !(field[i][0].equals("")) ){
            return true;
            }
        }
        for (int i = 0; i < 5; i++) {
            if(field[i][1].equals(field[i][2]) && field[i][1].equals(field[i][3]) && field[i][1].equals(field[i][4]) && !(field[i][1].equals("")) ){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1])  && field[0][0].equals(field[2][2]) && field[0][0].equals(field[3][3]) && !field[0][0].equals("")){
            return true;
        }
        if(field[4][4].equals(field[1][1])  && field[4][4].equals(field[2][2]) && field[4][4].equals(field[3][3]) && !field[4][4].equals("")){
            return true;
        }
        if(field[0][4].equals(field[1][3])  && field[0][4].equals(field[2][2]) && field[0][4].equals(field[3][1]) && !field[0][4].equals("")){
            return true;
        }
        if(field[4][0].equals(field[1][3])  && field[4][0].equals(field[2][2]) && field[4][0].equals(field[3][1]) && !field[4][0].equals("")){
            return true;
        }
        if(field[0][3].equals(field[1][2])  && field[0][3].equals(field[2][1]) && field[0][3].equals(field[3][0]) && !field[0][3].equals("")){
            return true;
        }
        if(field[0][1].equals(field[1][2])  && field[0][1].equals(field[2][3]) && field[0][1].equals(field[3][4]) && !field[0][1].equals("")){
            return true;
        }
        if(field[1][0].equals(field[2][1])  && field[1][0].equals(field[3][2]) && field[1][0].equals(field[4][3]) && !field[1][0].equals("")){
            return true;
        }
        if(field[1][4].equals(field[2][3])  && field[1][4].equals(field[3][2]) && field[1][4].equals(field[4][1]) && !field[1][4].equals("")){
            return true;
        }

        return false;
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
        isPlayer1Turn = true;
        for(int i = 0; i < 5 ; i++){
            for(int j = 0; j < 5; j++ ){
                buttons[i][j].setText("");
            }
        }
    }
}
