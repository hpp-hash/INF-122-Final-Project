package network;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Network {//transfers the game information to the host web server
    private static String serverURL = "https://a5games.xyz/";//server url
    public Network(){}
    public static String postQuery(String game, String user)
    {
        URL url = null;
        try {
            url = new URL(serverURL + "login.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String postData = "type=" + game + "&username=" + user;
        byte[] postDataBytes = new byte[0];
        try {
            postDataBytes = postData.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Connect, easy
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Tell server that this is POST and in which format is the data
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        try {
            conn.getOutputStream().write(postDataBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // This gets the output from your server
        Reader in = null;
        String output = "";
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            for (int c; (c = in.read()) >= 0;)
            {
                output += (char)c;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
    public static void checkResult(String game, int user, int score)
    {
        URL url = null; // URL to your application
        try {
            url = new URL(serverURL + "action.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String postData = "action=update_score&type=" + game + "&user=" + user + "&score=" + score;
        byte[] postDataBytes = new byte[0];
        try {
            postDataBytes = postData.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Connect, easy
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Tell server that this is POST and in which format is the data
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        try {
            conn.getOutputStream().write(postDataBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // This gets the output from your server
        Reader in = null;
        String output = "";
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            for (int c; (c = in.read()) >= 0;)
            {
                output += (char)c;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        openBrowser(game);
    }
    public static void openBrowser(String game)
    {
        String url = serverURL + "index.php?type=" + game;
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        try{

            if (os.indexOf( "win" ) >= 0) {
                rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.indexOf( "mac" ) >= 0) {
                rt.exec( "open " + url);
            } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {
                String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror", "netscape","opera","links","lynx"};
                StringBuffer cmd = new StringBuffer();
                for (int i=0; i<browsers.length; i++)
                    cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
                rt.exec(new String[] { "sh", "-c", cmd.toString() });
            } else {
                return;
            }
        }catch (Exception e){
            return;
        }
    }
}
