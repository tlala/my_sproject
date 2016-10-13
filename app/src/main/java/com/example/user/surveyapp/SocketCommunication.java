package com.example.user.surveyapp;

import android.widget.TextView;

import com.facebook.AccessToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.net.ssl.SSLSocket;

/**
 * Created by User on 22/08/2016.
 */
public class SocketCommunication {

    public static Socket client;
    public static PrintWriter printWriter;
    public static SSLSocket ss;

    public static String serverAddress = "10.0.0.9";
    public static Integer serverPort = 5000;

    public static String finishMessageCode = "</data>";

    public static void sendNewSurvey(final AccessToken accessToken, final List<String> survey){
        final Boolean isSentSuccessfuly = null;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String token = accessToken.getToken();
                    String ques = "<Question>" + survey.get(0) + "</Question>";
                    String ans = "";
                    for(int i = 1;i < survey.size(); i++){
                        ans += "<AnswerNUM>xxx</AnswerNUM>".replace("xxx",survey.get(i)).replace("NUM",Integer.toString(i));
                    }
                    String surveyXML = ques + ans;
                    //debugLoger.log(ques+ans);
                    String message = "<data> " +
                            "<RequestType Type = \"createNewSurvey\" >" +
                            "<AccessToken>" + token + "</AccessToken>" +
                            surveyXML+
                            "</RequestType>" +
                            "</data>";
                    debugLoger.log(message);
                    client = new Socket(serverAddress, serverPort);
                    printWriter = new PrintWriter(client.getOutputStream());
                    printWriter.write(message);
                    printWriter.flush();

                    String response = getAllMessage();
                    debugLoger.log(response);

                    printWriter.close();
                    client.close();
                }catch (Exception e){
                    debugLoger.log(e.getMessage());
                }
            }
        });
        t.start();
    }

    public static XMLReader register(final AccessToken accessToken, final String username){
        // the function connects to the server, sends him the chosen username, and get a message from the server
        // that determins whether the username is valid or not
        final XMLReader[] xmlReader = new XMLReader[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String token = accessToken.getToken();
                    String message = "<data> " +
                            "<RequestType Type = \"register\" >" +
                                  "<AccessToken>" + token + "</AccessToken>" +
                                  "<Username>" + username + "</Username>" +
                            "</RequestType>" +
                            "</data>";

                    client = new Socket(serverAddress, serverPort);
                    printWriter = new PrintWriter(client.getOutputStream());
                    printWriter.write(message);
                    printWriter.flush();

                    String response = getAllMessage();
                    xmlReader[0] = new XMLReader(response);

                    printWriter.close();
                    client.close();


                }catch(Exception e){
                    debugLoger.log(e.getMessage());
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            debugLoger.log(e.getMessage());
        }
        return xmlReader[0];
    }

    public static Boolean signIn(final AccessToken accessToken){
        // the function connect to the server and get a boolean that determine whether the user is new or not
        // the function return the boolean - return true if the user is new
        final Boolean[] isNewUser = {null};
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    try {
                        String token = accessToken.getToken();
                        String message = "<data> " +
                                "<RequestType Type = \"signIn\" >" +
                                "<AccessToken>" + token + "</AccessToken>" +
                                "</RequestType>" +
                                "</data>";
                        client = new Socket();
                        client.connect(new InetSocketAddress(serverAddress, serverPort));

                        printWriter = new PrintWriter(client.getOutputStream());
                        printWriter.write(message);
                        printWriter.flush();
                        /**
                         //printWriter.write("second message");
                         // printWriter.flush();
                         */
                        String response = getAllMessage();
                        debugLoger.log(response);
                        XMLReader xmlReader = new XMLReader(response);
                        isNewUser[0] = Boolean.parseBoolean(xmlReader.getElementContentByName("IsNewUser"));
                        printWriter.close();
                        client.close();
                        debugLoger.log("hh");
                    } catch (Exception e) {
                        debugLoger.log("couldn connect the server");
                    }
                }
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            debugLoger.log(e.getMessage());
        }
        return isNewUser[0];

    }



    public static void sendAndReciveMessage(final String message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Socket(serverAddress,serverPort);
                    printWriter = new PrintWriter(client.getOutputStream());
                    printWriter.write(message);
                    printWriter.flush();
                    /**
                    //printWriter.write("second message");
                   // printWriter.flush();
                    */
                    String response = getAllMessage();
                    debugLoger.log(response + ","+response.length());
                    printWriter.close();
                    client.close();

                } catch (IOException e) {
                    debugLoger.log("couldn connect the server");
                }
            }
        }).start();
    }

    public static String getAllMessage(){
        String response = getMessage();
        while(!response.endsWith(finishMessageCode)){
            response += getMessage();
        }
        return response;

    }

    private static String getMessage(){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
            byte[] buffer = new byte[2048];
            int bytesRead;
            String response = "";

            InputStream inputStream = client.getInputStream();
            bytesRead = inputStream.read(buffer);
            byteArrayOutputStream.write(buffer, 0, bytesRead);
            response = byteArrayOutputStream.toString("UTF-8");



            return response;

        }catch (Exception e){
            return "error accured";
        }

    }


    // try to fix later

    private static void connectToServer(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Socket("10.0.0.9",5001);
                } catch (IOException e) {
                    debugLoger.log(e.toString());
                }
            }
        }).start();

    }

    private static void sendMessage(final String message){
        try {
            debugLoger.log("00");
            printWriter = new PrintWriter(client.getOutputStream());
            debugLoger.log("11");
            printWriter.write(message);
            debugLoger.log("22");
            printWriter.println();
            printWriter.flush();
            debugLoger.log("33");
        } catch (Exception e) {
            debugLoger.log(e.getMessage());
           sendMessage(message);
        }

    }

    private static void closeConnection(){

        try {
            printWriter.close();
            client.close();
        } catch (IOException e) {
            debugLoger.log(e.toString());
        }
    }

}
