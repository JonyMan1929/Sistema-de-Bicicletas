
package ui;

/*
 * 
 * SISTEMA DE GESTION DE BICICLETAS
 * 
 * Esta clase representa la interfaz gráfica principal (GUI) del sistema.
 * Permite la gestión de bicicletas y usuarios mediante interacciones visuales.
 * 
 * Funcionalidades principales:
 * 
 * - Registro y consulta de bicicletas
 * - Registro y consulta de usuarios
 * - Préstamo y devolución de bicicletas
 * - Visualización del historial de préstamos
 * 
 * Desarrollado por:
 * Jonathan Morales Jasso
 * Angel Reyes Cabrera
 * Erick Vargas Rangel
 * 
 * Fecha de creación: 7 de mayo de 2025
 * 
 */

//Librerias

import javax.swing.*;

//import org.w3c.dom.events.MouseEvent;
import java.awt.*;

/*
 * Clase principal que representa la ventana de la interfaz gráfica (GUI) 
 * del sistema de gestión de bicicletas.
 * 
 * Hereda de JFrame y utiliza componentes Swing para permitir:
 * - Registro y consulta de bicicletas.
 * - Registro y consulta de usuarios.
 * - Préstamo y devolución de bicicletas.
 * - Visualización del historial de préstamos.
 */
public class AppBicicletas extends JFrame {

    // Componentes de la interfaz gráfica
    public JTextField txtIdBicicleta, txtModeloBicicleta, txtEstadoBicicleta;
    public JTextField txtIdUsuario, txtNombreUsuario, txtEstadoUsuario, txtBicicletaUsuario;
    public JTextArea txtHistorial;
    public JButton btnConsultarBici, btnBicisDisponibles, btnConsultarUsr, btnHistorial;
    public JButton[] boton = new JButton[4];
    
    /**
     * Constructor principal. Inicializa la interfaz gráfica.
     */
    public AppBicicletas() {
        // Configurar ventana principal
        setTitle("Sistema de Gestión de Bicicletas Comunitarias");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(204, 204, 255));
        setLayout(null);
        setResizable(false);
        
        // Crear componentes
        crearComponentes();
    }
    
    /**
     * Crea y configura todos los componentes gráficos de la ventana.
     * Incluye etiquetas, campos de texto, botones y áreas de texto.
     */
    private void crearComponentes() {
        Font tituloFont = new Font("Arial", Font.BOLD | Font.ITALIC, 24);
        Font seccionFont = new Font("Arial", Font.BOLD, 16);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        
        // Titulo
        JLabel titulo = new JLabel("SISTEMA DE BICICLETAS COMUNITARIAS", SwingConstants.CENTER);
        titulo.setFont(tituloFont);
        titulo.setBounds(50, 20, 800, 40);
        add(titulo);
        
        // Seccion de bicicletas
        JLabel infoBici = new JLabel("INFORMACIÓN DE BICICLETAS", SwingConstants.CENTER);
        infoBici.setFont(seccionFont);
        infoBici.setBounds(300, 70, 300, 20);
        add(infoBici);
        
        // Campos de bicicleta
        JLabel lblIdBicicleta = new JLabel("ID BICICLETA");
        lblIdBicicleta.setFont(labelFont);
        lblIdBicicleta.setBounds(150, 100, 150, 25);
        add(lblIdBicicleta);
        
        txtIdBicicleta = new JTextField();
        txtIdBicicleta.setBounds(300, 100, 300, 25);
        add(txtIdBicicleta);
        
        JLabel lblModeloBicicleta = new JLabel("MODELO");
        lblModeloBicicleta.setFont(labelFont);
        lblModeloBicicleta.setBounds(150, 140, 150, 25);
        add(lblModeloBicicleta);
        
        txtModeloBicicleta = new JTextField();
        txtModeloBicicleta.setBounds(300, 140, 300, 25);
        txtModeloBicicleta.setEditable(false); 
        add(txtModeloBicicleta);
        
        JLabel lblEstadoBicicleta = new JLabel("ESTADO");
        lblEstadoBicicleta.setFont(labelFont);
        lblEstadoBicicleta.setBounds(150, 180, 150, 25);
        add(lblEstadoBicicleta);
        
        txtEstadoBicicleta = new JTextField();
        txtEstadoBicicleta.setBounds(300, 180, 300, 25);
        txtEstadoBicicleta.setEditable(false);
        add(txtEstadoBicicleta);
        
        // Botones de bicicleta
        btnConsultarBici = new JButton("CONSULTAR");
        btnConsultarBici.setBounds(620, 100, 150, 30);
        btnConsultarBici.setBackground(new Color(0, 0, 153));
        btnConsultarBici.setForeground(Color.WHITE);
        btnConsultarBici.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        add(btnConsultarBici);
        
        btnBicisDisponibles = new JButton("VER DISPONIBLES");
        btnBicisDisponibles.setBounds(620, 140, 150, 30);
        btnBicisDisponibles.setBackground(new Color(0, 100, 0));
        btnBicisDisponibles.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBicisDisponibles.setForeground(Color.WHITE);
        add(btnBicisDisponibles);
        
        // Sección de usuarios
        JLabel infoUsuario = new JLabel("INFORMACIÓN DE USUARIO", SwingConstants.CENTER);
        infoUsuario.setFont(seccionFont);
        infoUsuario.setBounds(300, 220, 300, 20);
        add(infoUsuario);
        
        // Campos de usuario
        JLabel lblIdUsuario = new JLabel("ID USUARIO");
        lblIdUsuario.setFont(labelFont);
        lblIdUsuario.setBounds(150, 250, 150, 25);
        add(lblIdUsuario);
        
        txtIdUsuario = new JTextField();
        txtIdUsuario.setBounds(300, 250, 300, 25);
        add(txtIdUsuario);
        
        JLabel lblNombreUsuario = new JLabel("NOMBRE");
        lblNombreUsuario.setFont(labelFont);
        lblNombreUsuario.setBounds(150, 290, 150, 25);
        add(lblNombreUsuario);
        
        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(300, 290, 300, 25);
        txtNombreUsuario.setEditable(false);
        add(txtNombreUsuario);
        
        JLabel lblEstadoUsuario = new JLabel("ESTADO");
        lblEstadoUsuario.setFont(labelFont);
        lblEstadoUsuario.setBounds(150, 330, 150, 25);
        add(lblEstadoUsuario);
        
        txtEstadoUsuario = new JTextField();
        txtEstadoUsuario.setBounds(300, 330, 300, 25);
        txtEstadoUsuario.setEditable(false); 
        add(txtEstadoUsuario);
        
        JLabel lblBicicletaUsuario = new JLabel("BICICLETA PRESTADA");
        lblBicicletaUsuario.setFont(labelFont);
        lblBicicletaUsuario.setBounds(150, 370, 150, 25);
        add(lblBicicletaUsuario);
        
        txtBicicletaUsuario = new JTextField();
        txtBicicletaUsuario.setBounds(300, 370, 300, 25);
        txtBicicletaUsuario.setEditable(false); 
        add(txtBicicletaUsuario);
        
        // Botones de usuario
        btnConsultarUsr = new JButton("CONSULTAR");
        btnConsultarUsr.setBounds(620, 250, 150, 30);
        btnConsultarUsr.setBackground(new Color(0, 0, 153));
        btnConsultarUsr.setForeground(Color.WHITE);
        btnConsultarUsr.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnConsultarUsr);
        
        btnHistorial = new JButton("VER HISTORIAL");
        btnHistorial.setBounds(620, 290, 150, 30);
        btnHistorial.setBackground(new Color(139, 0, 0));
        btnHistorial.setForeground(Color.WHITE);
        btnHistorial.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnHistorial);
        
        // area de texto para historial
        txtHistorial = new JTextArea();
        txtHistorial.setEditable(false);
        txtHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtHistorial);
        scrollPane.setBounds(50, 450, 800, 200);
        add(scrollPane);
        
        // Botones principales
        String[] botones = {"REGISTRAR BICICLETA", "REGISTRAR USUARIO", "PRESTAR", "DEVOLVER"};
        int[] xBotones = {50, 250, 450, 650};
        
        for (int i = 0; i < botones.length; i++) {
            boton[i] = new JButton(botones[i]);
            boton[i].setBounds(xBotones[i], 410, 180, 30);
            boton[i].setBackground(new Color(0, 0, 153));
            boton[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            boton[i].setForeground(Color.WHITE);
            
            add(boton[i]);
        }
    }
}