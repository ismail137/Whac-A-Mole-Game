import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wacamole {
    int frameHieght = 600;
    int framewidth = 650;
    JFrame frame;
    JButton [] buttons;
    Random random;
    JLabel textLabel;
    JPanel textPanel;
    JPanel board;
    ImageIcon moleIcon;
    ImageIcon plantIcon;
    Timer montyTimer;
    Timer planTimer;
    JButton currMontty;
    JButton currPlant;
    int score = 0;


Wacamole(){

        Image montyImage= new ImageIcon(getClass().getResource("monty.png")).getImage();
        moleIcon = new ImageIcon(montyImage.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

        Image plantImage= new ImageIcon(getClass().getResource("piranha.png")).getImage();
        plantIcon = new ImageIcon(plantImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

    frame = new JFrame(); 
frame.setSize(framewidth,frameHieght);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLocationRelativeTo(null);
frame.setLayout(new BorderLayout());

textLabel= new JLabel("Your Score is :");
textLabel.setFont(new Font("Arial",Font.BOLD ,50 ) );
textLabel.setHorizontalAlignment(JLabel.CENTER);

textPanel= new JPanel();
textPanel.add(textLabel);
frame.add(textPanel,BorderLayout.NORTH);

board = new JPanel();
board.setLayout(new GridLayout(4,4));
frame.add(board);

buttons = new JButton[12];

for (int i=0 ;i<12 ; i++) {
    JButton tile = new JButton();
      buttons[i]=tile  ;
    board.add(tile);
    // tile.setIcon(plantIcon);
     tile.setFocusable(false);
     tile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            if(currMontty == e.getSource()){
                score +=5;
                textLabel.setText("Your score is :" + score);
            }
            else if(currPlant == e.getSource()){
                textLabel.setText("Game Over");
                montyTimer.stop();
                planTimer.stop();
                for ( int i= 0 ; i<12; i++){
                    buttons[i].setEnabled(false);
                }


            }
        }
     });

    

}
random= new Random();
montyTimer = new Timer(1000, new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
JButton tile = new JButton();
if(currMontty != null){
        currMontty.setIcon(null);
        
    }

    int num = random.nextInt(12);
    tile = buttons[num];
if(currPlant == tile) return;
    currMontty=tile;
    currMontty.setIcon(moleIcon);
    
    } 
});
    

    planTimer = new Timer(1300,new ActionListener(){
public void actionPerformed(ActionEvent e){
if(currPlant != null){
currPlant.setIcon(null);
}

JButton tile = new JButton();
 int num = random.nextInt(12);
tile = buttons[num];

if(currMontty == tile) return;

currPlant = tile;
currPlant.setIcon(plantIcon);

}
    });
    

planTimer.start();
montyTimer.start();
 

 frame.setVisible(true);
    
}



}
