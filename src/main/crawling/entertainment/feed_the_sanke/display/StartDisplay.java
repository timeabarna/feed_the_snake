package crawling.entertainment.feed_the_sanke.display;

import crawling.entertainment.feed_the_sanke.Game;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartDisplay implements ActionListener {

    private JFrame frame;
    private String title;
    private int width;
    private int height;
    private Dimension screenSize;

    private TextField userNameLabel;
    private TextField userNameInput;
    private TextField widthLabel;
    private TextField widthInput;
    private TextField heightLabel;
    private TextField heightInput;
    private Button startGameButton;
    private TextField validationFailedLabel;
    private boolean visible = true;

    public StartDisplay(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        if (screenSize == null) {
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        }

        createDisplay();
    }

    private void createDisplay() {
        initializeJFrame();
    }

    private void initializeJFrame() {
        initializeLabels();
        initializeInputs();
        initializeButtons();
        setVisibilityOnElements();

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(userNameLabel);
        frame.add(userNameInput);
        frame.add(widthLabel);
        frame.add(widthInput);
        frame.add(heightLabel);
        frame.add(heightInput);
        frame.add(validationFailedLabel);
        frame.add(startGameButton);

    }

    private void initializeLabels() {
        userNameLabel = new TextField();
        userNameLabel.setBounds(50,50,200,20);
        userNameLabel.setText("Please enter your 3 letter name: ");
        userNameLabel.setEditable(false);

        widthLabel =new TextField();
        widthLabel.setBounds(50,100,300,20);
        widthLabel.setText("Please enter screen width between 640 and " + screenSize.width);
        widthLabel.setEditable(false);

        heightLabel =new TextField();
        heightLabel.setBounds(50,150,300,20);
        heightLabel.setText("Please enter screen width between 480 and " + screenSize.height);
        heightLabel.setEditable(false);

        validationFailedLabel = new TextField();
        validationFailedLabel.setBounds(50,50,230,20);
        validationFailedLabel.setText("Invalid input, please close the game... ");
        validationFailedLabel.setEditable(false);
    }

    private void initializeInputs() {
        userNameInput = new TextField();
        userNameInput.setBounds(250,50,50,20);

        widthInput =new TextField();
        widthInput.setBounds(350,100,50,20);

        heightInput =new TextField();
        heightInput.setBounds(350,150,50,20);
    }

    private void initializeButtons() {
        startGameButton =new Button("Start Game");
        startGameButton.setBounds(50,200,100,50);
        startGameButton.addActionListener(this);
    }

    private void setVisibilityOnElements() {
        userNameLabel.setVisible(visible);
        widthLabel.setVisible(visible);
        heightLabel.setVisible(visible);
        validationFailedLabel.setVisible(!visible);

        userNameInput.setVisible(visible);
        widthInput.setVisible(visible);
        heightInput.setVisible(visible);

        startGameButton.setVisible(visible);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startGameButton){
            String userName = userNameInput.getText();
            int widthFromInput = convertStringToInteger(widthInput.getText());
            int heightFromInput = convertStringToInteger(heightInput.getText());

            visible = validateUserName(userName) &&
                    isBetweenGivenValue(widthFromInput, 640, screenSize.width) &&
                    isBetweenGivenValue(heightFromInput, 480, screenSize.height);
            if (!visible) {
                setVisibilityOnElements();
                frame.repaint();
            } else {
                Game game = new Game("Feed the snake", widthFromInput, heightFromInput, userName);
                game.start();
                frame.dispose();
            }
        }
    }

    private boolean validateUserName(String username) {
        return username.length() == 3;
    }

    private int convertStringToInteger(String number) {
        try {
            return Integer.valueOf(number).intValue();
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isBetweenGivenValue(int numberInput, int smallerNumberToCompare, int greaterNumberToCompare) {
        return numberInput >= smallerNumberToCompare && numberInput <= greaterNumberToCompare;
    }
}
