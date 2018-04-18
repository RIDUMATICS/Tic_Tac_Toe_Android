package sunkanmi.com.ttt;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class threeByThree extends AppCompatActivity implements View.OnClickListener {

    private Button [][] buttons = new Button[3][3];
    private  boolean player1Turn = true;
    private int  player1points, player2points;
    private  String player1Symbol;
    private  String player2Symbol;
    private TextView player1PointView, player2PointView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_by_three);

        player1Symbol = getIntent().getExtras().getString("playerSymbol");
        switch (player1Symbol){
            case "X" :
                player2Symbol = "O";
                break;

            case "O" :
                player2Symbol = "X";
                break;
        }

        player1PointView = findViewById(R.id.player1View);
        player2PointView = findViewById(R.id.player2View);

        for (int i = 0 ; i < 3; i++){
            for (int j = 0 ; j < 3 ; j++){
                String buttonID = "button_"+i+""+j;
                int resID = getResources().getIdentifier(buttonID, "id" , getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }


        }

        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1PointView.setText("Player 1 : 0");
                player1points = 0;
                player2PointView.setText("Player 2 : 0");
                player2points = 0;
                clearBoard();

            }
        });


    }

    public boolean checkFullBoard(){
        int countSpace = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                countSpace = (buttons[i][j].getText().toString() == "")? countSpace +1 : countSpace+0;
            }
        }
        return  (countSpace == 0)? true: false;
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){

            ((Button) v).setText(player1Symbol);
            aiMove();

        }
        if(checkForWin() != ""){
            if(checkForWin() == player1Symbol){
                player1Wins();

            }else{
                player2Wins();
            }
        }else if(checkFullBoard()){
            draw();
        }

    }




    public String checkForWin(){
        String field [][] = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
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
        player1points++;
        Toast.makeText(this, "Player 1 wins! ", Toast.LENGTH_SHORT ).show();
        player1PointView.setText("Player 1: " + player1points);
        clearBoard();
    }

    private void player2Wins() {
        player2points++;
        Toast.makeText(this, "Player 2 wins! ", Toast.LENGTH_SHORT ).show();
        player2PointView.setText("Player 2: " + player2points);
        clearBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT ).show();
        clearBoard();
    }

    private void clearBoard() {
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3; j++ ){
                buttons[i][j].setText("");
            }
        }
    }

    public void aiMove(){
        for (int i = 0; i < 3; i++){
            if(buttons[0][i].getText().toString().equals(buttons[1][i].getText().toString()) && buttons[0][i].getText().toString().equals(player2Symbol)){
                if(buttons[2][i].getText().toString() == ""){
                    buttons[2][i].setText(player2Symbol);
                    return;
                }
            }
        }
        for (int i = 0; i < 3; i++){
            if(buttons[i][0].getText().toString().equals(buttons[i][2].getText().toString()) && buttons[i][0].getText().toString().equals(player2Symbol)){
                if(buttons[i][1].getText().toString() == ""){
                    buttons[i][1].setText(player2Symbol);
                    return;
                }
            }
        }

        for(int i = 0; i<3; i++){
            if(buttons[2][i].getText().toString().equals(buttons[1][i].getText().toString()) && buttons[2][i].getText().toString().equals(player2Symbol)){

                if(buttons[0][i].getText().toString() == ""){
                    buttons[0][i].setText(player2Symbol);
                    return;
                }
            }
        }

        // check if you can take a win diagonally


        if(buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[0][0].getText().toString().equals(player2Symbol)){

            if(buttons[2][2].getText().toString() == ""){
                buttons[2][2].setText(player2Symbol);
                return;
            }
        }

        if(buttons[2][2].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[2][2].getText().toString().equals(player2Symbol)){

            if(buttons[0][0].getText().toString() == ""){
                buttons[0][0].setText(player2Symbol);
                return;
            }
        }

        if(buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[0][0].getText().toString().equals(player2Symbol)){

            if(buttons[2][2].getText().toString() == ""){
                buttons[2][2].setText(player2Symbol);
                return;
            }
        }

        if(buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[0][2].getText().toString().equals(player2Symbol)){

            if(buttons[2][0].getText().toString() == ""){
                buttons[2][0].setText(player2Symbol);
                return;
            }
        }

        if(buttons[2][0].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[2][0].getText().toString().equals(player2Symbol)){

            if(buttons[0][2].getText().toString() == ""){
                buttons[0][2].setText(player2Symbol);
                return;
            }
        }


        // BLOCKS!!!! //

        // check if you can block a win horizontally
        for(int i = 0; i<3; i++){

            if(buttons[0][i].getText().toString().equals(buttons[1][i].getText().toString()) && buttons[0][i].getText().toString().equals(player1Symbol)){
                if(buttons[2][i].getText().toString() == ""){
                    buttons[2][i].setText(player2Symbol);
                    return;
                }

            }

        }
        for(int i = 0; i<3; i++){

            if(buttons[2][i].getText().toString().equals(buttons[0][i].getText().toString()) && buttons[2][i].getText().toString().equals(player1Symbol)){

                if(buttons[1][i].getText().toString() == ""){
                    buttons[1][i].setText(player2Symbol);
                    return;
                }

            }


        }


        for(int i = 0; i<3; i++){

            if(buttons[2][i].getText().toString().equals(buttons[1][i].getText().toString()) && buttons[2][i].getText().toString().equals(player1Symbol)){

                if(buttons[0][i].getText().toString() == ""){
                    buttons[0][i].setText(player2Symbol);
                    return;
                }
            }
        }

        // check if you can block a win horizontally
        for(int i = 0; i<3; i++){

            if(buttons[i][0].getText().toString().equals(buttons[i][1].getText().toString()) && buttons[i][0].getText().toString().equals(player1Symbol)){

                if(buttons[i][2].getText().toString() == ""){
                    buttons[i][2].setText(player2Symbol);
                    return;
                }

            }
        }

        for(int i = 0; i<3; i++){

            if(buttons[i][0].getText().toString().equals(buttons[i][2].getText().toString()) && buttons[i][0].getText().toString().equals(player1Symbol)){

                if(buttons[i][1].getText().toString() == ""){
                    buttons[i][1].setText(player2Symbol);
                    return;
                }

            }
        }

        for(int i = 0; i<3; i++){

            if(buttons[i][2].getText().toString().equals(buttons[i][1].getText().toString()) && buttons[i][2].getText().toString().equals(player1Symbol)){

                if(buttons[i][0].getText().toString()  == ""){
                    buttons[i][0].setText(player2Symbol);
                    return;
                }

            }

        }


        // check if you can block a win diagonally

        if(buttons[0][2].getText().toString().equals(buttons[2][0].getText().toString()) && buttons[0][2].getText().toString().equals(player1Symbol)){

            if(buttons[1][1].getText().toString() == ""){
                buttons[1][1].setText(player2Symbol);
                return;
            }
        }

        if(buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[0][0].getText().toString().equals(player1Symbol)){

            if(buttons[2][2].getText().toString() == ""){
                buttons[2][2].setText(player2Symbol);
                return;
            }
        }

        if(buttons[0][0].getText().toString().equals(buttons[2][2].getText().toString()) && buttons[0][0].getText().toString().equals(player1Symbol)){

            if(buttons[1][1].getText().toString() == ""){
                buttons[1][1].setText(player2Symbol);
                return;
            }
        }

        if(buttons[0][2].getText().toString().equals(buttons[2][0].getText().toString()) && buttons[0][0].getText().toString().equals(player1Symbol)){

            if(buttons[1][1].getText().toString() == ""){
                buttons[1][1].setText(player2Symbol);
                return;
            }
        }

        if(buttons[2][2].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[2][2].getText().toString().equals(player1Symbol)){

            if(buttons[0][0].getText().toString() == ""){
                buttons[0][0].setText(player2Symbol);
                return;
            }
        }

        if(buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[0][0].getText().toString().equals(player1Symbol)){
            if(buttons[2][2].getText().toString() == ""){
                buttons[2][2].setText(player2Symbol);
                return;
            }
        }

        if(buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[0][2].getText().toString().equals(player1Symbol)){

            if(buttons[2][0].getText().toString() == ""){
                buttons[2][0].setText(player2Symbol);
                return;
            }
        }

        if(buttons[2][0].getText().toString().equals(buttons[1][1].getText().toString()) && buttons[2][0].getText().toString().equals(player1Symbol)){

            if(buttons[0][2].getText().toString() == ""){
                buttons[0][2].setText(player2Symbol);
                return;
            }
        }



        // make random move if above rules don't apply
        for(int i = 0; i<3; i++){
            if(buttons[i][0].getText().toString() == ""){
                buttons[i][0].setText(player2Symbol);
                return;
            }
        }
        for(int i = 0; i<3; i++){
            if(buttons[i][1].getText().toString() == "" && buttons[i][0].getText().toString() != player1Symbol){
                buttons[i][1].setText(player2Symbol);
                return;
            }
        }
        for(int i = 0; i<3; i++){
            if(buttons[i][2].getText().toString() == "" && buttons[i][0].getText().toString() != player1Symbol){
                buttons[i][2].setText(player2Symbol);
                return;
            }
        }

    }
}
