/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;


/**
 * Clase que representa la configuración de una cuenta de correo electrónico.
 *
 * @author Angelo
 * @author Owen
 * @author Elian
 * @version 1.0
 */
public class CuentaCorreo {
  private String usuario;
  private String clave = "mibs vmcy ziem xwyg";
  private String servidor = "smtp.gmail.com";
  private String puerto = "587";
  private Properties propiedades;

  /**
   * Constructor para la clase CuentaCorreo.
   * Inicializa las propiedades de correo necesarias para la comunicación SMTP.
   *
   * @param pCorreo La dirección de correo electrónico asociada a la cuenta.
   */
  public CuentaCorreo(String pCorreo) {
    propiedades = new Properties();
    propiedades.put("mail.smtp.host", servidor);
    propiedades.put("mail.smtp.port", puerto);
    propiedades.put("mail.smtp.auth", "true");
    propiedades.put("mail.smtp.starttls.enable", "true");
    usuario = pCorreo;
  }
  
  /**
   * Abre una sesión de correo utilizando las propiedades configuradas.
   *
   * @return La sesión de correo configurada.
   */
  private Session abrirSesion() {
    Session sesion = Session.getInstance(propiedades,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(usuario, clave);
        }
      });
    return sesion;
  }
  
  
  /**
   * Envía un correo electrónico simple.
   *
   * @param pDestinatario La dirección de correo del destinatario.
   * @param pTituloCorreo El título del correo.
   * @param pCuerpo El cuerpo del mensaje.
   */
  public void enviarCorreo(String pDestinatario, String pTituloCorreo, String pCuerpo) {
    Session sesion = abrirSesion();
    
    try {
      Message message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(usuario));
      message.setRecipients(
        Message.RecipientType.TO,
        InternetAddress.parse(pDestinatario)
      );
      message.setSubject(pTituloCorreo);
      message.setText(pCuerpo);
      
      Transport.send(message);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Envía un correo electrónico con archivos adjuntos.
   *
   * @param pDestinatario La dirección de correo del pDestinatario.
   * @param pTituloCorreo El título del correo.
   * @param pCuerpo El cuerpo del mensaje.
   * @param pArchivosAdjuntos Rutas de los archivos a adjuntar.
   */
  public void enviarCorreo(String pDestinatario, String pTituloCorreo, String pCuerpo, String[] pArchivosAdjuntos) {
    Session sesion = abrirSesion();
    
    try {
      Message message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(usuario));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pDestinatario));
      message.setSubject(pTituloCorreo);
      
      BodyPart cuerpoMensaje = new MimeBodyPart();
      cuerpoMensaje.setText(pCuerpo);
      
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(cuerpoMensaje);
      
      if(pArchivosAdjuntos != null) {
        for(String archivoPath: pArchivosAdjuntos) {
          File archivo = new File(archivoPath);
          
          if(!archivo.exists()) {
            System.out.println("[Error] El archivo no existe: " + archivoPath);
            continue;
          }
          
          MimeBodyPart adjunto = new MimeBodyPart();
          DataSource fuenteArchivo = new FileDataSource(archivoPath);
          adjunto.setDataHandler(new javax.activation.DataHandler(fuenteArchivo));
          adjunto.setFileName(archivo.getName());
          adjunto.setDisposition(MimeBodyPart.ATTACHMENT);
          
          multipart.addBodyPart(adjunto);
          System.out.println("Archivo adjuntado: " + archivo.getName());
        }
      }
      
      message.setContent(multipart);
      Transport.send(message);
      System.out.println("Correo enviado exitosamente a " + pDestinatario);
      
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Envía un correo electrónico en formato HTML.
   *
   * @param pDestinatario La dirección de correo del pDestinatario.
   * @param pTituloCorreo El título del correo.
   * @param pCuerpoHtml El cuerpo del mensaje en formato HTML.
   */
  public void enviarCorreoHtml(String pDestinatario, String pTituloCorreo, String pCuerpoHtml) {
    Session sesion = abrirSesion();
    
    try {
      Message message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(usuario));
      message.setRecipients(
        Message.RecipientType.TO,
        InternetAddress.parse(pDestinatario)
      );
      message.setSubject(pTituloCorreo);
      
      // Establecer el contenido como HTML
      message.setContent(pCuerpoHtml, "text/html; charset=utf-8");
      
      Transport.send(message);
      System.out.println("Correo enviado a " + pDestinatario);
      
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
