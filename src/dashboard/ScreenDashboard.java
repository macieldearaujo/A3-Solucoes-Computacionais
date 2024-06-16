package dashboard;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

public class ScreenDashboard extends JFrame {
    private static final String DASHBOARD_URL = "https://app.fabric.microsoft.com/view?r=eyJrIjoiYjkwNDliYzgtNjZjZS00M2I2LTk4NGEtYjg2ODNiOTEwZGFiIiwidCI6IjdlOGFlZjlhLTkyOGMtNGIwZC05ODc0LTE4MDJlZjhkNzE3ZCIsImMiOjZ9 "; // Substitua com a URL do seu dashboard

    public ScreenDashboard() {
        // Configurações da janela principal
        setTitle("Dashboard BI Integration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Adiciona o painel JavaFX
        JFXPanel jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);

        // Inicializa a plataforma JavaFX
        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load(DASHBOARD_URL);

            Scene scene = new Scene(webView);
            jfxPanel.setScene(scene);
        });
    }
    
    public static void OpenScreen() {
        // Configure o caminho para os módulos do JavaFX
        System.setProperty("javafx.platform", "Swing");
        System.setProperty("prism.order", "sw");
        
        SwingUtilities.invokeLater(() -> {
            ScreenDashboard frame = new ScreenDashboard();
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        OpenScreen();
    }
}
