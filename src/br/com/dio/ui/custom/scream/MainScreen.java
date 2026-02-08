package br.com.dio.ui.custom.scream;

import br.com.dio.service.BoardService;
import br.com.dio.service.FinishGameButton;
import br.com.dio.service.ResetButton;
import br.com.dio.ui.custom.frame.MainFrame;
import br.com.dio.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600,600);

    private final BoardService boardService;

    private JButton finishGameButton;
    private JButton checkGameStatusButton;
    private JButton resetButton;



    public MainScreen(final Map<String , String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension,mainPanel);
        addResertButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addFinishGameButton(JPanel mainPanel) {
                 finishGameButton = new FinishGameButton(e ->{
                    if(boardService.gameIsFinished()){
                        JOptionPane.showMessageDialog(null,"Parabens voce concluiu o jogo");
                        resetButton.setEnabled(false);
                        finishGameButton.setEnabled(false);
                    }else {
                        var message ="Seu jogo tem alguma inconsistencia , ajuste e tente novamente.";
                        JOptionPane.showMessageDialog(null,message);
                    }
                });
    }

    private void addCheckGameStatusButton(JPanel mainPanel) {
         checkGameStatusButton = new FinishGameButton(e -> {
            var hasError= boardService.hasError();
            var gamesStatus = boardService.getStatus();
            var message = switch (gamesStatus){
                case NON_STARTED -> "O jogo não foi iniciado.";
                case INCOMPLETE -> "O jogo esta incompleto.";
                case COMPLETE -> "O jogo finalizado com sucesso.";

            };
            message += hasError ? " e contém erros" : "e não contém erros";
            JOptionPane.showMessageDialog(null,message);
        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addResertButton(JPanel mainPanel) {
        resetButton = new ResetButton(e -> {
            var dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo ",
                    "Limpar o jogo",
                               YES_NO_OPTION,
                                QUESTION_MESSAGE
            );
            if(dialogResult == 0){
                boardService.reset();
            }

        });
        mainPanel.add(resetButton);
    }
}
