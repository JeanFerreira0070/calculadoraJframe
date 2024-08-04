import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JTextField display;

    private String valorAtual = "";
    private String operacaoAtual = "";
    private double resultado = 0;

    public Calculator() {

        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        display = new JTextField();
        display.setEditable(true);
        add(display, BorderLayout.NORTH);


        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new GridLayout(4, 4));


        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    valorAtual += button.getText();
                    display.setText(valorAtual);
                }
            });
            panelBotoes.add(button);
        }


        JButton button0 = new JButton("0");
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!valorAtual.equals("0")) {
                    valorAtual += "0";
                    display.setText(valorAtual);
                }
            }
        });
        panelBotoes.add(button0);


        JButton buttonPonto = new JButton(".");
        buttonPonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!valorAtual.contains(".")) {
                    valorAtual += ".";
                    display.setText(valorAtual);
                }
            }
        });
        panelBotoes.add(buttonPonto);


        JButton buttonIgual = new JButton("=");
        buttonIgual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
        panelBotoes.add(buttonIgual);


        String[] operacoes = {"+", "-", "*", "/"};
        for (String operacao : operacoes) {
            JButton button = new JButton(operacao);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    operacaoAtual = operacao;
                    if (!valorAtual.isEmpty()) {
                        resultado = Double.parseDouble(valorAtual);
                        valorAtual = "";
                    }
                }
            });
            panelBotoes.add(button);
        }


        add(panelBotoes, BorderLayout.CENTER);

        setVisible(true);
    }

    private void calcular() {
        if (!valorAtual.isEmpty()) {
            double valor = Double.parseDouble(valorAtual);
            switch (operacaoAtual) {
                case "+":
                    resultado += valor;
                    break;
                case "-":
                    resultado -= valor;
                    break;
                case "*":
                    resultado *= valor;
                    break;
                case "/":
                    if (valor != 0) {
                        resultado /= valor;
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro: Divisão por zero!");
                    }
                    break;
            }
            display.setText(String.valueOf(resultado));
            valorAtual = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}
